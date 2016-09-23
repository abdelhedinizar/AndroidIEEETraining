package com.nizar.abdelhedi.androidieeetraining;

/**
 * Created by abdelhedi on 24/09/2016.
 */
public class TODO {

    String name;
    String memo;
    String Date;

    public TODO(String name, String memo, String date) {
        this.name = name;
        this.memo = memo;
        Date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public TODO(String name, String memo) {
        this.name = name;
        this.memo = memo;
    }


}
