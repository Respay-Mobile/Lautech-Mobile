package com.example.lautechmobileapp.Profile.Results.ParentRecyclerView;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lautechmobileapp.News.AllNewsFragmentRecyclerView.AllNewsAdapter;
import com.example.lautechmobileapp.News.AllNewsFragmentRecyclerView.AllNewsItem;
import com.example.lautechmobileapp.Profile.Results.ChildRecyclerView.ChildCourseResultAdapter;
import com.example.lautechmobileapp.Profile.Results.ChildRecyclerView.ChildCourseResultItem;
import com.example.lautechmobileapp.R;

import java.util.ArrayList;
import java.util.List;

public class ParentSemesterResultAdapter extends RecyclerView.Adapter<ParentSemesterResultAdapter.RecyclerViewHolder> {
    private List<ParentSemesterResultItem> parentSemesterResultItems;
    public Context cxt;

    public ParentSemesterResultAdapter(List<ParentSemesterResultItem> parentSemesterResultItems, Context context){
        this.parentSemesterResultItems = parentSemesterResultItems;
        this.cxt = context;
    }

    // This method returns our layout
    @NonNull
    @Override
    public ParentSemesterResultAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_results_layout, parent, false);
        return new ParentSemesterResultAdapter.RecyclerViewHolder(view);
    }

    // This method binds the screen with the view
    @Override
    public void onBindViewHolder(@NonNull ParentSemesterResultAdapter.RecyclerViewHolder holder, int position) {

        ParentSemesterResultItem data = parentSemesterResultItems.get(position);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(cxt, LinearLayoutManager.VERTICAL, false);
        holder.childRecyclerview.setLayoutManager(layoutManager);
        holder.childRecyclerview.setHasFixedSize(true);
        holder.level.setText(data.level);
        holder.semester.setText(data.semester);
        holder.gpa.setText(data.gpa);
        holder.gpa2.setText(data.gpa2);

        //change card color depending on if the card is the first or second
        if((position % 2) == 0){
            holder.resultCard.setCardBackgroundColor(Color.parseColor("#FCFFF4"));
        }else {
            holder.resultCard.setCardBackgroundColor(Color.parseColor("#F4F9FF"));

        }

        ArrayList<ChildCourseResultItem> arrayList = new ArrayList<>();

        // added the first child row
        if (parentSemesterResultItems.get(position).level.equals("100 Level")  && parentSemesterResultItems.get(position).semester.equals("1st Semester")) {
            arrayList.add(new ChildCourseResultItem("SSC 202","67%", "B", "12 (4X3)"));
            arrayList.add(new ChildCourseResultItem("PHY 206","87%", "A", "15 (5X3)"));
            arrayList.add(new ChildCourseResultItem("CHM 202","72%", "A", "20 (5X4)"));
            arrayList.add(new ChildCourseResultItem("MTH 204","77%", "A", "15 (5X3)"));
            arrayList.add(new ChildCourseResultItem("MTH 206","65%", "B", "12 (4X3)"));
            }

        // added the second child row
        if (parentSemesterResultItems.get(position).level.equals("100 Level")  && parentSemesterResultItems.get(position).semester.equals("2nd Semester")) {
            arrayList.add(new ChildCourseResultItem("SSC 202","67%", "B", "12 (4X3)"));
            arrayList.add(new ChildCourseResultItem("PHY 206","87%", "A", "15 (5X3)"));
            arrayList.add(new ChildCourseResultItem("CHM 202","72%", "A", "20 (5X4)"));
            arrayList.add(new ChildCourseResultItem("MTH 204","77%", "A", "15 (5X3)"));
            arrayList.add(new ChildCourseResultItem("MTH 206","65%", "B", "12 (4X3)"));
        }

        // added the third child row
        if (parentSemesterResultItems.get(position).level.equals("200 Level")  && parentSemesterResultItems.get(position).semester.equals("1st Semester")) {
            arrayList.add(new ChildCourseResultItem("SSC 202","67%", "B", "12 (4X3)"));
            arrayList.add(new ChildCourseResultItem("PHY 206","87%", "A", "15 (5X3)"));
            arrayList.add(new ChildCourseResultItem("CHM 202","72%", "A", "20 (5X4)"));
            arrayList.add(new ChildCourseResultItem("MTH 204","77%", "A", "15 (5X3)"));
            arrayList.add(new ChildCourseResultItem("MTH 206","65%", "B", "12 (4X3)"));
        }

        // added the fourth child row
        if (parentSemesterResultItems.get(position).level.equals("200 Level")  && parentSemesterResultItems.get(position).semester.equals("2nd Semester")) {
            arrayList.add(new ChildCourseResultItem("SSC 202","67%", "B", "12 (4X3)"));
            arrayList.add(new ChildCourseResultItem("PHY 206","87%", "A", "15 (5X3)"));
            arrayList.add(new ChildCourseResultItem("CHM 202","72%", "A", "20 (5X4)"));
            arrayList.add(new ChildCourseResultItem("MTH 204","77%", "A", "15 (5X3)"));
            arrayList.add(new ChildCourseResultItem("MTH 206","65%", "B", "12 (4X3)"));
        }

        ChildCourseResultAdapter childCourseResultAdapter = new ChildCourseResultAdapter(arrayList, holder.childRecyclerview.getContext());
        holder.childRecyclerview.setAdapter(childCourseResultAdapter);

    }

    // This Method returns the size of the Array
    @Override
    public int getItemCount() {
        return parentSemesterResultItems.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{

        RecyclerView childRecyclerview;
        CardView resultCard;
        TextView level, semester, gpa, gpa2;
        public RecyclerViewHolder(View itemView){
            super(itemView);
            childRecyclerview = itemView.findViewById(R.id.childCourseResultRecyclerView);
            resultCard = itemView.findViewById(R.id.resultCard);
            level = itemView.findViewById(R.id.levelTextView);
            semester = itemView.findViewById(R.id.semesterTextView);
            gpa = itemView.findViewById(R.id.gpaTextView);
            gpa2 = itemView.findViewById(R.id.gpaTextView2);
        }
    }
}
