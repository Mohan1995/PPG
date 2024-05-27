package com.nauroo.ppg.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.nauroo.ppg.R;
import com.nauroo.ppg.ui.home.calculator.CalculatorFragment;
import com.nauroo.ppg.ui.home.comparator.ComparatorFragment;
import com.nauroo.ppg.ui.home.interactiveguide.InteractiveGuideFragment;
import com.nauroo.ppg.ui.home.more.MoreFragment;
import com.nauroo.ppg.ui.home.more.pds_and_sds.PDSfragment;
import com.nauroo.ppg.utils.Constants;
import com.nauroo.ppg.utils.LocaleHelper;

import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigation;
    @BindView(R.id.backImageVew)
    ImageView backImageVew;
    @BindView(R.id.tittleTextView)
    TextView tittleTextView;


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getResources().getBoolean(R.bool.portrait_only)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().hide();
        if (!getResources().getBoolean(R.bool.portrait_only)) {
            // Changing the size of bottom navigation view icon
            BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
            BottomNavigationMenuView menuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                final View iconView = menuView.getChildAt(i).findViewById(com.google.android.material.R.id.icon);
                final ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
                final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
                if (i==2){
                    layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 45, displayMetrics);
                    layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 45, displayMetrics);
                    iconView.setLayoutParams(layoutParams);
                    iconView.setPadding(0,0,0,30);
                }else {
                    layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, displayMetrics);
                    layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, displayMetrics);
                    iconView.setLayoutParams(layoutParams);
                }
            }
        } else {


            BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
            BottomNavigationMenuView menuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                View activeLabel = item.findViewById(R.id.largeLabel);
                if (activeLabel instanceof TextView) {
                    activeLabel.setPadding(0, 0, 0, 0);
                }
            }
//            BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
//            BottomNavigationMenuView menuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
            final View iconView = menuView.getChildAt(2).findViewById(com.google.android.material.R.id.icon);
            final ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
            final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 37, displayMetrics);
            layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 37, displayMetrics);
            iconView.setLayoutParams(layoutParams);
            iconView.setPadding(0,0,0,20);

        }

        removeShiftMode(bottomNavigation);
      //  disableShiftMode(bottomNavigation);

