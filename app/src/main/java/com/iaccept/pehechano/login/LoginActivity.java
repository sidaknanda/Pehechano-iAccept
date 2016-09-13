package com.iaccept.pehechano.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.iaccept.pehechano.R;
import com.iaccept.pehechano.common.Constants;
import com.iaccept.pehechano.home.HomeActivity;

public class LoginActivity extends AppCompatActivity {

    private FloatingActionButton loginButton;
    private TextInputEditText emailIdEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setupUI();
    }

    private void setupUI() {
        loginButton = (FloatingActionButton) findViewById(R.id.fab_login);
        emailIdEditText = (TextInputEditText) findViewById(R.id.editTextEmailId);
        passwordEditText = (TextInputEditText) findViewById(R.id.editTextpassword);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredEmail = String.valueOf(emailIdEditText.getText()).trim();
                String enteredPassword = String.valueOf(passwordEditText.getText()).trim();

                if (enteredEmail.equals(getString(R.string.dummy_login)) && enteredPassword.equals(getString(R.string.dummy_password))) {

                    saveLoginCreds(enteredEmail, enteredPassword);
                    Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    passwordEditText.setText("");
                }
            }
        });
    }

    private void saveLoginCreds(String username, String password) {
        SharedPreferences.Editor editor = getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE).edit();
        editor.putString(Constants.PREFERENCES_USERNAME, username);
        editor.putString(Constants.PREFERENCES_PASSWORD, password);
        editor.apply();
    }
}