package com.nauroo.ppg.ui.languageselection;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nauroo.ppg.R;
import com.nauroo.ppg.ui.home.HomeActivity;
import com.nauroo.ppg.ui.loginorregister.LoginOrRegisterActivity;
import com.nauroo.ppg.utils.LocaleHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LanguageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LanguageFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.spanishLayout)
    RelativeLayout spanishLayout;
    @BindView(R.id.englishLayout)
    RelativeLayout englishLayout;
    @BindView(R.id.nextButton)
    Button nextButton;
    @BindView(R.id.spanishTextView)
    TextView spanishTextView;
    @BindView(R.id.spanishImageView)
    ImageView spanishImageView;
    @BindView(R.id.englishTextView)
    TextView englishTextView;
    @BindView(R.id.englishIcon)
    ImageView englishIcon;
    @BindView(R.id.cardView)
    LinearLayout cardView;
    int selectedLanguage = 0;
    // TODO: Rename and change types of parameters
    private boolean moveToLoginOrRegisterActivity;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public LanguageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment LanguageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LanguageFragment newInstance(boolean param1) {
        LanguageFragment fragment = new LanguageFragment();
        Bundle args = new Bundle();
        args.putBoolean(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            moveToLoginOrRegisterActivity = getArguments().getBoolean(ARG_PARAM1);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_language, container, false);
        ButterKnife.bind(this, view);
        return view;
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

    @OnClick({R.id.spanishLayout, R.id.englishLayout, R.id.nextButton})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.spanishLayout:
                makeSpanishAsActive();
                break;
            case R.id.englishLayout:
                makeEnglishAsActive();
                break;
            case R.id.nextButton:
                if (selectedLanguage == 0) {
                    Toast.makeText(getContext(), getString(R.string.please_select_your_langugae), Toast.LENGTH_SHORT).show();
                } else {
                    if (selectedLanguage == 1) {
                        LocaleHelper.setLocale(getContext(), "es");
                    } else {
                        //changeLang(getContext(), "en");
                        LocaleHelper.setLocale(getContext(), "en");
                    }
                    if (moveToLoginOrRegisterActivity) {
                        moveToLoginOrRegisterActivity();
                    } else {
                        getFragmentManager().popBackStack();
                        HomeActivity instance = (HomeActivity) getActivity();
                        instance.refreshActivityAfterLanguageChange();
                    }
                }
                break;
        }

    }



    private void moveToLoginOrRegisterActivity() {
        startActivity(new Intent(getContext(), LoginOrRegisterActivity.class));
    }

    private void makeSpanishAsActive() {
        spanishTextView.setTextColor(ContextCompat.getColor(getContext(), R.color.selected_text_orange_color));
        spanishImageView.setVisibility(View.VISIBLE);
        englishTextView.setTextColor(ContextCompat.getColor(getContext(), R.color.default_text_color));
        englishIcon.setVisibility(View.GONE);
        selectedLanguage = 1;
    }

    private void makeEnglishAsActive() {
        englishTextView.setTextColor(ContextCompat.getColor(getContext(), R.color.selected_text_orange_color));
        englishIcon.setVisibility(View.VISIBLE);
        spanishTextView.setTextColor(ContextCompat.getColor(getContext(), R.color.default_text_color));
        spanishImageView.setVisibility(View.GONE);
        selectedLanguage = 2;
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
