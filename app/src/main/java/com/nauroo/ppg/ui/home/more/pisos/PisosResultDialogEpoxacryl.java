package com.nauroo.ppg.ui.home.more.pisos;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.nauroo.ppg.databinding.PisosCalculatorResultEfm104MorteroLayoutBinding;
import com.nauroo.ppg.databinding.PisosCalculatorResultUltrafacilBinding;

public class PisosResultDialogEpoxacryl extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    double performanceTheoretical;
    double performancePractical;
    double paintVolume;




    public PisosResultDialogEpoxacryl(Activity a, double performanceTheoretical, double performancePractical, double paintVolume) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.performanceTheoretical = performanceTheoretical;
        this.performancePractical = performancePractical;
        this.paintVolume = paintVolume;


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        PisosCalculatorResultUltrafacilBinding binding = PisosCalculatorResultUltrafacilBinding.inflate(LayoutInflater.from(getContext()));
        setContentView(binding.getRoot());


        binding.performanceTheoreticalTextView.setText(String.format("%.2f", performanceTheoretical).replace(",", "."));
        binding.performancePracticalTextView.setText(String.format("%.2f", performancePractical).replace(",", "."));
        binding.paintVolumeTextView.setText(String.format("%.2f", paintVolume).replace(",", "."));

        binding.calculateButton.setOnClickListener(this);
        binding.returnButton.setOnClickListener(this);


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