package com.example.lautechmobileapp.Users.Registration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.lautechmobileapp.MainClass;
import com.example.lautechmobileapp.R;
import com.example.lautechmobileapp.Users.LoginActivity;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RegistrationStudentActivity2 extends AppCompatActivity {

    private TextInputLayout othernameTextInput, phoneTextInput, passwordTextInput;
    private TextView signInTextView;
    private Button contBtn;
    private String matric, dob, surname, firstname, othername, phone, password;
    MainClass mainClass = new MainClass(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_student2);

        signInTextView = findViewById(R.id.haveAcctSignin2);
        othernameTextInput = findViewById(R.id.othernamesOutlinedTextField);
        phoneTextInput = findViewById(R.id.phoneOutlinedTextField);
        passwordTextInput = findViewById(R.id.passwordOutlinedTextField);
        contBtn = findViewById(R.id.continueBtn);

        //Get details from previous activity and ensure they are not empty
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            matric = extras.getString("matricNumber");
            dob = extras.getString("dateOfBirth");
            surname = extras.getString("surname");
            firstname = extras.getString("firstname");

        } else {
            Intent intent = new Intent(getApplicationContext(), RegistrationExistingStudentActivity.class);
            startActivity(intent);
        }


        //Change top bar color
        setTopBarColor();

        //Make text bold
        SpannableStringBuilder boldText = mainClass.makesignInBold("Have an account? Sign in");
        signInTextView.setText(boldText);


        //Create object for text watcher class which is used with the textinputedittext
        othernameTextInput.getEditText().addTextChangedListener(new RegistrationStudentActivity2.ValidationTextWatcher(othernameTextInput.getEditText()));
        phoneTextInput.getEditText().addTextChangedListener(new RegistrationStudentActivity2.ValidationTextWatcher(phoneTextInput.getEditText()));
        passwordTextInput.getEditText().addTextChangedListener(new RegistrationStudentActivity2.ValidationTextWatcher(passwordTextInput.getEditText()));

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

                othername= othernameTextInput.getEditText().getText().toString().trim();
                phone= phoneTextInput.getEditText().getText().toString().trim();
                password= passwordTextInput.getEditText().getText().toString().trim();

                //store user data in JSON file
                storeAsJson(matric, dob, surname, firstname, othername, phone, password);

            }
        });

        //TODO:validate values and handle sign in
    }

    public void setTopBarColor() {
        //set status bar color
        Window window = this.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.loginTop));
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

    public void storeAsJson(String matric, String dob, String surname, String firstname, String othername, String phone, String password) {

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Matric_Number", matric);
            jsonObject.put("Date_of_Birth", dob);
            jsonObject.put("Surname", surname);
            jsonObject.put("Firstname", firstname);
            jsonObject.put("Othername", othername);
            jsonObject.put("Phone", phone);
            jsonObject.put("Password", password);

            // Convert JsonObject to String Format
            String userString = jsonObject.toString();

            // Define the File Path and its Name
            File file = new File(getFilesDir(),"user-details.json");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(userString);
            bufferedWriter.close();

            Intent intent = new Intent(getApplicationContext(), OTPActivity.class);
            intent.putExtra("phoneNumber", phone);
            intent.putExtra("surname", surname);
            intent.putExtra("firstname", firstname);
            intent.putExtra("othername", othername);
            startActivity(intent);
            contBtn.setEnabled(false);
            Log.d("RegisteredUser", userString);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}