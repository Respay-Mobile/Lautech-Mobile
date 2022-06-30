package com.example.lautechmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.Hashtable;

public class DigitalExamPassActivity extends AppCompatActivity {

    private ImageView barCodeImage;
    private TextView nameText, departmentText, matricNumberText;
    String name, department, matricNum, data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digital_exam_pass);

        barCodeImage = findViewById(R.id.barCodeImage);
        nameText = findViewById(R.id.fullNameTextView);
        departmentText = findViewById(R.id.departmentTextView);
        matricNumberText = findViewById(R.id.matricNumTextView);

        name = nameText.getText().toString();
        department = departmentText.getText().toString();
        matricNum = matricNumberText.getText().toString();

        //adding all text together to display them in QR code format
        data = name + "\n" +department + "\n" +matricNum;

        createBarCode();
    }


    public void createBarCode(){
        if(data != null){
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            try {
                BitMatrix bitMatrix = multiFormatWriter.encode(data, BarcodeFormat.CODE_128, 252,100);
                int width = bitMatrix.getWidth();
                int height = bitMatrix.getHeight();
                Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
                for (int i = 0; i<width; i++){
                    for (int j = 0; j<height; j++){
                        bitmap.setPixel(i,j,bitMatrix.get(i,j)? getResources().getColor(R.color.timeTableCard):getResources().getColor(R.color.plain));
                    }
                }
                barCodeImage.setImageBitmap(bitmap);
            } catch (WriterException e) {
                e.printStackTrace();
            }
        }

    }
}