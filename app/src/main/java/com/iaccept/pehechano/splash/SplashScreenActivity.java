package com.iaccept.pehechano.splash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.iaccept.pehechano.R;
import com.iaccept.pehechano.common.Constants;
import com.iaccept.pehechano.home.HomeActivity;
import com.iaccept.pehechano.login.LoginActivity;
import com.iaccept.pehechano.search.SearchActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences preferences = getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE);
                if (preferences.getString(Constants.PREFERENCES_USERNAME, null) == null) {
                    startActivity(new Intent(SplashScreenActivity.this, SearchActivity.class));
                } else {
                    startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class));
                }
                finish();
            }
        }, Constants.SPLASH_TIME_OUT);
    }
}