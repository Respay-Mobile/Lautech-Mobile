package com.example.lautechmobileapp.Wallet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lautechmobileapp.R;

import java.util.List;

public class WalletCardAdapter extends RecyclerView.Adapter<WalletCardAdapter.RecyclerViewHolder> {

   private List<WalletCardItem> walletCardItems;
   int num = 4;

   public WalletCardAdapter(List<WalletCardItem> walletCardItems) {
      this.walletCardItems = walletCardItems;
   }

   // This method returns our layout
   @NonNull
   @Override
   public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transactions_layout, parent, false);
      return new RecyclerViewHolder(view);
   }

   // This method binds the screen with the view
   @Override
   public void onBindViewHolder(@NonNull WalletCardAdapter.RecyclerViewHolder holder, int position) {

      WalletCardItem data = walletCardItems.get(position);
      holder.transactionImage.setImageResource(data.mTransactionImage);
      holder.transactionTitle.setText(data.mTransactionName);
      holder.transactionStateIcon.setImageResource(data.mTransactionState);
      holder.transactionSubtitle.setText(data.mTransactionSubtitle);
      holder.transactionAmount.setText(data.mTransactionAmount);
      holder.transactionTime.setText(data.mTransactionTime);

   }


   // This Method returns the size of the Array
   @Override
   public int getItemCount() {
       if(walletCardItems.size() > num) {
          return num;
       } else {
          return walletCardItems.size();
       }


   }

   public class RecyclerViewHolder extends RecyclerView.ViewHolder {
      ImageView transactionImage, transactionStateIcon;
      TextView transactionTitle, transactionSubtitle, transactionCreditDebit, transactionAmount, transactionTime;

      public RecyclerViewHolder(View itemView) {
         super(itemView);
         transactionImage = itemView.findViewById(R.id.transactionPicture);
         transactionTitle = itemView.findViewById(R.id.transactionName);
         transactionStateIcon = itemView.findViewById(R.id.transactionStateIcon);
         transactionSubtitle = itemView.findViewById(R.id.transactionSubtitle);
         transactionAmount = itemView.findViewById(R.id.transactionAmount);
         transactionTime = itemView.findViewById(R.id.transactionTime);
      }
   }
}

