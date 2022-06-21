package com.example.lautechmobileapp.Users.Registration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

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

public class RegistrationNewStudentActivity extends AppCompatActivity {

    private TextInputLayout surnameTextInput, firstnameTextInput, othernameTextInput;
    private TextView signInTextView;
    private Button continueBtn;
    private String surname,firstname,othername;
    MainClass mainClass = new MainClass(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_new_student);

        surnameTextInput = findViewById(R.id.surnameOutlinedTextField);
        firstnameTextInput = findViewById(R.id.firstnameOutlinedTextField);
        othernameTextInput = findViewById(R.id.othernameOutlinedTextField);
        signInTextView = findViewById(R.id.haveAcctSignin);
        continueBtn = findViewById(R.id.continueBtn);

        //Create object for text watcher class which is used with the textinputedittext
        surnameTextInput.getEditText().addTextChangedListener(new RegistrationNewStudentActivity.ValidationTextWatcher(surnameTextInput.getEditText()));
        firstnameTextInput.getEditText().addTextChangedListener(new RegistrationNewStudentActivity.ValidationTextWatcher(firstnameTextInput.getEditText()));
        othernameTextInput.getEditText().addTextChangedListener(new RegistrationNewStudentActivity.ValidationTextWatcher(othernameTextInput.getEditText()));

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

        //when continue button is clicked
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateSurname() || !validateFirstname() || !validateOthername()) {
                    return;
                }

                surname = surnameTextInput.getEditText().getText().toString().trim();
                firstname = firstnameTextInput.getEditText().getText().toString().trim();
                othername = othernameTextInput.getEditText().getText().toString().trim();
                moveToSignUpPage2(surname, firstname, othername);
            }
        });
    }


    // method to validate surname
    public boolean validateSurname() {
        if (surnameTextInput.getEditText().getText().toString().trim().isEmpty()) {
            surnameTextInput.setError("Required");
            return false;
        } else {
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

    // method to validate surname
    public boolean validateOthername() {
        if (othernameTextInput.getEditText().getText().toString().trim().isEmpty()) {
            othernameTextInput.setError("Required");
            return false;

        }else {
            othernameTextInput.setErrorEnabled(false);
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

    public void moveToSignUpPage2(String surname, String firstname, String othername){
        Intent intent = new Intent(getApplicationContext(), RegistrationNewStudentActivity2.class);
        intent.putExtra("surname", surname);
        intent.putExtra("firstname", firstname);
        intent.putExtra("othername", othername);
         startActivity(intent);
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



}