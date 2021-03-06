package com.example.veeez.services.http.veeez;

import com.example.veeez.User;
import com.example.veeez.feature.EditUserResponse;
import com.example.veeez.data.ApplyVoucherResponse;
import com.example.veeez.data.AuthResponse;
import com.example.veeez.data.CalculatePriceResponse;
import com.example.veeez.data.UserAddressResponse;
import com.example.veeez.feature.OrderHistoryResponse;
import com.example.veeez.feature.financial.FinancialResponse;
import com.example.veeez.feature.message.UserMessageResponse;
import com.google.gson.JsonObject;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface VeeezApiInterface {
    @GET("Login/Authentication")
    Single<AuthResponse> login(@Query("PhoneNumber") String phone);

    @POST("VerificationCode/Generate")
    Single<AuthResponse> signUp(@Body JsonObject jsonObject);

    @GET("VerificationCode/Confirmation")
    Single<AuthResponse> userVerification(@Query("PhoneNumber") String phone, @Query("Code") String validateCode);

    @GET("VerificationCode/Resend")
    Single<AuthResponse> resendVerification(@Query("PhoneNumber") String phone);

    @POST("Orders/Calculate")
    Single<CalculatePriceResponse> getPrice(@Body JsonObject jsonObject);

    @GET("Orders/GetDiscount")
    Single<ApplyVoucherResponse> setVoucher(@Query("Code") String code, @Query("UserId") String userId, @Query("Cost") int cost, @Query("Price") int price);

    @GET("Server/Connection")
    Single<Boolean> isServerReady();

    @GET("SelectedAddress/List")
    Single<UserAddressResponse> getUserAddressList(@Query("UserId") String userId);

    @GET("UserInformation/Notification")
    Single<UserMessageResponse> getUserMessages(@Query("UserId") String userId);

    @GET("UserInformation/getUser")
    Single<User> getUserInfo(@Query("UserId") String userId);

    @POST("UserInformation/EditUser")
    Single<EditUserResponse> editUserData(@Body JsonObject userObject);

    @GET("UserInformation/Payment")
    Single<FinancialResponse> getFinancialItems(@Query("UserId") String userId);

    @GET("Orders/GetTrips")
    Single<OrderHistoryResponse> getTripList(@Query("UserId") String userId);

}
