package com.nauroo.ppg.ui.home.more.pisos;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.nauroo.ppg.R;
import com.nauroo.ppg.databinding.FragmentPisosCalculatorBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PisosCalculator#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PisosCalculator extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    List<String> companyList;

    List<String> firstCompanyOption;
    List<String> secondCompanyOption;

    FragmentPisosCalculatorBinding binding;

    public PisosCalculator() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PisosCalculator.
     */
    // TODO: Rename and change types and number of parameters
    public static PisosCalculator newInstance(String param1, String param2) {
        PisosCalculator fragment = new PisosCalculator();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPisosCalculatorBinding.inflate(inflater, container, false);
        setUpData();

        //Force app to stick with Portrait Mode
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        binding.calculateButton.setOnClickListener(this);
        binding.deleteButton.setOnClickListener(this);


        binding.optionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                EFM-104
                if (binding.companySpinner.getSelectedItemPosition() == 0) {
                    if (binding.optionSpinner.getSelectedItemPosition() == 0) {
                        binding.epsEditText.setText(10 + "");
                        binding.epsIndicatorTextView.setText(getString(R.string.select_10));

                    } else if (binding.optionSpinner.getSelectedItemPosition() == 1) {
                        binding.epsEditText.setText(80 + "");
//                        binding.epsIndicatorTextView.setText("Selecciona de un rango de 80 a 120");
                        binding.epsIndicatorTextView.setText(getString(R.string.select_from_a_range_of) + " 80-120");
                    }
                }
//                EFM-105
                else if (binding.companySpinner.getSelectedItemPosition() == 1) {
                    if (binding.optionSpinner.getSelectedItemPosition() == 0) {
                        binding.epsEditText.setText(20 + "");
                        binding.epsIndicatorTextView.setText(getString(R.string.select_from_a_range_of) + " 20-40");

                    } else if (binding.optionSpinner.getSelectedItemPosition() == 1) {
                        binding.epsEditText.setText(80 + "");
                        binding.epsIndicatorTextView.setText(getString(R.string.select_from_a_range_of) + " 80-120");
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.companySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //EFM-104
                if (binding.companySpinner.getSelectedItemPosition() == 0) {

                    showOptionSpinner();

                    ArrayAdapter companyAdapter = new ArrayAdapter<String>(getContext(),
                            R.layout.custom_spinner_item_1, firstCompanyOption);
                    companyAdapter.setDropDownViewResource(R.layout.custom_spinner_drop_down_item1);
                    binding.optionSpinner.setAdapter(companyAdapter);

                    setVolumeValue(100);

                    binding.mermaEditText.setText("20");

                    //Mix Ratio
                    binding.mixRatioLayout.setVisibility(View.VISIBLE);
                    binding.mixRatio1EdiText.setText("3");
                    binding.mixRatio2EdiText.setText("1");
                }
//                EFM-105
                else if (binding.companySpinner.getSelectedItemPosition() == 1) {

                    showOptionSpinner();

                    ArrayAdapter companyAdapter = new ArrayAdapter<String>(getContext(),
                            R.layout.custom_spinner_item_1, secondCompanyOption);
                    companyAdapter.setDropDownViewResource(R.layout.custom_spinner_drop_down_item1);
                    binding.optionSpinner.setAdapter(companyAdapter);

                    setVolumeValue(100);

                    binding.mermaEditText.setText("20");

                    //Mix Ratio
                    binding.mixRatioLayout.setVisibility(View.VISIBLE);
                    binding.mixRatio1EdiText.setText("2");
                    binding.mixRatio2EdiText.setText("1");
                }
//                UFC-20
                else if (binding.companySpinner.getSelectedItemPosition() == 2) {

                    hideOptionSpinner();

                    setVolumeValue(40);

                    binding.mermaEditText.setText("20");

                    binding.epsEditText.setText("1");

                    binding.epsIndicatorTextView.setText(getString(R.string.select_from_a_range_of) + " 1 a 2");

                    //Mix Ratio
                    binding.mixRatioLayout.setVisibility(View.VISIBLE);
                    binding.mixRatio1EdiText.setText("3");
                    binding.mixRatio2EdiText.setText("1");

                }
//                E-10
                else if (binding.companySpinner.getSelectedItemPosition() == 3) {

                    hideOptionSpinner();

                    setVolumeValue(70);

                    binding.mermaEditText.setText("20");

                    binding.epsEditText.setText("4");

                    binding.epsIndicatorTextView.setText(getString(R.string.select_from_a_range_of) + " 4 a 6");

                    //Mix Ratio
                    binding.mixRatioLayout.setVisibility(View.VISIBLE);
                    binding.mixRatio1EdiText.setText("1");
                    binding.mixRatio2EdiText.setText("1");
                }
//                Epoxacryl
                else if (binding.companySpinner.getSelectedItemPosition() == 4) {

                    hideOptionSpinner();

                    binding.solventLayout.setVisibility(View.GONE);

                    setVolumeValue(36);

                    binding.mermaEditText.setText("20");

                    binding.epsEditText.setText("2");

                    binding.epsIndicatorTextView.setText(getString(R.string.select_from_a_range_of) + " 2 a 3");

                    //Mix Ratio
                    binding.mixRatioLayout.setVisibility(View.GONE);
                }
//                Ultrafácil
                else if (binding.companySpinner.getSelectedItemPosition() == 5) {

                    hideOptionSpinner();

                    setVolumeValue(35);

                    binding.mermaEditText.setText("20");

                    binding.epsEditText.setText("2");

                    binding.epsIndicatorTextView.setText(getString(R.string.select_from_a_range_of) + " 2 a 3");

                    //Mix Ratio
                    binding.mixRatioLayout.setVisibility(View.GONE);
                }
//                Sellador Base Solvente
                else if (binding.companySpinner.getSelectedItemPosition() == 6) {

                    hideOptionSpinner();

                    binding.solventLayout.setVisibility(View.GONE);

                    setVolumeValue(25);

                    binding.mermaEditText.setText("20");

                    binding.epsEditText.setText("2");

                    binding.epsIndicatorTextView.setText(getString(R.string.select_from_a_range_of) + " 2 a 3");

                    //Mix Ratio
                    binding.mixRatioLayout.setVisibility(View.GONE);
                }
//                Sellador Base Agua
                else if (binding.companySpinner.getSelectedItemPosition() == 7) {

                    hideOptionSpinner();

                    binding.solventLayout.setVisibility(View.GONE);

                    setVolumeValue(30);

                    binding.mermaEditText.setText("20");

                    binding.epsEditText.setText("2");

                    binding.epsIndicatorTextView.setText(getString(R.string.select_from_a_range_of) + " 2 a 3");

                    //Mix Ratio
                    binding.mixRatioLayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return binding.getRoot();
    }

    private void setVolumeValue(int value) {
        binding.volumeValueEditText.setText(value + " ");
    }

    private void hideOptionSpinner() {
        binding.optionSpinner.setVisibility(View.GONE);
        binding.solventLayout.setVisibility(View.VISIBLE);
    }

    private void showOptionSpinner() {
        binding.optionSpinner.setVisibility(View.VISIBLE);
        binding.solventLayout.setVisibility(View.GONE);
    }

    private void setUpData() {

        //Company list
        companyList = new ArrayList<>();
        companyList.add("EFM-104");
        companyList.add("EFM-105");
        companyList.add("UFC-20");
        companyList.add("E-10");
        companyList.add("Epoxacryl");
        companyList.add("Ultrafácil");
        companyList.add("Sellador Base Solvente");
        companyList.add("Sellador Base Agua");


        //First Company option
        firstCompanyOption = new ArrayList<>();
        firstCompanyOption.add("Primario");
        firstCompanyOption.add("Mortero");

        //Second Company option
        secondCompanyOption = new ArrayList<>();
        secondCompanyOption.add("Pintura");
        secondCompanyOption.add("Autonivelante");


        ArrayAdapter companyAdapter = new ArrayAdapter<String>(getContext(),
                R.layout.custome_spinner_item, companyList);
        companyAdapter.setDropDownViewResource(R.layout.custom_spinner_drop_down_item1);
        binding.companySpinner.setAdapter(companyAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.deleteButton:
                clearAllFields();
                break;
            case R.id.calculateButton:

                if (binding.areaValueEditText.getText().length() == 0) {
                    Toast.makeText(getContext(), getString(R.string.area_value_is_required), Toast.LENGTH_SHORT).show();
                } else if (binding.volumeValueEditText.getText().length() == 0 || binding.volumeValueEditText.getText().toString().equals("0")) {
                    Toast.makeText(getContext(), getString(R.string.please_select_volume_value), Toast.LENGTH_SHORT).show();
                } else if (binding.mermaEditText.getText().length() == 0) {
                    Toast.makeText(getContext(), getString(R.string.please_select_merma), Toast.LENGTH_SHORT).show();
                } else if (Double.parseDouble(binding.mermaEditText.getText().toString()) > 50.0) {
                    Toast.makeText(getContext(), getString(R.string.enter_the_loss_within_range), Toast.LENGTH_SHORT).show();
                } else if (binding.epsEditText.getText().length() == 0 || binding.epsEditText.getText().toString().equals("0")) {
                    Toast.makeText(getContext(), getString(R.string.thickness_is_required), Toast.LENGTH_SHORT).show();
                } else if (binding.mixRatioLayout.getVisibility() == View.VISIBLE && binding.mixRatio1EdiText.getText().length() == 0 && binding.mixRatio2EdiText.getText().length() == 0) {
                    Toast.makeText(getContext(), getString(R.string.mix_ratio_is_required), Toast.LENGTH_LONG).show();
                } else if (binding.solventLayout.getVisibility() == View.VISIBLE && binding.solventEditText.getText().length() == 0) {
                    Toast.makeText(getContext(), getString(R.string.solvent_is_required), Toast.LENGTH_LONG).show();
                }
//                EFM-104 - Primario
                else if (binding.companySpinner.getSelectedItemPosition() == 0
                        && binding.optionSpinner.getSelectedItemPosition() == 0
                        && Double.parseDouble(binding.epsEditText.getText().toString()) > 10.0) {
                    Toast.makeText(getContext(), getString(R.string.enter_a_thickness_of_10), Toast.LENGTH_LONG).show();

//                EFM-104 - Mortero
                } else if (binding.companySpinner.getSelectedItemPosition() == 0
                        && binding.optionSpinner.getSelectedItemPosition() == 1
                        && (Double.parseDouble(binding.epsEditText.getText().toString()) < 80.0 || Double.parseDouble(binding.epsEditText.getText().toString()) > 120.0)) {
                    Toast.makeText(getContext(), getString(R.string.please_enter_the_thickness_within_the_range_of) + " 80-120", Toast.LENGTH_LONG).show();

                }
                //                EFM-105 - Pintura
                else if (binding.companySpinner.getSelectedItemPosition() == 1
                        && binding.optionSpinner.getSelectedItemPosition() == 0
                        && (Double.parseDouble(binding.epsEditText.getText().toString()) < 20.0 || Double.parseDouble(binding.epsEditText.getText().toString()) > 40.0)) {
                    Toast.makeText(getContext(), getString(R.string.please_enter_the_thickness_within_the_range_of) + " 20-40", Toast.LENGTH_LONG).show();
                }
                //                EFM-105 - Autonivelante
                else if (binding.companySpinner.getSelectedItemPosition() == 1
                        && binding.optionSpinner.getSelectedItemPosition() == 1
                        && (Double.parseDouble(binding.epsEditText.getText().toString()) < 80.0 || Double.parseDouble(binding.epsEditText.getText().toString()) > 120.0)) {
                    Toast.makeText(getContext(), getString(R.string.please_enter_the_thickness_within_the_range_of) + " 80-120", Toast.LENGTH_LONG).show();
                }
//                UFC-20
                else if (binding.companySpinner.getSelectedItemPosition() == 2
                        && (Double.parseDouble(binding.epsEditText.getText().toString()) < 1.0 || Double.parseDouble(binding.epsEditText.getText().toString()) > 2.0)) {
                    Toast.makeText(getContext(), getString(R.string.please_enter_the_thickness_within_the_range_of) + " 1 a 2", Toast.LENGTH_LONG).show();
                }

//                E-10
                else if (binding.companySpinner.getSelectedItemPosition() == 3
                        && (Double.parseDouble(binding.epsEditText.getText().toString()) < 4.0 || Double.parseDouble(binding.epsEditText.getText().toString()) > 6.0)) {
                    Toast.makeText(getContext(), getString(R.string.please_enter_the_thickness_within_the_range_of) + " 4 a 6", Toast.LENGTH_LONG).show();
                }

//                Epoxacryl
                else if (binding.companySpinner.getSelectedItemPosition() == 4
                        && (Double.parseDouble(binding.epsEditText.getText().toString()) < 2.0 || Double.parseDouble(binding.epsEditText.getText().toString()) > 3.0)) {
                    Toast.makeText(getContext(), getString(R.string.please_enter_the_thickness_within_the_range_of) + " 2 a 3", Toast.LENGTH_LONG).show();
                }

//                Ultrafácil
                else if (binding.companySpinner.getSelectedItemPosition() == 5
                        && (Double.parseDouble(binding.epsEditText.getText().toString()) < 2.0 || Double.parseDouble(binding.epsEditText.getText().toString()) > 3.0)) {
                    Toast.makeText(getContext(), getString(R.string.please_enter_the_thickness_within_the_range_of) + " 2 a 3", Toast.LENGTH_LONG).show();
                }

//                Sellador Base Solvente
                else if (binding.companySpinner.getSelectedItemPosition() == 6
                        && (Double.parseDouble(binding.epsEditText.getText().toString()) < 2.0 || Double.parseDouble(binding.epsEditText.getText().toString()) > 3.0)) {
                    Toast.makeText(getContext(), getString(R.string.please_enter_the_thickness_within_the_range_of) + " 2 a 3", Toast.LENGTH_LONG).show();
                }


//                Sellador Base Agua
                else if (binding.companySpinner.getSelectedItemPosition() == 7
                        && (Double.parseDouble(binding.epsEditText.getText().toString()) < 2.0 || Double.parseDouble(binding.epsEditText.getText().toString()) > 3.0)) {
                    Toast.makeText(getContext(), getString(R.string.please_enter_the_thickness_within_the_range_of) + " 2 a 3", Toast.LENGTH_LONG).show();
                } else {

                    try {
                        //EFM-104
                        if (binding.companySpinner.getSelectedItemPosition() == 0) {
                            //Primary
                            if (binding.optionSpinner.getSelectedItemPosition() == 0) {
                                calculateResultFor_EFM_104_Primary();
                            }
                            //Mortero
                            else if (binding.optionSpinner.getSelectedItemPosition() == 1) {
                                calculateResultFor_EFM_104_Mortero();
                            }

                        }
//                    EFM-105
                        else if (binding.companySpinner.getSelectedItemPosition() == 1) {
                            //Pintura
                            if (binding.optionSpinner.getSelectedItemPosition() == 0) {
                                calculateResultFor_EFM_105_Pintura();
                            }
                            //Autonivelante
                            else if (binding.optionSpinner.getSelectedItemPosition() == 1) {
                                calculateResultFor_EFM_105_Auto();
                            }
                        }

//                   UFC-20 & E-10
                        else if (binding.companySpinner.getSelectedItemPosition() == 2 || binding.companySpinner.getSelectedItemPosition() == 3) {
                            calculateForUFC20_E10();
                        }

//                        ULTRAFACIL
                        else if (binding.companySpinner.getSelectedItemPosition() == 5) {
                            calculateForULTRAFACIL();
                        }
//                        Epoxacryl, Sellador Base Solvente
//                        Sellador Base Agua
                        else if (binding.companySpinner.getSelectedItemPosition() == 4 || binding.companySpinner.getSelectedItemPosition() == 6 || binding.companySpinner.getSelectedItemPosition() == 7) {
                            calculateResultForEpoxacryl_Sellador();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    private void clearAllFields() {
        binding.mt2.setChecked(true);
        binding.areaValueEditText.setText("");
        binding.volumeValueEditText.setText("");

        binding.epsEditText.setText("");
        binding.mermaEditText.setText("");
        binding.solventEditText.setText("");

//        binding.companySpinner.setSelection(0,true);
//        binding.optionSpinner.setSelection(0,true);
        setUpData();
    }

    private void calculateResultForEpoxacryl_Sellador() {
        double volume = Double.parseDouble(binding.volumeValueEditText.getText().toString()) / 100;
        double eps = Double.parseDouble(binding.epsEditText.getText().toString());
        double performanceTheoretical = 39.4 * (volume / eps);

        double merma = Double.parseDouble(binding.mermaEditText.getText().toString()) / 100;
        double performancePractical = performanceTheoretical * (1 - merma);

        double area;
        if (binding.ft2.isChecked()) {
            area = Double.parseDouble(binding.areaValueEditText.getText().toString()) / 10.7639;
        } else {
            area = Double.parseDouble(binding.areaValueEditText.getText().toString());
        }
        double paintVolume = area / performancePractical;


        showDialogForEpoxacryl_Sellador(performanceTheoretical, performancePractical, paintVolume);

    }

    private void showDialogForEpoxacryl_Sellador(double performanceTheoretical, double performancePractical, double paintVolume) {
        final PisosResultDialogEpoxacryl cdd = new PisosResultDialogEpoxacryl(getActivity(), performanceTheoretical, performancePractical, paintVolume);
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.show();
        cdd.setCancelable(false);

        Button returnButton = (Button) cdd.findViewById(R.id.returnButton);
        Button newCalculationButton = (Button) cdd.findViewById(R.id.calculateButton);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cdd.dismiss();
            }
        });

        newCalculationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearAllFields();
                cdd.dismiss();
            }
        });
    }

    private void showDialogForEpoxacryl(double performanceTheoretical, double performancePractical, double paintVolume) {
        final PisosResultDialogEpoxacryl cdd = new PisosResultDialogEpoxacryl(getActivity(), performanceTheoretical, performancePractical, paintVolume);
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.show();
        cdd.setCancelable(false);

        Button returnButton = (Button) cdd.findViewById(R.id.returnButton);
        Button newCalculationButton = (Button) cdd.findViewById(R.id.calculateButton);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cdd.dismiss();
            }
        });

        newCalculationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearAllFields();
                cdd.dismiss();
            }
        });
    }

    private void calculateForUFC20_E10() {
        double volume = Double.parseDouble(binding.volumeValueEditText.getText().toString()) / 100;
        double eps = Double.parseDouble(binding.epsEditText.getText().toString());
        double performanceTheoretical = 39.4 * (volume / eps);

        double merma = Double.parseDouble(binding.mermaEditText.getText().toString()) / 100;
        double performancePractical = performanceTheoretical * (1 - merma);

        double area;
        if (binding.ft2.isChecked()) {
            area = Double.parseDouble(binding.areaValueEditText.getText().toString()) / 10.7639;
        } else {
            area = Double.parseDouble(binding.areaValueEditText.getText().toString());
        }
        double paintVolume = area / performancePractical;

        double solvente = Double.parseDouble(binding.solventEditText.getText().toString()) / 100;
        double temp = 1 + solvente;
        double temp1 = eps * temp;
        Log.w("EPH", (temp1 / volume) + "");
        double eph = temp1 / volume;

        double totalVolume = paintVolume;

        double mixRatio1 = Double.parseDouble(binding.mixRatio1EdiText.getText().toString());
        double mixRatio2 = Double.parseDouble(binding.mixRatio2EdiText.getText().toString());

        double ratio = mixRatio1 + mixRatio2;


        double litersPartA = paintVolume * (mixRatio1 / ratio);

        double litersPartB = paintVolume * (mixRatio2 / ratio);


        showDialogForUFC20_E10(performanceTheoretical, performancePractical, paintVolume, eph, totalVolume, litersPartA, litersPartB);

    }

    private void calculateForULTRAFACIL() {
        double volume = Double.parseDouble(binding.volumeValueEditText.getText().toString()) / 100;
        double eps = Double.parseDouble(binding.epsEditText.getText().toString());
        double performanceTheoretical = 39.4 * (volume / eps);

        double merma = Double.parseDouble(binding.mermaEditText.getText().toString()) / 100;
        double performancePractical = performanceTheoretical * (1 - merma);

        double area;
        if (binding.ft2.isChecked()) {
            area = Double.parseDouble(binding.areaValueEditText.getText().toString()) / 10.7639;
        } else {
            area = Double.parseDouble(binding.areaValueEditText.getText().toString());
        }
        double paintVolume = area / performancePractical;


        showDialogForEpoxacryl(performanceTheoretical, performancePractical, paintVolume);
    }

    private void showDialogForUFC20_E10(double performanceTheoretical, double performancePractical, double paintVolume, double eph, double totalVolume, double litersPartA, double litersPartB) {
        final PisosResultDialogUFC20_E10 cdd = new PisosResultDialogUFC20_E10(getActivity(), performanceTheoretical, performancePractical, paintVolume, eph, totalVolume, litersPartA, litersPartB);
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.show();
        cdd.setCancelable(false);

        Button returnButton = (Button) cdd.findViewById(R.id.returnButton);
        Button newCalculationButton = (Button) cdd.findViewById(R.id.calculateButton);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cdd.dismiss();
            }
        });

        newCalculationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearAllFields();
                cdd.dismiss();
            }
        });
    }

    private void calculateResultFor_EFM_105_Auto() {
        double volume = Double.parseDouble(binding.volumeValueEditText.getText().toString()) / 100;
        double eps = Double.parseDouble(binding.epsEditText.getText().toString());
        double performanceTheoretical = 39.4 * (volume / eps);

        double merma = Double.parseDouble(binding.mermaEditText.getText().toString()) / 100;
        double performancePractical = performanceTheoretical * (1 - merma);

        double area;
        if (binding.ft2.isChecked()) {
            area = Double.parseDouble(binding.areaValueEditText.getText().toString()) / 10.7639;
        } else {
            area = Double.parseDouble(binding.areaValueEditText.getText().toString());
        }
        double paintVolume = area / performancePractical;

        int eph = 80;

        double totalVolume = paintVolume;

        double mixRatio1 = Double.parseDouble(binding.mixRatio1EdiText.getText().toString());
        double mixRatio2 = Double.parseDouble(binding.mixRatio2EdiText.getText().toString());

        double ratio = mixRatio1 + mixRatio2;


        double litersPartA = paintVolume * (mixRatio1 / ratio);

        double litersPartB = paintVolume * (mixRatio2 / ratio);


        showDialogFor_EFM_105_Auto(performanceTheoretical, performancePractical, paintVolume, eph, totalVolume, litersPartA, litersPartB);
    }

    private void showDialogFor_EFM_105_Auto(double performanceTheoretical, double performancePractical, double paintVolume, int eph, double totalVolume, double litersPartA, double litersPartB) {
        final PisosResultDialogEFM105Auto cdd = new PisosResultDialogEFM105Auto(getActivity(), performanceTheoretical, performancePractical, paintVolume, eph, totalVolume, litersPartA, litersPartB);
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.show();
        cdd.setCancelable(false);

        Button returnButton = (Button) cdd.findViewById(R.id.returnButton);
        Button newCalculationButton = (Button) cdd.findViewById(R.id.calculateButton);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cdd.dismiss();
            }
        });

        newCalculationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearAllFields();
                cdd.dismiss();
            }
        });
    }

    private void calculateResultFor_EFM_105_Pintura() {
        double volume = Double.parseDouble(binding.volumeValueEditText.getText().toString()) / 100;
        double eps = Double.parseDouble(binding.epsEditText.getText().toString());
        double performanceTheoretical = 39.4 * (volume / eps);

        double merma = Double.parseDouble(binding.mermaEditText.getText().toString()) / 100;
        double performancePractical = performanceTheoretical * (1 - merma);

        double area;
        if (binding.ft2.isChecked()) {
            area = Double.parseDouble(binding.areaValueEditText.getText().toString()) / 10.7639;
        } else {
            area = Double.parseDouble(binding.areaValueEditText.getText().toString());
        }
        double paintVolume = area / performancePractical;

        int eph = 80;

        double totalVolume = paintVolume;

        double mixRatio1 = Double.parseDouble(binding.mixRatio1EdiText.getText().toString());
        double mixRatio2 = Double.parseDouble(binding.mixRatio2EdiText.getText().toString());

        double ratio = mixRatio1 + mixRatio2;


        double litersPartA = paintVolume * (mixRatio1 / ratio);

        double litersPartB = paintVolume * (mixRatio2 / ratio);

        double litterOfMix = totalVolume;

        double aggregates = litterOfMix * 8;

        showDialogFor_EFM_105_Pintura(performanceTheoretical, performancePractical, paintVolume, eph, totalVolume, litersPartA, litersPartB,
                litterOfMix, aggregates);
    }

    private void showDialogFor_EFM_105_Pintura(double performanceTheoretical, double performancePractical, double paintVolume, int eph, double totalVolume, double litersPartA, double litersPartB, double litterOfMix, double aggregates) {
        final PisosResultDialogEFM105Pintura cdd = new PisosResultDialogEFM105Pintura(getActivity(), performanceTheoretical, performancePractical, paintVolume, eph, totalVolume, litersPartA, litersPartB, litterOfMix, aggregates);
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.show();
        cdd.setCancelable(false);

        Button returnButton = (Button) cdd.findViewById(R.id.returnButton);
        Button newCalculationButton = (Button) cdd.findViewById(R.id.calculateButton);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cdd.dismiss();
            }
        });

        newCalculationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearAllFields();
                cdd.dismiss();
            }
        });
    }

    private void calculateResultFor_EFM_104_Mortero() throws Exception {

        double volume = Double.parseDouble(binding.volumeValueEditText.getText().toString()) / 100;
        double eps = Double.parseDouble(binding.epsEditText.getText().toString());
        double performanceTheoretical = 39.4 * (volume / eps);

        double merma = Double.parseDouble(binding.mermaEditText.getText().toString()) / 100;
        double performancePractical = performanceTheoretical * (1 - merma);

        double area;
        if (binding.ft2.isChecked()) {
            area = Double.parseDouble(binding.areaValueEditText.getText().toString()) / 10.7639;
        } else {
            area = Double.parseDouble(binding.areaValueEditText.getText().toString());
        }
        double paintVolume = area / performancePractical;

        int eph = 80;

        double totalVolume = paintVolume;

        double mixRatio1 = Double.parseDouble(binding.mixRatio1EdiText.getText().toString());
        double mixRatio2 = Double.parseDouble(binding.mixRatio2EdiText.getText().toString());

        double ratio = mixRatio1 + mixRatio2;


        double litersPartA = paintVolume * (mixRatio1 / ratio);

        double litersPartB = paintVolume * (mixRatio2 / ratio);

        double litterOfMix = totalVolume;

        double aggregates = litterOfMix * 8;

        double kgMeshSand = aggregates * 0.15;

        double kgMeshSand_50_60 = aggregates * 0.15;

        double kgMeshSand_20_30 = aggregates * 0.7;

        showDialogFor_EFM_104_Mortero(performanceTheoretical, performancePractical, paintVolume, eph, totalVolume, litersPartA, litersPartB,
                litterOfMix, aggregates, kgMeshSand, kgMeshSand_50_60, kgMeshSand_20_30);
    }

    private void showDialogFor_EFM_104_Mortero(double performanceTheoretical, double performancePractical, double paintVolume, int eph, double totalVolume, double litersPartA, double litersPartB, double litterOfMix, double aggregates, double kgMeshSand, double kgMeshSand_50_60, double kgMeshSand_20_30) {
        final PisosResultDialogEFM104Mortero cdd = new PisosResultDialogEFM104Mortero(getActivity(), performanceTheoretical, performancePractical, paintVolume, eph, totalVolume, litersPartA, litersPartB, litterOfMix, aggregates, kgMeshSand, kgMeshSand_50_60, kgMeshSand_20_30);
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.show();
        cdd.setCancelable(false);

        Button returnButton = (Button) cdd.findViewById(R.id.returnButton);
        Button newCalculationButton = (Button) cdd.findViewById(R.id.calculateButton);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cdd.dismiss();
            }
        });

        newCalculationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearAllFields();
                cdd.dismiss();
            }
        });
    }

    private void calculateResultFor_EFM_104_Primary() throws Exception {
        double volume = Double.parseDouble(binding.volumeValueEditText.getText().toString()) / 100;
        double eps = Double.parseDouble(binding.epsEditText.getText().toString());
        double performanceTheoretical = 39.4 * (volume / eps);

        double merma = Double.parseDouble(binding.mermaEditText.getText().toString()) / 100;
        double performancePractical = performanceTheoretical * (1 - merma);

        double area;
        if (binding.ft2.isChecked()) {
            area = Double.parseDouble(binding.areaValueEditText.getText().toString()) / 10.7639;
        } else {
            area = Double.parseDouble(binding.areaValueEditText.getText().toString());
        }


        double paintVolume = area / performancePractical;

        int eph = 80;

        double totalVolume = paintVolume;


        double mixRatio1 = Double.parseDouble(binding.mixRatio1EdiText.getText().toString());
        double mixRatio2 = Double.parseDouble(binding.mixRatio2EdiText.getText().toString());

        double ratio = mixRatio1 + mixRatio2;


        double litersPartA = paintVolume * (mixRatio1 / ratio);

        double litersPartB = paintVolume * (mixRatio2 / ratio);

        showDialogFor_EFM_104_Primary(performanceTheoretical, performancePractical, paintVolume, eph, litersPartA, litersPartB);

    }

    private void showDialogFor_EFM_104_Primary(double performanceTheoretical, double performancePractical, double paintVolume, int eph, double litersPartA, double litersPartB) {
        final PisosResultDialogEFM104Primary cdd = new PisosResultDialogEFM104Primary(getActivity(), performanceTheoretical, performancePractical, paintVolume, eph, litersPartA, litersPartB);
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.show();
        cdd.setCancelable(false);

        Button returnButton = (Button) cdd.findViewById(R.id.returnButton);
        Button newCalculationButton = (Button) cdd.findViewById(R.id.calculateButton);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cdd.dismiss();
            }
        });

        newCalculationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearAllFields();
                cdd.dismiss();
            }
        });
    }

    @Override
    public void onDestroy() {
        //Reset Orientation to normal
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        super.onDestroy();

    }
}