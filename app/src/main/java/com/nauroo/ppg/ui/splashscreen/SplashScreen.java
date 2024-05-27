package com.nauroo.ppg.ui.splashscreen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.nauroo.ppg.R;
import com.nauroo.ppg.model.CatalogsModel;
import com.nauroo.ppg.model.GenericResponse;
import com.nauroo.ppg.network.NetworkAdapter;
import com.nauroo.ppg.network.ResponseCallback;
import com.nauroo.ppg.ui.home.HomeActivity;
import com.nauroo.ppg.ui.loginorregister.LoginOrRegisterActivity;
import com.nauroo.ppg.utils.Constants;
import com.nauroo.ppg.utils.LocaleHelper;

public class SplashScreen extends AppCompatActivity {

    ProgressBar progressBar;

    private int progressStatus = 0;
    private Handler handler = new Handler();

    @Override
    protected void attachBaseContext(Context base) {
        LocaleHelper.setLocale(base, "es");
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getResources().getBoolean(R.bool.portrait_only)){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        getCatalogs();
        //showLoadingProgress();

        moveToHomeActivity();
    }

    private void getCatalogs() {
        NetworkAdapter.getInstance(getApplicationContext()).getCatalogs(new ResponseCallback<GenericResponse<CatalogsModel>>(SplashScreen.this) {
            @Override
            public void onResponse(GenericResponse<CatalogsModel> response) {
                Constants.catalogsModel=response.getData();
            }

            @Override
            public void onFailure() {
            }
        });
    }

    private void showLoadingProgress() {
        // Start long running operation in a background thread
        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 1;
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            if (progressStatus == 99) {
                                if (isFirstTime()) {
                                    moveToLanguageSelectionActivity();
                                } else {
                                    moveToHomeActivity();
                                }

                            }
                        }


                    });
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

    private void moveToHomeActivity() {
        Intent intent = new Intent(SplashScreen.this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private boolean isFirstTime() {
        SharedPreferences preferences = getSharedPreferences(Constants.PREFERENCE_RAN_BEFORE, MODE_PRIVATE);
        boolean ranBefore = preferences.getBoolean("RanBefore", false);
        if (!ranBefore) {
            // first time
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("RanBefore", true);
            editor.apply();
        }
        return !ranBefore;
    }

    private void moveToLanguageSelectionActivity() {
        startActivity(new Intent(SplashScreen.this, LoginOrRegisterActivity.class));
        finish();
    }
}
