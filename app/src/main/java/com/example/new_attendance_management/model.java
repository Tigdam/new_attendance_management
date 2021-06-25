package com.example.new_attendance_management;

public class model {
    String HolidayTo, HolidayFrom, HolidayDes;

    public model() {
    }

    public model(String holidayTo, String holidayFrom, String holidayDes) {
        HolidayTo = holidayTo;
        HolidayFrom = holidayFrom;
        HolidayDes = holidayDes;
    }

    public String getHolidayTo() {
        return HolidayTo;
    }

    public void setHolidayTo(String holidayTo) {
        HolidayTo = holidayTo;
    }

    public String getHolidayFrom() {
        return HolidayFrom;
    }

    public void setHolidayFrom(String holidayFrom) {
        HolidayFrom = holidayFrom;
    }

    public String getHolidayDes() {
        return HolidayDes;
    }

    public void setHolidayDes(String holidayDes) {
        HolidayDes = holidayDes;
    }
}
