package net.skhu.notice;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface NoticeApi {

    //전체 bord불러오기
    @GET("post/")
    Call<List<Notice>> getAll();

    //검색 기능
    @GET("post/title/{title}")
    Call<List<Notice>> getTitle(@Path("title") String title);

    //객체 리스트에서 한 개 클릭 시
    @GET("post/id/{id}")
    Call<Notice> getPost(@Path("id") int id);

    @POST("post/posttest")
    Call<Notice> setPost(@Body Notice notice);

    @FormUrlEncoded
    @POST("post/posttest")
    Call<Notice> setPostJson(
            @Field("id") int id,
            @Field("title") String title,
            @Field("body") String body,
            @Field("start") int start,
            @Field("end") int end,
            @Field("loca") String loca,
            @Field("member") int member,
            @Field("date") int date,
            @Field("hit") int hit,
            @Field("cate") String cate
    );

    @GET("post/test")
    Call<List<Notice>> getDatas(@Query("id") int id) ;




    @GET("post/")
    Call<List<Notice>> getTest();

    //test용임 삭제해
    @GET("/posts")
    Call<List<Post>> getData(@Query("userId") String id);

}
