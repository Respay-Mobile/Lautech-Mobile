package com.example.lautechmobileapp.Profile.Results.ChildRecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lautechmobileapp.News.AllNewsFragmentRecyclerView.AllNewsAdapter;
import com.example.lautechmobileapp.News.AllNewsFragmentRecyclerView.AllNewsItem;
import com.example.lautechmobileapp.R;

import java.util.List;

public class ChildCourseResultAdapter extends RecyclerView.Adapter<ChildCourseResultAdapter.RecyclerViewHolder> {

    private List<ChildCourseResultItem> childCourseResultItems;
    Context cxt;

    public ChildCourseResultAdapter(List<ChildCourseResultItem> childCourseResultItems, Context mContext){
        this.cxt = mContext;
        this.childCourseResultItems = childCourseResultItems;
    }

    // This method binds the screen with the view
    @Override
    public void onBindViewHolder(@NonNull ChildCourseResultAdapter.RecyclerViewHolder holder, int position) {

        ChildCourseResultItem data = childCourseResultItems.get(position);

        holder.courseCode.setText(data.courseCode);
        holder.courseScore.setText(data.courseScore);
        holder.courseGrade.setText(data.courseGrade);
        holder.courseUnit.setText(data.courseUnit);



    }

    // This method returns our layout
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_results_layout, parent, false);
        return new RecyclerViewHolder(view);
    }

    // This Method returns the size of the Array
    @Override
    public int getItemCount() {
        return childCourseResultItems.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{

        TextView courseCode, courseScore, courseGrade, courseUnit;
        public RecyclerViewHolder(View itemView){
            super(itemView);
            courseCode = itemView.findViewById(R.id.courseCode);
            courseScore = itemView.findViewById(R.id.courseScore);
            courseGrade = itemView.findViewById(R.id.courseGrade);
            courseUnit = itemView.findViewById(R.id.courseUnit);

        }
    }
}
