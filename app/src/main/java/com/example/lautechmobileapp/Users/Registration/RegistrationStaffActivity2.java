package com.example.lautechmobileapp.Users.Registration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AutomaticZenRule;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.lautechmobileapp.MainClass;
import com.example.lautechmobileapp.R;
import com.example.lautechmobileapp.Users.LoginActivity;
import com.google.android.material.textfield.TextInputLayout;

public class RegistrationStaffActivity2 extends AppCompatActivity {

    private TextInputLayout othernameTextInput, phoneTextInput, passwordTextInput;
    private TextView signInTextView;
    private Button contBtn;
    MainClass mainClass = new MainClass(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_staff2);

        signInTextView = findViewById(R.id.haveAcctSignin2);
        othernameTextInput = findViewById(R.id.othernamesOutlinedTextField);
        phoneTextInput = findViewById(R.id.phoneOutlinedTextField);
        passwordTextInput = findViewById(R.id.passwordOutlinedTextField);
        contBtn = findViewById(R.id.continueBtn);

        //Change top bar color
        setTopBarColor();

        //Make text bold
        SpannableStringBuilder boldText = mainClass.makesignInBold("Have an account? Sign in");
        signInTextView.setText(boldText);

        //Create object for text watcher class which is used with the textinputedittext
        othernameTextInput.getEditText().addTextChangedListener(new RegistrationStaffActivity2.ValidationTextWatcher(othernameTextInput.getEditText()));
        phoneTextInput.getEditText().addTextChangedListener(new RegistrationStaffActivity2.ValidationTextWatcher(phoneTextInput.getEditText()));
        passwordTextInput.getEditText().addTextChangedListener(new RegistrationStaffActivity2.ValidationTextWatcher(passwordTextInput.getEditText()));

        //when sign in textview is clicked
        signInTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        contBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateOtherName() || !validatephoneNumber() || !validatePassword()) {
                    return;
                }

            }
        });

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


    // method to validate othername
    public boolean validateOtherName() {
        if (othernameTextInput.getEditText().getText().toString().trim().isEmpty()) {
            othernameTextInput.setError("Required");
            return false;
        } else {
            othernameTextInput.setErrorEnabled(false);
        }
        return true;
    }

    // method to validate phonenumber
    public boolean validatephoneNumber() {
        int phone = phoneTextInput.getEditText().getText().toString().trim().length();
        if (phoneTextInput.getEditText().getText().toString().trim().isEmpty()) {
            phoneTextInput.setError("Required");
            return false;
        } else if(phone < 11 ||phone > 11 ){
            phoneTextInput.setError("Invalid phone number");
            return false;
        }else {
            phoneTextInput.setErrorEnabled(false);
        }
        return true;
    }


    public boolean validatePassword() {
        // method to validate othername
        int password = passwordTextInput.getEditText().getText().toString().trim().length();
        if (passwordTextInput.getEditText().getText().toString().trim().isEmpty()) {
            passwordTextInput.setError("Required");
            return false;
        }else if(password < 6){
            passwordTextInput.setError("Can not be less than 6 characters");
        }else {
            passwordTextInput.setErrorEnabled(false);
        }
        return true;
    } //Text watcher class which will be used with the validateEmail method

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
            switch (view.getId()) {
                case R.id.othernamesTextInputEditText:
                    validateOtherName();
                    break;

                case R.id.phoneTextInputEditText:
                    validatephoneNumber();
                    break;

                case R.id.passwordTextInputEditText:
                    validatePassword();
                    break;

            }
        }
    }

}