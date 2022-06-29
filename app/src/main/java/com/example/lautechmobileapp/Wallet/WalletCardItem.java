package com.example.lautechmobileapp.Wallet;

public class WalletCardItem {

   String mTransactionName, mTransactionSubtitle, mTransactionAmount, mTransactionTime;
   int mTransactionImage, mTransactionState;

   public WalletCardItem(String transactionName, String transactionSubtitle, String transactionAmount, String transactionTime, int transactionImage, int transactionState){
      this.mTransactionName = transactionName;
      this.mTransactionSubtitle = transactionSubtitle;
      this.mTransactionAmount = transactionAmount;
      this.mTransactionTime = transactionTime;
      this.mTransactionImage = transactionImage;
      this.mTransactionState = transactionState;
   }

}
