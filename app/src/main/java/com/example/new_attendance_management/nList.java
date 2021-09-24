package com.example.new_attendance_management;

public class nList {
    String Title;
    String Description;

    public nList() {
    }

    @Override
    public String toString() {
        return "nList{" +
                "Title='" + Title + '\'' +
                ", Description='" + Description + '\'' +
                '}';
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
