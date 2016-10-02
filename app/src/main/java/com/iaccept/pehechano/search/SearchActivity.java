package com.iaccept.pehechano.search;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.iaccept.pehechano.R;
import com.iaccept.pehechano.common.Constants;
import com.iaccept.pehechano.common.NotificationUtils;
import com.iaccept.pehechano.common.network.VolleySingleton;
import com.iaccept.pehechano.models.SearchEmployee;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private EditText editTextSearchNo;
    private FloatingActionButton buttonSearch;
    public static final String RESPONSE_CODE = "responseCode";
    public static final String INTENT_PARAM_SEARCH = "employee";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        editTextSearchNo = (EditText) findViewById(R.id.editTextSearchPhone);
        editTextSearchNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 10) {
                    buttonSearch.setEnabled(true);
                    buttonSearch.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(SearchActivity.this, R.color.colorPrimary)));
                    hideKeyboard(buttonSearch);
                } else {
                    buttonSearch.setEnabled(false);
                    buttonSearch.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(SearchActivity.this, R.color.lightGrey)));
                }
            }
        });
        buttonSearch = (FloatingActionButton) findViewById(R.id.buttonSearchPhone);
        buttonSearch.setEnabled(false);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchEmployeeRequest();
            }
        });
    }

    private void searchEmployeeRequest() {

        NotificationUtils.showProgressDialog(this, "Loading !!!");
        RequestQueue requestQueue = VolleySingleton.getInstance().getRequestQueue();
        StringRequest request = new StringRequest(Request.Method.GET, Constants.SEARCH_URL.concat(editTextSearchNo.getText().toString()), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    if (jsonResponse.getInt(RESPONSE_CODE) == 302) {
                        SearchEmployee employee = new SearchEmployee();
                        employee.setEmploeeName(jsonResponse.getString(SearchEmployee.SEARCH_NAME));
                        employee.setMobileNumber(jsonResponse.getString(SearchEmployee.SEARCH_MOBILE));
                        employee.setPehechanoId(jsonResponse.getString(SearchEmployee.SEARCH_ID));
                        employee.setProfilePic(jsonResponse.getString(SearchEmployee.SEARCH_PIC));

                        Intent intent = new Intent(SearchActivity.this, DisplaySearchActivity.class);
                        ArrayList<SearchEmployee> employees = new ArrayList<>();
                        employees.add(employee);
                        intent.putParcelableArrayListExtra(INTENT_PARAM_SEARCH, employees);

                        NotificationUtils.dismissProgressDialog();

                        startActivity(intent);
                    } else if (jsonResponse.getInt(RESPONSE_CODE) == 404) {
                        NotificationUtils.showToast(SearchActivity.this, "Mobile Number Not Registered !!!");
                        NotificationUtils.dismissProgressDialog();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    NotificationUtils.showToast(SearchActivity.this, "Network Error");
                    NotificationUtils.dismissProgressDialog();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NotificationUtils.dismissProgressDialog();
            }
        });

        requestQueue.add(request);

    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}