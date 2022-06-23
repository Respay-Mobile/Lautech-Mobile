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
import android.util.Log;
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
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class RegistrationExistingStudentActivity extends AppCompatActivity {

    private TextInputLayout matricTextInput, othernameTextInput, surnameTextInput, firstnameTextInput;
    private EditText othernameEditText;
    private TextView signInTextView;
    private Button nextBtn;
    private String matric,othername,surname,firstname;
    MainClass mainClass = new MainClass(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_existing_student);

        signInTextView = findViewById(R.id.haveAcctSignin);

        matricTextInput = findViewById(R.id.matricOutlinedTextField);
        othernameTextInput = findViewById(R.id.othernamesOutlinedTextField);
        surnameTextInput = findViewById(R.id.surnameOutlinedTextField);
        firstnameTextInput = findViewById(R.id.firstnameOutlinedTextField);

        nextBtn = findViewById(R.id.continueBtn);

        //Change top bar color
        setTopBarColor();

        //Make text bold
        SpannableStringBuilder boldText = mainClass.makesignInBold("Have an account? Sign in");
        signInTextView.setText(boldText);

        //Create object for text watcher class which is used with the textinputedittext
        matricTextInput.getEditText().addTextChangedListener(new RegistrationExistingStudentActivity.ValidationTextWatcher(matricTextInput.getEditText()));
        othernameTextInput.getEditText().addTextChangedListener(new RegistrationExistingStudentActivity.ValidationTextWatcher(othernameTextInput.getEditText()));
        surnameTextInput.getEditText().addTextChangedListener(new RegistrationExistingStudentActivity.ValidationTextWatcher(surnameTextInput.getEditText()));
        firstnameTextInput.getEditText().addTextChangedListener(new RegistrationExistingStudentActivity.ValidationTextWatcher(firstnameTextInput.getEditText()));

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

                if (!validateMatric() || !validateOthername() || !validateSurname() || !validateFirstname()) {
                    return;
                }

                matric = matricTextInput.getEditText().getText().toString().trim();
                othername = othernameTextInput.getEditText().getText().toString().trim();
                surname = surnameTextInput.getEditText().getText().toString().trim();
                firstname = firstnameTextInput.getEditText().getText().toString().trim();
                moveToSignUpPage2(matric, othername, surname, firstname);
            }
        });
    }


    // method to validate matric number
    public boolean validateMatric() {
        if (matricTextInput.getEditText().getText().toString().trim().isEmpty()) {
            matricTextInput.setError("Required");
            return false;
        } else {
            matricTextInput.setErrorEnabled(false);
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
                    validateMatric();
                    break;

                case R.id.surnameTextInputEditText:
                    validateSurname();
                    break;

                case R.id.firstnameTextInputEditText:
                    validateFirstname();
                    break;

                case R.id.othernamesTextInputEditText:
                    validateOthername();
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

    public void moveToSignUpPage2(String matric, String surname, String firstname, String othername){
        Intent intent = new Intent(getApplicationContext(), RegistrationStudentActivity2.class);
        intent.putExtra("matricNumber", matric );
        intent.putExtra("surname", surname);
        intent.putExtra("firstname", firstname);
        intent.putExtra("othername", othername);
        startActivity(intent);
    }

}