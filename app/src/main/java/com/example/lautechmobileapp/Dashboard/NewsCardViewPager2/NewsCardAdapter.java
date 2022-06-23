package com.example.lautechmobileapp.Dashboard.NewsCardViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lautechmobileapp.R;

import java.util.List;

public class NewsCardAdapter extends RecyclerView.Adapter<NewsCardAdapter.CardViewHolder> {
    private List<NewsCardItem> newsCardItems;

    public NewsCardAdapter(List<NewsCardItem> newsCardItems) {
        this.newsCardItems = newsCardItems;
    }

    // This method returns our layout
    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_card, parent, false);
        return new CardViewHolder(view);
    }

    // This method binds the screen with the view
    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        String title = newsCardItems.get(position).getTitle();
        String subtitle1 = newsCardItems.get(position).getSubtitle();
    }

    // This Method returns the size of the Array
    @Override
    public int getItemCount() {
        return newsCardItems.size();
    }

    static class CardViewHolder extends RecyclerView.ViewHolder{
        CardViewHolder(@NonNull View itemView){
            super(itemView);
        }
    }


}
