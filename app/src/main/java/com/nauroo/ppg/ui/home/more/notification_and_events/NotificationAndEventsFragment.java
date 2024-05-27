package com.nauroo.ppg.ui.home.more.notification_and_events;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.content.ContextCompat;
import androidx.cardview.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nauroo.ppg.R;
import com.nauroo.ppg.model.AdvertisementResponseModel;
import com.nauroo.ppg.model.GenericResponse;
import com.nauroo.ppg.network.NetworkAdapter;
import com.nauroo.ppg.network.ResponseCallback;
import com.nauroo.ppg.ui.home.more.notification_and_events.events.EventsFragment;
import com.nauroo.ppg.ui.home.more.notification_and_events.notification.NotificationFragment;
import com.nauroo.ppg.utils.Constants;
import com.nauroo.ppg.utils.PageRefreshEvent;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Calendar;
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
 * Use the {@link NotificationAndEventsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationAndEventsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.notificationButton)
    TextView notificationButton;
    @BindView(R.id.eventButton)
    TextView eventButton;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.adLayout)
    RelativeLayout adLayout;
    @BindView(R.id.advertisementImageView)
    ImageView advertisementImageView;
    @BindView(R.id.advertisementTittleTextView)
    TextView advertisementTittleTextView;
    @BindView(R.id.advertisementBodyTextView)
    TextView advertisementBodyTextView;
    String advertisementUrl;
    @BindView(R.id.imageLayout)
    CardView imageLayout;
    @BindView(R.id.diagonalImageView)
    ImageView diagonalImageView;
    TextView yearTextView, monthTextView;
    RelativeLayout topLayout;
    RelativeLayout fragmentContainer;
    @BindView(R.id.leftArrowImageView)
    ImageView leftArrowImageView;
    @BindView(R.id.rightArrowImageView)
    ImageView rightArrowImageView;
    int checkedMonth = 0;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public NotificationAndEventsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotificationAndEventsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotificationAndEventsFragment newInstance(String param1, String param2) {
        NotificationAndEventsFragment fragment = new NotificationAndEventsFragment();
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
        View view = inflater.inflate(R.layout.fragment_notification_and_events, container, false);
        yearTextView = (TextView) view.findViewById(R.id.yearTextView);
        monthTextView = (TextView) view.findViewById(R.id.mothTextView);
        topLayout = (RelativeLayout) view.findViewById(R.id.monthLayout);
        fragmentContainer = (RelativeLayout) view.findViewById(R.id.fragment_container);
        ButterKnife.bind(this, view);
        Log.w("Current Fragment", Constants.CURRENT_FRAGMENT + "--");
        monthTextView.setText(getMonth());
        int year = Calendar.getInstance().get(Calendar.YEAR);
        yearTextView.setText(year + "");
        if (Constants.CURRENT_FRAGMENT != Constants.NOTIFICATION_FRAGMENT) {
            diagonalImageView.setVisibility(View.VISIBLE);
            topLayout.setVisibility(View.VISIBLE);
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) fragmentContainer.getLayoutParams();
                params1.setMargins(0, 30, 0, 0); //substitute parameters for left, top, right, bottom
                fragmentContainer.setLayoutParams(params1);
            } else {
                LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) fragmentContainer.getLayoutParams();
                params1.setMargins(0, 200, 0, 0); //substitute parameters for left, top, right, bottom
                fragmentContainer.setLayoutParams(params1);
            }
            makeEventButtonAsActive();
            hideAdLayout();
            EventsFragment eventsFragment = new EventsFragment();
            switchFragment(eventsFragment, eventsFragment.getClass().getName());
        } else {

            if (!EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().register(this);
            }
            makeNotificationAsActive();

            diagonalImageView.setVisibility(View.GONE);
            topLayout.setVisibility(View.GONE);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) fragmentContainer.getLayoutParams();
            params.setMargins(0, 0, 0, 0); //substitute parameters for left, top, right, bottom
            fragmentContainer.setLayoutParams(params);


            NotificationFragment notificationFragment = new NotificationFragment();
            switchFragment(notificationFragment, notificationFragment.getClass().getName());
            getAdvertisement();
        }
        return view;
    }

    private String getMonth() {
        String[] monthName = {"Enero ", "Febrero ", "Marzo ", "Abril ", "Mayo ", "Junio ", "Julio ",
                "Agosto ", "Septiembre ", "Octubre ", "Noviembre ", "Diciembre "};

        Calendar cal = Calendar.getInstance();
        String month = monthName[cal.get(Calendar.MONTH)];
        return month;
    }

    private String getMonth(int i) {
        String[] monthName = {"Enero ", "Febrero ", "Marzo ", "Abril ", "Mayo ", "Junio ", "Julio ",
                "Agosto ", "Septiembre ", "Octubre ", "Noviembre ", "Diciembre "};

        String month = monthName[i];
        return month;
    }

    private void getAdvertisement() {
        NetworkAdapter.getInstance(getContext()).getAdvertisement(new ResponseCallback<GenericResponse<List<AdvertisementResponseModel>>>(getContext()) {
            @Override
            public void onResponse(GenericResponse<List<AdvertisementResponseModel>> response) {
                if (response.getStatus() == Constants.RESPONSE_SUCCESS && response.getData().size() != 0) {
                    if (isAdded()) {
                        advertisementImageView.setImageURI(Uri.parse(Constants.IMAGE_BASE_URL + response.getData().get(0).getUrl_image()));
                        //Picasso.with(getContext()).load(Constants.IMAGE_BASE_URL + response.getData().get(0).getUrl_image()).into(advertisementImageView);
                        Locale currentLanguage = getResources().getConfiguration().locale;
                        for (int i = 0; i < response.getData().get(0).getTranslations().size(); i++) {
                            if (response.getData().get(0).getTranslations().get(i).getLanguage_id().equals(currentLanguage.toString().toUpperCase())) {
                                advertisementTittleTextView.setText(response.getData().get(0).getTranslations().get(i).getTitle());
                                advertisementBodyTextView.setText(response.getData().get(0).getTranslations().get(i).getDescription());
                                advertisementUrl = response.getData().get(0).getTranslations().get(i).getUrl();
                            }
                        }
                        adLayout.setBackgroundColor(Color.parseColor("#" + response.getData().get(0).getColor()));
                        adLayout.setVisibility(View.VISIBLE);
                    }

                }

            }

            @Override
            public void onFailure() {

            }
        });
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

    @OnClick({R.id.notificationButton, R.id.eventButton, R.id.leftArrowImageView, R.id.yearTextView, R.id.mothTextView, R.id.rightArrowImageView})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.notificationButton:
                diagonalImageView.setVisibility(View.GONE);
                topLayout.setVisibility(View.GONE);
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) fragmentContainer.getLayoutParams();
                params.setMargins(0, 0, 0, 0); //substitute parameters for left, top, right, bottom
                fragmentContainer.setLayoutParams(params);
                makeNotificationAsActive();
                getAdvertisement();
                NotificationFragment notificationFragment = new NotificationFragment();
                switchFragment(notificationFragment, notificationFragment.getClass().getName());
                break;
            case R.id.eventButton:
                diagonalImageView.setVisibility(View.VISIBLE);
                topLayout.setVisibility(View.VISIBLE);
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) fragmentContainer.getLayoutParams();
                    params1.setMargins(0, 30, 0, 0); //substitute parameters for left, top, right, bottom
                    fragmentContainer.setLayoutParams(params1);
                } else {
                    LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) fragmentContainer.getLayoutParams();
                    params1.setMargins(0, 200, 0, 0); //substitute parameters for left, top, right, bottom
                    fragmentContainer.setLayoutParams(params1);
                }
                makeEventButtonAsActive();
                hideAdLayout();
                EventsFragment eventsFragment = new EventsFragment();
                switchFragment(eventsFragment, eventsFragment.getClass().getName());

                break;
            case R.id.leftArrowImageView:
                Calendar cal = Calendar.getInstance();
                checkedMonth = checkedMonth - 1;
                cal.add(Calendar.MONTH, checkedMonth);
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                monthTextView.setText(getMonth(month));
                month=month+1;
                String sMonth = "";
                if (month < 10) {
                    sMonth = "0" + String.valueOf(month);
                } else {
                    sMonth = String.valueOf(month);
                }

                yearTextView.setText(year + "");
                EventBus.getDefault().post(new PageRefreshEvent(2, year + "/" + sMonth));
                Log.w("Selected date", year + "/" + sMonth);
                break;
            case R.id.rightArrowImageView:
                cal = Calendar.getInstance();
                checkedMonth = checkedMonth + 1;
                cal.add(Calendar.MONTH, checkedMonth);
                year = cal.get(Calendar.YEAR);
                month = cal.get(Calendar.MONTH);
                monthTextView.setText(getMonth(month));
                month=month+1;
                sMonth = "";
                if (month < 10) {
                    sMonth = "0" + String.valueOf(month);
                } else {
                    sMonth = String.valueOf(month);
                }

                yearTextView.setText(year + "");
                EventBus.getDefault().post(new PageRefreshEvent(2, year + "/" + sMonth));
                Log.w("Selected date", year + "/" + sMonth);
                break;
        }
    }

    public void makeNotificationAsActive() {
        notificationButton.setTextColor(ContextCompat.getColor(getContext(), R.color.selected_button_text_color));
        notificationButton.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
        eventButton.setTextColor(ContextCompat.getColor(getContext(), R.color.un_selected_button_text_color));
        if (!getResources().getBoolean(R.bool.portrait_only)) {
            eventButton.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.un_selected_button_background_color));
        } else {
            eventButton.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.un_selected_button_background_color));
        }

    }

    public void makeEventButtonAsActive() {
        eventButton.setTextColor(ContextCompat.getColor(getContext(), R.color.selected_button_text_color));
        eventButton.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
        notificationButton.setTextColor(ContextCompat.getColor(getContext(), R.color.un_selected_button_text_color));
        if (!getResources().getBoolean(R.bool.portrait_only)) {
            notificationButton.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.un_selected_button_background_color));
        } else {
            notificationButton.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.un_selected_button_background_color));
        }
    }

    public void switchFragment(Fragment fragment, String tag) {
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment, tag).commit();

    }

    public void showAdLayout() {
        adLayout.setVisibility(View.VISIBLE);
    }


    public void hideAdLayout() {
        adLayout.setVisibility(View.GONE);
    }

    @OnClick(R.id.adLayout)
    public void onClick() {
        openAdvertisementUrl(advertisementUrl);
    }

    private void openAdvertisementUrl(String advertisementUrl) {
        try {
            Uri webpage = Uri.parse(advertisementUrl);
            if (!advertisementUrl.startsWith("http://") && !advertisementUrl.startsWith("https://")) {
                webpage = Uri.parse("http://" + advertisementUrl);
            }
            Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
            if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivity(intent);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    @Subscribe
    public void onMessageEvent(final PageRefreshEvent event) {

        if (event.getType() == 2) {

        } else {
            if (event.getId().equals("1")) {
                diagonalImageView.setVisibility(View.GONE);
                topLayout.setVisibility(View.GONE);
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) fragmentContainer.getLayoutParams();
                params.setMargins(0, 0, 0, 0); //substitute parameters for left, top, right, bottom4
                fragmentContainer.setLayoutParams(params);
            } else {
                diagonalImageView.setVisibility(View.VISIBLE);
                topLayout.setVisibility(View.VISIBLE);
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) fragmentContainer.getLayoutParams();
                    params1.setMargins(0, 30, 0, 0); //substitute parameters for left, top, right, bottom
                    fragmentContainer.setLayoutParams(params1);
                } else {
                    LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) fragmentContainer.getLayoutParams();
                    params1.setMargins(0, 200, 0, 0); //substitute parameters for left, top, right, bottom
                    fragmentContainer.setLayoutParams(params1);
                }
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        Constants.CURRENT_FRAGMENT = Constants.NOTIFICATION_FRAGMENT;
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
