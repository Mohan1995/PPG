package com.nauroo.ppg.ui.home.interactiveguide;

import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nauroo.ppg.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InteractiveGuideFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InteractiveGuideFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.interactiveGuideRecyclerView)
    RecyclerView interactiveGuideRecyclerView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public InteractiveGuideFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InteractiveGuideFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InteractiveGuideFragment newInstance(String param1, String param2) {
        InteractiveGuideFragment fragment = new InteractiveGuideFragment();
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
        View view = inflater.inflate(R.layout.fragment_interactive_guide, container, false);
        ButterKnife.bind(this, view);
        setUpRecyclerView();
        return view;
    }

    private void setUpRecyclerView() {
        List<String> interactiveGuideList=new ArrayList<>();

        interactiveGuideList.add(getString(R.string.mining_6));
        interactiveGuideList.add(getString(R.string.maritime_4));
        interactiveGuideList.add(getString(R.string.oil_gas_2));
        interactiveGuideList.add(getString(R.string.railway_industry_5));
        interactiveGuideList.add(getString(R.string.pisos));
        interactiveGuideList.add(getString(R.string.energy_generatoin));




//        interactiveGuideList.add(getString(R.string.mining_6));
//        interactiveGuideList.add(getString(R.string.food_and_drinks_7));
//        interactiveGuideList.add(getString(R.string.certifications_8));
//        interactiveGuideList.add(getString(R.string.pisos));

        interactiveGuideList.add(getString(R.string.structures));
        interactiveGuideList.add(getString(R.string.pfp));
        interactiveGuideList.add(getString(R.string.profesional_protection));
        interactiveGuideList.add(getString(R.string.water_treatment));
        interactiveGuideList.add(getString(R.string.water_treatment_2));
        interactiveGuideList.add(getString(R.string.refinery));
        interactiveGuideList.add(getString(R.string.marine_platform));
        interactiveGuideList.add(getString(R.string.industrial_ship));
        interactiveGuideList.add(getString(R.string.hydroeletric_plants));
        interactiveGuideList.add(getString(R.string.certifications));

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        interactiveGuideRecyclerView.setLayoutManager(layoutManager);
        interactiveGuideRecyclerView.setNestedScrollingEnabled(false);
        InteractiveGuildRecyclerViewAdapter interactiveGuildRecyclerViewAdapter=new InteractiveGuildRecyclerViewAdapter(getActivity(),interactiveGuideList);
        interactiveGuideRecyclerView.setAdapter(interactiveGuildRecyclerViewAdapter);
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
