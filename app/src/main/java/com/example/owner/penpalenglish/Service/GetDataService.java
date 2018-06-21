package com.example.owner.penpalenglish.Service;


import com.example.owner.penpalenglish.Model.UserProfile;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

//    @GET("/photos")
//    Call<List<RetroPhoto>> getAllPhotos();

    @GET("giaserver/users/profiles")
    Call<List<UserProfile>> getAllUser();
}
