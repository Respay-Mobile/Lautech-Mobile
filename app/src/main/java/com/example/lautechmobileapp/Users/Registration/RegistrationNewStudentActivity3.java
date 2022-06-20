package com.example.lautechmobileapp.Users.Registration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.example.lautechmobileapp.MainClass;
import com.example.lautechmobileapp.R;
import com.example.lautechmobileapp.Users.LoginActivity;
import com.google.android.material.textfield.TextInputLayout;

public class RegistrationNewStudentActivity3 extends AppCompatActivity {

    private TextInputLayout emailTextInput, passwordTextInput, admissionTextInput;
    private String email, password, admission;
    private TextView signInTextView;
    private AutoCompleteTextView admissionType;
    private String[] admissionTypeText = {"Pre-Degree", "UTME", "Direct Entry", "Part-time", "Masters"};
    private Button signUpBtn;
    MainClass mainClass = new MainClass(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_new_student3);

        admissionType = findViewById(R.id.admissionDropDown);
        emailTextInput = findViewById(R.id.emailOutlinedTextField);
        passwordTextInput = findViewById(R.id.passwordOutlinedTextField);
        admissionTextInput = findViewById(R.id.admissionOutlinedTextField);

        //Array adapter for stateOfOrigindropdown and genderdropdown
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.list_item, admissionTypeText);

        //Create object for text watcher class which is used with the textinputedittext
        emailTextInput.getEditText().addTextChangedListener(new RegistrationNewStudentActivity3.ValidationTextWatcher(emailTextInput.getEditText()));
        passwordTextInput.getEditText().addTextChangedListener(new RegistrationNewStudentActivity3.ValidationTextWatcher(passwordTextInput.getEditText()));
        admissionTextInput.getEditText().addTextChangedListener(new RegistrationNewStudentActivity3.ValidationTextWatcher(admissionTextInput.getEditText()));

        //Set the adapter
        admissionType.setAdapter(adapter1);

        signInTextView = findViewById(R.id.haveAcctSignin);
        signUpBtn = findViewById(R.id.signUpBtn);

        //hide soft keyboard for drop downs
        admissionType.setInputType(InputType.TYPE_NULL);
        admissionType.setKeyListener(null);


        //Change top bar color
        setTopBarColor();

        //Make text bold
        SpannableStringBuilder boldText = mainClass.makesignInBold("Have an account? Sign in");
        signInTextView.setText(boldText);

        //when sign in textview is clicked
        signInTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validateMail() || !validatePassword() || !validatePassword()){
                    return;
                }
            }
        });

    }


    // method to validate phone
    public boolean validateMail() {
        if (emailTextInput.getEditText().getText().toString().trim().isEmpty()) {
            emailTextInput.setError("Required");
            return false;
        } else {
            String emailId = emailTextInput.getEditText().getText().toString();
            Boolean isValid = Patterns.EMAIL_ADDRESS.matcher(emailId).matches();

            if (!isValid) {
                emailTextInput.setError("Invalid Email Address, ex: abc@example.com");
                return false;
            } else {
                emailTextInput.setErrorEnabled(false);

            }
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
            passwordTextInput.setError("Password must be at least 6 character");
            return false;
        }
        else {
            passwordTextInput.setErrorEnabled(false);
        }
        return true;

    }

    // method to validate admission
    public boolean validateAdmission() {
        if (admissionTextInput.getEditText().getText().toString().trim().isEmpty()) {
            admissionTextInput.setError("Required");
            return false;

        }else {
            admissionTextInput.setErrorEnabled(false);
        }
        return true;

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
                case R.id.emailTextInputEditText:
                    validateMail();
                    break;

                case R.id.passwordTextInputEditText:
                    validatePassword();
                    break;

                case R.id.admissionDropDown:
                    validateAdmission();
                    break;

            }
        }

    }


}