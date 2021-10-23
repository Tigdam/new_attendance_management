package com.example.new_attendance_management;

public class UserHelperClass {

    String n, title, des, type_of_leave, toDate, fromDate, name, url;

    public UserHelperClass(String toString, String toString1) {
        this.name = name;
        this.url = url;
    }

    public UserHelperClass(String n,String title, String des, String typ_of_leave, String fromDate, String toDate) {
        this.n = n;
        this.title = title;
        this.des = des;
        this.type_of_leave = type_of_leave;
        this.toDate = toDate;
        this.fromDate = fromDate;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getType_of_leave() {
        return type_of_leave;
    }

    public void setType_of_leave(String type_of_leave) {
        this.type_of_leave = type_of_leave;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public UserHelperClass() {
    }
    @Override
    public String toString() {
        return "UserHelperClass{" +
                "n='" + n + '\'' +
                "title='" + title + '\'' +
                ", des='" + des + '\'' +
                '}';
    }


}
