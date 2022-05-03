package com.example.locationpet;

import com.example.locationpet.dto.Register;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterInterface {
    String LOGIN_URL = "http://3.39.42.187:8080/api/";
    @POST("auth/signup")
    Call<Register.Response> PostRequest(
            @Body Register.Request request
    );
}
