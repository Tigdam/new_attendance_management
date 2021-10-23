package com.example.new_attendance_management;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class leaveAdaptor extends FirebaseRecyclerAdapter<UserHelperClass, leaveAdaptor.PastViewHolder> {

    private Context context;


    public leaveAdaptor(@NonNull FirebaseRecyclerOptions<UserHelperClass> options, Context context) {
        super(options);
        this.context=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull leaveAdaptor.PastViewHolder holder, int position, @NonNull UserHelperClass model) {
        holder.nameyear.setText(model.getN());
        holder.title.setText(model.getTitle());
       holder.description.setText(model.getDes());
        
        
        
    }


    @NonNull
    @Override
    public leaveAdaptor.PastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.display_view, parent, false);
        return new leaveAdaptor.PastViewHolder(view);
    }

    static class PastViewHolder extends RecyclerView.ViewHolder{
        TextView nameyear,title,description;


        public PastViewHolder(@NonNull View itemView) {
            super(itemView);
            nameyear = itemView.findViewById(R.id.name1);
            title= itemView.findViewById(R.id.description1);
            description=itemView.findViewById(R.id.description2);


        }
    }
}
