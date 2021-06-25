package com.example.new_attendance_management;

import android.app.AlertDialog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;

import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;


public class myadapter extends FirebaseRecyclerAdapter<model,myadapter.myviewholder>
{

    public myadapter(@NonNull FirebaseRecyclerOptions<model> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder, final int position, @NonNull final model model)
    {
        holder.to.setText(model.getHolidayTo());
        holder.frm.setText(model.getHolidayFrom());
        holder.des.setText(model.getHolidayDes());

        holder.edit.setOnClickListener(view -> {
            DialogPlus dialogPlus = DialogPlus.newDialog(holder.to.getContext())
                    .setContentHolder(new ViewHolder(R.layout.dialogcentent))
                    .setExpanded(true,1100)
                    .create();

            View myview = dialogPlus.getHolderView();

            final EditText updateTo = myview.findViewById(R.id.dateto);
            final EditText updateFrm = myview.findViewById(R.id.datefrm);
            final EditText updateDes = myview.findViewById(R.id.occasion);
            Button submit = myview.findViewById(R.id.usubmit);




            updateTo.setText(model.getHolidayTo());
            updateFrm.setText(model.getHolidayFrom());
            updateDes.setText(model.getHolidayDes());

            dialogPlus.show();

            submit.setOnClickListener(view1 -> {
                Map<String,Object> map = new HashMap<>();
                map.put("HolidayTo",updateTo.getText().toString());
                map.put("HolidayFrom",updateFrm.getText().toString());
                map.put("HolidayDes",updateDes.getText().toString());

                FirebaseDatabase.getInstance().getReference().child("students")
                        .child(getRef(position).getKey()).updateChildren(map)
                        .addOnSuccessListener(aVoid -> dialogPlus.dismiss())
                        .addOnFailureListener(e -> dialogPlus.dismiss());
            });


        });


        holder.delete.setOnClickListener(view -> {
            AlertDialog.Builder builder=new AlertDialog.Builder(holder.to.getContext());
            builder.setTitle("Delete Panel");
            builder.setMessage("Delete?");

            builder.setPositiveButton("Yes", (dialogInterface, i) -> FirebaseDatabase.getInstance().getReference().child("students")
                    .child(getRef(position).getKey()).removeValue());

            builder.setNegativeButton("No", (dialogInterface, i) -> {

            });

            builder.show();
        });

    } // End of OnBindViewMethod

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myviewholder(view);
    }


    class myviewholder extends RecyclerView.ViewHolder
    {

        ImageView edit,delete;
        TextView to, frm, des;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);

            to = itemView.findViewById(R.id.date);
            frm = itemView.findViewById(R.id.day);
            des = itemView.findViewById(R.id.occasion);

            edit=(ImageView)itemView.findViewById(R.id.editicon);
            delete=(ImageView)itemView.findViewById(R.id.deleteicon);
        }
    }
}
