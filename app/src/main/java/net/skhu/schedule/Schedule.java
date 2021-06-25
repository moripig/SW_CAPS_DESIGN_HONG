package net.skhu.schedule;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Schedule implements Serializable {

    @SerializedName("idx")
    private int idx;

    @SerializedName("place")
    private String place;

    @SerializedName("start")
    private int start;

    @SerializedName("end")
    private int end;

    @SerializedName("total")
    private int total;

    @SerializedName("userid")
    private int userid;

    public Schedule(int idx, String place, int start, int end, int total, int userid) {
        this.idx = idx;
        this.place = place;
        this.start = start;
        this.end = end;
        this.total = total;
        this.userid = userid;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
