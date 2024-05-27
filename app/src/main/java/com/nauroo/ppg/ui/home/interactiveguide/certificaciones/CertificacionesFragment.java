package com.nauroo.ppg.ui.home.interactiveguide.certificaciones;

import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nauroo.ppg.R;
import com.nauroo.ppg.utils.Constants;

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
 * Use the {@link CertificacionesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CertificacionesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.certificationsRecyclerView)
    RecyclerView certificationsRecyclerView;
    @BindView(R.id.backImageVew)
    ImageView backImageVew;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CertificacionesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CertificacionesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CertificacionesFragment newInstance(String param1, String param2) {
        CertificacionesFragment fragment = new CertificacionesFragment();
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
        View view = inflater.inflate(R.layout.fragment_certificaciones, container, false);
        ButterKnife.bind(this, view);
        setUpRecyclerView();
        return view;
    }


    private void setUpRecyclerView() {
        List<String> interactiveGuideList = new ArrayList<>();
        interactiveGuideList.add(getString(R.string.aviation_fuel));
        interactiveGuideList.add(getString(R.string.cellulosic_pfp));
        interactiveGuideList.add(getString(R.string.d8500_02));
        interactiveGuideList.add(getString(R.string.food));
        interactiveGuideList.add(getString(R.string.hydrocarbon_pfp));
        interactiveGuideList.add(getString(R.string.IMO_PSPC_COT));
        interactiveGuideList.add(getString(R.string.IMO_PSPC_WBT));
        interactiveGuideList.add(getString(R.string.ISO12944_6));
        interactiveGuideList.add(getString(R.string.ISO_20340));
        interactiveGuideList.add(getString(R.string.LOW_FLAME_SPREAD_CHARACTERISTICS));
       // interactiveGuideList.add(getString(R.string.LOW_FLAME_SPREAD_CHARACTERISTICS_BD));
        interactiveGuideList.add(getString(R.string.NORSOK_M_501));
        interactiveGuideList.add(getString(R.string.NRF_053));
        interactiveGuideList.add(getString(R.string.NRF_295));
        interactiveGuideList.add(getString(R.string.POTABLE_WATER));
        interactiveGuideList.add(getString(R.string.RECOGNIZED_ANTIFOULING_PAINT));
        interactiveGuideList.add(getString(R.string.RECOGNIZED_CORROSION_CONTROL_COATING));
        interactiveGuideList.add(getString(R.string.Slip_B_Coefficient));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        certificationsRecyclerView.setLayoutManager(layoutManager);
        certificationsRecyclerView.setNestedScrollingEnabled(false);
        CertificationsAdapter interactiveGuildRecyclerViewAdapter = new CertificationsAdapter(getActivity(), interactiveGuideList);
        certificationsRecyclerView.setAdapter(interactiveGuildRecyclerViewAdapter);
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

    @OnClick(R.id.backImageVew)
    public void onClick() {
        getActivity().getSupportFragmentManager().popBackStack();
        Log.w("back stack count", +getActivity().getSupportFragmentManager().getBackStackEntryCount() + "");
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
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }
}
