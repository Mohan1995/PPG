package com.nauroo.ppg.utils;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nauroo.ppg.R;

/**
 * Created by Mohan M on 1/8/2018.
 */

public class NotationsDialog extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public ImageView cancelButton;
    TextView tittleTextView, bodyTextView;
    String tittleText,bodyText;

    public NotationsDialog(Activity a,String tittleText, String bodyText) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.tittleText=tittleText;
        this.bodyText=bodyText;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.notations_layout);
        cancelButton = (ImageView) findViewById(R.id.cancelButton);
        tittleTextView = (TextView) findViewById(R.id.tittleTextView);
        bodyTextView=(TextView)findViewById(R.id.bodyTextView);
        tittleTextView.setText(tittleText);
        bodyTextView.setText(bodyText);
        cancelButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.calculateButton:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}