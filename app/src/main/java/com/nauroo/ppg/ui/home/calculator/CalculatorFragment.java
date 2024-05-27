package com.nauroo.ppg.ui.home.calculator;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.nauroo.ppg.R;
import com.nauroo.ppg.utils.CalculationResultDialog;
import com.nauroo.ppg.utils.Constants;
import com.nauroo.ppg.utils.TermsAndConditionDialog;
import com.tobiasschuerg.prefixsuffix.PrefixSuffixEditText;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CalculatorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalculatorFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.deleteButton)
    Button deleteButton;
    @BindView(R.id.calculateButton)
    Button calculateButton;
    @BindView(R.id.mt2)
    RadioButton mt2;
    @BindView(R.id.ft2)
    RadioButton ft2;
    @BindView(R.id.areaValueEditText)
    PrefixSuffixEditText areaValueEditText;
    @BindView(R.id.microSpinner)
    Spinner microSpinner;
    double areaValue;
    double redimiento;
    double rt;
    ArrayAdapter microAdapter;
    List<String> microList = new ArrayList<>();
    @BindView(R.id.volumeValueEditText)
    PrefixSuffixEditText volumeValueEditText;
    @BindView(R.id.percentageOfLossEditText)
    PrefixSuffixEditText percentageOfLossEditText;
    @BindView(R.id.mincroValueEditText)
    EditText thicknessEditText;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CalculatorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalculatorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalculatorFragment newInstance(String param1, String param2) {
        CalculatorFragment fragment = new CalculatorFragment();
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
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);
        ButterKnife.bind(this, view);


        String m2String = "m\u00B2";
        String ft2String = "ft\u00B2";
        setUpMicroSpinner();
        mt2.setChecked(true);
        mt2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                // calculateVolumeAndPercentageLoss();
                if (b) {
                    if (areaValueEditText.getText().length() == 0)
                        areaValueEditText.setSuffix("");
                    else
                        areaValueEditText.setSuffix(" " + m2String);
                }

            }
        });

        ft2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                // calculateVolumeAndPercentageLoss();
                if (b) {
                    if (areaValueEditText.getText().length() == 0)
                        areaValueEditText.setSuffix("");
                    else
                        areaValueEditText.setSuffix(" " + ft2String);
                }
            }
        });
        areaValueEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //calculateVolumeAndPercentageLoss();
                if (editable.length() == 0) {
                    areaValueEditText.setSuffix("");
                } else {
                    if (mt2.isChecked()) {
                        areaValueEditText.setSuffix(" " + m2String);
                    } else if (ft2.isChecked()) {
                        areaValueEditText.setSuffix(" " + ft2String);
                    }
                }


            }
        });


        volumeValueEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 0) {
                    volumeValueEditText.setSuffix("");
                } else {
                    if (mt2.isChecked()) {
                        volumeValueEditText.setSuffix(" %");
                    } else if (ft2.isChecked()) {
                        volumeValueEditText.setSuffix(" %");
                    }
                }
            }
        });


        percentageOfLossEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 0) {
                    percentageOfLossEditText.setSuffix("");
                } else {
                    if (mt2.isChecked()) {
                        percentageOfLossEditText.setSuffix(" %");
                    } else if (ft2.isChecked()) {
                        percentageOfLossEditText.setSuffix(" %");
                    }
                }
            }
        });
        return view;
    }

    private void setUpMicroSpinner() {
        microList.add(getString(R.string.micro));
        microList.add(getString(R.string.milesimas));
        microAdapter = new ArrayAdapter<String>(getContext(),
                R.layout.custome_spinner_item, microList);
        microAdapter.setDropDownViewResource(R.layout.custom_spinner_drop_down_item);
        microSpinner.setAdapter(microAdapter);
        microSpinner.setSelection(1);
    }

