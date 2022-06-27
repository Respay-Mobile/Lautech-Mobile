package com.example.lautechmobileapp.Tasks;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.lautechmobileapp.Courses.CourseCardRecyclerView.CourseCardItem;
import com.example.lautechmobileapp.R;
import com.example.lautechmobileapp.Tasks.AssignmentFragmentRecyclerView.AssignmentsAdapter;
import com.example.lautechmobileapp.Tasks.AssignmentFragmentRecyclerView.AssignmentsItem;

import java.util.ArrayList;
import java.util.List;

public class AssignmentFragment extends Fragment {

   private View view;
    private RecyclerView mRecyclerView;
    private AssignmentsAdapter mAssignmentAdapter;
   private ListView assignmentList;
    private List assignmentDataList = new ArrayList<>();
    String[] courseCodeArray = {"CHM 201" , "CHM 201", "CHM 201"};
    String[] assignmentDescArray = {"Lorem ipsum dolor sit amet, consectetur adipisci elit. Dictumst laoreet faucibus eu, faucibus odio porta. Eu vitae, bibendum pulvinar orci pellen sit. Sit facilisi sem mauris amet, enim urna viverra so. Sit consectetur fermentum pretium vitae duis nisi eget nec. ", "Lorem ipsum dolor sit amet, consectetur adipisci elit. Dictumst laoreet faucibus eu, faucibus odio porta. Eu vitae, bibendum pulvinar orci pellen sit. Sit facilisi sem mauris amet, enim urna viverra so. Sit consectetur fermentum pretium vitae duis nisi eget nec. ", "Lorem ipsum dolor sit amet, consectetur adipisci elit. Dictumst laoreet faucibus eu, faucibus odio porta. Eu vitae, bibendum pulvinar orci pellen sit. Sit facilisi sem mauris amet, enim urna viverra so. Sit consectetur fermentum pretium vitae duis nisi eget nec. "};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_assignment, container, false);

       mRecyclerView = view.findViewById(R.id.assignmentRecyclerView);

        //Object for NewsCardAdapter
        mAssignmentAdapter = new AssignmentsAdapter(assignmentDataList);
        mRecyclerView.setAdapter(mAssignmentAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()) );
        //Use the method declared below
        setAssignmentValues();


        // Inflate the layout for this fragment
        return view;
    }

    //set the texts for the textviews
    public void setAssignmentValues(){
        AssignmentsItem data = new AssignmentsItem("CHM 201", "Lorem ipsum dolor sit amet, consectetur adipisci elit. Dictumst laoreet faucibus eu, faucibus odio porta. Eu vitae, bibendum pulvinar orci pellen sit. Sit facilisi sem mauris amet, enim urna viverra so. Sit consectetur fermentum pretium vitae duis nisi eget nec.", "Pending" );
        assignmentDataList.add(data);

        data = new AssignmentsItem("CHM 201", "Lorem ipsum dolor sit amet, consectetur adipisci elit. Dictumst laoreet faucibus eu, faucibus odio porta. Eu vitae, bibendum pulvinar orci pellen sit. Sit facilisi sem mauris amet, enim urna viverra so. Sit consectetur fermentum pretium vitae duis nisi eget nec.", "Submitted" );
        assignmentDataList.add(data);

        data = new AssignmentsItem("CHM 201", "Lorem ipsum dolor sit amet, consectetur adipisci elit. Dictumst laoreet faucibus eu, faucibus odio porta. Eu vitae, bibendum pulvinar orci pellen sit. Sit facilisi sem mauris amet, enim urna viverra so. Sit consectetur fermentum pretium vitae duis nisi eget nec.", "Submitted" );
        assignmentDataList.add(data);

        data = new AssignmentsItem("CHM 201", "Lorem ipsum dolor sit amet, consectetur adipisci elit. Dictumst laoreet faucibus eu, faucibus odio porta. Eu vitae, bibendum pulvinar orci pellen sit. Sit facilisi sem mauris amet, enim urna viverra so. Sit consectetur fermentum pretium vitae duis nisi eget nec.", "Pending" );
        assignmentDataList.add(data);
    }

}
