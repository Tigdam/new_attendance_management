package com.example.new_attendance_management;

import android.content.Context;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;
class Tadaptor extends FirebaseRecyclerAdapter<Tlist, Tadaptor.PastViewHolder> {
    private final Object cls ;
    private Context context;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param option
     * @param mca
     */
    public Tadaptor(@NonNull FirebaseRecyclerOptions<Tlist> options, String cls, Context context) {
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

        holder.update.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                DialogPlus dialog = DialogPlus.newDialog(context)

                        .setContentHolder(new ViewHolder(R.layout.Timetable_content))
                        .setMargin(100,0,100,0)
                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();
                View holderView = dialog.getHolderView();
                TextView t1= holderView.findViewById(R.id.t_day);
                EditText l1= holderView.findViewById(R.id.lec_1);
                EditText l2= holderView.findViewById(R.id.lec_2);
                EditText l3= holderView.findViewById(R.id.lec_3);
                EditText l4= holderView.findViewById(R.id.lec_4);
                EditText l5= holderView.findViewById(R.id.lec_5);
                EditText l6= holderView.findViewById(R.id.lce_6);
                EditText l7= holderView.findViewById(R.id.lec_7);
                t1.setText(model.getDay());
                l1.setText(model.getLec1());
                l2.setText(model.getLec2());
                l3.setText(model.getLec3());
                l4.setText(model.getLec4());
                l5.setText(model.getLec5());
                l6.setText(model.getLec6());
                l7.setText(model.getLec7());
                Button submit = holderView.findViewById(R.id.T_button);
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Map<String,Object> map= new HashMap<>();
                        map.put("lec1",l1.getText().toString());
                        map.put("lec2",l2.getText().toString());
                        map.put("lec3",l3.getText().toString());
                        map.put("lec4",l4.getText().toString());
                        map.put("lec5",l5.getText().toString());
                        map.put("lec6",l6.getText().toString());
                        map.put("lec7",l7.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child((String) cls)
                                .child(getRef(position).getKey())
                                .updateChildren(map)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        dialog.dismiss();
                                    }
                                });

                    }
                });

                dialog.show();

            }
        });



    }



    @NonNull
    @Override
    public PastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.Timetable_post, parent, false);
        return new PastViewHolder(view);

    }

    class PastViewHolder extends RecyclerView.ViewHolder{
        TextView tday,t1,t2,t3,t4,t5,t6,t7;
        ImageView update;



        public PastViewHolder(@NonNull View itemView) {
            super(itemView);
            tday= itemView.findViewById(R.id.tday);
            t1= itemView.findViewById(R.id.tb1);
            t2= itemView.findViewById(R.id.tb2);
            t3= itemView.findViewById(R.id.tb3);
            t4= itemView.findViewById(R.id.tb4);
            t5= itemView.findViewById(R.id.tb5);
            t6= itemView.findViewById(R.id.tb6);
            t7= itemView.findViewById(R.id.tb7);
            update= itemView.findViewById(R.id.t_update);




        }
    }
}