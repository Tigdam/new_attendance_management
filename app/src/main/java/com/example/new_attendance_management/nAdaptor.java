package com.example.new_attendance_management;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
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


public class nAdaptor extends FirebaseRecyclerAdapter<nList, nAdaptor.PastViewHolder> {
    private Context context;
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public nAdaptor(@NonNull FirebaseRecyclerOptions<nList> options, Context context) {
        super(options);
        this.context=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull PastViewHolder holder, int position, @NonNull nList model) {
        holder.title.setText(model.getTitle());
        holder.description.setText(model.getDescription());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference().child("Notice")
                        .child(getRef(0).getKey())
                        .removeValue()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });
            }
        });
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPlus dialog = DialogPlus.newDialog(context)
                        .setGravity(Gravity.CENTER)
                        .setMargin(50,0,50,0)
                       .setContentHolder(new ViewHolder(R.layout.content))
                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();
                View holderView =(ConstraintLayout)dialog.getHolderView();
                EditText title= holderView.findViewById(R.id.title2);
                EditText discription= holderView.findViewById(R.id.descrption2);
                Button update = holderView.findViewById(R.id.submit);
                title.setText(model.getTitle());
                discription.setText(model.getDescription());
                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map=new HashMap<>();
                      map.put("Title",title.getText().toString());
                      map.put("Description",discription.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Notice")
                                .child(getRef(0).getKey())
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
                .inflate(R.layout.post, parent, false);
        return new PastViewHolder(view);

    }

    class PastViewHolder extends RecyclerView.ViewHolder{
        TextView title,description;
        ImageView delete,add,update;

        public PastViewHolder(@NonNull View itemView) {
            super(itemView);
            title= itemView.findViewById(R.id.title1);
            description=itemView.findViewById(R.id.description1);
            delete= itemView.findViewById(R.id.Delete);
            update=itemView.findViewById(R.id.update1);

        }
    }
}
