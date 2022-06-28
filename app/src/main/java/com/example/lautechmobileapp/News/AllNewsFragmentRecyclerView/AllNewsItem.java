package com.example.lautechmobileapp.News.AllNewsFragmentRecyclerView;

import android.media.Image;

public class AllNewsItem {

    String mTitleResource, mSubtitleResource, mTime, mViews;
    int mNewsImage;

    public AllNewsItem(String title, String subtitle, String time, String views, int newsImage){
        this.mTitleResource = title;
        this.mSubtitleResource = subtitle;
        this.mTime = time;
        this.mViews = views;
        this.mNewsImage = newsImage;
    }

}
