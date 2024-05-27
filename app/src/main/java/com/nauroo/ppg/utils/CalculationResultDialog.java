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
 * Created by Mohan M on 12/8/2017.
 */

public class CalculationResultDialog extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button returnButton, newCalculationButton;
    String liters,gallons;
    TextView litterTextView,gallonsTextView,content;

    public CalculationResultDialog(Activity a,String litters,String gallons) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.liters=litters;
        this.gallons=gallons;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.calculator_result_layout);
        returnButton = (Button) findViewById(R.id.returnButton);
        newCalculationButton = (Button) findViewById(R.id.newCalculationButton);
        litterTextView=(TextView)findViewById(R.id.litterTextView);
        gallonsTextView=(TextView)findViewById(R.id.galonesTextView);
        content=(TextView)findViewById(R.id.content);
        litterTextView.setText(liters);
        gallonsTextView.setText(gallons);
        returnButton.setOnClickListener(this);
        newCalculationButton.setOnClickListener(this);
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