//    private void calculateVolumeAndPercentageLoss() {
//        if (areaValueEditText.getText().length() == 0) {
//            if (volumeAdapter != null && percentageLossAapter != null && microAdapter != null) {
//                volumeAdapter.clear();
//                percentageLossAapter.clear();
//                //  microAdapter.clear();
//            }
//        } else {
//            volumeValueList = new ArrayList<String>();
//            lossValueList = new ArrayList<String>();
//            double result;
//            if (mt2.isChecked()) {
//                areaValue = Double.parseDouble(areaValueEditText.getText().toString());
//            } else {
//                areaValue = Double.parseDouble(areaValueEditText.getText().toString()) * 10.7639;
//            }
//            volumeValueList.add(getString(R.string.volume_value));
//            lossValueList.add(getString(R.string.percentage_of_loss));
//            Log.w("Area value", areaValue + "");
//            for (int j = 5; j <= 100; j += 5) {
////                result = (areaValue / 100.0f) * j;
//                result = (areaValue * j) / 100.0f;
//                volumeValueList.add(decimalFormat.format(result));
//                lossValueList.add(decimalFormat.format(result));
//            }
//            setUpVolumeSpinner(volumeValueList);
//            setUpPercentageOfLossSpinner(lossValueList);
//        }
//    }

