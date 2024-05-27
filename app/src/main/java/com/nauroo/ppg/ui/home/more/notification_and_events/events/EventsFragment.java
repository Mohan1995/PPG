package com.nauroo.ppg.ui.home.more.notification_and_events.events;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nauroo.ppg.R;
import com.nauroo.ppg.model.EventsResponseModel;
import com.nauroo.ppg.model.GenericResponse;
import com.nauroo.ppg.network.NetworkAdapter;
import com.nauroo.ppg.network.ResponseCallback;
import com.nauroo.ppg.ui.home.more.notification_and_events.NotificationAndEventsFragment;
import com.nauroo.ppg.utils.Constants;
import com.nauroo.ppg.utils.InternetAvailability;
import com.nauroo.ppg.utils.PageRefreshEvent;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
 * Use the {@link EventsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.eventRecyclerView)
    RecyclerView eventRecyclerView;
    @BindView(R.id.linearViewImageView)
    ImageView linearViewImageView;
    @BindView(R.id.calendarViiewImageView)
    ImageView calendarViiewImageView;
    @BindView(R.id.calendarViewContainer)
    RelativeLayout calendarViewContainer;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    List<EventsResponseModel> events;
    List<EventsResponseModel> filteredResult;
    List<EventsResponseModel> mothFilterResult;
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;
    String[] dateString;
    ColorDrawable orange;
    ColorDrawable window_color;
    Date lastSelectedDate;
    boolean hasEvent = false;
    Date todayDate;
    ColorDrawable blue;
    @BindView(R.id.noEventMessageTextView)
    TextView noEventMessageTextView;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Locale currentLanguage;
    private OnFragmentInteractionListener mListener;
    NotificationAndEventsFragment notificationAndEventsFragment;

    public EventsFragment() {
        // Required empty public constructor
    }


