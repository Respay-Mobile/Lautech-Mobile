package com.example.lautechmobileapp;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.text.style.SuperscriptSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.text.HtmlCompat;
import androidx.viewpager.widget.PagerAdapter;

public class SlideAdapter extends PagerAdapter {

    public SlideAdapter(Context context1) {
        this.context = context1;
    }


        Context context;
    LayoutInflater layoutInflater;

  //  Spanned s = Html.fromHtml("eduWallet<small>ᵀᴹ</small>");

    //Images for the onboarding page
    public int[] sliders = {
            R.drawable.frame1,
            R.drawable.frame2,
            R.drawable.frame3,
            R.drawable.frame4
    };


    //Heading for the onboarding page
    public String[] headings = {
            "Student Services",
            "School Services",
            "eduwalletᵀᴹ",
            "School Services"
    };

    public String[] subheadings = {
            "Admissions, Clearance and Result Verification, Semester Enrollment, Course Registration, Results, CGPA Calculator, Electronic ID, Exam Pass and more.",
            "Improved student connection with real-time access to latest news, time-tables, important dates, public notices, fee payment options, and other paid services.",
            "Meet the payment and fund saving needs of students and other members of the university community.",
            "Learn everything to improve your skills taught by the best mentors."
    };

    //Subheadings for the onboarding page



    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImage = view.findViewById(R.id.slideImage);
        TextView header = view.findViewById(R.id.onbTitle);
        TextView subHeader = view.findViewById(R.id.onbDesc);

        //Change size of subheader and header based on screen size
        MainClass mainClass = new MainClass(context);
        if (mainClass.getWidth() >= 720) {
            if (mainClass.getSize() <= 1) {
                header.setTextSize(24f);
                subHeader.setTextSize(18f);
            } else {
                header.setTextSize(24f);
                subHeader.setTextSize(14f);
            }

        } else {
            header.setTextSize(18f);
            subHeader.setTextSize(13f);
        }

        //set the images,headers and subheaders to be displayed
        slideImage.setImageResource(sliders[position]);
        header.setText(headings[position]);
        subHeader.setText(subheadings[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}

