package com.example.lautechmobileapp.Dashboard.NewsCardViewPager2;

import android.graphics.Color;
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

        NewsCardItem data = newsCardItems.get(position);
        holder.title.setText(data.mTitleResource);
        holder.subtitle.setText(data.mSubtitleResource);

        if((position % 2) == 0){
            holder.card.setCardBackgroundColor(Color.parseColor("#813D38"));
            holder.image.setBackgroundColor(Color.parseColor("#813D38"));
        }else {
            holder.card.setCardBackgroundColor(Color.parseColor("#173C70"));
            holder.image.setBackgroundColor(Color.parseColor("#173C70"));
        }

    }

    // This Method returns the size of the Array
    @Override
    public int getItemCount() {
        return newsCardItems.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder{
        CardView card;
        ImageView image;
        TextView title, subtitle;
        public CardViewHolder(View itemView){
            super(itemView);
            card = itemView.findViewById(R.id.card);
            image = itemView.findViewById(R.id.cardImage);
            title = itemView.findViewById(R.id.cardTitle);
            subtitle = itemView.findViewById(R.id.cardSubtitle);
        }
    }


}
