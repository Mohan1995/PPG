package com.nauroo.ppg.ui.home.interactiveguide.mineria;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nauroo.ppg.R;
import com.nauroo.ppg.model.InteractiveGuideModel;
import com.nauroo.ppg.ui.home.interactiveguide.maritimo.MarinoAdapter;
import com.nauroo.ppg.utils.InteractiveGuideAlertDialoge;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MineriaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MineriaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.button1)
    ImageView button1;
    @BindView(R.id.button2)
    ImageView button2;
    @BindView(R.id.button3)
    ImageView button3;
    @BindView(R.id.button4)
    ImageView button4;
    @BindView(R.id.button5)
    ImageView button5;
    @BindView(R.id.tittleTextView)
    TextView tittleTextView;
    int width;
    int height;

    ImageView previouslyClickedButton;
    @BindView(R.id.button6)
    ImageView button6;
    @BindView(R.id.button7)
    ImageView button7;
    @BindView(R.id.button8)
    ImageView button8;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tittleTextView2)
    TextView tittleTextView2;
    @BindView(R.id.recyclerView2)
    RecyclerView recyclerView2;
    @BindView(R.id.titleLayout2)
    LinearLayout titleLayout2;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MineriaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MineriaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MineriaFragment newInstance(String param1, String param2) {
        MineriaFragment fragment = new MineriaFragment();
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
        View view = inflater.inflate(R.layout.fragment_mineria, container, false);
        ButterKnife.bind(this, view);
        hideTitleLayout2();
        showDialoge();
        intializeFirstButtonData();
        return view;
    }

    private void hideTitleLayout2() {
        titleLayout2.setVisibility(View.GONE);
    }

    private void showTitleLayout2(){
        titleLayout2.setVisibility(View.VISIBLE);
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

    private void intializeFirstButtonData() {
        changeButtonAsActive(button3);
        previouslyClickedButton = button3;
        String tittle = getString(R.string.mineria_button_3_tittle);
        changeTittle(tittle);
        List<InteractiveGuideModel> interactiveGuideModelList = new ArrayList<>();
        InteractiveGuideModel interactiveGuideModel1 = new InteractiveGuideModel();
        interactiveGuideModel1.setHeader(getString(R.string.mineria_button_3_header_1));
        interactiveGuideModel1.setBody(getString(R.string.mineria_button_3_header_1_body));
        interactiveGuideModelList.add(interactiveGuideModel1);

        InteractiveGuideModel interactiveGuideModel12 = new InteractiveGuideModel();
        interactiveGuideModel12.setHeader(getString(R.string.mineria_button_3_header_2));
        interactiveGuideModel12.setBody(getString(R.string.mineria_button_3_header_2_body));
        interactiveGuideModelList.add(interactiveGuideModel12);
        setUpRecyclerView(interactiveGuideModelList);
    }

    @OnClick({R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                hideTitleLayout2();
                if (previouslyClickedButton != button1) {
                    changeButtonAsActive(button1);
                    if (previouslyClickedButton != null)
                        changeButtonAsUnActive(previouslyClickedButton);
                    previouslyClickedButton = button1;
                    String tittle = getString(R.string.mineria_button_1_tittle);
                    changeTittle(tittle);
                    List<InteractiveGuideModel> interactiveGuideModelList = new ArrayList<>();
                    InteractiveGuideModel interactiveGuideModel1 = new InteractiveGuideModel();
                    interactiveGuideModel1.setHeader(getString(R.string.mineria_button_1_header_1));
                    interactiveGuideModel1.setBody(getString(R.string.mineria_button_1_header_1_body));
                    interactiveGuideModelList.add(interactiveGuideModel1);

                    InteractiveGuideModel interactiveGuideModel12 = new InteractiveGuideModel();
                    interactiveGuideModel12.setHeader(getString(R.string.mineria_button_1_header_2));
                    interactiveGuideModel12.setBody(getString(R.string.mineria_button_1_header_2_body));
                    interactiveGuideModelList.add(interactiveGuideModel12);

                    setUpRecyclerView(interactiveGuideModelList);
                }
                break;
            case R.id.button2:
                hideTitleLayout2();
                if (previouslyClickedButton != button2) {
                    changeButtonAsActive(button2);
                    if (previouslyClickedButton != null)
                        changeButtonAsUnActive(previouslyClickedButton);
                    previouslyClickedButton = button2;
                    String tittle = getString(R.string.mineria_button_2_tittle);
                    changeTittle(tittle);
                    List<InteractiveGuideModel> interactiveGuideModelList = new ArrayList<>();
                    InteractiveGuideModel interactiveGuideModel1 = new InteractiveGuideModel();
                    interactiveGuideModel1.setHeader(getString(R.string.mineria_button_2_header));
                    interactiveGuideModel1.setBody(getString(R.string.mineria_button_2_body));
                    interactiveGuideModelList.add(interactiveGuideModel1);

                    setUpRecyclerView(interactiveGuideModelList);
                }
                break;
            case R.id.button3:
                hideTitleLayout2();
                if (previouslyClickedButton != button3) {
                    changeButtonAsActive(button3);
                    if (previouslyClickedButton != null)
                        changeButtonAsUnActive(previouslyClickedButton);
                    previouslyClickedButton = button3;
                    String tittle = getString(R.string.mineria_button_3_tittle);
                    changeTittle(tittle);
                    List<InteractiveGuideModel> interactiveGuideModelList = new ArrayList<>();
                    InteractiveGuideModel interactiveGuideModel1 = new InteractiveGuideModel();
                    interactiveGuideModel1.setHeader(getString(R.string.mineria_button_3_header_1));
                    interactiveGuideModel1.setBody(getString(R.string.mineria_button_3_header_1_body));
                    interactiveGuideModelList.add(interactiveGuideModel1);

                    InteractiveGuideModel interactiveGuideModel12 = new InteractiveGuideModel();
                    interactiveGuideModel12.setHeader(getString(R.string.mineria_button_3_header_2));
                    interactiveGuideModel12.setBody(getString(R.string.mineria_button_3_header_2_body));
                    interactiveGuideModelList.add(interactiveGuideModel12);
                    setUpRecyclerView(interactiveGuideModelList);
                }
                break;
            case R.id.button4:
                hideTitleLayout2();
                if (previouslyClickedButton != button4) {
                    changeButtonAsActive(button4);
                    if (previouslyClickedButton != null)
                        changeButtonAsUnActive(previouslyClickedButton);
                    previouslyClickedButton = button4;
                    String tittle = getString(R.string.mineria_button_4_tittle);
                    changeTittle(tittle);
                    List<InteractiveGuideModel> interactiveGuideModelList = new ArrayList<>();
                    InteractiveGuideModel interactiveGuideModel1 = new InteractiveGuideModel();
                    interactiveGuideModel1.setHeader(getString(R.string.mineria_button_4_header_1));
                    interactiveGuideModel1.setBody(getString(R.string.mineria_button_4_header_1_body));
                    interactiveGuideModelList.add(interactiveGuideModel1);

                    InteractiveGuideModel interactiveGuideModel12 = new InteractiveGuideModel();
                    interactiveGuideModel12.setHeader(getString(R.string.mineria_button_4_header_2));
                    interactiveGuideModel12.setBody(getString(R.string.mineria_button_4_header_2_body));
                    interactiveGuideModelList.add(interactiveGuideModel12);

                    setUpRecyclerView(interactiveGuideModelList);
                }
                break;
            case R.id.button5:
                hideTitleLayout2();
                if (previouslyClickedButton != button5) {
                    changeButtonAsActive(button5);
                    if (previouslyClickedButton != null)
                        changeButtonAsUnActive(previouslyClickedButton);
                    previouslyClickedButton = button5;
                    String tittle = getString(R.string.mineria_button_5_tittle);
                    changeTittle(tittle);

                    List<InteractiveGuideModel> interactiveGuideModelList = new ArrayList<>();
                    InteractiveGuideModel interactiveGuideModel1 = new InteractiveGuideModel();
                    interactiveGuideModel1.setHeader(getString(R.string.mineria_button_5_header));
                    interactiveGuideModel1.setBody(getString(R.string.mineria_button_5_body_1));
                    interactiveGuideModelList.add(interactiveGuideModel1);
                    setUpRecyclerView(interactiveGuideModelList);
                }
                break;
            case R.id.button6:
                hideTitleLayout2();
                if (previouslyClickedButton != button6) {
                    changeButtonAsActive(button6);
                    if (previouslyClickedButton != null)
                        changeButtonAsUnActive(previouslyClickedButton);
                    previouslyClickedButton = button6;
                    String tittle = getString(R.string.mineria_button_6_tittle);
                    changeTittle(tittle);
                    List<InteractiveGuideModel> interactiveGuideModelList = new ArrayList<>();
                    InteractiveGuideModel interactiveGuideModel1 = new InteractiveGuideModel();
                    interactiveGuideModel1.setHeader(getString(R.string.mineria_button_6_header));
                    interactiveGuideModel1.setBody(getString(R.string.mineria_button_6_body));
                    interactiveGuideModelList.add(interactiveGuideModel1);
                    setUpRecyclerView(interactiveGuideModelList);
                }
                break;
            case R.id.button7:
               showTitleLayout2();
                if (previouslyClickedButton != button7) {
                    changeButtonAsActive(button7);
                    if (previouslyClickedButton != null)
                        changeButtonAsUnActive(previouslyClickedButton);
                    previouslyClickedButton = button7;
                    String tittle = getString(R.string.mineria_button_7_tittle);
                    changeTittle(tittle);
                    List<InteractiveGuideModel> interactiveGuideModelList = new ArrayList<>();
                    InteractiveGuideModel interactiveGuideModel1 = new InteractiveGuideModel();
                    interactiveGuideModel1.setHeader(getString(R.string.mineria_button_7_header_1));
                    interactiveGuideModel1.setBody(getString(R.string.mineria_button_7_header_1_body));
                    interactiveGuideModelList.add(interactiveGuideModel1);

                    InteractiveGuideModel interactiveGuideModel12 = new InteractiveGuideModel();
                    interactiveGuideModel12.setHeader(getString(R.string.mineria_button_7_header_2));
                    interactiveGuideModel12.setBody(getString(R.string.mineria_button_7_header_2_body));
                    interactiveGuideModelList.add(interactiveGuideModel12);

                    setUpRecyclerView(interactiveGuideModelList);


                    String tittle2 = getString(R.string.mineria_button_7_tittle_2);
                    changeTittle2(tittle2);
                    List<InteractiveGuideModel> interactiveGuideModelList2 = new ArrayList<>();
                    InteractiveGuideModel interactiveGuideModel21 = new InteractiveGuideModel();
                    interactiveGuideModel21.setHeader(getString(R.string.mineria_button_7_2_header_1));
                    interactiveGuideModel21.setBody(getString(R.string.mineria_button_7_2_header_1_body));
                    interactiveGuideModelList2.add(interactiveGuideModel21);

                    InteractiveGuideModel interactiveGuideModel22 = new InteractiveGuideModel();
                    interactiveGuideModel22.setHeader(getString(R.string.mineria_button_7_2_header_2));
                    interactiveGuideModel22.setBody(getString(R.string.mineria_button_7_2_header_2_body));
                    interactiveGuideModelList2.add(interactiveGuideModel22);


                    InteractiveGuideModel interactiveGuideModel23 = new InteractiveGuideModel();
                    interactiveGuideModel23.setHeader(getString(R.string.mineria_button_7_2_header_3));
                    interactiveGuideModel23.setBody(getString(R.string.mineria_button_7_2_header_3_body));
                    interactiveGuideModelList2.add(interactiveGuideModel23);

                    setUpRecyclerView2(interactiveGuideModelList2);
                }
                break;
            case R.id.button8:
                hideTitleLayout2();
                if (previouslyClickedButton != button8) {
                    changeButtonAsActive(button8);
                    if (previouslyClickedButton != null)
                        changeButtonAsUnActive(previouslyClickedButton);
                    previouslyClickedButton = button8;
                    String tittle = getString(R.string.mineria_button_8_tittle);
                    changeTittle(tittle);
                    List<InteractiveGuideModel> interactiveGuideModelList = new ArrayList<>();
                    InteractiveGuideModel interactiveGuideModel1 = new InteractiveGuideModel();
                    interactiveGuideModel1.setHeader(getString(R.string.mineria_button_8_header_1));
                    interactiveGuideModel1.setBody(getString(R.string.mineria_button_8_header_body_1));
                    interactiveGuideModelList.add(interactiveGuideModel1);

                    setUpRecyclerView(interactiveGuideModelList);
                }
                break;

        }
    }

    private void setUpRecyclerView(List<InteractiveGuideModel> interactiveGuideModelList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        MarinoAdapter marinoAdapter = new MarinoAdapter(getContext(), interactiveGuideModelList);
        recyclerView.setAdapter(marinoAdapter);
    }

    private void setUpRecyclerView2(List<InteractiveGuideModel> interactiveGuideModelList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView2.setLayoutManager(layoutManager);
        recyclerView2.setNestedScrollingEnabled(false);
        MarinoAdapter marinoAdapter = new MarinoAdapter(getContext(), interactiveGuideModelList);
        recyclerView2.setAdapter(marinoAdapter);
    }

    private void changeTittle(String tittle) {
        tittleTextView.setText(tittle);
    }

    private void changeTittle2(String tittle) {
        tittleTextView2.setText(tittle);
    }
    private void changeButtonAsActive(ImageView button) {
        button.setImageResource(R.drawable.active_round_button_1);
        if (getResources().getBoolean(R.bool.portrait_only)) {
            width = (int) getResources().getDimension(R.dimen.active_button_size_1);
            height = (int) getResources().getDimension(R.dimen.active_button_size_1);
        } else {
            width = (int) getResources().getDimension(R.dimen.active_button_size_tablet_1);
            height = (int) getResources().getDimension(R.dimen.active_button_size_tablet_1);
        }
        button.getLayoutParams().height = height;
        button.getLayoutParams().width = width;
        button.requestLayout();
    }

    private void changeButtonAsUnActive(ImageView button) {
        button.setImageResource(R.drawable.un_active_round_button_1);
        if (getResources().getBoolean(R.bool.portrait_only)) {
            width = (int) getResources().getDimension(R.dimen.un_active_button_size_1);
            height = (int) getResources().getDimension(R.dimen.un_active_button_size_1);
        } else {
            width = (int) getResources().getDimension(R.dimen.un_active_button_size_tablet_1);
            height = (int) getResources().getDimension(R.dimen.un_active_button_size_tablet_1);
        }
        button.getLayoutParams().height = height;
        button.getLayoutParams().width = width;
        button.requestLayout();
    }

    private void showDialoge() {
        String content = getString(R.string.interactive_guide_dialoge_content);
        final InteractiveGuideAlertDialoge cdd = new InteractiveGuideAlertDialoge(getActivity(), content);
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.show();
        cdd.setCancelable(false);

        TextView accpetButton = (TextView) cdd.findViewById(R.id.acceptTextView);

        accpetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cdd.dismiss();
            }
        });

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
