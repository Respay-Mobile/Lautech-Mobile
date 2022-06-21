package com.example.lautechmobileapp.Users.Registration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lautechmobileapp.MainClass;
import com.example.lautechmobileapp.R;
import com.example.lautechmobileapp.Users.LoginActivity;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class RegistrationStaffActivity extends AppCompatActivity {

    private TextInputLayout staffIdTextInput, DOBTextInput, surnameTextInput, firstnameTextInput;
    private EditText DOBEditText;
    private TextView signInTextView;
    private Button nextBtn;
    private String staffId,DOB,surname,firstname;
    MainClass mainClass = new MainClass(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_staff);

        signInTextView = findViewById(R.id.haveAcctSignin);

        staffIdTextInput = findViewById(R.id.staffNumOutlinedTextField);
        DOBTextInput = findViewById(R.id.DOBOutlinedTextField);
        surnameTextInput = findViewById(R.id.surnameOutlinedTextField);
        firstnameTextInput = findViewById(R.id.firstnameOutlinedTextField);
        DOBEditText = findViewById(R.id.DOBTextInputEditText);

        nextBtn = findViewById(R.id.continueBtn);

        DOBEditText.setInputType(InputType.TYPE_NULL);
        DOBEditText.setKeyListener(null);
        DOBEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCalender();
            }
        });

        //Change top bar color
        setTopBarColor();

        //Make text bold
        SpannableStringBuilder boldText = mainClass.makesignInBold("Have an account? Sign in");
        signInTextView.setText(boldText);

        //Create object for text watcher class which is used with the textinputedittext
        staffIdTextInput.getEditText().addTextChangedListener(new RegistrationStaffActivity.ValidationTextWatcher(staffIdTextInput.getEditText()));
        DOBTextInput.getEditText().addTextChangedListener(new RegistrationStaffActivity.ValidationTextWatcher(DOBTextInput.getEditText()));
        surnameTextInput.getEditText().addTextChangedListener(new RegistrationStaffActivity.ValidationTextWatcher(surnameTextInput.getEditText()));
        firstnameTextInput.getEditText().addTextChangedListener(new RegistrationStaffActivity.ValidationTextWatcher(firstnameTextInput.getEditText()));

        //when sign in textview is clicked
        signInTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        //When next button clicked
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!validateStaffId() || !validateDOB() || !validateSurname() || !validateFirstname()) {
                    return;
                }

                staffId = staffIdTextInput.getEditText().getText().toString().trim();
                DOB = DOBTextInput.getEditText().getText().toString().trim();
                surname = surnameTextInput.getEditText().getText().toString().trim();
                firstname = firstnameTextInput.getEditText().getText().toString().trim();
                moveToSignUpPage2(staffId, DOB, surname, firstname);
            }
        });
    }




    public void showCalender(){
        //Create Instance of materialdatePicker
        MaterialDatePicker.Builder materialDateBuilder = MaterialDatePicker.Builder.datePicker();

        // now define the properties of the
        // materialDateBuilder that is title text as SELECT A DATE
        materialDateBuilder.setTitleText("Select Date of Birth");

        // now create the instance of the material date
        // picker
        final MaterialDatePicker materialDatePicker = materialDateBuilder.build();

        materialDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");

        //handling the positive button click from the
        materialDatePicker.addOnPositiveButtonClickListener(
                new MaterialPickerOnPositiveButtonClickListener<Long>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onPositiveButtonClick(Long selection) {
                        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                        calendar.setTimeInMillis(selection);
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        String formattedDate  = format.format(calendar.getTime());
                        DOBEditText.setText(formattedDate);

                    }
                });

    }




    // method to validate staff ID
    public boolean validateStaffId() {
        if (staffIdTextInput.getEditText().getText().toString().trim().isEmpty()) {
            staffIdTextInput.setError("Required");
            return false;
        } else {
            staffIdTextInput.setErrorEnabled(false);
        }
        return true;

    }

    // method to validate DOB
    public boolean validateDOB() {
        if (DOBTextInput.getEditText().getText().toString().trim().isEmpty()) {
            DOBTextInput.setError("Required");
            return false;

        }else {
            DOBTextInput.setErrorEnabled(false);
        }
        return true;

    }

    // method to validate surname
    public boolean validateSurname() {
        if (surnameTextInput.getEditText().getText().toString().trim().isEmpty()) {
            surnameTextInput.setError("Required");
            return false;

        }else {
            surnameTextInput.setErrorEnabled(false);
        }
        return true;

    }

    // method to validate firstname
    public boolean validateFirstname() {
        if (firstnameTextInput.getEditText().getText().toString().trim().isEmpty()) {
            firstnameTextInput.setError("Required");
            return false;
        }else {
            firstnameTextInput.setErrorEnabled(false);
        }
        return true;

    }


    //Text watcher class which will be used with the validateEmail method
    private class ValidationTextWatcher implements TextWatcher {
        private View view;

        private ValidationTextWatcher(View view) {
            this.view = view;
        }
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }
        public void afterTextChanged(Editable editable) {
            switch (view.getId()){
                case R.id.matricTextInputEditText:
                    validateStaffId();
                    break;

                case R.id.DOBTextInputEditText:
                    validateDOB();
                    break;

                case R.id.surnameTextInputEditText:
                    validateSurname();
                    break;

                case R.id.firstnameTextInputEditText:
                    validateFirstname();
                    break;

            }
        }

    }



    public void setTopBarColor(){
        //set status bar color
        Window window = this.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.loginTop));
    }

    public void moveToSignUpPage2(String staffId, String DOB, String surname, String firstname){
        Intent intent = new Intent(getApplicationContext(), RegistrationStaffActivity2.class);
        intent.putExtra("staffId", staffId );
        intent.putExtra("dateOfBirth", DOB );
        intent.putExtra("surname", surname );
        intent.putExtra("firstname", firstname);
        startActivity(intent);
    }

}