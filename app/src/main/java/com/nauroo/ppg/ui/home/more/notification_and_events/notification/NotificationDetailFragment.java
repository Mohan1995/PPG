package com.nauroo.ppg.ui.home.more.notification_and_events.notification;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.nauroo.ppg.R;
import com.nauroo.ppg.model.NewsResponseModel;
import com.nauroo.ppg.ui.home.HomeActivity;
import com.nauroo.ppg.utils.Constants;
import com.squareup.picasso.Picasso;
import com.uncopt.android.widget.text.justify.JustifiedTextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NotificationDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationDetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    NewsResponseModel news;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.tittleTextView)
    TextView tittleTextView;
    @BindView(R.id.bodyTextView)
    JustifiedTextView bodyTextView;
    @BindView(R.id.thumbnailView)
    YouTubeThumbnailView thumbnailView;
    @BindView(R.id.videoLayout)
    RelativeLayout videoLayout;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public NotificationDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        HomeActivity instance = (HomeActivity) getActivity();
        instance.removePadding();
    }

    // TODO: Rename and change types and number of parameters
    public static NotificationDetailFragment newInstance(NewsResponseModel news) {
        NotificationDetailFragment fragment = new NotificationDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, news);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            news = getArguments().getParcelable(ARG_PARAM1);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification_detail, container, false);
        ButterKnife.bind(this, view);
        HomeActivity instance = (HomeActivity) getActivity();
        instance.setPadding();

        setData();
        return view;
    }

    private void setData() {
//        new Handler().post(new Runnable() {
//            @Override
//            public void run() {
//                Picasso.with(getContext()).load(Constants.IMAGE_BASE_URL + news.getImage_url()).into(imageView);
//
//            }
//        });
        Glide.with(getContext()).load(Constants.IMAGE_BASE_URL + news.getImage_url()).fitCenter().into(imageView);
        //imageView.setImageURI(Uri.parse(Constants.IMAGE_BASE_URL + news.getImage_url()));
        tittleTextView.setText(news.getTranslations().get(0).getTitle());
        bodyTextView.setText(Html.fromHtml(news.getTranslations().get(0).getDescription()) + "\n \n  \n \n \n \n \n \n ");

        if (news.getTranslations().get(0).getVideo() == null) {
            videoLayout.setVisibility(View.GONE);
        } else {


            /*  initialize the thumbnail image view , we need to pass Developer Key */
            thumbnailView.initialize(Constants.YOUTUBE_API_KEY, new YouTubeThumbnailView.OnInitializedListener() {
                @Override
                public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, final YouTubeThumbnailLoader youTubeThumbnailLoader) {
                    //when initialization is sucess, set the video id to thumbnail to load
                    // youTubeThumbnailLoader.setVideo(news.getTranslations().get(0).getVideo());
                    Log.w("VIDEO URL",news.getTranslations().get(0).getVideo());
                    Log.w("VIDEO ID",extractYTId(news.getTranslations().get(0).getVideo()));
                    youTubeThumbnailLoader.setVideo(extractYTId(news.getTranslations().get(0).getVideo()));

                    youTubeThumbnailLoader.setOnThumbnailLoadedListener(new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
                        @Override
                        public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                            //when thumbnail loaded successfully release the thumbnail loader as we are showing thumbnail in adapter
                            youTubeThumbnailLoader.release();
                            videoLayout.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {
                            //print or show error when thumbnail load failed
                            Log.e(TAG, "Youtube Thumbnail Error");
                            videoLayout.setVisibility(View.GONE);
                        }
                    });
                }

                @Override
                public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {

                }


            });
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();



    }

    @OnClick(R.id.videoLayout)
    public void onViewClicked() {
        Intent intent= new Intent(getContext(), YoutubePlayerActivity.class);
        String videoId=extractYTId(news.getTranslations().get(0).getVideo());
        Log.w("VIDEO ID",videoId);
        intent.putExtra("video_id",videoId);
        getActivity().startActivity(intent);
    }

    public static String extractYTId(String ytUrl) {
        String pattern = "(?<=youtu.be/|watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(ytUrl);
        if(matcher.find()){
            return matcher.group();
        } else {
            return "error";
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
