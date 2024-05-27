package com.nauroo.ppg.ui.home.comparator;

import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.nauroo.ppg.R;
import com.nauroo.ppg.model.CategoryModel;
import com.nauroo.ppg.model.ComparatorResultModel;
import com.nauroo.ppg.model.GenericResponse;
import com.nauroo.ppg.model.ManufacturerModel;
import com.nauroo.ppg.network.NetworkAdapter;
import com.nauroo.ppg.network.ResponseCallback;
import com.nauroo.ppg.ui.home.HomeActivity;
import com.nauroo.ppg.utils.Constants;
import com.nauroo.ppg.utils.CustomSpinnerAdapter;
import com.nauroo.ppg.utils.InternetAvailability;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ComparatorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ComparatorFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.manufacturerNameSpinner)
    Spinner manufacturerNameSpinner;
    @BindView(R.id.termAndConditionTextView)
    TextView termAndConditionTextView;
    @BindView(R.id.compareButton)
    Button compareButton;
    @BindView(R.id.checkbox)
    CheckBox checkbox;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    List<ManufacturerModel> manufacturerModelList;
    int check = 0;
    List<CategoryModel> categoryModelList;

    List<CategoryModel> subCategoryModelList;
    int check1 = 0;
    @BindView(R.id.backImageVew)
    ImageView backImageVew;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ComparatorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ComparatorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ComparatorFragment newInstance(String param1, String param2) {
        ComparatorFragment fragment = new ComparatorFragment();
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
        View view = inflater.inflate(R.layout.fragment_comparator, container, false);
        ButterKnife.bind(this, view);
        HomeActivity activity = (HomeActivity) getActivity();
        activity.hideToolbar();
        String first = getString(R.string.i_agree_with_the);
        String second = " " + getString(R.string.terms_and_conditions);
        String next = "<font color='#3dabd0'><u>" + second + "</u></font>";
        termAndConditionTextView.setText(Html.fromHtml(first + next));
        getManufacturer();

        manufacturerNameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (++check > 1) {
                    if (manufacturerNameSpinner.getSelectedItemPosition() > 0) {
                        // getCategory();

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }

//    private void getCategory() {
//        showProgressBar();
//        NetworkAdapter.getInstance(getContext()).getCategory(
//                manufacturerModelList.get(manufacturerNameSpinner.getSelectedItemPosition() - 1).getId(),
//                new ResponseCallback<GenericResponse<List<CategoryModel>>>(getContext()) {
//                    @Override
//                    public void onResponse(GenericResponse<List<CategoryModel>> response) {
//                        hideProgressBar();
//                        if (response.getStatus() == Constants.RESPONSE_SUCCESS) {
//                            categoryModelList = response.getData();
//                            List<String> subCategoryNameList = new ArrayList<String>();
//                            subCategoryNameList.add(getString(R.string.category));
//                            for (int i = 0; i < categoryModelList.size(); i++) {
//                                subCategoryNameList.add(categoryModelList.get(i).getName());
//                            }
//                            ArrayAdapter<String> temperatureAdapter = new ArrayAdapter<String>(getContext(),
//                                    R.layout.custome_spinner_item, subCategoryNameList);
//                            temperatureAdapter.setDropDownViewResource(R.layout.custom_spinner_drop_down_item);
//                            categorySpinner.setAdapter(temperatureAdapter);
//                        }
//                    }
//
//                    @Override
//                    public void onFailure() {
//
//                    }
//                });
//    }

//    private void getSubCategory() {
//        showProgressBar();
//        NetworkAdapter.getInstance(getContext()).getSubCategory(
//                categoryModelList.get(categorySpinner.getSelectedItemPosition() - 1).getId(),
//                new ResponseCallback<GenericResponse<List<CategoryModel>>>(getContext()) {
//                    @Override
//                    public void onResponse(GenericResponse<List<CategoryModel>> response) {
//                        hideProgressBar();
//                        if (response.getStatus() == Constants.RESPONSE_SUCCESS) {
//                            if (response.getData().size() == 0 || response.getData() == null) {
//                                subCategorySpinner.setVisibility(View.GONE);
//                            } else {
//                                subCategorySpinner.setVisibility(View.VISIBLE);
//                                subCategoryModelList = response.getData();
//                                List<String> subCategoryNameList = new ArrayList<String>();
//                                subCategoryNameList.add(getString(R.string.subcategory));
//                                for (int i = 0; i < subCategoryModelList.size(); i++) {
//                                    subCategoryNameList.add(subCategoryModelList.get(i).getName());
//                                }
//                                ArrayAdapter<String> temperatureAdapter = new ArrayAdapter<String>(getContext(),
//                                        R.layout.custome_spinner_item, subCategoryNameList);
//                                temperatureAdapter.setDropDownViewResource(R.layout.custom_spinner_drop_down_item);
//                                subCategorySpinner.setAdapter(temperatureAdapter);
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onFailure() {
//
//                    }
//                });
//    }


    private void getManufacturer() {
        showProgressBar();
        if (InternetAvailability.isNetworkAvailable(getContext())) {
            NetworkAdapter.getInstance(getContext()).getManufacturer(new ResponseCallback<GenericResponse<List<ManufacturerModel>>>(getContext()) {
                @Override
                public void onResponse(GenericResponse<List<ManufacturerModel>> response) {
                    hideProgressBar();
                    if (isAdded()) {
                        if (response.getStatus() == Constants.RESPONSE_SUCCESS) {
                            manufacturerModelList = response.getData();
                            List<String> manufacturerNameList = new ArrayList<String>();
                            manufacturerNameList.add(getString(R.string.manufacturer));
                            for (int i = 0; i < manufacturerModelList.size(); i++) {
                                manufacturerNameList.add(manufacturerModelList.get(i).getName());
                            }
//                            ArrayAdapter<String> temperatureAdapter = new ArrayAdapter<String>(getContext(),
//                                    R.layout.custom_spinner_item1, manufacturerNameList);
                            CustomSpinnerAdapter dataAdapter = new CustomSpinnerAdapter(getContext(), R.layout.custom_spinner_item1, manufacturerNameList, 0);
                            dataAdapter.setDropDownViewResource(R.layout.custom_spinner_drop_down_item1);
                            manufacturerNameSpinner.setAdapter(dataAdapter);
                        }
                    }
                }

                @Override
                public void onFailure() {

                }
            });
        } else {
            Toast.makeText(getContext(), getString(R.string.internet_is_not_available), Toast.LENGTH_SHORT).show();
        }
    }

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

    @OnClick({R.id.termAndConditionTextView, R.id.compareButton})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.termAndConditionTextView:
                HomeActivity activity = (HomeActivity) getActivity();
                TermsAndConditionFragment termsAndConditionFragment = new TermsAndConditionFragment();
                activity.addFragment(termsAndConditionFragment, termsAndConditionFragment.getClass().getName(), getString(R.string.terms_and_conditions_tittle));
                break;
            case R.id.compareButton:
                if (manufacturerNameSpinner.getSelectedItemPosition() == 0) {
                    Toast.makeText(getContext(), getString(R.string.please_select_manufacturer), Toast.LENGTH_SHORT).show();
                } else if (!checkbox.isChecked()) {
                    Toast.makeText(getContext(), getString(R.string.please_agree_the_terms_and_condition), Toast.LENGTH_SHORT).show();
                } else {
                    getCompetitor();
                }
                break;

        }
    }

    private void getCompetitor() {
        if (InternetAvailability.isNetworkAvailable(getContext())) {
            showProgressBar();
            NetworkAdapter.getInstance(getContext()).getCompetitor(manufacturerModelList.get(manufacturerNameSpinner.getSelectedItemPosition() - 1).getId(), new ResponseCallback<GenericResponse<List<ComparatorResultModel>>>(getContext()) {
                @Override
                public void onResponse(GenericResponse<List<ComparatorResultModel>> response) {
                    if (response.getStatus() == Constants.RESPONSE_SUCCESS) {
                        if (isAdded()) {
                            moveToResultPage(response.getData());
                            hideProgressBar();
                        }

                    }

                }

                @Override
                public void onFailure() {


                }
            });
        } else {
            Toast.makeText(getContext(), getString(R.string.internet_is_not_available), Toast.LENGTH_SHORT).show();
        }
    }

//    private void compareWithSubCategory() {
//        showProgressBar();
//        NetworkAdapter.getInstance(getContext()).getComparator(
//                manufacturerModelList.get(manufacturerNameSpinner.getSelectedItemPosition() - 1).getId(),
//                categoryModelList.get(categorySpinner.getSelectedItemPosition() - 1).getId(), subCategoryModelList.get(subCategorySpinner.getSelectedItemPosition() - 1).getId(), new ResponseCallback<GenericResponse<List<ComparatorResultModel>>>(getContext()) {
//                    @Override
//                    public void onResponse(GenericResponse<List<ComparatorResultModel>> response) {
//                        hideProgressBar();
//                        if (response.getStatus() == Constants.RESPONSE_SUCCESS) {
//                            moveToResultPage(response.getData());
//                        }
//                    }
//
//                    @Override
//                    public void onFailure() {
//
//                    }
//                });
//
//    }

    private void moveToResultPage(List<ComparatorResultModel> data) {
        ComparatorResultFragment comparatorResultFragment = ComparatorResultFragment.newInstance(data,manufacturerModelList.get(manufacturerNameSpinner.getSelectedItemPosition()-1).getName());
        HomeActivity instance = (HomeActivity) getActivity();
        instance.addFragment(comparatorResultFragment, comparatorResultFragment.getClass().getName(), getString(R.string.comparator_restult_tittle));
    }

//    private void compareWithoutSubCategory() {
//        showProgressBar();
//        NetworkAdapter.getInstance(getContext()).getComparatorResult(
//                manufacturerModelList.get(manufacturerNameSpinner.getSelectedItemPosition() - 1).getId(),
//                categoryModelList.get(categorySpinner.getSelectedItemPosition() - 1).getId(), new ResponseCallback<GenericResponse<List<ComparatorResultModel>>>(getContext()) {
//                    @Override
//                    public void onResponse(GenericResponse<List<ComparatorResultModel>> response) {
//                        hideProgressBar();
//                        if (response.getStatus() == Constants.RESPONSE_SUCCESS) {
//                            moveToResultPage(response.getData());
//                        }
//                    }
//
//                    @Override
//                    public void onFailure() {
//
//                    }
//                });
//    }

    @OnClick(R.id.backImageVew)
    public void onClick() {
        if (getActivity().getSupportFragmentManager().getBackStackEntryCount() == 0) {
            //Finish Activity
            // finish();
        } else {
            getActivity().getSupportFragmentManager().popBackStack();
            if (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 1) {
                if (Constants.TITTLES.size() > 0) {
                    Constants.TITTLES.remove(Constants.TITTLES.size() - 1);
                    try {
                    } catch (ArrayIndexOutOfBoundsException e) {
                        e.printStackTrace();

                    }
                } else {

                }

            } else {
                Constants.TITTLES.clear();
            }

        }
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

    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }
}
