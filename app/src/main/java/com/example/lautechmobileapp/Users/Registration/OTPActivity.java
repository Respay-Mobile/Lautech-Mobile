package com.example.lautechmobileapp.Users.Registration;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lautechmobileapp.R;
import com.example.lautechmobileapp.Users.LoginActivity;
import com.google.android.material.snackbar.Snackbar;

public class OTPActivity extends AppCompatActivity {

    String surname, firstname, othername, fullname, phone;
    TextView topText,phoneTextView, resendOTP;
    private RelativeLayout layout1,layout2, successLayout;
    private LinearLayout otplayout;
    private TextView resendTextView;
    private EditText otp_box1, otp_box2, otp_box3, otp_box4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpactivity);


        //Change top bar color
        setTopBarColor();

        layout1 = findViewById(R.id.relativeLayout1);
        layout2 = findViewById(R.id.layout2);
        otplayout = findViewById(R.id.root_otp_layout);
        resendTextView = findViewById(R.id.OTPResend);
        successLayout = findViewById(R.id.successLayout);
        otp_box1 = findViewById(R.id.otp_edit_box1);
        otp_box2 = findViewById(R.id.otp_edit_box2);
        otp_box3 = findViewById(R.id.otp_edit_box3);
        otp_box4 = findViewById(R.id.otp_edit_box4);
        topText = findViewById(R.id.nameTitle);
        phoneTextView = findViewById(R.id.OTPNumber);
        resendOTP = findViewById(R.id.OTPResend);

        EditText[] edit = {otp_box1, otp_box2, otp_box3, otp_box4};

        otp_box1.addTextChangedListener(new GenericTextWatcher(otp_box1, edit));
        otp_box2.addTextChangedListener(new GenericTextWatcher(otp_box2, edit));
        otp_box3.addTextChangedListener(new GenericTextWatcher(otp_box3, edit));
        otp_box4.addTextChangedListener(new GenericTextWatcher(otp_box4, edit));

        resendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //Get details from previous activity and ensure they are not empty
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            surname = extras.getString("surname");
            firstname = extras.getString("firstname");
            othername = extras.getString("othername");
            phone = extras.getString("phoneNumber");

        }else {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }

        //set fullname and phone number to the textviews
        fullname = "Hi, "+ surname +" "+ firstname + " " + othername;
        topText.setText(fullname);

        String phoneEdited = phone.substring(0, 3)+"XXXXXX"+phone.substring(9, 11);
        phoneTextView.setText(phoneEdited);


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

    public void validateOTP(View view){
        String a,b,c,d,total;
        int OTP;
        hideKeyboard();
        a = otp_box1.getText().toString().trim();
        b = otp_box2.getText().toString().trim();
        c = otp_box3.getText().toString().trim();
        d = otp_box4.getText().toString().trim();
        if(a.isEmpty() || b.isEmpty() || c.isEmpty() || d.isEmpty()){
            return;
        }
        total = a+b+c+d;

        OTP = Integer.valueOf(total);

        if(OTP != 1234){
            otp_box1.setText("");
            otp_box2.setText("");
            otp_box3.setText("");
            otp_box4.setText("");
            otp_box1.requestFocus();
            showErr("Invalid OTP", view);
        } else {
            onSuccess();
        }
    }

    //text watcher for the OTP TEXTBOXES
    public class GenericTextWatcher implements TextWatcher {
        private final EditText[] editText;
        private View view;
        public GenericTextWatcher(View view, EditText editText[])
        {
            this.editText = editText;
            this.view = view;
        }

        @Override
        public void afterTextChanged(Editable editable) {
            String text = editable.toString();
            switch (view.getId()) {

                case R.id.otp_edit_box1:
                    if (text.length() == 1)
                        editText[1].requestFocus();
                    break;
                case R.id.otp_edit_box2:

                    if (text.length() == 1)
                        editText[2].requestFocus();
                    else if (text.length() == 0)
                        editText[0].requestFocus();
                    break;
                case R.id.otp_edit_box3:
                    if (text.length() == 1)
                        editText[3].requestFocus();
                    else if (text.length() == 0)
                        editText[1].requestFocus();
                    break;
                case R.id.otp_edit_box4:
                    if (text.length() == 0)
                        editText[2].requestFocus();
                    else if(text.length() == 1)
                        validateOTP(view);
                    break;
            }
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
        }

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
        }


    }

    public void onSuccess(){
        layout1.setVisibility(View.GONE);
        layout2.setVisibility(View.GONE);
        otplayout.setVisibility(View.GONE);
        resendTextView.setVisibility(View.GONE);
        successLayout.setVisibility(View.VISIBLE);

        //Wait for a few seconds before moving to new activity
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // do something
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                // If you just use this that is not a valid context. Use ActivityName.this
                startActivity(intent);
            }
        }, 1000);
    }

    //Method for error
    public void showErr(String errorMessage, View view){
        Snackbar snackbar = Snackbar.make(view, errorMessage, Snackbar.LENGTH_SHORT);
        snackbar.setBackgroundTint(getResources().getColor(R.color.onbBtn));
        snackbar.show();
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

    //Hide keyboard
    public  void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}