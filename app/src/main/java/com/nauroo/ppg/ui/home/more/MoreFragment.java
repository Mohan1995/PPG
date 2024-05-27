package com.nauroo.ppg.ui.home.more;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.nauroo.ppg.R;
import com.nauroo.ppg.ui.home.HomeActivity;
import com.nauroo.ppg.ui.home.more.notification_and_events.NotificationAndEventsFragment;
import com.nauroo.ppg.ui.home.more.pisos.PisosFragment;
import com.nauroo.ppg.ui.home.more.tank.TankFragment;
import com.nauroo.ppg.ui.languageselection.LanguageFragment;
import com.nauroo.ppg.ui.register.RegisterActivity;
import com.nauroo.ppg.utils.Constants;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MoreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoreFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.tankLayout)
    RelativeLayout tankLayout;
    @BindView(R.id.notificationAndEventLayout)
    CardView notificationAndEventsLayout;
    @BindView(R.id.myProfileLayout)
    CardView myProfileLayout;
    @BindView(R.id.languageLayout)
    RelativeLayout languageLayout;
    @BindView(R.id.adjustmentsLayout)
    RelativeLayout adjustmentsLayout;
    @BindView(R.id.tankImageView)
    ImageView tankImageView;
    @BindView(R.id.notificationAndEventImageView)
    ImageView notificationAndEventImageView;

    @BindView(R.id.profileImageView)
    ImageView profileImageView;
    @BindView(R.id.languageImageView)
    ImageView languageImageView;
    @BindView(R.id.adjustmentsImageView)
    ImageView adjustmentsImageView;
    @BindView(R.id.pisosLayout)
    CardView pisosLayout;

    @BindView(R.id.pisosImageView)
    ImageView pisosImageView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MoreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MoreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MoreFragment newInstance(String param1, String param2) {
        MoreFragment fragment = new MoreFragment();
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
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        ButterKnife.bind(this, view);

        //Force app to stick with Portrait Mode
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        final int width=dpToPx(60);
       final int height=dpToPx(60);
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                if(isAdded()) {
//                    if (getResources().getBoolean(R.bool.portrait_only)) {
//                        Picasso.with(getContext()).load(R.drawable.tank_icon).resize(tankImageView.getWidth(), tankImageView.getHeight()).into(tankImageView);
//                        Picasso.with(getContext()).load(R.drawable.pds_sds).resize(pdsImageView.getWidth(), pdsImageView.getHeight()).into(pdsImageView);
//                        Picasso.with(getContext()).load(R.drawable.notification_icon).resize(notificationImageView.getWidth(), notificationImageView.getHeight()).into(notificationImageView);
//                        Picasso.with(getContext()).load(R.drawable.my_profile_icon).resize(profileImageView.getWidth(), profileImageView.getHeight()).into(profileImageView);
//                        Picasso.with(getContext()).load(R.drawable.language_icon).resize(languageImageView.getWidth(), languageImageView.getHeight()).into(languageImageView);
//                        Picasso.with(getContext()).load(R.drawable.adjustment_icon).resize(adjustmentsImageView.getWidth(), adjustmentsImageView.getHeight()).into(adjustmentsImageView);
//                    } else {
//                        Picasso.with(getContext()).load(R.drawable.tablet_tank_icon).resize(width,height).into(tankImageView);
//                        Picasso.with(getContext()).load(R.drawable.tablet_pds_sds_icon).resize(pdsImageView.getWidth(), pdsImageView.getHeight()).into(pdsImageView);
//                        Picasso.with(getContext()).load(R.drawable.tablet_notification_and_event_icon).resize(notificationImageView.getWidth(), notificationImageView.getHeight()).into(notificationImageView);
//                        Picasso.with(getContext()).load(R.drawable.tablet_my_profile_icon).resize(profileImageView.getWidth(), profileImageView.getHeight()).into(profileImageView);
//                        Picasso.with(getContext()).load(R.drawable.tablet_language_icon).resize(languageImageView.getWidth(), languageImageView.getHeight()).into(languageImageView);
//                        Picasso.with(getContext()).load(R.drawable.tablet_adjustes_icon).resize(adjustmentsImageView.getWidth(), adjustmentsImageView.getHeight()).into(adjustmentsImageView);
//
//                    }

                    Picasso.with(getContext()).load(R.drawable.user).resize(width,height).into(profileImageView);
                    Picasso.with(getContext()).load(R.drawable.pisos).resize(width,height).into(pisosImageView);
                    Picasso.with(getContext()).load(R.drawable.news).resize(width,height).into(notificationAndEventImageView);


                    Picasso.with(getContext()).load(R.drawable.tablet_tank_icon).resize(width,height).into(tankImageView);
                //    Picasso.with(getContext()).load(R.drawable.tablet_comparator_icon).resize(width,height).into(comparatorImageView);
                    Picasso.with(getContext()).load(R.drawable.tablet_language_icon).resize(width,height).into(languageImageView);
                    Picasso.with(getContext()).load(R.drawable.tablet_adjustes_icon).resize(width,height).into(adjustmentsImageView);


//                    if (getResources().getBoolean(R.bool.portrait_only)) {
//                        Picasso.with(getContext()).load(R.drawable.tank_icon).resize(width,height).into(tankImageView);
//                        Picasso.with(getContext()).load(R.drawable.menu_selector).resize(width,height).into(selectorImageView);
//                        Picasso.with(getContext()).load(R.drawable.menu_comparator).resize(width,height).into(comparatorImageView);
//                        Picasso.with(getContext()).load(R.drawable.my_profile_icon).resize(width,height).into(profileImageView);
//                        Picasso.with(getContext()).load(R.drawable.language_icon).resize(width,height).into(languageImageView);
//                        Picasso.with(getContext()).load(R.drawable.adjustment_icon).resize(width,height).into(adjustmentsImageView);
//                    } else {
//                        Picasso.with(getContext()).load(R.drawable.tablet_tank_icon).resize(width,height).into(tankImageView);
//                        Picasso.with(getContext()).load(R.drawable.menu_selector).resize(width,height).into(selectorImageView);
//                        Picasso.with(getContext()).load(R.drawable.menu_comparator).resize(width,height).into(comparatorImageView);
//                        Picasso.with(getContext()).load(R.drawable.tablet_my_profile_icon).resize(width,height).into(profileImageView);
//                        Picasso.with(getContext()).load(R.drawable.tablet_language_icon).resize(width,height).into(languageImageView);
//                        Picasso.with(getContext()).load(R.drawable.tablet_adjustes_icon).resize(width,height).into(adjustmentsImageView);
//
//                    }
                }
            }
        });
        return view;
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
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

    @OnClick({R.id.pisosLayout,R.id.tankLayout, R.id.notificationAndEventLayout, R.id.myProfileLayout, R.id.languageLayout, R.id.adjustmentsLayout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tankLayout:
                TankFragment tankFragment=new TankFragment();
                changeFragment(getContext(),tankFragment,getString(R.string.tank));
                break;
            case R.id.notificationAndEventLayout:
                NotificationAndEventsFragment notificationAndEventsFragment=new NotificationAndEventsFragment();
                changeFragment(getContext(),notificationAndEventsFragment,getString(R.string.notifications_and_events));
                break;
//            case R.id.comparatorLayout:
//                ComparatorFragment comparatorFragment = new ComparatorFragment();
//                changeFragment(getContext(), comparatorFragment, getString(R.string.comparator_small));
//                break;
            case R.id.myProfileLayout:
                Intent intent = new Intent(getContext(), RegisterActivity.class);
                intent.putExtra(Constants.FINISH, true);
                startActivity(intent);
                break;
            case R.id.languageLayout:
                LanguageFragment languageFragment = LanguageFragment.newInstance(false);
                changeFragment(getContext(), languageFragment, getString(R.string.language));
                break;
            case R.id.adjustmentsLayout:
                break;
            case R.id.pisosLayout:
                PisosFragment pisosFragment=new PisosFragment();
                HomeActivity activity = (HomeActivity) getContext();
                activity.addFragmentWithoutToolbar(pisosFragment,"","");
                break;
        }
    }

    private void changeFragment(Context context, Fragment fragment, String tittle) {
        HomeActivity activity = (HomeActivity) context;
        activity.addFragment(fragment, fragment.getClass().getName(), tittle);
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
//        if (getResources().getBoolean(R.bool.portrait_only)) {
//            if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//                getFragmentManager().beginTransaction().detach(this).attach(this).commit();
//            } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
//                getFragmentManager().beginTransaction().detach(this).attach(this).commit();
//            }
//        }

    }

    @Override
    public void onDestroy() {
        //Reset Orientation to normal
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);

        super.onDestroy();

    }
}
