
package com.example.android.android_exam.Calendar.model;

/**
 * Created by student on 2015-09-09.
 */
public class Schedule {

    private long id;
    private String date;
    private int hour;
    private int minute;
    private String contents;

    public Schedule(int hour, int minute, String contents) {
        this.hour = hour;
        this.minute = minute;
        this.contents = contents;
    }

    public Schedule(long id, String date, int hour, int minute, String contents) {
        this(hour, minute, contents);

        this.id = id;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
