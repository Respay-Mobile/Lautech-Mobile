package com.example.lautechmobileapp.Courses;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lautechmobileapp.Courses.OverviewFragmentViewPager2.OverviewCardAdapter;
import com.example.lautechmobileapp.Courses.OverviewFragmentViewPager2.OverviewCardItem;
import com.example.lautechmobileapp.Dashboard.NewsCardViewPager2.NewsCardAdapter;
import com.example.lautechmobileapp.Dashboard.NewsCardViewPager2.NewsCardItem;
import com.example.lautechmobileapp.R;

import java.util.ArrayList;
import java.util.List;

public class OverviewFragment extends Fragment {

    private TextView discover, requirement1, requirement2;
    private ViewPager2 mViewPager;
    private OverviewCardAdapter mCardAdapter;
    private List overviewDataList = new ArrayList<>();

    String[] requirementsArray = {"Lorem ipsum dolor sit amet","Eu vitae, bibendum pulvinar orci pellen sit. "};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_overview, container, false);


        //view pager for card
        mViewPager =  view.findViewById(R.id.pager);
        discover = view.findViewById(R.id.discoverDescTextView);
        requirement1 = view.findViewById(R.id.requirement1);
        requirement2 = view.findViewById(R.id.requirement2);

        discover.setText("Lorem ipsum dolor sit amet, consectetur adipisci elit. Dictumst laoreet faucibus eu, faucibus odio porta. " +
                "Eu vitae, bibendum pulvinar orci pellen sit. Sit facilisi sem mauris amet, enim urna viverra so. Sit consectetur fermentum pretium " +
                "vitae duis nisi eget nec.");
        requirement1.setText("Lorem ipsum dolor sit amet,");
        requirement2.setText("Eu vitae, bibendum pulvinar orci pellen sit.");

        setOverviewCardValues();

        //Object for NewsCardAdapter
        mCardAdapter = new OverviewCardAdapter(overviewDataList);

        //Set CardAdapter as viewpager2adapter
        mViewPager.setAdapter(mCardAdapter);
        setOverviewCardValues();
        mViewPager.setClipToPadding(false);
        mViewPager.setClipChildren(false);
        mViewPager.setOffscreenPageLimit(3);

        int pageMarginPx = 210;
        int offsetPx = 220;

        mViewPager.setPageTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                ViewPager2 viewPager = (ViewPager2) page.getParent().getParent();
                float offset = position * -(2 * offsetPx + pageMarginPx);

                if (viewPager.getOrientation()  == ViewPager2.ORIENTATION_HORIZONTAL) {
                    if (ViewCompat.getLayoutDirection(viewPager) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                        page.setTranslationX(-offset);
                    } else {
                        page.setTranslationX(offset);
                    }
                } else {
                    page.setTranslationY(offset);
                }
            }
        });


        // Inflate the layout for this fragment
        return view;
    }

    public void setOverviewCardValues(){
        OverviewCardItem data = new OverviewCardItem("Prof. Sunny Addy", R.drawable.ic_person_icon);
        overviewDataList.add(data);
        data = new OverviewCardItem("Dr. Lanre Oguns", R.drawable.ic_person_icon);
        overviewDataList.add(data);
        data = new OverviewCardItem("Prof. Sunny Addy", R.drawable.ic_person_icon);
        overviewDataList.add(data);
        data = new OverviewCardItem("Dr. Lanre Oguns", R.drawable.ic_person_icon);
        overviewDataList.add(data);
    }
}