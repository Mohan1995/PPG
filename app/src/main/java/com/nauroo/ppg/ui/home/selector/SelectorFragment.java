package com.nauroo.ppg.ui.home.selector;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.nauroo.ppg.R;
import com.nauroo.ppg.model.GenericResponse;
import com.nauroo.ppg.model.SearchResultModel;
import com.nauroo.ppg.network.NetworkAdapter;
import com.nauroo.ppg.network.ResponseCallback;
import com.nauroo.ppg.ui.home.HomeActivity;
import com.nauroo.ppg.ui.home.comparator.SearchResultFragment;
import com.nauroo.ppg.utils.Constants;
import com.nauroo.ppg.utils.InternetAvailability;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SelectorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectorFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.substrateTypeSpinner)
    Spinner substrateTypeSpinner;
    @BindView(R.id.tempratureSpinner)
    Spinner tempratureSpinner;
    @BindView(R.id.environmentalExhibitionSpinner)
    Spinner environmentalExhibitionSpinner;
    @BindView(R.id.durabilityRangeSpinner)
    Spinner durabilityRangeSpinner;

    @BindView(R.id.deletButton)
    Button deletButton;
    @BindView(R.id.calculateButton)
    Button calculateButton;
    List<String> substrateIdList = new ArrayList<>();
    List<String> temperatureIdList = new ArrayList<>();
    List<String> environmentIdList = new ArrayList<>();
    List<String> durabilityIdList = new ArrayList<>();
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.backImageVew)
    ImageView backImageVew;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SelectorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SelectorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectorFragment newInstance(String param1, String param2) {
        SelectorFragment fragment = new SelectorFragment();
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
        View view = inflater.inflate(R.layout.fragment_selector, container, false);
        ButterKnife.bind(this, view);
        HomeActivity activity = (HomeActivity) getActivity();
        activity.hideToolbar();
        try {
            setData();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return view;
    }

    private void setData() {

        //Setup substrate type
        List<String> substrateNameList = new ArrayList<>();
        substrateNameList.add(getString(R.string.type_of_substrate));
        Locale currentLanguage = getResources().getConfiguration().locale;
        for (int i = 0; i < Constants.catalogsModel.getSubstratums().size(); i++) {
            for (int j = 0; j < Constants.catalogsModel.getSubstratums().get(i).getTranslations().size(); j++) {
                if (currentLanguage.getLanguage().toUpperCase().equals(Constants.catalogsModel.getSubstratums().get(i).getTranslations().get(j).getLanguage_id())) {
                    substrateNameList.add(Constants.catalogsModel.getSubstratums().get(i).getTranslations().get(j).getName());
                    substrateIdList.add(Constants.catalogsModel.getSubstratums().get(i).getTranslations().get(j).getSubstratum_id());
                }
            }
        }
        ArrayAdapter<String> substrateAdapter = new ArrayAdapter<String>(getActivity(),
                R.layout.custome_spinner_item, substrateNameList);
        substrateAdapter.setDropDownViewResource(R.layout.custom_spinner_drop_down_item);
        substrateTypeSpinner.setAdapter(substrateAdapter);


        //setup temperature spinner
        List<String> temperatureNameList = new ArrayList<>();
        temperatureNameList.add(getString(R.string.temperature));
        for (int i = 0; i < Constants.catalogsModel.getTemperatures().size(); i++) {
            temperatureNameList.add(Constants.catalogsModel.getTemperatures().get(i).getDescription());
            temperatureIdList.add(Constants.catalogsModel.getTemperatures().get(i).getId());
        }
        ArrayAdapter<String> temperatureAdapter = new ArrayAdapter<String>(getActivity(),
                R.layout.custome_spinner_item, temperatureNameList);
        temperatureAdapter.setDropDownViewResource(R.layout.custom_spinner_drop_down_item);
        tempratureSpinner.setAdapter(temperatureAdapter);


        //Setup environment spinner
        List<String> environmentNameList = new ArrayList<>();
        environmentNameList.add(getString(R.string.enviromental_exhibition));
        for (int i = 0; i < Constants.catalogsModel.getEnvironments().size(); i++) {
            environmentNameList.add(Constants.catalogsModel.getEnvironments().get(i).getDescription());
            environmentIdList.add(Constants.catalogsModel.getEnvironments().get(i).getId());
        }
        ArrayAdapter<String> environmentAdapter = new ArrayAdapter<String>(getActivity(),
                R.layout.custome_spinner_item, environmentNameList);
        environmentAdapter.setDropDownViewResource(R.layout.custom_spinner_drop_down_item);
        environmentalExhibitionSpinner.setAdapter(environmentAdapter);

        //Setup durability spinner
        List<String> durabilityNameList = new ArrayList<>();
        durabilityNameList.add(getString(R.string.durability_range));
        for (int i = 0; i < Constants.catalogsModel.getDurabilities().size(); i++) {
            durabilityNameList.add(Constants.catalogsModel.getDurabilities().get(i).getDescription());
            durabilityIdList.add(Constants.catalogsModel.getDurabilities().get(i).getId());
        }
        ArrayAdapter<String> durailityAdatper = new ArrayAdapter<String>(getActivity(),
                R.layout.custome_spinner_item, durabilityNameList);
        durailityAdatper.setDropDownViewResource(R.layout.custom_spinner_drop_down_item);
        durabilityRangeSpinner.setAdapter(durailityAdatper);


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

    @OnClick({R.id.deletButton, R.id.calculateButton})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.deletButton:
                setInputDataToDefaults();
                break;
            case R.id.calculateButton:
                if (substrateTypeSpinner.getSelectedItemPosition() == 0) {
                    Toast.makeText(getContext(), getString(R.string.please_select_substratum), Toast.LENGTH_SHORT).show();
                } else if (tempratureSpinner.getSelectedItemPosition() == 0) {
                    Toast.makeText(getContext(), getString(R.string.please_select_temprature), Toast.LENGTH_SHORT).show();
                } else if (environmentalExhibitionSpinner.getSelectedItemPosition() == 0) {
                    Toast.makeText(getContext(), getString(R.string.please_select_environmental_exhibition), Toast.LENGTH_SHORT).show();
                } else if (durabilityRangeSpinner.getSelectedItemPosition() == 0) {
                    Toast.makeText(getContext(), getString(R.string.please_select_duarability_range), Toast.LENGTH_SHORT).show();
                } else {
                    if (InternetAvailability.isNetworkAvailable(getContext())) {
                        showProgressBar();
                        String substrateType = substrateIdList.get(substrateTypeSpinner.getSelectedItemPosition() - 1);
                        String temperature = temperatureIdList.get(tempratureSpinner.getSelectedItemPosition() - 1);
                        String environment = environmentIdList.get(environmentalExhibitionSpinner.getSelectedItemPosition() - 1);
                        String durability = durabilityIdList.get(durabilityRangeSpinner.getSelectedItemPosition() - 1);
                        NetworkAdapter.getInstance(getContext()).getSearchResult(substrateType, temperature, environment, durability, Constants.COUNTRY_ID, new ResponseCallback<GenericResponse<List<SearchResultModel>>>(getContext()) {
                            @Override
                            public void onResponse(GenericResponse<List<SearchResultModel>> response) {
                                hideProgressBar();
                                if (response.getStatus() == Constants.RESPONSE_SUCCESS && response.getData().size() != 0 && response.getData() != null) {
                                    moveToSearchResultPage(response.getData());
                                } else {
                                    Toast.makeText(getContext(), getString(R.string.no_item_found), Toast.LENGTH_SHORT).show();
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

                break;
        }
    }

    private void setInputDataToDefaults() {
        substrateTypeSpinner.setSelection(0);
        tempratureSpinner.setSelection(0);
        environmentalExhibitionSpinner.setSelection(0);
        durabilityRangeSpinner.setSelection(0);
    }

    public void moveToSearchResultPage(List<SearchResultModel> searchResultModelList) {
        SearchResultFragment searchResultFragment = SearchResultFragment.newInstance(searchResultModelList);
        HomeActivity instance = (HomeActivity) getActivity();
        instance.addFragment(searchResultFragment, searchResultFragment.getClass().getName(), getString(R.string.comparator_restult_tittle));
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

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
}
