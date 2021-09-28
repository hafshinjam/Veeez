package com.example.veeez.services.http.veeez;

import com.example.veeez.data.AuthResponse;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface VeeezApiInterface {
    @GET("Login/Authentication")
    Single<AuthResponse> login(@Query("phone") String phone);

    @POST("VerificationCode/Generate")
    Single<AuthResponse> signUp(@Body JsonObject jsonObject);

    @GET("VerificationCode/Confirmation")
    Single<AuthResponse> userVerification(@Query("phone") String phone, @Query("validate_code") String validateCode);

    @GET("VerificationCode/Resend")
    Single<AuthResponse> resendVerification(@Query("phone") String phone);

}
