package net.skhu.schedule;

import com.google.gson.annotations.SerializedName;

public class Schedule {

    @SerializedName("idx")
    private int idx;

    @SerializedName("where")
    private String where;

    @SerializedName("start")
    private int start;

    @SerializedName("end")
    private int end;

    @SerializedName("total")
    private int total;

    @SerializedName("userid")
    private int userid;

    public Schedule(int idx, String where, int start, int end, int total, int userid) {
        this.idx = idx;
        this.where = where;
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

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
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
