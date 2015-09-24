
package com.example.android.android_exam.Calendar.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by student on 2015-09-09.
 */
public class Schedule implements Parcelable {

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.date);
        dest.writeInt(this.hour);
        dest.writeInt(this.minute);
        dest.writeString(this.contents);
    }

    protected Schedule(Parcel in) {
        this.id = in.readLong();
        this.date = in.readString();
        this.hour = in.readInt();
        this.minute = in.readInt();
        this.contents = in.readString();
    }

    public static final Parcelable.Creator<Schedule> CREATOR = new Parcelable.Creator<Schedule>() {
        public Schedule createFromParcel(Parcel source) {
            return new Schedule(source);
        }

        public Schedule[] newArray(int size) {
            return new Schedule[size];
        }
    };
}
