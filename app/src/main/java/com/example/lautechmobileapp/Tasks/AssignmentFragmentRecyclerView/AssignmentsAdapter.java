package com.example.lautechmobileapp.Tasks.AssignmentFragmentRecyclerView;


import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lautechmobileapp.R;

import java.util.List;

public class AssignmentsAdapter extends RecyclerView.Adapter<AssignmentsAdapter.RecyclerViewHolder> {
   private List<AssignmentsItem> assignmentsItems;

   public AssignmentsAdapter(List<AssignmentsItem> assignmentsItems) {
      this.assignmentsItems = assignmentsItems;
   }

   // This method returns our layout
   @NonNull
   @Override
   public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.assignment_layout, parent, false);
      return new RecyclerViewHolder(view);
   }

   // This method binds the screen with the view
   @Override
   public void onBindViewHolder(@NonNull AssignmentsAdapter.RecyclerViewHolder holder, int position) {

      AssignmentsItem data = assignmentsItems.get(position);
      holder.title.setText(data.mTitleResource);
      holder.subtitle.setText(data.mSubtitleResource);
      holder.assignmentState.setText(data.mAssignmentState);

      if(holder.assignmentState.getText().toString().equals("Submitted")){
         holder.assignmentState.setTextColor(Color.parseColor("#068631"));
      }else {
         holder.assignmentState.setTextColor(Color.parseColor("#DA251D"));
      }

      holder.iconUpload.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {

         }
      });

   }

   // This Method returns the size of the Array
   @Override
   public int getItemCount() {
      return assignmentsItems.size();
   }

   public class RecyclerViewHolder extends RecyclerView.ViewHolder{

      ImageView iconUpload;
      TextView title, subtitle, assignmentState;
      public RecyclerViewHolder(View itemView){
         super(itemView);
         iconUpload = itemView.findViewById(R.id.uploadIcon);
         title = itemView.findViewById(R.id.courseCode);
         subtitle = itemView.findViewById(R.id.courseAssignmentDesc);
         assignmentState = itemView.findViewById(R.id.assignmentState);
      }
   }
}


