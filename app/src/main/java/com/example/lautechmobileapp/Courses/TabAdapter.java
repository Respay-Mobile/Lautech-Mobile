package com.example.lautechmobileapp.Courses;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TabAdapter extends FragmentStateAdapter {

   Context context;
   int totalTabs;
   public TabAdapter(@NonNull Context c, FragmentManager fm, Lifecycle lifecycle, int totalTabs) {
      super(fm, lifecycle);
      context = c;
      this.totalTabs = totalTabs;
   }



   @NonNull
   @Override
   public Fragment createFragment(int position) {
      switch (position) {
         case 0:
            OverviewFragment overviewFragment = new OverviewFragment();
            return overviewFragment;
         case 1:
            CourseContent courseContent = new CourseContent();
            return courseContent;
         default:
            return null;
      }
   }

   @Override
   public int getItemCount() {
      return totalTabs;
   }
}
