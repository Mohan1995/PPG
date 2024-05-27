package com.nauroo.ppg.ui.loginorregister;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

import com.nauroo.ppg.R;
import com.nauroo.ppg.ui.home.HomeActivity;
import com.nauroo.ppg.ui.register.RegisterActivity;
import com.nauroo.ppg.utils.LocaleHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginOrRegisterActivity extends AppCompatActivity {
    @BindView(R.id.skipButton)
    Button skipButton;
    @BindView(R.id.registerButton)
    Button registerButton;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getResources().getBoolean(R.bool.portrait_only)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        setContentView(R.layout.activity_login_or_register);
        ButterKnife.bind(this);
     //   loadBackgroundImage();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().hide();

    }


    @OnClick({R.id.skipButton, R.id.registerButton})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.skipButton:
                moveToHomeActivity();
                break;
            case R.id.registerButton:
                moveToRegisterActvity();
                break;
        }
    }

    private void moveToHomeActivity() {
        Intent intent = new Intent(LoginOrRegisterActivity.this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void moveToRegisterActvity() {
        startActivity(new Intent(LoginOrRegisterActivity.this, RegisterActivity.class));
    }
}
