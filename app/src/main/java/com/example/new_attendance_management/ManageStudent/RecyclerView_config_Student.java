package com.example.new_attendance_management.ManageStudent;

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
import com.example.new_attendance_management.Student;

import java.util.List;

public class RecyclerView_config_Student {

    //reading data started
    private Context mContext;
    private StudentAdaptor mStudentAdaptor;
    public void setConfig(RecyclerView recyclerView, Context context, List<Student> students, List<String> keys){
        mContext=context;
        mStudentAdaptor=new StudentAdaptor(students,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mStudentAdaptor);
    }
    class StudentItemView extends RecyclerView.ViewHolder
    {
        private TextView mName;
        private TextView mEmail;
        private String key,mRollNo,mBatch,mClass;

        public StudentItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.faculty_item,parent,false));

            mName=(TextView)itemView.findViewById(R.id.text_view_title);
            mEmail=(TextView)itemView.findViewById(R.id.text_view_email);
            //mType=(TextView)itemView.findViewById(R.id.txttype);

            //reading data ended

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(mContext, StudentModification.class);
                    intent.putExtra("key",key);
                    intent.putExtra("sname",mName.getText().toString());
                    intent.putExtra("semail",mEmail.getText().toString());
                    intent.putExtra("srollno",mRollNo);
                    intent.putExtra("sclass",mClass);
                    intent.putExtra("sbatch",mBatch);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                }
            });

        }

        // reading data started
        public void bind(Student student, String key){
            mName.setText(student.getSname());
            mEmail.setText(student.getSemail());
            mRollNo=student.getSrollno();
            mClass=student.getSclass();
            mBatch=student.getSbatch();
            this.key = key;
        }
    }
    class StudentAdaptor extends RecyclerView.Adapter<StudentItemView>{

        private List<Student> mStudentList;
        private List<String> mKeys;

        public StudentAdaptor(List<Student> mStudentList, List<String> mKeys) {
            this.mStudentList = mStudentList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public StudentItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new StudentItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull StudentItemView holder, int position) {
            holder.bind(mStudentList.get(position),mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mStudentList.size();
        }
    }
}
//reading data ended
