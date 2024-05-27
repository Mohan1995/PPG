package com.nauroo.ppg.ui.home.more.pisos;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.nauroo.ppg.databinding.PisosCalculatorResultEfm104PrimaryLayoutBinding;
import com.nauroo.ppg.databinding.PisosCalculatorResultUfc20E10Binding;

public class PisosResultDialogUFC20_E10 extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    double performanceTheoretical;
    double performancePractical;
    double paintVolume;
    double totalVolume;
    double litersPartA;
    double litersPartB;
    double eph;

    public PisosResultDialogUFC20_E10(Activity a, double performanceTheoretical, double performancePractical, double paintVolume, double eph, double totalVolume, double litersPartA, double litersPartB) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.performanceTheoretical=performanceTheoretical;
        this.performancePractical=performancePractical;
        this.paintVolume=paintVolume;
        this.eph=eph;
        this.totalVolume=totalVolume;
        this.litersPartA=litersPartA;
        this.litersPartB=litersPartB;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        PisosCalculatorResultUfc20E10Binding binding=PisosCalculatorResultUfc20E10Binding.inflate(LayoutInflater.from(getContext()));
        setContentView(binding.getRoot());


        binding.performanceTheoreticalTextView.setText(String.format("%.2f", performanceTheoretical).replace(",","."));
        binding.performancePracticalTextView.setText(String.format("%.2f", performancePractical).replace(",","."));
        binding.paintVolumeTextView.setText(String.format("%.2f", paintVolume).replace(",","."));
        binding.ephTextView.setText(String.format("%.2f", eph).replace(",","."));
       // binding.totalVolumeTextView.setText(String.format("%.2f", totalVolume).replace(",","."));
        binding.litersPartATextView.setText(String.format("%.2f", litersPartA).replace(",","."));
        binding.litersPartBTextView.setText(String.format("%.2f", litersPartB).replace(",","."));

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