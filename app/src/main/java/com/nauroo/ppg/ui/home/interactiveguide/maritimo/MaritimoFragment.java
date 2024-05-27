package com.nauroo.ppg.ui.home.interactiveguide.maritimo;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nauroo.ppg.R;
import com.nauroo.ppg.model.InteractiveGuideModel;
import com.nauroo.ppg.utils.InteractiveGuideAlertDialoge;

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
 * Use the {@link MaritimoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MaritimoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.tittleTextView)
    TextView tittleTextView;
//    @BindView(R.id.bodyTextView)
//    TextView bodyTextView;
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
    @BindView(R.id.button6)
    ImageView button6;
    @BindView(R.id.button7)
    ImageView button7;
    @BindView(R.id.button8)
    ImageView button8;
    int width;
    int height;

    ImageView previouslyClickedButton;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MaritimoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MaritimoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MaritimoFragment newInstance(String param1, String param2) {
        MaritimoFragment fragment = new MaritimoFragment();
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
        View view = inflater.inflate(R.layout.fragment_interactive_guide_detail_fragement, container, false);
        ButterKnife.bind(this, view);
        showDialoge();
        intializeFirstButtonData();
        return view;
    }

    private void intializeFirstButtonData() {
        changeButtonAsActive(button1);
        previouslyClickedButton = button1;
        changeTitle(getString(R.string.minario_1_button_tittle));
        List<InteractiveGuideModel> interactiveGuideModelList=new ArrayList<>();
        InteractiveGuideModel interactiveGuideModel1=new InteractiveGuideModel();
        interactiveGuideModel1.setHeader(getString(R.string.minario_1_button_1_tittle));
        interactiveGuideModel1.setBody(getString(R.string.minario_1_button_1_tittle_1_header));
        interactiveGuideModelList.add(interactiveGuideModel1);

        InteractiveGuideModel interactiveGuideModel12=new InteractiveGuideModel();
        interactiveGuideModel12.setHeader(getString(R.string.minario_1_button_2_tittle));
        interactiveGuideModel12.setBody(getString(R.string.minario_1_button_2_tittle_1_header));
        interactiveGuideModelList.add(interactiveGuideModel12);

        InteractiveGuideModel interactiveGuideModel13=new InteractiveGuideModel();
        interactiveGuideModel13.setHeader(getString(R.string.minario_1_button_3_tittle));
        interactiveGuideModel13.setBody(getString(R.string.minario_1_button_3_tittle_1_header));
        interactiveGuideModelList.add(interactiveGuideModel13);
        setUpRecyclerView(interactiveGuideModelList);
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


    @OnClick({R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7,R.id.button8})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button8:
                if (previouslyClickedButton != button8) {
                    changeButtonAsActive(button8);
                    if (previouslyClickedButton != null)
                        changeButtonAsUnActive(previouslyClickedButton);
                    previouslyClickedButton = button8;
                    changeTitle(getString(R.string.marion_8_button_tittle));
                    List<InteractiveGuideModel> interactiveGuideModelList=new ArrayList<>();
                    InteractiveGuideModel interactiveGuideModel1=new InteractiveGuideModel();
                    interactiveGuideModel1.setHeader(getString(R.string.marino_1header_8_button));
                    interactiveGuideModel1.setBody(getString(R.string.marino_1header_8_button_body));
                    interactiveGuideModelList.add(interactiveGuideModel1);

                    InteractiveGuideModel interactiveGuideModel12=new InteractiveGuideModel();
                    interactiveGuideModel12.setHeader(getString(R.string.marino_2header_8_button));
                    interactiveGuideModel12.setBody(getString(R.string.marino_2header_8_button_body));
                    interactiveGuideModelList.add(interactiveGuideModel12);

                    InteractiveGuideModel interactiveGuideModel13=new InteractiveGuideModel();
                    interactiveGuideModel13.setHeader(getString(R.string.marino_3header_8_button));
                    interactiveGuideModel13.setBody(getString(R.string.marino_3header_8_button_body));
                    interactiveGuideModelList.add(interactiveGuideModel13);
                    setUpRecyclerView(interactiveGuideModelList);
                }
                break;
            case R.id.button1:
                if (previouslyClickedButton != button1) {
                    changeButtonAsActive(button1);
                    if (previouslyClickedButton != null)
                        changeButtonAsUnActive(previouslyClickedButton);
                    previouslyClickedButton = button1;
                    changeTitle(getString(R.string.minario_1_button_tittle));
                    List<InteractiveGuideModel> interactiveGuideModelList=new ArrayList<>();
                    InteractiveGuideModel interactiveGuideModel1=new InteractiveGuideModel();
                    interactiveGuideModel1.setHeader(getString(R.string.minario_1_button_1_tittle));
                    interactiveGuideModel1.setBody(getString(R.string.minario_1_button_1_tittle_1_header));
                    interactiveGuideModelList.add(interactiveGuideModel1);

                    InteractiveGuideModel interactiveGuideModel12=new InteractiveGuideModel();
                    interactiveGuideModel12.setHeader(getString(R.string.minario_1_button_2_tittle));
                    interactiveGuideModel12.setBody(getString(R.string.minario_1_button_2_tittle_1_header));
                    interactiveGuideModelList.add(interactiveGuideModel12);

                    InteractiveGuideModel interactiveGuideModel13=new InteractiveGuideModel();
                    interactiveGuideModel13.setHeader(getString(R.string.minario_1_button_3_tittle));
                    interactiveGuideModel13.setBody(getString(R.string.minario_1_button_3_tittle_1_header));
                    interactiveGuideModelList.add(interactiveGuideModel13);
                    setUpRecyclerView(interactiveGuideModelList);
                }
                break;
            case R.id.button2:
                if (previouslyClickedButton != button2) {
                    changeButtonAsActive(button2);
                    if (previouslyClickedButton != null)
                        changeButtonAsUnActive(previouslyClickedButton);
                    previouslyClickedButton = button2;
//                    String tittle = getString(R.string.marino_button_2_tittle);
//                    String description = getString(R.string.marino_button_2_body);
//                    changeContent(tittle, description);
                    changeTitle(getString(R.string.minario_2_button_tittle));
                    List<InteractiveGuideModel> interactiveGuideModelList=new ArrayList<>();
                        InteractiveGuideModel interactiveGuideModel1=new InteractiveGuideModel();
                        interactiveGuideModel1.setHeader(getString(R.string.marino_1header_2_button));
                    interactiveGuideModel1.setBody(getString(R.string.marion_1hear_2_button_body));
                    interactiveGuideModelList.add(interactiveGuideModel1);
                    InteractiveGuideModel interactiveGuideModel2=new InteractiveGuideModel();
                    interactiveGuideModel2.setHeader(getString(R.string.marino_2header_2_button));
                    interactiveGuideModel2.setBody(getString(R.string.marion_2hear_2_button_body));
                    interactiveGuideModelList.add(interactiveGuideModel2);
                    setUpRecyclerView(interactiveGuideModelList);
                }
                break;
            case R.id.button3:
                if (previouslyClickedButton != button3) {
                    changeButtonAsActive(button3);
                    if (previouslyClickedButton != null)
                        changeButtonAsUnActive(previouslyClickedButton);
                    previouslyClickedButton = button3;
                    changeTitle(getString(R.string.minario_3_button_tittle));
                    List<InteractiveGuideModel> interactiveGuideModelList=new ArrayList<>();
                    InteractiveGuideModel interactiveGuideModel1=new InteractiveGuideModel();
                    interactiveGuideModel1.setHeader(getString(R.string.minario_3_button_1_tittle));
                    interactiveGuideModel1.setBody(getString(R.string.minario_3_button_1_body));
                    interactiveGuideModelList.add(interactiveGuideModel1);
                    InteractiveGuideModel interactiveGuideModel2=new InteractiveGuideModel();
                    interactiveGuideModel2.setHeader(getString(R.string.minario_3_button_2_tittle));
                    interactiveGuideModel2.setBody(getString(R.string.minario_3_button_2_body));
                    interactiveGuideModelList.add(interactiveGuideModel2);
                    InteractiveGuideModel interactiveGuideModel3=new InteractiveGuideModel();
                    interactiveGuideModel3.setHeader(getString(R.string.minario_3_button_3_tittle));
                    interactiveGuideModel3.setBody(getString(R.string.minario_3_button_3_body));
                    interactiveGuideModelList.add(interactiveGuideModel3);
                    setUpRecyclerView(interactiveGuideModelList);
                }
                break;
            case R.id.button4:
                if (previouslyClickedButton != button4) {
                    changeButtonAsActive(button4);
                    if (previouslyClickedButton != null)
                        changeButtonAsUnActive(previouslyClickedButton);
                    previouslyClickedButton = button4;
                    changeTitle(getString(R.string.minario_4_button_tittle));
                    List<InteractiveGuideModel> interactiveGuideModelList=new ArrayList<>();
                    InteractiveGuideModel interactiveGuideModel1=new InteractiveGuideModel();
                    interactiveGuideModel1.setHeader(getString(R.string.minario_4_button_1_tittle));
                    interactiveGuideModel1.setBody(getString(R.string.minario_4_button_1_body));
                    interactiveGuideModelList.add(interactiveGuideModel1);
                    InteractiveGuideModel interactiveGuideModel2=new InteractiveGuideModel();
                    interactiveGuideModel2.setHeader(getString(R.string.minario_4_button_2_tittle));
                    interactiveGuideModel2.setBody(getString(R.string.minario_4_button_2_body));
                    interactiveGuideModelList.add(interactiveGuideModel2);
                    setUpRecyclerView(interactiveGuideModelList);
                }
                break;
            case R.id.button5:
                if (previouslyClickedButton != button5) {
                    changeButtonAsActive(button5);
                    if (previouslyClickedButton != null)
                        changeButtonAsUnActive(previouslyClickedButton);
                    previouslyClickedButton = button5;
                    String tittle = getString(R.string.marino_button_5_tittle);
                    changeTitle(tittle);
                    List<InteractiveGuideModel> interactiveGuideModelList=new ArrayList<>();
                    InteractiveGuideModel interactiveGuideModel1=new InteractiveGuideModel();
                    interactiveGuideModel1.setHeader(getString(R.string.minario_5_button_1_tittle));
                    interactiveGuideModel1.setBody(getString(R.string.minario_5_button_1_tittle_body));
                    interactiveGuideModelList.add(interactiveGuideModel1);

                    InteractiveGuideModel interactiveGuideModel12=new InteractiveGuideModel();
                    interactiveGuideModel12.setHeader(getString(R.string.minario_5_button_2_tittle));
                    interactiveGuideModel12.setBody(getString(R.string.minario_5_button_2_tittle_body));
                    interactiveGuideModelList.add(interactiveGuideModel12);

                    InteractiveGuideModel interactiveGuideModel13=new InteractiveGuideModel();
                    interactiveGuideModel13.setHeader(getString(R.string.minario_5_button_3_tittle));
                    interactiveGuideModel13.setBody(getString(R.string.minario_5_button_3_tittle_body));
                    interactiveGuideModelList.add(interactiveGuideModel13);
                    setUpRecyclerView(interactiveGuideModelList);
                }
                break;
            case R.id.button6:
                if (previouslyClickedButton != button6) {
                    changeButtonAsActive(button6);
                    if (previouslyClickedButton != null)
                        changeButtonAsUnActive(previouslyClickedButton);
                    previouslyClickedButton = button6;
                    changeTitle(getString(R.string.minario_6_button_tittle));
                    List<InteractiveGuideModel> interactiveGuideModelList=new ArrayList<>();
                    InteractiveGuideModel interactiveGuideModel1=new InteractiveGuideModel();
                    interactiveGuideModel1.setHeader(getString(R.string.minario_6_button_1_tittle));
                    interactiveGuideModel1.setBody(getString(R.string.minario_6_button_1_body));
                    interactiveGuideModelList.add(interactiveGuideModel1);
                    InteractiveGuideModel interactiveGuideModel2=new InteractiveGuideModel();
                    interactiveGuideModel2.setHeader(getString(R.string.minario_6_button_2_tittle));
                    interactiveGuideModel2.setBody(getString(R.string.minario_6_button_2_body));
                    interactiveGuideModelList.add(interactiveGuideModel2);
                    setUpRecyclerView(interactiveGuideModelList);
                }
                break;
            case R.id.button7:
                if (previouslyClickedButton != button7) {
                    changeButtonAsActive(button7);
                    if (previouslyClickedButton != null)
                        changeButtonAsUnActive(previouslyClickedButton);
                    previouslyClickedButton = button7;
                    changeTitle(getString(R.string.minario_7_button_tittle));
                    List<InteractiveGuideModel> interactiveGuideModelList=new ArrayList<>();
                    InteractiveGuideModel interactiveGuideModel1=new InteractiveGuideModel();
                    interactiveGuideModel1.setHeader(getString(R.string.minario_7_button_1_tittle));
                    interactiveGuideModel1.setBody(getString(R.string.minario_7_button_1_body));
                    interactiveGuideModelList.add(interactiveGuideModel1);
                    InteractiveGuideModel interactiveGuideModel2=new InteractiveGuideModel();
                    interactiveGuideModel2.setHeader(getString(R.string.minario_7_button_2_tittle));
                    interactiveGuideModel2.setBody(getString(R.string.minario_7_button_2_body));
                    interactiveGuideModelList.add(interactiveGuideModel2);
                    setUpRecyclerView(interactiveGuideModelList);
                }
                break;
        }
    }

    private void setUpRecyclerView(List<InteractiveGuideModel> interactiveGuideModelList) {
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        MarinoAdapter marinoAdapter=new MarinoAdapter(getContext(),interactiveGuideModelList);
        recyclerView.setAdapter(marinoAdapter);
    }

    private void changeTitle(String tittle) {
        tittleTextView.setText(tittle);

    }

    private void changeButtonAsActive(ImageView button) {
        button.setImageResource(R.drawable.active_round_button);
        if (getResources().getBoolean(R.bool.portrait_only)) {
            width = (int) getResources().getDimension(R.dimen.active_button_size);
            height = (int) getResources().getDimension(R.dimen.active_button_size);
        } else {
            width = (int) getResources().getDimension(R.dimen.active_button_size_tablet);
            height = (int) getResources().getDimension(R.dimen.active_button_size_tablet);
        }
        button.getLayoutParams().height = height;
        button.getLayoutParams().width = width;
        button.requestLayout();
    }

    private void changeButtonAsUnActive(ImageView button) {
        button.setImageResource(R.drawable.un_active_round_button);
        if (getResources().getBoolean(R.bool.portrait_only)) {
            width = (int) getResources().getDimension(R.dimen.un_active_button_size);
            height = (int) getResources().getDimension(R.dimen.un_active_button_size);
        } else {
            width = (int) getResources().getDimension(R.dimen.un_active_button_size_tablet);
            height = (int) getResources().getDimension(R.dimen.un_active_button_size_tablet);
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

    @OnClick(R.id.recyclerView)
    public void onClick() {
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