//    public EventsFragment(NotificationAndEventsFragment notificationAndEventsFragment) {
//        // Required empty public constructor
//        this.notificationAndEventsFragment=notificationAndEventsFragment;
//    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EventsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EventsFragment newInstance(String param1, String param2) {
        EventsFragment fragment = new EventsFragment();
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
        View view = inflater.inflate(R.layout.fragment_events, container, false);
        ButterKnife.bind(this, view);
        currentLanguage = getResources().getConfiguration().locale;
        Constants.CURRENT_FRAGMENT = Constants.EVENT_FRAGMENT;
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        getEvents();
        hideCalendar();

        return view;
    }

    private void getEvents() {
        if (InternetAvailability.isNetworkAvailable(getContext())) {
            showProgressBar();
            NetworkAdapter.getInstance(getContext()).getEvents(new ResponseCallback<GenericResponse<List<EventsResponseModel>>>(getContext()) {
                @Override
                public void onResponse(GenericResponse<List<EventsResponseModel>> response) {
                    hideProgressBar();
                    if (isAdded()) {
                        if (response.getStatus() == Constants.RESPONSE_SUCCESS && response.getData() != null && response.getData().size() != 0) {
                            events = response.getData();
                            setUpRecyclerView(response.getData());
                            setUpCalendar(response.getData());
                            Calendar cal = Calendar.getInstance();
                            int year = cal.get(Calendar.YEAR);
                            int month = cal.get(Calendar.MONTH);
                            month = month + 1;
                            String sMonth = "";
                            if (month < 10) {
                                sMonth = "0" + String.valueOf(month);
                            } else {
                                sMonth = String.valueOf(month);
                            }

                            checkMonthHasEvent(year + "", sMonth + "");
                        } else {
                            Toast.makeText(getContext(), getString(R.string.no_data_found), Toast.LENGTH_SHORT).show();
                        }
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

    private void setUpRecyclerView(List<EventsResponseModel> response) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        eventRecyclerView.setLayoutManager(layoutManager);
        eventRecyclerView.setNestedScrollingEnabled(false);

        for (int i = 0; i < response.size(); i++) {
            for (int j = 0; j < response.get(i).getTranslations().size(); j++) {
                if (!currentLanguage.getLanguage().toUpperCase().equals(response.get(i).getTranslations().get(j).getLanguage_id())) {
                    response.get(i).getTranslations().remove(j);
                }
            }
        }
        EventsAdapter eventsAdapter = new EventsAdapter(getContext(), response);
        eventRecyclerView.setAdapter(eventsAdapter);
    }

    private void setUpCalendar(List<EventsResponseModel> data) {
        final CaldroidFragment caldroidFragment = new CaldroidFragment();
        Bundle args = new Bundle();
        final Calendar cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        args.putInt(CaldroidFragment.THEME_RESOURCE, com.caldroid.R.style.CaldroidDefaultDark);
        todayDate = cal.getTime();
        caldroidFragment.setArguments(args);
        FragmentTransaction t = getFragmentManager().beginTransaction();
        t.replace(R.id.calendarViewContainer, caldroidFragment);
        t.commit();

        String[] fullDate;
        String[] finalDate;
        for (int i = 0; i < data.size(); i++) {
            fullDate = data.get(i).getStart_date().split(" ");
            finalDate = fullDate[0].split("-");
            Calendar eventDate = Calendar.getInstance();
            eventDate.set(Calendar.YEAR, Integer.parseInt(finalDate[0]));
            eventDate.set(Calendar.MONTH, Integer.parseInt(finalDate[1]) - 1);
            eventDate.set(Calendar.DATE, Integer.parseInt(finalDate[2]));
            Date date = eventDate.getTime();
            blue = new ColorDrawable(getResources().getColor(R.color.colorAccent));
            caldroidFragment.setBackgroundDrawableForDate(blue, todayDate);
            orange = new ColorDrawable(getResources().getColor(R.color.today_date_color));
            window_color = new ColorDrawable(getResources().getColor(R.color.window_background_color));
            caldroidFragment.setBackgroundDrawableForDate(ContextCompat.getDrawable(getContext(), R.drawable.square), date);
        }

        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        final CaldroidListener listener = new CaldroidListener() {

            @Override
            public void onSelectDate(Date date, View view) {
                caldroidFragment.setBackgroundDrawableForDate(blue, todayDate);
                filteredResult = new ArrayList<>();
                if (lastSelectedDate == null) {
                    caldroidFragment.setBackgroundDrawableForDate(orange, date);
                    caldroidFragment.refreshView();
                    lastSelectedDate = date;
                } else {
                    if (hasEvent) {
                        caldroidFragment.setBackgroundDrawableForDate(ContextCompat.getDrawable(getContext(), R.drawable.square), lastSelectedDate);
                    } else {
                        caldroidFragment.setBackgroundDrawableForDate(window_color, lastSelectedDate);
                    }
                    caldroidFragment.setBackgroundDrawableForDate(orange, date);
                    caldroidFragment.refreshView();
                    lastSelectedDate = date;

                }
                for (int i = 0; i < events.size(); i++) {
                    dateString = events.get(i).getStart_date().split(" ");
                    if (dateString[0].equals(formatter.format(date).toString())) {
                        filteredResult.add(events.get(i));
                    }
                }

                if (filteredResult.size() == 0) {
                    hasEvent = false;
                    Toast.makeText(getContext(), getString(R.string.no_event_found_on) + " " + formatter.format(date), Toast.LENGTH_SHORT).show();
                } else {
                    hasEvent = true;
                    caldroidFragment.setBackgroundDrawableForDate(ContextCompat.getDrawable(getContext(), R.drawable.orange_square), date);
                    caldroidFragment.refreshView();
                    if (filteredResult.size() == 1) {
                        Toast.makeText(getContext(), filteredResult.size() + " " + getString(R.string.event_found), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), filteredResult.size() + " " + getString(R.string.events_found), Toast.LENGTH_SHORT).show();
                    }
                    setUpRecyclerView(filteredResult);
                    scrollView.scrollTo(0, scrollView.getBottom());
                }

            }

            @Override
            public void onChangeMonth(int month, int year) {

            }

            @Override
            public void onLongClickDate(Date date, View view) {

            }

            @Override
            public void onCaldroidViewCreated() {

            }

        };

        caldroidFragment.setCaldroidListener(listener);


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

    @OnClick({R.id.linearViewImageView, R.id.calendarViiewImageView})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linearViewImageView:
                noEventMessageTextView.setVisibility(View.GONE);
                makeLinearViewImageViewAsActive();
                EventBus.getDefault().post(new PageRefreshEvent(1, "0"));
                hideCalendar();
                break;
            case R.id.calendarViiewImageView:
                noEventMessageTextView.setVisibility(View.GONE);
                makeCalandarViewAsActive();
                EventBus.getDefault().post(new PageRefreshEvent(1, "1"));
                viewCalendar();
                break;
        }
    }

    private void hideCalendar() {
        calendarViewContainer.setVisibility(View.GONE);
    }


    private void viewCalendar() {
        calendarViewContainer.setVisibility(View.VISIBLE);
    }

    private void makeCalandarViewAsActive() {
        calendarViiewImageView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.selected_text_orange_color));
        linearViewImageView.setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.transparent));
    }

    private void makeLinearViewImageViewAsActive() {
        linearViewImageView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.selected_text_orange_color));
        calendarViiewImageView.setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.transparent));
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    public void checkMonthHasEvent(String year, String month) {
        String[] monthAndYearString;
        mothFilterResult = new ArrayList<>();
        for (int i = 0; i < events.size(); i++) {
            dateString = events.get(i).getStart_date().split(" ");
            monthAndYearString = dateString[0].split("-");
            if (monthAndYearString[0].equals(year) && monthAndYearString[1].equals(month)) {
                mothFilterResult.add(events.get(i));
            }
        }

        if (mothFilterResult.size() == 0) {
            hasEvent = false;
            setUpRecyclerView(mothFilterResult);
            noEventMessageTextView.setVisibility(View.VISIBLE);
        } else {
            setUpRecyclerView(mothFilterResult);
            noEventMessageTextView.setVisibility(View.GONE);
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

    @Subscribe
    public void onMessageEvent(final PageRefreshEvent event) {

        if (event.getType() == 2) {
            String[] monthAndYear = event.getId().split("/");

            checkMonthHasEvent(monthAndYear[0], monthAndYear[1]);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getFragmentManager().beginTransaction().detach(EventsFragment.this).attach(EventsFragment.this).commit();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            getFragmentManager().beginTransaction().detach(EventsFragment.this).attach(EventsFragment.this).commit();
        }
    }
}
