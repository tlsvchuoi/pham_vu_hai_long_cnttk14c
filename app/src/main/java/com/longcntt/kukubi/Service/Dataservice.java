package com.longcntt.kukubi.Service;

import com.longcntt.kukubi.model.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Dataservice {

    @FormUrlEncoded
    @POST("login.php")
    Call<String> check(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("register.php")
    Call<String> insert(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("insert.php")
    Call<String> add(@Field("id") int id, @Field("total") int total, @Field("rank") int rank, @Field("status") int status);

    @FormUrlEncoded
    @POST
    Call<User> getUser(@Field("username") String username);
}
