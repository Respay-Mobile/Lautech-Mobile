package com.example.lautechmobileapp.Users;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.lautechmobileapp.Dashboard.HomeActivity;
import com.example.lautechmobileapp.MainClass;
import com.example.lautechmobileapp.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private TextView haveAccountTextView, forgotPassTextView;
    private TextInputLayout emailTextInput, passwordTextInput;
    private Button signIn;
    private String emailText, passwordText;
    MainClass mainClass = new MainClass(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        haveAccountTextView = findViewById(R.id.haveAcctSignin);
        signIn = findViewById(R.id.signInBtn);
        emailTextInput = findViewById(R.id.emailOutlinedTextField);
        passwordTextInput = findViewById(R.id.passwordOutlinedTextField);

        setTopBarColor();

        //Make text bold
        SpannableStringBuilder boldText = mainClass.makecreateHereBold("Don't have an account? Create Here");
        haveAccountTextView.setText(boldText);

        //When sign in is clicked
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!validateEmail() || !validatepassword()) {
                    return;
                }

                //Get username and password then pass it to signIn method
                emailText =  emailTextInput.getEditText().getText().toString().trim();
                passwordText = passwordTextInput.getEditText().getText().toString().trim();
                signIn(emailText, passwordText, view);

            }
        });

        haveAccountTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToSignUpPage();
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


    public void moveToSignUpPage(){
        Intent intent = new Intent(getApplicationContext(), AccountSetupActivity.class);
        startActivity(intent);
    }

    public void signIn(String user, String pass, View view){
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        Log.d("User", user);
        Log.d("Pass", pass);
        if(user.equals("admin@gmail.com")  && pass.equals("admin")){
            startActivity(intent);
            emailTextInput.getEditText().getText().clear();
            passwordTextInput.getEditText().getText().clear();
        } else if(user.equals("134/321") && pass.equals("admin")){
            startActivity(intent);
            emailTextInput.getEditText().getText().clear();
            passwordTextInput.getEditText().getText().clear();

        } else if(user.equals("12345") && pass.equals("admin")){
            startActivity(intent);
            emailTextInput.getEditText().getText().clear();
            passwordTextInput.getEditText().getText().clear();

        } else {

            showErr("Invalid username or password", view);
        }

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

    public boolean validatepassword() {
        if (passwordTextInput.getEditText().getText().toString().trim().isEmpty()) {
            passwordTextInput.setError("Required");
            return false;
        } else {
            passwordTextInput.setErrorEnabled(false);
        }
        return true;

    }



    //Method for error
    public void showErr(String errorMessage, View view){
        Snackbar snackbar = Snackbar.make(view, errorMessage, Snackbar.LENGTH_SHORT);
        snackbar.setBackgroundTint(getResources().getColor(R.color.onbBtn));
        snackbar.show();
    }

}
