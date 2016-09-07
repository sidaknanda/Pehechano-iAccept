package com.iaccept.pehechano.splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.iaccept.pehechano.common.Constants;
import com.iaccept.pehechano.home.HomeActivity;
import com.iaccept.pehechano.login.LoginActivity;
import com.iaccept.pehechano.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                // TODO check sharedpreferences and launch either login or home
                startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class));
                finish();
            }
        }, Constants.SPLASH_TIME_OUT);
    }
}