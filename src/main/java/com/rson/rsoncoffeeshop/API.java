package com.rson.rsoncoffeeshop;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface API {

    @POST("api/v2/register")
    Call<ResponseBody> createUser (
            @Body User user
    );

    @POST("api/v3/login")
    Call<ResponseBody> checkUser (
            @Body User user
    );

    @POST("api/v3/logout")
    Call<ResponseBody> logoutUser (
        @Body User user
    );

    @POST("api/v3/giftcards")
    Call<List<GiftCard>> getGiftCards (
        @Body User user
    );

    @POST("api/v3/account/user")
    Call<User> getLoggedUser (
            @Body User user
    );

    @POST("api/v3/giftcard/add")
    Call<ResponseBody> addGiftCard (
            @Body GiftCard giftCard
    );
}
