package com.example.lautechmobileapp.Users.Registration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.AutomaticZenRule;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.TextView;

import com.example.lautechmobileapp.MainClass;
import com.example.lautechmobileapp.R;
import com.example.lautechmobileapp.Users.LoginActivity;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class RegistrationStaffActivity2 extends AppCompatActivity {

    private TextInputLayout DOBTextInput, phoneTextInput, passwordTextInput;
    private TextInputEditText DOBEditText;
    private TextView signInTextView;
    private Button contBtn;
    private String staffId, DOB, surname, firstname, othername, phone, password;
    MainClass mainClass = new MainClass(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_staff2);

        signInTextView = findViewById(R.id.haveAcctSignin2);
        DOBTextInput = findViewById(R.id.DOBOutlinedTextField);
        phoneTextInput = findViewById(R.id.phoneOutlinedTextField);
        passwordTextInput = findViewById(R.id.passwordOutlinedTextField);
        contBtn = findViewById(R.id.continueBtn);

        //Get details from previous activity and ensure they are not empty
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            staffId = extras.getString("staffId");
            othername = extras.getString("othername");
            surname = extras.getString("surname");
            firstname = extras.getString("firstname");

        } else {
            Intent intent = new Intent(getApplicationContext(), RegistrationStaffActivity.class);
            startActivity(intent);
        }

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
        DOBTextInput.getEditText().addTextChangedListener(new RegistrationStaffActivity2.ValidationTextWatcher(DOBTextInput.getEditText()));
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
                if (!validateDOB() || !validatephoneNumber() || !validatePassword()) {
                    return;
                }

                othername= DOBTextInput.getEditText().getText().toString().trim();
                phone= phoneTextInput.getEditText().getText().toString().trim();
                password= passwordTextInput.getEditText().getText().toString().trim();

                //store user data in JSON file
                storeAsJson(staffId, DOB, surname, firstname, othername, phone, password);


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
    public boolean validateDOB() {
        if (DOBTextInput.getEditText().getText().toString().trim().isEmpty()) {
            DOBTextInput.setError("Required");
            return false;
        } else {
            DOBTextInput.setErrorEnabled(false);
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
                    validateDOB();
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
            jsonObject.put("StaffId", matric);
            jsonObject.put("Surname", surname);
            jsonObject.put("Firstname", firstname);
            jsonObject.put("Othername", othername);
            jsonObject.put("Date_of_Birth", dob);
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