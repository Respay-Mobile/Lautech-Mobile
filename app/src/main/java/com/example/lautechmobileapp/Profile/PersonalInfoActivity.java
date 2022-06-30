package com.example.lautechmobileapp.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.example.lautechmobileapp.R;
import com.example.lautechmobileapp.Users.Registration.RegistrationNewStudentActivity2;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class PersonalInfoActivity extends AppCompatActivity {

    private Button saveAndContBtn, saveAndGoBtn;
    private AutoCompleteTextView stateOfOrigin, genderDrop;
    private TextInputEditText DOBEditText;
    private String[] stateOfOriginText = {"Abia", "Adamawa", "Akwa Ibom", "Anambra", "Bauchi", "Bayelsa", "Benue", "Borno", "Cross River", "Delta", "Ebonyi",
            "Edo", "Ekiti", "Enugu", "Gombe", "Imo", "Jigawa", "Kaduna", "Kano", "Katsina", "Kebbi", "Kogi", "Kwara", "Lagos", "Nasarawa", "Ogun", "Ondo", "Osun",
            "Oyo", "Plateau", "Rivers", "Sokoto", "Taraba", "Yobe", "Zamfara"};
    private String[] genderText = {"Male", "Female"};
    private TextInputLayout surnameTextInput, firstNameTextInput, othernameTextInput, genderTextInput, dobTextInput, originTextInput;
    private String surname, firstname, othername, dob, gender, origin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        saveAndContBtn = findViewById(R.id.saveAndContinueBtn);
        saveAndGoBtn = findViewById(R.id.saveAndGoBtn);
        stateOfOrigin = findViewById(R.id.stateOfOriginDropDown);
        genderDrop = findViewById(R.id.genderDropDown);
        surnameTextInput = findViewById(R.id.surnameOutlinedTextField);
        firstNameTextInput = findViewById(R.id.firstnameOutlinedTextField);
        othernameTextInput = findViewById(R.id.othernameOutlinedTextField);
        genderTextInput = findViewById(R.id.genderOutlinedTextField);
        dobTextInput = findViewById(R.id.DOBOutlinedTextField);
        originTextInput = findViewById(R.id.stateOfOriginOutlinedTextField);


        surname = surnameTextInput.getEditText().getText().toString().trim();
        firstname = firstNameTextInput.getEditText().getText().toString().trim();
        othername = othernameTextInput.getEditText().getText().toString().trim();
        gender = genderTextInput.getEditText().getText().toString();
        dob = dobTextInput.getEditText().getText().toString().trim();
        origin = originTextInput.getEditText().getText().toString().trim();


        DOBEditText = findViewById(R.id.DOBTextInputEditText);
        DOBEditText.setInputType(InputType.TYPE_NULL);
        DOBEditText.setKeyListener(null);

        DOBEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCalender();
            }
        });

        //hide soft keyboard for both drop downs
        stateOfOrigin.setInputType(InputType.TYPE_NULL);
        stateOfOrigin.setKeyListener(null);

        genderDrop.setInputType(InputType.TYPE_NULL);
        genderDrop.setKeyListener(null);

        //Array adapter for stateOfOrigindropdown and genderdropdown
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.list_item, stateOfOriginText);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.list_item, genderText);

        stateOfOrigin.setAdapter(adapter1);
        genderDrop.setAdapter(adapter2);

        //Create object for text watcher class which is used with the textinputedittext
        surnameTextInput.getEditText().addTextChangedListener(new PersonalInfoActivity.ValidationTextWatcher(surnameTextInput.getEditText()));
        firstNameTextInput.getEditText().addTextChangedListener(new PersonalInfoActivity.ValidationTextWatcher(firstNameTextInput.getEditText()));
        othernameTextInput.getEditText().addTextChangedListener(new PersonalInfoActivity.ValidationTextWatcher(othernameTextInput.getEditText()));
        dobTextInput.getEditText().addTextChangedListener(new PersonalInfoActivity.ValidationTextWatcher(dobTextInput.getEditText()));
        originTextInput.getEditText().addTextChangedListener(new PersonalInfoActivity.ValidationTextWatcher(originTextInput.getEditText()));


        saveAndGoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!validateSurName() || !validateFirstName() || !validateOtherName() || !validateGender() || !validateDOB() || !validateOrigin()){
                    return;
                }
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);


            }
        });

        saveAndContBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validateSurName() || !validateFirstName() || !validateOtherName() || !validateGender() || !validateDOB() || !validateOrigin()){
                    return;
                }

                Intent intent = new Intent(getApplicationContext(), PersonalInfo2Activity.class);
                startActivity(intent);


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

    // method to validate surname
    public boolean validateSurName() {
        if (surnameTextInput.getEditText().getText().toString().trim().isEmpty()) {
            surnameTextInput.setError("Required");
            return false;

        }else {
            surnameTextInput.setErrorEnabled(false);
        }
        return true;
    }

    // method to validate firstname
    public boolean validateFirstName() {
        if (firstNameTextInput.getEditText().getText().toString().trim().isEmpty()) {
            firstNameTextInput.setError("Required");
            return false;

        }else {
            firstNameTextInput.setErrorEnabled(false);
        }
        return true;
    }

    // method to validate othername
    public boolean validateOtherName() {
        if (othernameTextInput.getEditText().getText().toString().trim().isEmpty()) {
            othernameTextInput.setError("Required");
            return false;

        }else {
            othernameTextInput.setErrorEnabled(false);
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

    // method to validate dob
    public boolean validateDOB() {
        if (dobTextInput.getEditText().getText().toString().trim().isEmpty()) {
            dobTextInput.setError("Required");
            return false;

        }else {
            dobTextInput.setErrorEnabled(false);
        }
        return true;
    }

    // method to validate gender
    public boolean validateOrigin() {
        if (originTextInput.getEditText().getText().toString().trim().isEmpty()) {
            originTextInput.setError("Required");
            return false;

        }else {
            originTextInput.setErrorEnabled(false);
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
                case R.id.surnameTextInputEditText:
                    validateSurName();
                    break;

                case R.id.firstnameTextInputEditText:
                    validateFirstName();
                    break;

                case R.id.othernameTextInputEditText:
                    validateOtherName();
                    break;

                case R.id.DOBTextInputEditText:
                    validateDOB();
                    break;

                case R.id.genderDropDown:
                    validateGender();
                    break;

                case R.id.stateOfOriginDropDown:
                    validateOrigin();
                    break;


            }
        }

    }
}