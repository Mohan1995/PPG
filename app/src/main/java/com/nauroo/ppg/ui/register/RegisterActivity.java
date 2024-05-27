package com.nauroo.ppg.ui.register;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import com.google.android.material.textfield.TextInputLayout;
import androidx.core.content.ContextCompat;
import androidx.core.widget.CompoundButtonCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.nauroo.ppg.R;
import com.nauroo.ppg.model.CountryModel;
import com.nauroo.ppg.model.GenericResponse;
import com.nauroo.ppg.model.User;
import com.nauroo.ppg.network.NetworkAdapter;
import com.nauroo.ppg.network.ResponseCallback;
import com.nauroo.ppg.ui.home.HomeActivity;
import com.nauroo.ppg.utils.Constants;
import com.nauroo.ppg.utils.CustomSpinnerAdapter;
import com.nauroo.ppg.utils.InternetAvailability;
import com.nauroo.ppg.utils.LocaleHelper;
import com.nauroo.ppg.utils.UserUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.userNameEditText)
    EditText userNameEditText;
    @BindView(R.id.userNameTextInputLayout)
    TextInputLayout userNameTextInputLayout;
    @BindView(R.id.emailEditText)
    EditText emailEditText;
    @BindView(R.id.emailInputLayout)
    TextInputLayout emailInputLayout;
    @BindView(R.id.companyEditText)
    EditText companyEditText;
    @BindView(R.id.companyInputLayout)
    TextInputLayout companyInputLayout;
    @BindView(R.id.countrySpinner)
    Spinner countrySpinner;

    @BindView(R.id.checkBox1)
    CheckBox checkBox1;
    @BindView(R.id.checkBox2)
    CheckBox checkBox2;
    @BindView(R.id.signUpButton)
    Button signUpButton;
    int checkboxOption = 0;
    @BindView(R.id.backImageVew)
    ImageView backImageVew;
    @BindView(R.id.content_register)
    RelativeLayout contentRegister;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    ArrayList<CountryModel> countryModelList;
    ArrayList<CountryModel> modifiedCountryModelList;
    int check = 0;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.cityEditText)
    EditText cityEditText;
    @BindView(R.id.cityInputLayout)
    TextInputLayout cityInputLayout;
    @BindView(R.id.segmentEditText)
    EditText segmentEditText;
    @BindView(R.id.segmentInputField)
    TextInputLayout segmentInputField;
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
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();
        ColorStateList colorStateList = new ColorStateList(
                new int[][]{
                        new int[]{-android.R.attr.state_checked}, // unchecked
                        new int[]{android.R.attr.state_checked}, // checked
                },
                new int[]{
                        Color.parseColor("#ffffff"),
                        Color.parseColor("#3dabd0"),
                }
        );

        if (getIntent().getBooleanExtra(Constants.FINISH, false) != false) {
            signUpButton.setText(getString(R.string.save));
        }
        if (getIntent().getBooleanExtra(Constants.FINISH, false) == false) {
            tittleTextView.setText(Html.fromHtml(getString(R.string.user_registration)));
        }else {
            tittleTextView.setText(Html.fromHtml(getString(R.string.my_profile_bold)));
        }
        getCountry();
        CompoundButtonCompat.setButtonTintList(checkBox1, colorStateList);
        CompoundButtonCompat.setButtonTintList(checkBox2, colorStateList);

        backImageVew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        checkBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkboxOption = 1;
                if (checkBox1.isChecked()) {
                    checkBox1.setTextColor(ContextCompat.getColor(RegisterActivity.this, R.color.white));
                } else {
                    checkBox1.setTextColor(ContextCompat.getColor(RegisterActivity.this, R.color.colorAccent));
                }
            }
        });
        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkboxOption = 2;
                if (checkBox2.isChecked()) {
                    checkBox2.setTextColor(ContextCompat.getColor(RegisterActivity.this, R.color.white));
                } else {
                    checkBox2.setTextColor(ContextCompat.getColor(RegisterActivity.this, R.color.colorAccent));
                }
            }
        });


    }

    private void setUserData() {
        signUpButton.setText(getString(R.string.save));
        User user = UserUtils.getUserProfile(RegisterActivity.this);
        userNameEditText.setText(user.getName() + " " + user.getLast_name());
        companyEditText.setText(user.getCompany());
        emailEditText.setText(user.getEmail());
        if (user.isGet_info()) {
            checkBox2.setChecked(true);
            checkboxOption = 2;
        }
        if (user.isExecutive_contact()) {
            checkBox1.setChecked(true);
            checkboxOption = 1;
        }
        Log.w("Country ID", user.getCountry_id());
        for (int j = 0; j < countryModelList.size(); j++) {
            if (user.getCountry_id().equals(countryModelList.get(j).getId())) {
                countrySpinner.setSelection(j + 1);
            }
        }
        segmentEditText.setText(user.getSegment());
        cityEditText.setText(user.getCity());


    }


    private void getCountry() {
        NetworkAdapter.getInstance(RegisterActivity.this).getCountry(new ResponseCallback<GenericResponse<ArrayList<CountryModel>>>(RegisterActivity.this) {
            @Override
            public void onResponse(GenericResponse<ArrayList<CountryModel>> response) {
                hideProgressBar();
                if (response.getStatus() == Constants.RESPONSE_SUCCESS) {
                    countryModelList = response.getData();
                    ArrayList<String> countryNameList = new ArrayList<String>();
                    countryNameList.add(getString(R.string.country));
                    for (int i = 0; i < countryModelList.size(); i++) {
                        if (i == 4) {
                            countryNameList.add("");
                        }
                        countryNameList.add(countryModelList.get(i).getName());
                    }
//                    ArrayAdapter<String> temperatureAdapter = new ArrayAdapter<String>(RegisterActivity.this,
//                            R.layout.custom_spinner_item1, countryNameList);
//                    Resources res = getResources();
//                    com.nauroo.ppg.ui.register.CustomSpinnerAdapter customSpinnerAdapter = new com.nauroo.ppg.ui.register.CustomSpinnerAdapter(RegisterActivity.this, R.layout.custom_spinner_drop_down_item, countryModelList, res);
                    CustomSpinnerAdapter dataAdapter = new CustomSpinnerAdapter(RegisterActivity.this, R.layout.custom_spinner_item1, countryNameList, 0);
                    dataAdapter.setDropDownViewResource(R.layout.custom_spinner_drop_down_item1);
                    countrySpinner.setAdapter(dataAdapter);
                    if (UserUtils.getUserId(RegisterActivity.this) != null) {
                        setUserData();
                    }
                }

            }

            @Override
            public void onFailure() {

            }
        });
    }

    @OnClick(R.id.signUpButton)
    public void onClick() {
        String[] fullName = userNameEditText.getText().toString().split(" ");
        if (userNameEditText.getText().length() == 0) {
            userNameEditText.setError(getString(R.string.user_name_is_required));
        } else if (fullName.length < 2) {
            Toast.makeText(RegisterActivity.this, getString(R.string.surnanme_is_required), Toast.LENGTH_SHORT).show();
        } else if (emailEditText.getText().length() == 0) {
            emailEditText.setError(getString(R.string.email_is_required));
        } else if (emailEditText.getText().length() != 0 && !isValidEmail(emailEditText.getText().toString())) {
            Toast.makeText(RegisterActivity.this, getString(R.string.please_enter_vaild_email_id), Toast.LENGTH_SHORT).show();
        } else if (companyEditText.getText().length() == 0) {
            companyEditText.setError(getString(R.string.company_field_is_required));
        } else if (checkboxOption == 0) {
            Toast.makeText(RegisterActivity.this, getString(R.string.please_select_checkbox), Toast.LENGTH_SHORT).show();
        } else if (segmentEditText.getText().length() == 0) {
            Toast.makeText(RegisterActivity.this, getString(R.string.please_select_segment), Toast.LENGTH_SHORT).show();
        } else if (countrySpinner.getSelectedItemPosition() == 0) {
            Toast.makeText(RegisterActivity.this, getString(R.string.please_select_country), Toast.LENGTH_SHORT).show();
        } else if (cityEditText.getText().length() == 0) {
            Toast.makeText(RegisterActivity.this, getString(R.string.please_select_city), Toast.LENGTH_SHORT).show();
        } else {
            RegisterToServer();
        }
    }

    private void RegisterToServer() {
        if (InternetAvailability.isNetworkAvailable(RegisterActivity.this)) {
            showProgressBar();
            final User user = new User();
            String[] fullName = userNameEditText.getText().toString().split(" ");
            if (fullName.length > 1) {
                user.setName(fullName[0]);
                user.setLast_name(fullName[1]);
            } else {
                user.setName(userNameEditText.getText().toString());
            }
            user.setSegment(segmentEditText.getText().toString());
            user.setEmail(emailEditText.getText().toString());
            user.setCompany(companyEditText.getText().toString());
            user.setCountry_id(countryModelList.get(countrySpinner.getSelectedItemPosition() - 1).getId());
            user.setCity(cityEditText.getText().toString());
            user.setExecutive_contact(false);
            user.setGet_info(false);
            if (checkBox1.isChecked()) {
                user.setExecutive_contact(true);
            }
            if (checkBox2.isChecked()) {
                user.setGet_info(true);
            }
            user.setInterests("XX");
            NetworkAdapter.getInstance(RegisterActivity.this).register(user, new ResponseCallback<GenericResponse<String>>(RegisterActivity.this) {
                @Override
                public void onResponse(GenericResponse<String> response) {
                    if (response.getStatus() == Constants.RESPONSE_SUCCESS) {
                        UserUtils.setUserProfile(RegisterActivity.this, user);
                        Toast.makeText(getApplicationContext(), getString(R.string.updated_caps), Toast.LENGTH_SHORT).show();
                        if (getIntent().getBooleanExtra(Constants.FINISH, false) == false) {
                            moveToHomeActivity();
                        } else {
                            finish();
                        }
                    }
                }

                @Override
                public void onFailure() {

                }
            });


        } else {
            Toast.makeText(RegisterActivity.this, getString(R.string.internet_is_not_available), Toast.LENGTH_SHORT).show();
        }
    }

    private void moveToHomeActivity() {
        Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    public boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
