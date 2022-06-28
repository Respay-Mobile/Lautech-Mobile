package com.example.lautechmobileapp.News;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lautechmobileapp.News.AllNewsFragmentRecyclerView.AllNewsAdapter;
import com.example.lautechmobileapp.News.AllNewsFragmentRecyclerView.AllNewsItem;
import com.example.lautechmobileapp.R;

import java.util.ArrayList;
import java.util.List;

public class AllNewsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private AllNewsAdapter mAllNewsAdapter;
    private List allNewsDataList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_news, container, false);

        mRecyclerView = view.findViewById(R.id.allNewsRecyclerView);

        //Object for NewsCardAdapter
        mAllNewsAdapter = new AllNewsAdapter(allNewsDataList);
        mRecyclerView.setAdapter(mAllNewsAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()) );

        //Use the method declared below
        setAllNewsValues();

        mAllNewsAdapter.setOnItemClickListener(new AllNewsAdapter.OnItemClickListener() {
            @Override
            public void onClick(AllNewsItem items) {
                Intent intent = new Intent(getContext(), NewsDetailsActivity.class);
                startActivity(intent);
            }
        });


        // Inflate the layout for this fragment
        return view;
    }

    //set the texts for the textviews
    public void setAllNewsValues(){
        AllNewsItem data = new AllNewsItem("Amet minim mollit non deserunt ulla", "Amet minim mollit non deserunt ullamco est sit aliqua dolor do.", "3 hours ago", "267 views", R.drawable.ic_person_icon );
        allNewsDataList.add(data);

        data = new AllNewsItem("Velit officia consequat duis enim veli", "Amet minim mollit non deserunt ullamco est sit aliqua dolor do.", "3 hours ago", "267 views", R.drawable.ic_person_icon );
        allNewsDataList.add(data);


        data = new AllNewsItem("Prof. Ajaguna appointment is valid", "Amet minim mollit non deserunt ullamco est sit aliqua dolor do.", "3 hours ago", "267 views", R.drawable.ic_person_icon );
        allNewsDataList.add(data);


        data = new AllNewsItem("Amet minim mollit non deserunt ulla", "Amet minim mollit non deserunt ullamco est sit aliqua dolor do.", "3 hours ago", "267 views", R.drawable.ic_person_icon );
        allNewsDataList.add(data);


    }
}