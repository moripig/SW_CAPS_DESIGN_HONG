package net.skhu.notice;

import com.google.gson.annotations.SerializedName;

public class Notice {

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("body")
    private String body;

    @SerializedName("start")
    private int start;

    @SerializedName("end")
    private int end;

    @SerializedName("loca")
    private String loca;

    @SerializedName("member")
    private int member;

    @SerializedName("date")
    private int date;

    @SerializedName("writeridx")
    private int writeridx;

    @SerializedName("cate")
    private String cate;

    public Notice(int id, String title, String body, int start, int end, String loca, int member, int date, int hit, String cate) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.start = start;
        this.end = end;
        this.loca = loca;
        this.member = member;
        this.date = date;
        this.writeridx = hit;
        this.cate = cate;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public String getLoca() {
        return loca;
    }

    public int getMember() {
        return member;
    }

    public int getDate() {
        return date;
    }

    public int getWriteridx() {
        return writeridx;
    }

    public String getCate() {
        return cate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public void setLoca(String loca) {
        this.loca = loca;
    }

    public void setMember(int member) {
        this.member = member;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setWriteridx(int writeridx) {
        this.writeridx = writeridx;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }
}
