package com.example.new_attendance_management;

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
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

public class myadapterHoliday extends FirebaseRecyclerAdapter<model,myadapter.myviewholder> {


    public myadapterHoliday(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull myadapter.myviewholder holder, int position, @NonNull model model) {
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







        });




    }


    @NonNull
    @Override
    public myadapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlarowstudent,parent,false);
        return new myadapter.myviewholder(view);
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


