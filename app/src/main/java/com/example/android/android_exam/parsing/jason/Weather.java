
package com.example.android.android_exam.parsing.jason;

/**
 * Created by student on 2015-09-14.
 */
public class Weather {
    private String time;
    private String temp;
    private String description;

    public Weather(String time, String temp, String description) {
        this.time = time;
        this.temp = temp;
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "time:" + time + "temp:" + temp + "description" + description;
    }
}
