package net.skhu.notice;

import com.google.gson.annotations.SerializedName;

public class Post {
    @SerializedName("userId")
    private int userId;
    @SerializedName("id")
    private int idd;
    @SerializedName("title")
    private String title;
    @SerializedName("body")
    private String body;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setIdd(int idd) {
        this.idd = idd;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getUserId() {
        return userId;
    }

    public int getIdd() {
        return idd;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
