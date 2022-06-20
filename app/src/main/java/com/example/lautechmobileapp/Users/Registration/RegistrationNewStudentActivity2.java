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

public class RegistrationNewStudentActivity2 extends AppCompatActivity {

    private TextView signInTextView;
    private AutoCompleteTextView stateOfOrigin, genderDrop;
    private String[] stateOfOriginText = {"Abia", "Adamawa", "Akwa Ibom", "Anambra", "Bauchi", "Bayelsa", "Benue", "Borno", "Cross River", "Delta", "Ebonyi",
            "Edo", "Ekiti", "Enugu", "Gombe", "Imo", "Jigawa", "Kaduna", "Kano", "Katsina", "Kebbi", "Kogi", "Kwara", "Lagos", "Nasarawa", "Ogun", "Ondo", "Osun",
            "Oyo", "Plateau", "Rivers", "Sokoto", "Taraba", "Yobe", "Zamfara"};
    private String[] genderText = {"Male", "Female"};
    private TextInputLayout phoneTextInput, genderTextInput, originTextInput;
    private String surname, firstname, othername, phone, gender, origin;
    private Button continueBtn;
    MainClass mainClass = new MainClass(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_new_student2);

        stateOfOrigin = findViewById(R.id.stateOfOriginDropDown);
        genderDrop = findViewById(R.id.genderDropDown);
        continueBtn = findViewById(R.id.continueBtn);
        phoneTextInput = findViewById(R.id.phoneOutlinedTextField);
        genderTextInput = findViewById(R.id.genderOutlinedTextField);
        originTextInput = findViewById(R.id.stateOfOriginOutlinedTextField);

        //hide soft keyboard for both drop downs
        stateOfOrigin.setInputType(InputType.TYPE_NULL);
        stateOfOrigin.setKeyListener(null);

        genderDrop.setInputType(InputType.TYPE_NULL);
        genderDrop.setKeyListener(null);

        //Array adapter for stateOfOrigindropdown and genderdropdown
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.list_item, stateOfOriginText);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.list_item, genderText);

        //Set the adapter
        stateOfOrigin.setAdapter(adapter1);
        genderDrop.setAdapter(adapter2);  signInTextView = findViewById(R.id.haveAcctSignin);

        //Create object for text watcher class which is used with the textinputedittext
        phoneTextInput.getEditText().addTextChangedListener(new RegistrationNewStudentActivity2.ValidationTextWatcher(phoneTextInput.getEditText()));
        genderTextInput.getEditText().addTextChangedListener(new RegistrationNewStudentActivity2.ValidationTextWatcher(genderTextInput.getEditText()));
        originTextInput.getEditText().addTextChangedListener(new RegistrationNewStudentActivity2.ValidationTextWatcher(originTextInput.getEditText()));

        //get previous string from intent
        surname = getIntent().getStringExtra("surname");
        surname = getIntent().getStringExtra("firstname");
        surname = getIntent().getStringExtra("othername");

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

        //When continue button is clicked
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validatePhone() || !validateGender() || !validateState()) {
                    return;
                }

                phone = phoneTextInput.getEditText().getText().toString().trim();
                gender = genderTextInput.getEditText().getText().toString().trim();
                origin = originTextInput.getEditText().getText().toString().trim();
                moveToSignUpPage3(phone, gender, origin, surname, firstname, othername);
            }
        });

    }


    // method to validate phone
    public boolean validatePhone() {
        int phoneLength = phoneTextInput.getEditText().getText().toString().trim().length();
        if (phoneTextInput.getEditText().getText().toString().trim().isEmpty()) {
            phoneTextInput.setError("Required");
            return false;
        } else if(phoneLength < 11 || phoneLength > 11){
            phoneTextInput.setError("Invalid phonenumber");
            return false;
        } else {
            phoneTextInput.setErrorEnabled(false);
        }
        return true;

    }

    // method to validate gender
    public boolean validateGender() {
        if (genderTextInput.getEditText().getText().toString().trim().isEmpty()) {
            genderTextInput.setError("Required");
            return false;

        }else {
            genderTextInput.setErrorEnabled(false);
        }
        return true;

    }

    // method to validate state
    public boolean validateState() {
        if (originTextInput.getEditText().getText().toString().trim().isEmpty()) {
            originTextInput.setError("Required");
            return false;

        }else {
            originTextInput.setErrorEnabled(false);
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


    public void moveToSignUpPage3(String phone, String gender, String origin, String surname, String firstname, String othername){
        Intent intent = new Intent(getApplicationContext(), RegistrationNewStudentActivity3.class);
        intent.putExtra(surname, "surname");
        intent.putExtra(firstname, "firstname");
        intent.putExtra(othername, "othername");
        intent.putExtra(phone, "phone");
        intent.putExtra(gender, "gender");
        intent.putExtra(origin, "origin");
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
                case R.id.phoneTextInputEditText:
                    validatePhone();
                    break;

                case R.id.stateOfOriginDropDown:
                    validateState();
                    break;

                case R.id.genderDropDown:
                    validateGender();
                    break;

            }
        }

    }


}