package net.skhu.notice;

import com.google.gson.annotations.SerializedName;

public class Comment {
    @SerializedName("idx")
    private int idx;

    @SerializedName("body")
    private String body;

    @SerializedName("date")
    private int date;

    @SerializedName("postidx")
    private int postidx;

    @SerializedName("writer")
    private int writer;

    public Comment(int idx, String body, int date, int postidx, int writer) {
        this.idx = idx;
        this.body = body;
        this.date = date;
        this.postidx = postidx;
        this.writer = writer;
    }

    public void setWriter(int writer) {
        this.writer = writer;
    }

    public int getWriter() {
        return writer;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setPostidx(int postidx) {
        this.postidx = postidx;
    }

    public int getIdx() {
        return idx;
    }

    public String getBody() {
        return body;
    }

    public int getDate() {
        return date;
    }

    public int getPostidx() {
        return postidx;
    }
}
