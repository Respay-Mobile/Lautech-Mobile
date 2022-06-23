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

    private TextInputLayout staffIdTextInput, surnameTextInput, firstnameTextInput, othernameTextInput;
    private TextView signInTextView;
    private Button nextBtn;
    private String staffId,surname,firstname,othername;
    MainClass mainClass = new MainClass(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_staff);

        signInTextView = findViewById(R.id.haveAcctSignin);

        staffIdTextInput = findViewById(R.id.staffNumOutlinedTextField);
        surnameTextInput = findViewById(R.id.surnameOutlinedTextField);
        firstnameTextInput = findViewById(R.id.firstnameOutlinedTextField);
        othernameTextInput = findViewById(R.id.othernamesOutlinedTextField);

        //  DOBEditText = findViewById(R.id.othernameTextInputEditText);

        nextBtn = findViewById(R.id.continueBtn);


        //Change top bar color
        setTopBarColor();

        //Make text bold
        SpannableStringBuilder boldText = mainClass.makesignInBold("Have an account? Sign in");
        signInTextView.setText(boldText);

        //Create object for text watcher class which is used with the textinputedittext
        staffIdTextInput.getEditText().addTextChangedListener(new RegistrationStaffActivity.ValidationTextWatcher(staffIdTextInput.getEditText()));
        othernameTextInput.getEditText().addTextChangedListener(new RegistrationStaffActivity.ValidationTextWatcher(othernameTextInput.getEditText()));
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

                if (!validateStaffId() || !validateOthername() || !validateSurname() || !validateFirstname()) {
                    return;
                }

                staffId = staffIdTextInput.getEditText().getText().toString().trim();
                surname = surnameTextInput.getEditText().getText().toString().trim();
                firstname = firstnameTextInput.getEditText().getText().toString().trim();
                othername = othernameTextInput.getEditText().getText().toString().trim();
                moveToSignUpPage2(staffId, surname, firstname, othername);
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
    public boolean validateOthername() {
        if (othernameTextInput.getEditText().getText().toString().trim().isEmpty()) {
            othernameTextInput.setError("Required");
            return false;

        }else {
            othernameTextInput.setErrorEnabled(false);
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

                case R.id.othernameTextInputEditText:
                    validateOthername();
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

    public void moveToSignUpPage2(String staffId, String othername, String surname, String firstname){
        Intent intent = new Intent(getApplicationContext(), RegistrationStaffActivity2.class);
        intent.putExtra("staffId", staffId );
        intent.putExtra("surname", surname );
        intent.putExtra("firstname", firstname);
        intent.putExtra("othername", othername );
        startActivity(intent);
    }

}