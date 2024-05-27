package com.nauroo.ppg.ui.home.comparator;

import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;

import com.nauroo.ppg.R;
import com.nauroo.ppg.model.SearchResultModel;
import com.nauroo.ppg.ui.home.HomeActivity;
import com.nauroo.ppg.utils.ExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SearchResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchResultFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    List<SearchResultModel> searchResultModelList;
    @BindView(R.id.expandableListView)
    ExpandableListView expandableListView;
    List<String> subCategory;
    List<String> filterList;
    @BindView(R.id.filterSpinner)
    Spinner filterSpinner;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SearchResultFragment() {
        // Required empty public constructor
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        HomeActivity activity=(HomeActivity)getActivity();
        activity.hideToolbar();
    }

    // TODO: Rename and change types and number of parameters
    public static SearchResultFragment newInstance(List<SearchResultModel> searchResultModelList) {
        SearchResultFragment fragment = new SearchResultFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PARAM1, (ArrayList<? extends Parcelable>) searchResultModelList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            searchResultModelList = getArguments().getParcelableArrayList(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_result, container, false);
        ButterKnife.bind(this, view);
        expandableListView.setChildDivider(ContextCompat.getDrawable(getActivity(), android.R.color.transparent));
        expandableListView.setDivider(ContextCompat.getDrawable(getActivity(), android.R.color.transparent));
        setUpExpandablelistView(searchResultModelList);
        setUpSpinner();
        filterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(filterSpinner.getSelectedItemPosition()!=0){
                setUpExpandablelistView(searchResultModelList.get(filterSpinner.getSelectedItemPosition()-1));
                }else {
                    setUpExpandablelistView(searchResultModelList);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return view;
    }

    private void setUpSpinner() {
        ArrayAdapter<String> durailityAdatper = new ArrayAdapter<String>(getActivity(),
                R.layout.custome_spinner_item, filterList);
        durailityAdatper.setDropDownViewResource(R.layout.custom_spinner_drop_down_item);
        filterSpinner.setAdapter(durailityAdatper);
    }

    private void setUpExpandablelistView(List<SearchResultModel> searchResultModelList) {
        Locale currentLanguage = getResources().getConfiguration().locale;
        subCategory = new ArrayList<>();
        filterList=new ArrayList<>();
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();
        filterList.add(getString(R.string.apply_filters));
        for (int i = 0; i < searchResultModelList.size(); i++) {
            filterList.add(searchResultModelList.get(i).getCode());
            for (int j = 0; j < searchResultModelList.get(i).getSubproducts().size(); j++) {
                subCategory.add(searchResultModelList.get(i).getSubproducts().get(j).getName());

            }
            for (int k = 0; k < searchResultModelList.get(i).getTranslations().size(); k++) {

                if (!currentLanguage.getLanguage().toUpperCase().equals(searchResultModelList.get(i).getTranslations().get(k).getLanguage_id())) {
                    searchResultModelList.get(i).getTranslations().remove(k);


                }
            }
            expandableListDetail.put(searchResultModelList.get(i).getTranslations().get(0).getName(), subCategory);

        }
        List<String> tittles = new ArrayList<String>(expandableListDetail.keySet());
        ExpandableListAdapter expandableListAdapter = new ExpandableListAdapter(getActivity(), tittles, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);


    }

    private void setUpExpandablelistView(SearchResultModel searchResultModelList) {
        Locale currentLanguage = getResources().getConfiguration().locale;
        subCategory = new ArrayList<>();
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();
            for (int j = 0; j < searchResultModelList.getSubproducts().size(); j++) {
                subCategory.add(searchResultModelList.getSubproducts().get(j).getName());

            }
            for (int k = 0; k < searchResultModelList.getTranslations().size(); k++) {
                if (!currentLanguage.getLanguage().toUpperCase().equals(searchResultModelList.getTranslations().get(k).getLanguage_id())) {
                    searchResultModelList.getTranslations().remove(k);
                }
            }
            expandableListDetail.put(searchResultModelList.getTranslations().get(0).getName(), subCategory);

        List<String> tittles = new ArrayList<String>(expandableListDetail.keySet());
        ExpandableListAdapter expandableListAdapter = new ExpandableListAdapter(getActivity(), tittles, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);




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
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }
}
