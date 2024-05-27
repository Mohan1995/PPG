package com.nauroo.ppg.utils;



import com.nauroo.ppg.model.CatalogsModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohan M on 12/11/2017.
 */

public class Constants {
    public static String PREFERENCE_RAN_BEFORE="RAN_BEFORE";
    public static List<String> TITTLES=new ArrayList<>();
    public static final String YOUTUBE_API_KEY = "AIzaSyD8UT05Q_WOhgTr6pRPwqpeuXkPU90szgk";
    //Network API response codes
    public static final int RESPONSE_SUCCESS=200;
    public static  int CURRENT_FRAGMENT=2;
    public static int EVENT_FRAGMENT=1;
    public static int NOTIFICATION_FRAGMENT=2;

    public static boolean isReloaded=false;
    public static String IMAGE_BASE_URL="http://phplaravel-134875-389695.cloudwaysapps.com/";

    public static CatalogsModel catalogsModel;
    public static String COUNTRY_ID="1";
    public static String CurrentPage="CURRENT_PAGE";
    public static int PAGE_CALCULATOR=1;
    public static int PAGE_COMPARATOR=2;
    public static int PAGE_INTERACTIVE_GUIDE=3;
    public static int PAGE_PDS_SDS=4;
    public static int PAGE_MORE=5;
    public static int PAGE_NOTIFICATION_EVENTS=6;
    public static int PAGE_EVENTS=7;
    public static int PAGE_NOTIFICATION_DETAIL_PAGE=8;
    public static int PAGE_EVENT_DETAIL=9;
    public static String IS_REGISTERED ="IS_REGISTERED";
    public static String SP_USER_PROFILE="USER_PROFILE";
    public static String FINISH="FINISH";
    public static String EXPANDED="EXPANDED";
    public static String PDS_SDS_URL="http://www.ppgpmc.com/Home.aspx";
    public static String TANK_URL="http://tankselect.sigmacoatings.com/uk/index.cfm?fuseaction=tanklining_selection&assetmetaAssetmeta_x_nChildID=60906";
}
