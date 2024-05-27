package com.nauroo.ppg.ui.home.more.notification_and_events.events;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nauroo.ppg.R;
import com.nauroo.ppg.model.EventsResponseModel;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EventDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventDetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final int READ_CONTACT_CODE = 100;


    @BindView(R.id.tittleTextView)
    TextView tittleTextView;
    @BindView(R.id.subTittleTextView)
    TextView subTittleTextView;
    @BindView(R.id.dateTextVew)
    TextView dateTextVew;
    @BindView(R.id.timeTextView)
    TextView timeTextView;
    @BindView(R.id.bodyTextView)
    TextView bodyTextView;
    @BindView(R.id.addToCalendarTextView)
    TextView addToCalendarTextView;

    // TODO: Rename and change types of parameters
    private EventsResponseModel event;


    private OnFragmentInteractionListener mListener;

    public EventDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment EventDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EventDetailFragment newInstance(EventsResponseModel event) {
        EventDetailFragment fragment = new EventDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, event);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            event = getArguments().getParcelable(ARG_PARAM1);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_detail, container, false);
        ButterKnife.bind(this, view);
        setData();
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CALENDAR)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CALENDAR},
                    READ_CONTACT_CODE);
        } else {
            //checkEventIsExistingOrNot();
        }

        return view;
    }


    private void setData() {
        tittleTextView.setText(event.getTranslations().get(0).getTitle());
        subTittleTextView.setText(event.getTranslations().get(0).getBrief());
        String[] date = event.getStart_date().split(" ");
        String[] endDate = event.getEnd_date().split(" ");
        String[] finalStartTime;
        String[] finalEndTime;
        finalStartTime = date[1].split(":");
        finalEndTime = endDate[1].split(":");
        timeTextView.setText(finalStartTime[0] + ":" + finalStartTime[1] + " - " + finalEndTime[0] + ":" + finalEndTime[1]);
        dateTextVew.setText(date[0].replace("-", "/") + " - " + endDate[0].replace("-", "/"));
        bodyTextView.setText(event.getTranslations().get(0).getDescription());
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

    @OnClick(R.id.addToCalendarTextView)
    public void onClick() {

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CALENDAR)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CALENDAR},
                    READ_CONTACT_CODE);
        } else {
            addToCalender();
        }

    }

    private void addToCalender() {
        String[] fullStartDate = event.getStart_date().split(" ");
        String[] finalsStartDate;
        finalsStartDate = fullStartDate[0].split("-");
        String[] finalStartTime = fullStartDate[1].split(":");
        Calendar startingDate = Calendar.getInstance();
        startingDate.set(Calendar.YEAR, Integer.parseInt(finalsStartDate[0]));
        startingDate.set(Calendar.MONTH, Integer.parseInt(finalsStartDate[1]) - 1);
        startingDate.set(Calendar.DATE, Integer.parseInt(finalsStartDate[2]));
        startingDate.set(Calendar.HOUR_OF_DAY, Integer.parseInt(finalStartTime[0]));
        startingDate.set(Calendar.MINUTE, Integer.parseInt(finalStartTime[1]));


        String[] fullEndDate = event.getEnd_date().split(" ");
        String[] finalEndDate;
        finalEndDate = fullEndDate[0].split("-");
        String[] finalEndTime = fullEndDate[1].split(":");
        Calendar endingDate = Calendar.getInstance();
        endingDate.set(Calendar.YEAR, Integer.parseInt(finalEndDate[0]));
        endingDate.set(Calendar.MONTH, Integer.parseInt(finalEndDate[1]) - 1);
        endingDate.set(Calendar.DATE, Integer.parseInt(finalEndDate[2]));
        endingDate.set(Calendar.HOUR_OF_DAY, Integer.parseInt(finalEndTime[0]));
        endingDate.set(Calendar.MINUTE, Integer.parseInt(finalEndTime[1]));

        if (Build.VERSION.SDK_INT >= 14) {
            Intent intent = new Intent(Intent.ACTION_INSERT)
                    .setData(CalendarContract.Events.CONTENT_URI)
                    .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startingDate.getTimeInMillis())
                    .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endingDate.getTimeInMillis())
                    .putExtra(CalendarContract.Events.TITLE, event.getTranslations().get(0).getTitle())
                    .putExtra(CalendarContract.Events.DESCRIPTION, event.getTranslations().get(0).getDescription())
                    .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);
            intent.putExtra("allDay", false);
            intent.putExtra(CalendarContract.Events.EVENT_LOCATION, event.getTranslations().get(0).getBrief());
            intent.putExtra(CalendarContract.Events.CALENDAR_ID, event.getId());
            startActivity(intent);
        } else {
            Intent intent = new Intent(Intent.ACTION_EDIT);
            intent.setType("vnd.android.cursor.item/event");
            intent.putExtra("beginTime", startingDate.getTimeInMillis());
            intent.putExtra("endTime", endingDate.getTimeInMillis());
            intent.putExtra("title", event.getTranslations().get(0).getTitle());
            intent.putExtra("description",event.getTranslations().get(0).getDescription());
            intent.putExtra("allDay", false);
            intent.putExtra("eventLocation",event.getTranslations().get(0).getBrief());
            intent.putExtra("calendar_id",event.getId());
            intent.putExtra("rule", "FREQ=YEARLY");
            startActivityForResult(intent, 100);
        }
