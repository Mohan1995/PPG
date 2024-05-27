package com.nauroo.ppg.utils;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.nauroo.ppg.R;

/**
 * Created by Mohan M on 4/19/2018.
 */

public class TermsAndConditionDialog extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button returnButton, acceptButton;
    TextView  content;

    public TermsAndConditionDialog(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.terms_and_condition_arlet);
        returnButton = (Button) findViewById(R.id.returnButton);
        acceptButton = (Button) findViewById(R.id.acceptTextView);
        content = (TextView) findViewById(R.id.content);
        returnButton.setOnClickListener(this);
        acceptButton.setOnClickListener(this);
        content.setMovementMethod(new ScrollingMovementMethod());


    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.returnButton:
//                dismiss();
//                break;
//            case R.id.newCalculationButton:
//                dismiss();
//                break;
//            default:
//                break;
//        }
        dismiss();
    }
}