//    private void setUpPercentageOfLossSpinner(List<String> lossValueList) {
//        percentageLossAapter = new ArrayAdapter<String>(getContext(),
//                R.layout.custome_spinner_item, lossValueList);
//        percentageLossAapter.setDropDownViewResource(R.layout.custom_spinner_drop_down_item);
//        percentageOfLossSpinner.setAdapter(percentageLossAapter);
//    }
//
//    private void setUpVolumeSpinner(List<String> volumeValueList) {
//        volumeAdapter = new ArrayAdapter<String>(getContext(),
//                R.layout.custome_spinner_item, volumeValueList);
//        volumeAdapter.setDropDownViewResource(R.layout.custom_spinner_drop_down_item);
//        volumeValueSpinner.setAdapter(volumeAdapter);
//    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @OnClick({R.id.deleteButton, R.id.calculateButton})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.deleteButton:
                clearAllFields();
                break;
            case R.id.calculateButton:
                if (areaValueEditText.getText().length() == 0) {
                    Toast.makeText(getContext(), getString(R.string.area_value_is_required), Toast.LENGTH_SHORT).show();
                } else if (volumeValueEditText.getText().length() == 0 || volumeValueEditText.getText().toString().equals("0")) {
                    Toast.makeText(getContext(), getString(R.string.please_select_volume_value), Toast.LENGTH_SHORT).show();
                } else if (percentageOfLossEditText.getText().length() == 0) {
                    Toast.makeText(getContext(), getString(R.string.please_select_percentage_loss), Toast.LENGTH_SHORT).show();
                } else if (thicknessEditText.getText().length() == 0 || thicknessEditText.getText().toString().equals("0")) {
                    Toast.makeText(getContext(), getString(R.string.thickness_is_required), Toast.LENGTH_SHORT).show();
                } else {
                    if (areaValueEditText.getText().length() == 0) {
                        Toast.makeText(getContext(), getString(R.string.area_value_is_required), Toast.LENGTH_SHORT).show();
                    } else if (volumeValueEditText.getText().length() == 0 || volumeValueEditText.getText().toString().equals("0")) {
                        Toast.makeText(getContext(), getString(R.string.please_select_volume_value), Toast.LENGTH_SHORT).show();
                    } else if (percentageOfLossEditText.getText().length() == 0) {
                        Toast.makeText(getContext(), getString(R.string.please_select_percentage_loss), Toast.LENGTH_SHORT).show();
                    } else if (thicknessEditText.getText().length() == 0 || thicknessEditText.getText().toString().equals("0")) {
                        Toast.makeText(getContext(), getString(R.string.thickness_is_required), Toast.LENGTH_SHORT).show();
                    } else {
                        if (mt2.isChecked()) {
                            areaValue = Double.parseDouble(areaValueEditText.getText().toString());
                        } else {
                            areaValue = Double.parseDouble(areaValueEditText.getText().toString()) / 10.7639;
                        }
                        if (microSpinner.getSelectedItemPosition() == 0) {

                            redimiento = Double.parseDouble(volumeValueEditText.getText().toString()) * (39.4 / 100) / (Double.parseDouble(thicknessEditText.getText().toString()) / 25.4);

                            Log.w("Area", areaValueEditText.getText().toString());

                            double result = areaValue / redimiento;

                            double liters = result + (result * Double.valueOf(percentageOfLossEditText.getText().toString()) / 100);
                            double gallons = liters * 0.2641;

                            Log.w("Litters", liters + "");
                            Log.w("gallons", gallons + "");

                            String finalLiters = String.format("%.2f", liters);
                            String finalGallons = String.format("%.2f", gallons);
                            if (isFirstTime()) {
                                showTeremsAndConditionDialog(finalLiters.replace(",", "."), finalGallons.replace(",", "."));
                            } else {
                                showResult(finalLiters.replace(",", "."), finalGallons.replace(",", "."));
                            }


                        } else {

                            double sv = Double.parseDouble(volumeValueEditText.getText().toString()) / 100;
                            Log.w("SV", sv + "");

                            double eps = Double.parseDouble(thicknessEditText.getText().toString());
                            Log.w("EPS", eps + "");

                            double merma = Double.parseDouble(percentageOfLossEditText.getText().toString()) / 100;
                            Log.w("MERMA", merma + "");

                            rt = ((39.4 * sv) / eps * (1 - merma));
                            Log.w("RT", rt + "");


                            Log.w("Area", areaValueEditText.getText().toString());

                            double liters = areaValue / rt;

                            Log.w("Liters", liters + "");

                            double gallons = liters / 3.785;

                            Log.w("Gallons", gallons + "");
                            String finalLiters = String.format("%.2f", liters);
                            String finalGallons = String.format("%.2f", gallons);
                            if (isFirstTime()) {
                                showTeremsAndConditionDialog(finalLiters.replace(",", "."), finalGallons.replace(",", "."));
                            } else {
                                showResult(finalLiters.replace(",", "."), finalGallons.replace(",", "."));
                            }

                        }

                    }

                    break;

                }
        }
    }

    private boolean isFirstTime() {
        SharedPreferences preferences = getActivity().getSharedPreferences(Constants.PREFERENCE_RAN_BEFORE, MODE_PRIVATE);
        boolean ranBefore = preferences.getBoolean("isPolicyAccepted", false);
        if (!ranBefore) {
            // first time
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isPolicyAccepted", true);
            editor.apply();
        }
        return !ranBefore;
    }

    private void showResult(String liters, String gallons) {
        final CalculationResultDialog cdd = new CalculationResultDialog(getActivity(), liters, gallons);
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.show();
        cdd.setCancelable(false);

        Button returnButton = (Button) cdd.findViewById(R.id.returnButton);
        Button newCalculationButton = (Button) cdd.findViewById(R.id.newCalculationButton);

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

    private void showTeremsAndConditionDialog(final String liters, final String gallons) {
        final TermsAndConditionDialog cdd = new TermsAndConditionDialog(getActivity());
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.show();
        cdd.setCancelable(false);

        Button returnButton = (Button) cdd.findViewById(R.id.returnButton);
        Button acceptButton = (Button) cdd.findViewById(R.id.acceptTextView);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cdd.dismiss();
            }
        });

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showResult(liters, gallons);
                cdd.dismiss();
            }
        });
    }

    private void clearAllFields() {
        mt2.setChecked(true);
        areaValueEditText.setText("");
        volumeValueEditText.setText("");
        microSpinner.setSelection(1);
        percentageOfLossEditText.setText("");
        thicknessEditText.setText("");
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(this).attach(this).commit();
            Log.w("Executed 1", "YES");
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
//            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(this).attach(this).commit();
            Log.w("Executed 2", "YES");
        }
    }
}
