package com.nauroo.ppg.ui.home.comparator;

import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.nauroo.ppg.R;
import com.nauroo.ppg.model.ComparatorResultModel;
import com.nauroo.ppg.ui.home.HomeActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ComparatorResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ComparatorResultFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.filterSpinner)
    Spinner filterSpinner;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    List<String> filterList = new ArrayList<>();
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    String tittle;
    @BindView(R.id.tittleTextView)
    TextView tittleTextView;
    // TODO: Rename and change types of parameters
    private List<ComparatorResultModel> comparatorResultModelList;
    private List<ComparatorResultModel> firstHalfList;
    private List<ComparatorResultModel> secondHalfList;


    private OnFragmentInteractionListener mListener;

    public ComparatorResultFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ComparatorResultFragment newInstance(List<ComparatorResultModel> comparatorResultModelList, String title) {
        ComparatorResultFragment fragment = new ComparatorResultFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PARAM1, (ArrayList<? extends Parcelable>) comparatorResultModelList);
        args.putString(ARG_PARAM2, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        HomeActivity activity = (HomeActivity) getActivity();
        activity.hideToolbar();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            comparatorResultModelList = getArguments().getParcelableArrayList(ARG_PARAM1);
            tittle = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_comparator_result, container, false);
        ButterKnife.bind(this, view);
        showProgressBar();
        tittleTextView.setText(tittle);
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                setUpRecyclerView();
                setUpSpinner();
                hideProgressBar();
            }
        });

        filterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (filterSpinner.getSelectedItemPosition() != 0) {
                    setUpRecyclerView(comparatorResultModelList.get(filterSpinner.getSelectedItemPosition() - 1));
                } else {
                    showProgressBar();
                    setUpRecyclerView();
                    hideProgressBar();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }

    private void setUpRecyclerView(ComparatorResultModel comparatorResultModel) {
        List<ComparatorResultModel> finalResult = new ArrayList<>();
        finalResult.add(comparatorResultModel);
        ComparatorResultAdapter comparatorResultAdapter = new ComparatorResultAdapter(getActivity(), finalResult);
        recyclerView.setAdapter(comparatorResultAdapter);
    }

    private void setUpSpinner() {
        filterList.add(getString(R.string.apply_filters));
        for (int i = 0; i < comparatorResultModelList.size(); i++) {
            filterList.add(comparatorResultModelList.get(i).getCode());
        }
        ArrayAdapter<String> durailityAdatper = new ArrayAdapter<String>(getActivity(),
                R.layout.custome_spinner_item, filterList);
        durailityAdatper.setDropDownViewResource(R.layout.custom_spinner_drop_down_item);
        filterSpinner.setAdapter(durailityAdatper);
    }

    private void setUpRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        ComparatorResultAdapter comparatorResultAdapter = new ComparatorResultAdapter(getActivity(), comparatorResultModelList);
        recyclerView.setAdapter(comparatorResultAdapter);

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

    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
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
