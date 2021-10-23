package com.example.new_attendance_management;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class sAdaptor extends FirebaseRecyclerAdapter<nList, nAdaptor.PastViewHolder> {
    private Context context;
    public sAdaptor(@NonNull FirebaseRecyclerOptions<nList> options,Context context) {
        super(options);
        this.context=context;
    }


    @Override
    protected void onBindViewHolder(@NonNull nAdaptor.PastViewHolder holder, int position, @NonNull nList model) {
        holder.title.setText(model.getTitle());
        holder.description.setText(model.getDescription());

    }


    @NonNull
    @Override
    public nAdaptor.PastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post2, parent, false);
        return new nAdaptor.PastViewHolder(view);
    }

    static class PastViewHolder extends RecyclerView.ViewHolder{
        TextView title,description;



        public PastViewHolder(@NonNull View itemView) {
            super(itemView);
            title= itemView.findViewById(R.id.title1);
            description=itemView.findViewById(R.id.description1);



        }
    }
}
