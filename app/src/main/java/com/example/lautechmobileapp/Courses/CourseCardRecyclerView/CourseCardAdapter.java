package com.example.lautechmobileapp.Courses.CourseCardRecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lautechmobileapp.R;

import java.util.List;

public class CourseCardAdapter extends RecyclerView.Adapter<CourseCardAdapter.RecyclerViewHolder> {

    private List<CourseCardItem> courseCardItems;
    OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public interface OnItemClickListener {
        public void onClick(CourseCardItem items);
    }

    public CourseCardAdapter(List<CourseCardItem> courseCardItems) {
        this.courseCardItems = courseCardItems;
    }

    // This method returns our layout
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_card, parent, false);
        return new RecyclerViewHolder(view);
    }

    // This method binds the screen with the view
    @Override
    public void onBindViewHolder(@NonNull CourseCardAdapter.RecyclerViewHolder holder, int position) {

        CourseCardItem data = courseCardItems.get(position);
        holder.title.setText(data.mTitleResource);
        holder.subtitle.setText(data.mSubtitleResource);
        holder.image.setImageResource(data.mImageResource);
        holder.unit.setText(data.mUnitResource);

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(data);
            }
        });

    }

    // This Method returns the size of the Array
    @Override
    public int getItemCount() {
        return courseCardItems.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        CardView card;
        ImageView image;
        TextView title, subtitle, unit;
        public RecyclerViewHolder(View itemView){
            super(itemView);
            card = itemView.findViewById(R.id.courseCard);
            image = itemView.findViewById(R.id.courseImage);
            title = itemView.findViewById(R.id.courseTitle);
            subtitle = itemView.findViewById(R.id.courseSubtitle);
            unit = itemView.findViewById(R.id.courseUnit);
        }
    }



}