//        if (Constants.isReloaded) {
//            MoreFragment moreFragment = new MoreFragment();
//            switchFragment(moreFragment, moreFragment.getClass().getName());
//            bottomNavigation.setSelectedItemId(R.id.more);
//            Constants.isReloaded=false;
//        }else {
//
//            Log.w("Executed when orientation chanages","YES");
//            //Initialize default fragment
//            Fragment directoryFragment = new CalculatorFragment();
//            switchFragment(directoryFragment, directoryFragment.getClass().getName());
//            bottomNavigation.setSelectedItemId(R.id.calculator);
//
//        }
        //Initialize default fragment
        Fragment directoryFragment = new CalculatorFragment();
        switchFragment(directoryFragment, directoryFragment.getClass().getName());
        bottomNavigation.setSelectedItemId(R.id.calculator);

        bottomNavigation.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.calculator:
                                Fragment calculatorFragment = new CalculatorFragment();
                                switchFragment(calculatorFragment, calculatorFragment.getClass().getName());
                                return true;
                            case R.id.interactive_guide:
                                InteractiveGuideFragment interactiveGuideFragment = new InteractiveGuideFragment();
                                switchFragment(interactiveGuideFragment, interactiveGuideFragment.getClass().getName());
                                return true;
                            case R.id.pds_sds:
                                Fragment pdsFragment = new PDSfragment();
                                switchFragment(pdsFragment, pdsFragment.getClass().getName());
                                return true;

                            case R.id.comparator:
                                Fragment comparatorFragment = new ComparatorFragment();
                                switchFragment(comparatorFragment, comparatorFragment.getClass().getName());
                                return true;

                            case R.id.more:
                                Fragment moreFragment = new MoreFragment();
                                switchFragment(moreFragment, moreFragment.getClass().getName());
                                return true;

                        }
                        return false;
                    }
                });

    }

    public void switchFragment(Fragment fragment, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment, tag).commit();
        tittleTextView.setText("");
        Constants.TITTLES.clear();
        getSupportActionBar().hide();

    }

    public void setPadding() {

        if (getResources().getBoolean(R.bool.portrait_only)) {
            tittleTextView.setPadding((int) getResources().getDimension(R.dimen.padding_left), 0, (int) getResources().getDimension(R.dimen.padding_rigt), 0);
            tittleTextView.setGravity(Gravity.START);
        } else {
            tittleTextView.setPadding((int) getResources().getDimension(R.dimen.padding_left_for_tab), 0, (int) getResources().getDimension(R.dimen.padding_rigt_for_tab), 0);
            tittleTextView.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
        }
    }

    public void removePadding() {
        tittleTextView.setPadding(0, 0, 0, 0);
        tittleTextView.setGravity(Gravity.CENTER);
    }


    public void hideToolbar() {
        getSupportActionBar().hide();
    }

    public void showToolbar() {
        getSupportActionBar().show();
    }

    public void addFragment(Fragment fragment, String tag, String tittle) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, fragment, tag).addToBackStack(tag).commit();
        backImageVew.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.arrow_left));
        tittleTextView.setText(tittle);
        Constants.TITTLES.add(tittle);
        getSupportActionBar().show();
    }

    public void addFragmentWithoutToolbar(Fragment fragment, String tag, String tittle) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, fragment, tag).addToBackStack(tag).commit();
        backImageVew.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.arrow_left));
        tittleTextView.setText(tittle);
        Constants.TITTLES.add(tittle);
    }

    public void addFragmentForInteractiveGuide(Fragment fragment, String tag, String tittle) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().remove(fragmentManager.findFragmentById(R.id.container));
        fragmentTransaction.add(R.id.container, fragment, tag).addToBackStack(tag).commit();
        backImageVew.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.arrow_left));
        tittleTextView.setText(tittle);
        Constants.TITTLES.add(tittle);
        getSupportActionBar().show();
    }

    public void addFragmentForCertificacions(Fragment fragment, String tag, String tittle) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, fragment, tag).addToBackStack(tag).commit();
        backImageVew.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.arrow_left));
        tittleTextView.setText(tittle);
        Constants.TITTLES.add(tittle);
        getSupportActionBar().hide();
    }

    public void LoadHomeFragment() {
        Fragment directoryFragment = new CalculatorFragment();
        switchFragment(directoryFragment, directoryFragment.getClass().getName());
    }

    public void refreshActivityAfterLanguageChange() {
        reload();
        getSupportActionBar().hide();
    }

    public void reload() {
        Constants.isReloaded = true;
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

//    public static void disableShiftMode(BottomNavigationView view) {
//        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
//        try {
//            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
//            shiftingMode.setAccessible(true);
//            shiftingMode.setBoolean(menuView, false);
//            shiftingMode.setAccessible(false);
//            for (int i = 0; i < menuView.getChildCount(); i++) {
//                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
//                //noinspection RestrictedApi
//                item.setShiftingMode(false);
//                // set once again checked value, so view will be updated
//                //noinspection RestrictedApi
//                item.setChecked(item.getItemData().isChecked());
//            }
//        } catch (NoSuchFieldException e) {
//            Log.e("BNVHelper", "Unable to get shift mode field", e);
//        } catch (IllegalAccessException e) {
//            Log.e("BNVHelper", "Unable to change value of shift mode", e);
//        }
//    }


        @SuppressLint("RestrictedApi")
         void removeShiftMode(BottomNavigationView view) {
            BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                //noinspection RestrictedApi
                item.setShifting(false);
                item.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);

                // set once again checked value, so view will be updated
                //noinspection RestrictedApi
                item.setChecked(item.getItemData().isChecked());
            }
        }


    @OnClick(R.id.backImageVew)
    public void onClick() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            //Finish Activity
            // finish();
            tittleTextView.setText("");
        } else {
            getSupportFragmentManager().popBackStack();
            Log.w("back stack count", +getSupportFragmentManager().getBackStackEntryCount() + "");
            if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                if (Constants.TITTLES.size() > 0) {
                    Constants.TITTLES.remove(Constants.TITTLES.size() - 1);
                    try {
                        tittleTextView.setText(Constants.TITTLES.get(Constants.TITTLES.size() - 1));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        e.printStackTrace();
                        getSupportActionBar().hide();
                    }
                } else {
                    getSupportActionBar().hide();
                }

            } else {
                getSupportActionBar().hide();
                Constants.TITTLES.clear();
            }

        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            //Finish Activity
            finish();

        } else {
            getSupportFragmentManager().popBackStack();
            if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                if (Constants.TITTLES.size() > 0) {
                    Constants.TITTLES.remove(Constants.TITTLES.size() - 1);
                    try {
                        tittleTextView.setText(Constants.TITTLES.get(Constants.TITTLES.size() - 1));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        e.printStackTrace();
                        getSupportActionBar().hide();
                    }
                } else {
                    getSupportActionBar().hide();
                }

            } else {
                getSupportActionBar().hide();
                Constants.TITTLES.clear();
            }

        }
    }
}
