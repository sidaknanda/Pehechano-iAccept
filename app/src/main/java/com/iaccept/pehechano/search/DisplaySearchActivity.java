package com.iaccept.pehechano.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.iaccept.pehechano.R;
import com.iaccept.pehechano.common.network.MethodUtils;
import com.iaccept.pehechano.models.SearchEmployee;

public class DisplaySearchActivity extends AppCompatActivity {

    private TextView etId, etName, etPhone;
    private ImageView imageProfilePic;
    private SearchEmployee employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_search);

        initView();
        getEmployee();
        setDetails();

    }

    private void setDetails() {
        etId.setText(employee.getPehechanoId());
        etName.setText(employee.getEmploeeName());
        etPhone.setText(employee.getMobileNumber());
        imageProfilePic.setImageBitmap(MethodUtils.getBitmapFromBytes(employee.getProfilePic()));
    }

    private void getEmployee() {
        employee = (SearchEmployee) getIntent().getParcelableArrayListExtra(SearchActivity.INTENT_PARAM_SEARCH).get(0);
        employee.getEmploeeName();
    }

    private void initView() {
        etId = (TextView) findViewById(R.id.textViewSearchId);
        etName = (TextView) findViewById(R.id.textViewSearchName);
        etPhone = (TextView) findViewById(R.id.textViewSearchPhone);
        imageProfilePic = (ImageView) findViewById(R.id.imageViewSearchPicture);
    }
}