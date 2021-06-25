package com.example.new_attendance_management;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.new_attendance_management.R;

import java.util.List;

public class RecyclerView_Config {

    //reading data started
    private Context mContext;
    private FacultyAdaptor mFacultyAdaptor;
    public void setConfig(RecyclerView recyclerView, Context context, List<Faculty> faculties, List<String> keys){
        mContext=context;
        mFacultyAdaptor=new FacultyAdaptor(faculties,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mFacultyAdaptor);
    }
    class FacultyItemView extends RecyclerView.ViewHolder
    {
        private TextView mName;
        private TextView mId;
        private TextView mType;


        private String key;

        public FacultyItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.faculty_item,parent,false));

            mName=(TextView)itemView.findViewById(R.id.text_view_title);
            mId=(TextView)itemView.findViewById(R.id.text_view_email);
            mType=(TextView)itemView.findViewById(R.id.txttype);

            //reading data ended


            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(mContext, UpdateDeleteFaculty.class);
                    intent.putExtra("key",key);
                    intent.putExtra("tname",mName.getText().toString());
                    intent.putExtra("tid",mId.getText().toString());
                    intent.putExtra("type",mType.getText().toString());

                    mContext.startActivity(intent);
                }
            });

        }

        // reading data started
        public void bind(Faculty faculty, String key){
                mName.setText(faculty.getTname());
                mId.setText(faculty.getTid());
                mType.setText(faculty.getType());
                this.key = key;
        }
    }
    class FacultyAdaptor extends RecyclerView.Adapter<FacultyItemView>{

        private List<Faculty> mFacultyList;
        private List<String> mKeys;

        public FacultyAdaptor(List<Faculty> mFacultyList, List<String> mKeys) {
            this.mFacultyList = mFacultyList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public FacultyItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new FacultyItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull FacultyItemView holder, int position) {
            holder.bind(mFacultyList.get(position),mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mFacultyList.size();
        }
    }
}
//reading data ended
