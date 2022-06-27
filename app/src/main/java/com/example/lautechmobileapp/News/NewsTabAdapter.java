package com.example.lautechmobileapp.News;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.lautechmobileapp.Tasks.AssignmentFragment;
import com.example.lautechmobileapp.Tasks.OtherTasksFragment;

public class NewsTabAdapter extends FragmentStateAdapter {

   Context context;
   int totalTabs;

   public NewsTabAdapter(@NonNull Context c, FragmentManager fm, Lifecycle lifecycle, int totalTabs) {
      super(fm, lifecycle);
      context = c;
      this.totalTabs = totalTabs;
   }

   @NonNull
   @Override
   public Fragment createFragment(int position) {
      switch (position) {
         case 0:
            AllNewsFragment allNewsFragment = new AllNewsFragment();
            return allNewsFragment;
         case 1:
            SchoolNewsFragment schoolNewsFragment = new SchoolNewsFragment();
            return schoolNewsFragment;
         case 2:
            FacultyNewsFragment facultyNewsFragment = new FacultyNewsFragment();
            return facultyNewsFragment;
         case 3:
            sugNewsFragment sugNewsFragment = new sugNewsFragment();
            return sugNewsFragment;
         case 4:
            CareerNewsFragment careerNewsFragment = new CareerNewsFragment();
            return careerNewsFragment;
         default:
            return null;
      }
   }

   @Override
   public int getItemCount() {
      return totalTabs;
   }


}
