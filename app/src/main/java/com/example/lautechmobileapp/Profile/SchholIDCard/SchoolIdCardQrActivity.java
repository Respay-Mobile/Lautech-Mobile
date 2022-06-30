package com.example.lautechmobileapp.Profile.SchholIDCard;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lautechmobileapp.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class SchoolIdCardQrActivity extends AppCompatActivity {

    private ImageView qrCodeImage;
    private TextView nameText, departmentText, matricNumberText;
    String name, department, matricNum, data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_id_card_qr);

        qrCodeImage = findViewById(R.id.qrCodeImage);
        nameText = findViewById(R.id.fullNameTextView);
        departmentText = findViewById(R.id.departmentTextView);
        matricNumberText = findViewById(R.id.matricNumTextView);

        name = nameText.getText().toString();
        department = departmentText.getText().toString();
        matricNum = matricNumberText.getText().toString();

        //adding all text together to display them in QR code format
        data = name + "\n" +department + "\n" +matricNum;

        //call method to display QR code
        displayQrCode();

    }

    //method displays qr code
    public void displayQrCode(){

        if(data.length() > 0){

            QRCodeWriter writer = new QRCodeWriter();
            try {
                BitMatrix bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, 380, 380);
                int width = bitMatrix.getWidth();
                int height = bitMatrix.getHeight();
                Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                    }
                }
                qrCodeImage.setImageBitmap(bmp);

            } catch (WriterException e) {
                e.printStackTrace();
            }
        }

    }
}