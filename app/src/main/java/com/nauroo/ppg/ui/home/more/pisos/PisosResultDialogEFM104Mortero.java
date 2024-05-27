package com.nauroo.ppg.ui.home.more.pisos;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.nauroo.ppg.databinding.PisosCalculatorResultEfm104MorteroLayoutBinding;

public class PisosResultDialogEFM104Mortero extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    double performanceTheoretical;
    double performancePractical;
    double paintVolume;
    double totalVolume;
    double litersPartA;
    double litersPartB;
    int eph;
    double litterOfMix;
    double aggregates;
    double kgMeshSand;
    double kgMeshSand_50_60;
    double kgMeshSand_20_30;

    public PisosResultDialogEFM104Mortero(Activity a, double performanceTheoretical, double performancePractical, double paintVolume, int eph, double totalVolume, double litersPartA, double litersPartB, double litterOfMix, double aggregates, double kgMeshSand, double kgMeshSand_50_60, double kgMeshSand_20_30) {
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
        this.litterOfMix=litterOfMix;
        this.aggregates=aggregates;
        this.kgMeshSand=kgMeshSand;
        this.kgMeshSand_50_60=kgMeshSand;
        this.kgMeshSand_20_30=kgMeshSand_20_30;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        PisosCalculatorResultEfm104MorteroLayoutBinding binding=PisosCalculatorResultEfm104MorteroLayoutBinding.inflate(LayoutInflater.from(getContext()));
        setContentView(binding.getRoot());


        binding.performanceTheoreticalTextView.setText(String.format("%.2f", performanceTheoretical).replace(",","."));
        binding.performancePracticalTextView.setText(String.format("%.2f", performancePractical).replace(",","."));
        binding.paintVolumeTextView.setText(String.format("%.2f", paintVolume).replace(",","."));
        binding.ephTextView.setText(eph+"");
       // binding.totalVolumeTextView.setText(String.format("%.2f", totalVolume).replace(",","."));
        binding.litersPartATextView.setText(String.format("%.2f", litersPartA).replace(",","."));
        binding.litersPartBTextView.setText(String.format("%.2f", litersPartB).replace(",","."));
       // binding.litersOfMixTextView.setText(String.format("%.2f", litterOfMix).replace(",","."));
        binding.aggregateTextView.setText(String.format("%.2f", aggregates).replace(",","."));
        binding.kgSandMeshTextView.setText(String.format("%.2f", kgMeshSand).replace(",","."));
        binding.kgSandMesh5060TextView.setText(String.format("%.2f", kgMeshSand_50_60).replace(",","."));
        binding.kgSandMesh2030TextView.setText(String.format("%.2f", kgMeshSand_20_30).replace(",","."));


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