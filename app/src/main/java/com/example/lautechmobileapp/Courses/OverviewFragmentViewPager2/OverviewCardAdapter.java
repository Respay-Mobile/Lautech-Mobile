package com.example.lautechmobileapp.Courses.OverviewFragmentViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lautechmobileapp.Dashboard.NewsCardViewPager2.NewsCardAdapter;
import com.example.lautechmobileapp.R;

import java.util.List;

public class OverviewCardAdapter extends RecyclerView.Adapter<OverviewCardAdapter.CardViewHolder> {

    private List<OverviewCardItem> overviewCardItems;

    public OverviewCardAdapter(List<OverviewCardItem> overviewCardItems) {
        this.overviewCardItems = overviewCardItems;
    }

    // This method returns our layout
    @NonNull
    @Override
    public OverviewCardAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lecturer_card, parent, false);
        return new OverviewCardAdapter.CardViewHolder(view);
    }

    // This method binds the screen with the view
    @Override
    public void onBindViewHolder(@NonNull OverviewCardAdapter.CardViewHolder holder, int position) {

        OverviewCardItem data = overviewCardItems.get(position);
        holder.image.setImageResource(data.mImage);
        holder.title.setText(data.mTitle);

    }

    // This Method returns the size of the Array
    @Override
    public int getItemCount() {
        return overviewCardItems.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        CardView card;
        ImageView image;
        TextView title;

        public CardViewHolder(View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.card);
            image = itemView.findViewById(R.id.cardImage);
            title = itemView.findViewById(R.id.cardTitle);
        }
    }
}