//        Intent intent = new Intent(Intent.ACTION_EDIT);
//        intent.setType("vnd.android.cursor.item/event");
//        intent.putExtra(CalendarContract.Events.DTSTART, startingDate.getTimeInMillis());
//        intent.putExtra(CalendarContract.Events.DTEND, endingDate.getTimeInMillis());
//        intent.putExtra(CalendarContract.Events.TITLE, event.getTranslations().get(0).getTitle());
//        intent.putExtra(CalendarContract.Events.DESCRIPTION, event.getTranslations().get(0).getDescription());
//        intent.putExtra("allDay", false);
//        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, event.getTranslations().get(0).getBrief());
//        intent.putExtra(CalendarContract.Events.CALENDAR_ID, event.getId());
//        intent.putExtra("rule", "FREQ=YEARLY");
//        startActivityForResult(intent, 100);
    }

//    private void checkEventIsExistingOrNot() {
//
//        ContentResolver contentResolver = getContext().getContentResolver();
//        Cursor cursor = contentResolver.query(Uri.parse("content://com.android.calendar/events"), new String[]{"calendar_id", "title", "description", "dtstart", "dtend", "eventLocation"}, null, null, null);
//        //Cursor cursor = cr.query(Uri.parse("content://calendar/calendars"), new String[]{ "_id", "name" }, null, null, null);
//        String add = null;
//        cursor.moveToFirst();
//        String[] CalNames = new String[cursor.getCount()];
//        int[] CalIds = new int[cursor.getCount()];
//        Log.e("Event id", event.getId());
//        for (int i = 0; i < CalNames.length; i++) {
//            Log.w("Id", cursor.getInt(0) + "");
//            CalIds[i] = cursor.getInt(0);
////            CalNames[i] = "Event"+cursor.getInt(0)+": \nTitle: "+ cursor.getString(1)+"\nDescription: "+cursor.getString(2)+"\nStart Date: "+new Date(cursor.getLong(3))+"\nEnd Date : "+new Date(cursor.getLong(4))+"\nLocation : "+cursor.getString(5);
////            if(add == null)
////                add = CalNames[i];
////            else{
////                add += CalNames[i];
////            }
//            if (String.valueOf(cursor.getInt(0)).equals(event.getId())) {
//                addToCalendarTextView.setVisibility(View.GONE);
//                break;
//            }
//
//            cursor.moveToNext();
//        }
//        cursor.close();
//
////        Cursor cursor = getContext().getContentResolver().query(
////                Uri.parse("content://com.android.calendar/events"),
////                new String[]{"_id"}, " _id = ? ",
////                new String[]{event.getId()}, null);
////
////        if (cursor.moveToFirst()) {
////        }else {
////            addToCalendarTextView.setVisibility(View.GONE);
////        }
//    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == READ_CONTACT_CODE) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                addToCalender();

            } else {
                requestPermissions(new String[]{Manifest.permission.READ_CALENDAR},
                        READ_CONTACT_CODE);

            }

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //checkEventIsExistingOrNot();
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
