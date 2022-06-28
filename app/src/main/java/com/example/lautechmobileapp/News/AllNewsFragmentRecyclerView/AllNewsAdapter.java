package com.example.lautechmobileapp.News.AllNewsFragmentRecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lautechmobileapp.R;

import java.util.List;

public class AllNewsAdapter extends RecyclerView.Adapter<AllNewsAdapter.RecyclerViewHolder> {
    private List<AllNewsItem> allNewsItems;
    AllNewsAdapter.OnItemClickListener listener;

    public void setOnItemClickListener(AllNewsAdapter.OnItemClickListener listener){
        this.listener = listener;
    }

    public interface OnItemClickListener {
        public void onClick(AllNewsItem items);
    }

    public AllNewsAdapter(List<AllNewsItem> allNewsItems){
        this.allNewsItems = allNewsItems;
    }

    // This method returns our layout
    @NonNull
    @Override
    public AllNewsAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_layout, parent, false);
        return new AllNewsAdapter.RecyclerViewHolder(view);
    }

    // This method binds the screen with the view
    @Override
    public void onBindViewHolder(@NonNull AllNewsAdapter.RecyclerViewHolder holder, int position) {

        AllNewsItem data = allNewsItems.get(position);
        holder.newsImage.setImageResource(data.mNewsImage);
        holder.title.setText(data.mTitleResource);
        holder.subtitle.setText(data.mSubtitleResource);
        holder.time.setText(data.mTime);
        holder.views.setText(data.mViews);

        holder.newsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    listener.onClick(data);

            }
        });


    }

    // This Method returns the size of the Array
    @Override
    public int getItemCount() {
        return allNewsItems.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout newsLayout;
        ImageView newsImage;
        TextView title, subtitle, time, views;
        public RecyclerViewHolder(View itemView){
            super(itemView);
            newsLayout = itemView.findViewById(R.id.newsLayout);
            newsImage = itemView.findViewById(R.id.newsImage);
            title = itemView.findViewById(R.id.newsTitle);
            subtitle = itemView.findViewById(R.id.newsSubtitle);
            time = itemView.findViewById(R.id.newsTime);
            views = itemView.findViewById(R.id.newsViews);
        }
    }
}
