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

    //전체 bord불러오기 사용x
//    @GET("post/")
//    Call<List<Notice>> getAll();

    //페이지 처리한 리스트
    @GET("post/{page}")
    Call<List<Notice>> getPage(@Path("page") int page);

    //검색 기능
    @GET("post/title/{title}/{page}")
    Call<List<Notice>> getTitle(@Path("title") String title, @Path("page") int page);

    //리스트에서 게시글 클릭 시
    @GET("post/id/{id}")
    Call<Notice> getPost(@Path("id") int id);

    //생성
    @POST("post/create")
    Call<Notice> setPost(@Body Notice notice);

    //수정
    @POST("post/edit")
    Call<Notice> editPost(@Body Notice notice);

    //삭제
    @POST("post/delete")
    Call<Notice> deletePost(@Body int id);

    //댓글 목록
    @GET("comment/all/{postidx}")
    Call<List<Comment>> getComment(@Path("postidx") int postidx);

    //댓글 생성
    @POST("comment/create")
    Call<Comment> setComment(@Body Comment comment);

}
