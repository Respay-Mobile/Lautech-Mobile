package com.example.lautechmobileapp.Users;

import androidx.appcompat.app.AlertDialog;
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

import com.example.lautechmobileapp.R;
import com.example.lautechmobileapp.SetupAccountActivity;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private TextView createAccoutnTextView, forgotPassTextView;
    private TextInputLayout emailTextInput, passwordTextInput;
    private Button signIn;
    private String emailText, passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        createAccoutnTextView = findViewById(R.id.dontHaveAccoutnTextView);
        signIn = findViewById(R.id.signInBtn);
        emailTextInput = findViewById(R.id.emailOutlinedTextField);
        passwordTextInput = findViewById(R.id.passwordOutlinedTextField);

        //set status bar color
        Window window = this.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.loginTop));

        //Create object for text watcher class which is used with the textinputedittext
        emailTextInput.getEditText().addTextChangedListener(new LoginActivity.ValidationTextWatcher(emailTextInput.getEditText()));
        passwordTextInput.getEditText().addTextChangedListener(new LoginActivity.ValidationTextWatcher(passwordTextInput.getEditText()));

        //Using SpannableStringBuilder method to make text bold
        SpannableStringBuilder str = new SpannableStringBuilder("Dont have an account? Create Here");
        str.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 22 ,33, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        createAccoutnTextView.setText(str);

        //When sign in is clicked
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!validateEmail() || !validatePassword()) {
                    return;
                }

                //Get username and password then pass it to signIn method
                emailText =  emailTextInput.getEditText().getText().toString().trim();
                passwordText = passwordTextInput.getEditText().getText().toString().trim();
                signIn(emailText, passwordText);

            }
        });
    }


    public void signIn(String a, String b){
        //TODO: method for sign in operation
        Intent intent = new Intent(getApplicationContext(), SetupAccountActivity.class);
        startActivity(intent);
    }


    //If user clicks back button show alert dialog
    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder
                .setMessage(getString(R.string.sureToExit))
                .setCancelable(true)
                .setPositiveButton(getString(R.string.no),
                        (dialog, which) -> dialog.dismiss())
                .setNegativeButton(getString(R.string.yesToExit),
                        (dialog, which) -> finishAffinity());

        AlertDialog dialog = alertDialogBuilder.create();
        dialog.show();
    }


    // method to validateEmail email address
    public boolean validateEmail() {
        if (emailTextInput.getEditText().getText().toString().trim().isEmpty()) {
            emailTextInput.setError("Required");
            return false;
        } else {
            emailTextInput.setErrorEnabled(false);
        }
        return true;

    }

    // method to validateEmail email address
    public boolean validatePassword() {
        if (passwordTextInput.getEditText().getText().toString().trim().isEmpty()) {
            passwordTextInput.setError("Required");
            return false;

        }else if(passwordTextInput.getEditText().getText().toString().length() <= 6){
            passwordTextInput.setError("Password must be at least 6 characters");
            return false;
        }else {
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
                case R.id.emailTextInputEditText:
                    validateEmail();
                    break;

                case R.id.passwordTextInputEditText:
                    validatePassword();
                    break;

            }
        }

    }


}
