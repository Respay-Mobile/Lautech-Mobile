package com.example.lautechmobileapp.Tasks;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.lautechmobileapp.Courses.CourseContent;
import com.example.lautechmobileapp.Courses.OverviewFragment;

public class TaskTabAdapter extends FragmentStateAdapter {

    Context context;
    int totalTabs;
    public TaskTabAdapter(@NonNull Context c, FragmentManager fm, Lifecycle lifecycle, int totalTabs) {
        super(fm, lifecycle);
        context = c;
        this.totalTabs = totalTabs;
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                AssignmentFragment assignmentFragment = new AssignmentFragment();
                return assignmentFragment;
            case 1:
                OtherTasksFragment otherTasksFragment = new OtherTasksFragment();
                return otherTasksFragment;
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return totalTabs;
    }

}
