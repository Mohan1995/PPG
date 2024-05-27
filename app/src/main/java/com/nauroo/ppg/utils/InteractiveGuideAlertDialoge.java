package com.nauroo.ppg.utils;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.nauroo.ppg.R;

/**
 * Created by Mohan M on 3/7/2018.
 */

public class InteractiveGuideAlertDialoge extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    String content;
    public Dialog d;
    TextView contentTextView, acceptTextView;

    public InteractiveGuideAlertDialoge(Activity a, String content) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.content = content;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.guide_alert_layout);
        contentTextView = (TextView) findViewById(R.id.contentTextView);
        acceptTextView = (TextView) findViewById(R.id.acceptTextView);
        contentTextView.setText(Html.fromHtml(content));

    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.acceptTextView:
//                dismiss();
//                break;
//        }
    }
}
