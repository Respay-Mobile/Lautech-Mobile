package com.example.lautechmobileapp.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;

import com.example.lautechmobileapp.R;
import com.google.android.material.textfield.TextInputLayout;

public class PersonalInfo2Activity extends AppCompatActivity {

    private TextInputLayout emailTextInput, departmentTextInput, facultyTextInput, levelTextInput, courseAdviserTextInput, unionPositionTextInput;
    private Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info2);

        emailTextInput = findViewById(R.id.emailOutlinedTextField);
        departmentTextInput = findViewById(R.id.departmentOutlinedTextField);
        facultyTextInput = findViewById(R.id.facultyOutlinedTextField);
        levelTextInput = findViewById(R.id.levelOutlinedTextField);
        courseAdviserTextInput = findViewById(R.id.courseAdviserOutlinedTextField);
        unionPositionTextInput = findViewById(R.id.unionPositionOutlinedTextField);
        saveBtn = findViewById(R.id.saveBtn);


        //Create object for text watcher class which is used with the textinputedittext
        emailTextInput.getEditText().addTextChangedListener(new PersonalInfo2Activity.ValidationTextWatcher(emailTextInput.getEditText()));
        departmentTextInput.getEditText().addTextChangedListener(new PersonalInfo2Activity.ValidationTextWatcher(departmentTextInput.getEditText()));
        facultyTextInput.getEditText().addTextChangedListener(new PersonalInfo2Activity.ValidationTextWatcher(facultyTextInput.getEditText()));
        levelTextInput.getEditText().addTextChangedListener(new PersonalInfo2Activity.ValidationTextWatcher(levelTextInput.getEditText()));
        courseAdviserTextInput.getEditText().addTextChangedListener(new PersonalInfo2Activity.ValidationTextWatcher(courseAdviserTextInput.getEditText()));
        unionPositionTextInput.getEditText().addTextChangedListener(new PersonalInfo2Activity.ValidationTextWatcher(unionPositionTextInput.getEditText()));


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validateMail() || !validateDepartment() || !validateFaculty() || !validateLevel() || !validateCourseAdviser() || !validateUnionPosition()){
                    return;
                }

                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    // method to validate email
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


    // method to validate department
    public boolean validateDepartment() {
        if (departmentTextInput.getEditText().getText().toString().trim().isEmpty()) {
            departmentTextInput.setError("Required");
            return false;

        }else {
            departmentTextInput.setErrorEnabled(false);
        }
        return true;
    }

    // method to validate faculty
    public boolean validateFaculty() {
        if (facultyTextInput.getEditText().getText().toString().trim().isEmpty()) {
            facultyTextInput.setError("Required");
            return false;

        }else {
            facultyTextInput.setErrorEnabled(false);
        }
        return true;
    }

    // method to validate level
    public boolean validateLevel() {
        if (levelTextInput.getEditText().getText().toString().trim().isEmpty()) {
            levelTextInput.setError("Required");
            return false;

        }else {
            levelTextInput.setErrorEnabled(false);
        }
        return true;
    }


    // method to validate course adviser
    public boolean validateCourseAdviser() {
        if (courseAdviserTextInput.getEditText().getText().toString().trim().isEmpty()) {
            courseAdviserTextInput.setError("Required");
            return false;

        }else {
            courseAdviserTextInput.setErrorEnabled(false);
        }
        return true;
    }


    // method to validate union position
    public boolean validateUnionPosition() {
        if (unionPositionTextInput.getEditText().getText().toString().trim().isEmpty()) {
            unionPositionTextInput.setError("Required");
            return false;

        }else {
            unionPositionTextInput.setErrorEnabled(false);
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
                    validateMail();
                    break;

                case R.id.departmentTextInputEditText:
                    validateDepartment();
                    break;

                case R.id.facultyTextInputEditText:
                    validateFaculty();
                    break;

                case R.id.levelTextInputEditText:
                    validateLevel();
                    break;

                case R.id.courseAdviserTextInputEditText:
                    validateCourseAdviser();
                    break;

                case R.id.unionPositionTextInputEditText:
                    validateUnionPosition();
                    break;


            }
        }

    }

}