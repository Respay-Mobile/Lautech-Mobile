package com.example.lautechmobileapp.Users.Registration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
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

public class AlumniRegistrationActivity extends AppCompatActivity {

    private TextInputLayout matricTextInput, DOBTextInput, passwordTextInput;
    private EditText DOBEditText;
    private TextView signInTextView;
    private Button continueBtn;
    private String matric,DOB,password;
    MainClass mainClass = new MainClass(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumni_registration);

        matricTextInput = findViewById(R.id.matricOutlinedTextField);
        DOBTextInput = findViewById(R.id.DOBOutlinedTextField);
        passwordTextInput = findViewById(R.id.passwordOutlinedTextField);
        continueBtn = findViewById(R.id.continueBtn);
        signInTextView = findViewById(R.id.haveAcctSignin);

        DOBEditText = findViewById(R.id.DOBTextInputEditText);
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
        matricTextInput.getEditText().addTextChangedListener(new AlumniRegistrationActivity.ValidationTextWatcher(matricTextInput.getEditText()));
        DOBTextInput.getEditText().addTextChangedListener(new AlumniRegistrationActivity.ValidationTextWatcher(DOBTextInput.getEditText()));
        passwordTextInput.getEditText().addTextChangedListener(new AlumniRegistrationActivity.ValidationTextWatcher(passwordTextInput.getEditText()));


        //when sign in textview is clicked
        signInTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        //When next button clicked
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!validateMatric() || !validateDOB() || !validatePassword()) {
                    return;
                }

                matric = matricTextInput.getEditText().getText().toString().trim();
                DOB = DOBTextInput.getEditText().getText().toString().trim();
                password = passwordTextInput.getEditText().getText().toString().trim();
                //TODO: moveToSignUpPage2(matric, DOB, surname, firstname);
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
    public boolean validateDOB() {
        if (DOBTextInput.getEditText().getText().toString().trim().isEmpty()) {
            DOBTextInput.setError("Required");
            return false;

        }else {
            DOBTextInput.setErrorEnabled(false);
        }
        return true;

    }

    // method to validate password
    public boolean validatePassword() {
        int password = passwordTextInput.getEditText().getText().toString().trim().length();
        if (passwordTextInput.getEditText().getText().toString().trim().isEmpty()) {
            passwordTextInput.setError("Required");
            return false;
        } else if(password < 6){
            passwordTextInput.setError("Password must be at least 6 characters");
            return false;
        } else {
            passwordTextInput.setErrorEnabled(false);
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

                case R.id.DOBTextInputEditText:
                    validateDOB();
                    break;

                case R.id.passwordTextInputEditText:
                    validatePassword();
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


}