package com.example.new_attendance_management;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class hAdaptor extends FirebaseRecyclerAdapter<model,myadapter.myviewholder> {


    public hAdaptor(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myadapter.myviewholder holder, int position, @NonNull model model) {
        holder.to.setText(model.getHolidayTo());
        holder.frm.setText(model.getHolidayFrom());
        holder.des.setText(model.getHolidayDes());
    }


    @NonNull
    @Override
    public myadapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow2,parent,false);
        return new myadapter.myviewholder(view);
    }

    static class myviewholder extends RecyclerView.ViewHolder
    {

        ImageView edit,delete;
        TextView to, frm, des;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);

            to = itemView.findViewById(R.id.date);
            frm = itemView.findViewById(R.id.day);
            des = itemView.findViewById(R.id.occasion);


        }
    }
}
