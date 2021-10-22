package com.example.new_attendance_management;



import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;
public class stAdaptor extends FirebaseRecyclerAdapter<Tlist, stAdaptor.PastViewHolder> {
    private final Object cls ;
    private final Context context;


    public stAdaptor(@NonNull FirebaseRecyclerOptions<Tlist> options, String cls, Context context) {
        super(options);
        this.context=context;
        this.cls=cls;
    }

    @Override
    protected void onBindViewHolder(@NonNull PastViewHolder holder, int position, @NonNull Tlist model) {
        holder.tday.setText(model.getDay());
        holder.t1.setText(model.getLec1());
        holder.t2.setText(model.getLec2());
        holder.t3.setText(model.getLec3());
        holder.t4.setText(model.getLec4());
        holder.t5.setText(model.getLec5());
        holder.t6.setText(model.getLec6());
        holder.t7.setText(model.getLec7());
        if(position == 0)
        {
            holder.Str.setBackgroundColor(Color.GREEN);
        }
        else
        {
            holder.Str.setBackgroundColor(Color.WHITE);
        }





    }



    @NonNull
    @Override
    public PastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.st_post, parent, false);
        return new PastViewHolder(view);

    }

    class PastViewHolder extends RecyclerView.ViewHolder {
        TextView tday, t1, t2, t3, t4, t5, t6, t7;
        TableRow Str;



        public PastViewHolder(@NonNull View itemView) {
            super(itemView);
            tday = itemView.findViewById(R.id.tday);
            t1 = itemView.findViewById(R.id.tb1);
            t2 = itemView.findViewById(R.id.tb2);
            t3 = itemView.findViewById(R.id.tb3);
            t4 = itemView.findViewById(R.id.tb4);
            t5 = itemView.findViewById(R.id.tb5);
            t6 = itemView.findViewById(R.id.tb6);
            t7 = itemView.findViewById(R.id.tb7);
            Str=itemView.findViewById(R.id.Str);


        }
    }
    }