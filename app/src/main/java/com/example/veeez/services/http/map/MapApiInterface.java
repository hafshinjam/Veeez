package com.example.veeez.services.http.map;


import com.example.veeez.feature.map.search.SearchModel;
import com.example.veeez.data.ReverseResponse;
import com.google.gson.JsonObject;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MapApiInterface {

    @GET("search")
    public Single<List<SearchModel>> search(@Query("q") String q, @Query("countrycodes") String countryCodes, @Query("limit") int limit, @Query("format") String format);

    @GET("reverse")
    public Single<ReverseResponse> reverse(@Query("format") Double lat, @Query("lon") Double lon, @Query("format") String format);

    @POST
    Single<String> submitOrder(@Body JsonObject jsonObject);






//    @GET("user/login")
//    Single<RegisterResponse> login(@Query("phone_number")  String phoneNumber);
//    // incloud phone number
//
//    @POST("user/register")
//    Single<RegisterResponse> register(@Body JsonObject jsonObject);
//    // incloud name, family, phoneNumber, introCode

}
