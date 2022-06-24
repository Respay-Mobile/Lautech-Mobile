package com.example.lautechmobileapp.Courses.CourseCardRecyclerView;

public class CourseCardItem {

    String mTitleResource, mSubtitleResource, mUnitResource;
    int mImageResource;

    public CourseCardItem(String title, String subtitle, String unit, int image) {
        this.mTitleResource = title;
        this.mSubtitleResource = subtitle;
        this.mUnitResource = unit;
        this.mImageResource = image;
    }

}
