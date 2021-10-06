package com.example.veeez.feature;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.veeez.R;
import com.example.veeez.data.UserManagerProvider;
import com.example.veeez.feature.profile.UserEditViewModel;
import com.example.veeez.services.http.veeez.VeeezApiInterfaceProvider;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;
import timber.log.Timber;

public class UserEditProfileActivity extends AppCompatActivity {
    private EditText firstNameEdt;
    private EditText lastNameEdt;
    private EditText phoneNumberEdt;
    private RadioGroup genderRadioGroup;
    private EditText emailEdt;
    private EditText fullAddressEdt;
    private EditText birthDayEdt;
    private Button profileEditConfirmButton;
    private PersianDatePickerDialog picker;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private UserEditViewModel viewModel = new UserEditViewModel(VeeezApiInterfaceProvider.getInstance(),
            UserManagerProvider.getInstance(this).getUserId());
    private PersianCalendar initDate = new PersianCalendar();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit_profile);
        initViews();
        initDate.setPersianDate(1400, 7, 9);
        setListeners();
    }

    private void setListeners() {
        profileEditConfirmButton.setOnClickListener(view -> {
            int gender;
            if (genderRadioGroup.getCheckedRadioButtonId() == R.id.femaleGenderRadio)
                gender = 2;
            else
                gender = 1;
            viewModel.editProfile(firstNameEdt.getText().toString(),
                    lastNameEdt.getText().toString(),
                    phoneNumberEdt.getText().toString(),
                    gender,
                    emailEdt.getText().toString(),
                    fullAddressEdt.getText().toString(),
                    birthDayEdt.getText().toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<EditUserResponse>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                            compositeDisposable.add(d);
                        }

                        @Override
                        public void onSuccess(@NonNull EditUserResponse o) {

                            if (o.getStatus() == 1) {
                                Toast.makeText(UserEditProfileActivity.this
                                        , "اطلاعات با موفقیت ویرایش شد!!!",
                                        Toast.LENGTH_LONG).show();
                                finish();
                            }
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            Timber.e(e);
                            Toast.makeText(UserEditProfileActivity.this,
                                    "خطا در هنگام ویرایش !!!", Toast.LENGTH_LONG).show();
                        }
                    });
        });
        birthDayEdt.setOnClickListener(v -> {
            if (picker == null) {
                picker = new PersianDatePickerDialog(UserEditProfileActivity.this)
                        .setPositiveButtonString("تایید")
                        .setNegativeButton("لغو")
                        .setTodayButton("امروز")
                        .setTodayButtonVisible(true)
                        .setInitDate(initDate)
                        .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
                        .setMinYear(1300)
                        .setActionTextColor(Color.GRAY)
                        .setTypeFace(Typeface.DEFAULT)
                        .setListener(new Listener() {
                            @Override
                            public void onDateSelected(PersianCalendar persianCalendar) {
                                Toast.makeText(UserEditProfileActivity.this,
                                        persianCalendar.getPersianYear() + "/" + persianCalendar.getPersianMonth() + "/" + persianCalendar.getPersianDay(),
                                        Toast.LENGTH_SHORT).show();
                                initDate = persianCalendar;
                                birthDayEdt.setText(initDate.getPersianLongDate());
                            }

                            @Override
                            public void onDismissed() {

                            }
                        });
            }
            picker.show();
        });
    }

    private void initViews() {
        firstNameEdt = findViewById(R.id.editProfileFirstNameEditText);
        lastNameEdt = findViewById(R.id.editProfileLastNameEditText);
        phoneNumberEdt = findViewById(R.id.editProfilePhoneEditText);
        genderRadioGroup = findViewById(R.id.edit_profile_gender_radio);
        emailEdt = findViewById(R.id.editProfileEmailEditText);
        fullAddressEdt = findViewById(R.id.editProfileFullAddressEditText);
        birthDayEdt = findViewById(R.id.editProfileBirthdayText);
        profileEditConfirmButton = findViewById(R.id.editProfileConfirmButton);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (compositeDisposable != null)
            compositeDisposable.clear();
    }
}