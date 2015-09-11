
package com.example.android.android_exam.Calendar.model;

/**
 * Created by student on 2015-09-09.
 */
public class Schedule {

    private int hour;
    private int minute;
    private String contents;

    public Schedule(int hour, int minute, String contents) {
        this.hour = hour;
        this.minute = minute;
        this.contents = contents;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        return hour + " : " + minute + " " + contents;
    }
}
