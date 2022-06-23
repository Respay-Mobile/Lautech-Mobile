package com.example.lautechmobileapp.Dashboard.NewsCardViewPager2;

public class NewsCardItem {

   private String mTitleResource, mSubtitleResource;

   public NewsCardItem(String title, String subtitle) {
      mTitleResource = title;
      mSubtitleResource = subtitle;
   }


   public String getTitle() {
      return mTitleResource;
   }

   public String getSubtitle() { return mSubtitleResource; }

}
