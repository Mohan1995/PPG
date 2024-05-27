package com.nauroo.ppg.ui.home.more.notification_and_events.notification;

import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nauroo.ppg.R;
import com.nauroo.ppg.model.GenericResponse;
import com.nauroo.ppg.model.NewsResponseModel;
import com.nauroo.ppg.network.NetworkAdapter;
import com.nauroo.ppg.network.ResponseCallback;
import com.nauroo.ppg.utils.Constants;
import com.nauroo.ppg.utils.InternetAvailability;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NotificationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.notificationRecyclerView)
    RecyclerView notificationRecyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    RecyclerView.LayoutManager layoutManager;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public NotificationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotificationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotificationFragment newInstance(String param1, String param2) {
        NotificationFragment fragment = new NotificationFragment();
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
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        ButterKnife.bind(this, view);
        Constants.CURRENT_FRAGMENT=Constants.NOTIFICATION_FRAGMENT;
        getNews();
        return view;
    }

    private void getNews() {
        if (InternetAvailability.isNetworkAvailable(getContext())) {
            showProgressBar();
            NetworkAdapter.getInstance(getContext()).getNews(new ResponseCallback<GenericResponse<List<NewsResponseModel>>>(getContext()) {
                @Override
                public void onResponse(GenericResponse<List<NewsResponseModel>> response) {
                    hideProgressBar();
                    if (response.getStatus()== Constants.RESPONSE_SUCCESS&&response.getData()!=null&&response.getData().size()!=0){
                        setUpNotificationRecyclerView(response.getData());
                    }else{
                        Toast.makeText(getContext(),getString(R.string.no_data_found),Toast.LENGTH_SHORT).show();
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

    private void setUpNotificationRecyclerView(List<NewsResponseModel> response) {
        List<NewsResponseModel> data=response;
        try {
            if(getResources().getBoolean(R.bool.portrait_only)){
                layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
            }else {
                layoutManager = new GridLayoutManager(getContext(), 2);
            }
            notificationRecyclerView.setLayoutManager(layoutManager);
            notificationRecyclerView.setNestedScrollingEnabled(false);
            //Locale currentLanguage = getResources().getConfiguration().locale;
            String  currentLanguage="EN";
            try {
                for (int i=0;i<response.size();i++){
                    for (int j=0;j<response.get(i).getTranslations().size();j++) {
                        if (currentLanguage.equals(response.get(i).getTranslations().get(j).getLanguage_id())){
                            response.get(i).getTranslations().remove(j);
                        }
                    }
                }
                Collections.reverse(data);
                NotificationAdapter notificationAdapter = new NotificationAdapter(getContext(), data);
                notificationRecyclerView.setAdapter(notificationAdapter);
            }catch (IndexOutOfBoundsException e){
                e.printStackTrace();
            }


        }catch (IllegalStateException e){
            e.printStackTrace();
        }


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

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
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
            } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
                getFragmentManager().beginTransaction().detach(this).attach(this).commit();
            }
        }

}
