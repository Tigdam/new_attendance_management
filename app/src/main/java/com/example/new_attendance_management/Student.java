package com.example.new_attendance_management;

public class Student {

    String Sname;
    String Semail;
    String Sclass;
    String Sbatch;
    String Spassword;
    String Srollno;

 public Student(){

 }

    public Student(String sname, String sid, String spass, String batch) {
        this.Sname = sname;
        this.Srollno = sid;
        this.Spassword = spass;
        this.Sbatch = batch;
    }

    public Student(String sname, String semail, String sclass, String sbatch, String spassword, String srollno) {
        Sname = sname;
        Semail = semail;
        Sclass = sclass;
        Sbatch = sbatch;
        Spassword = spassword;
        Srollno = srollno;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        Sname = sname;
    }

    public String getSemail() {
        return Semail;
    }

    public void setSemail(String semail) {
        Semail = semail;
    }

    public String getSclass() {
        return Sclass;
    }

    public void setSclass(String sclass) {
        Sclass = sclass;
    }

    public String getSbatch() {
        return Sbatch;
    }

    public void setSbatch(String sbatch) {
        Sbatch = sbatch;
    }

    public String getSpassword() {
        return Spassword;
    }

    public void setSpassword(String spassword) {
        Spassword = spassword;
    }

    public String getSrollno() {
        return Srollno;
    }

    public void setSrollno(String srollno) {
        Srollno = srollno;
    }
}
