package com.example.locationpet;

import com.example.locationpet.dto.Register;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterInterface {
    String REGISTER_URL = "http://112.153.247.72:8080/api/";
    @POST("auth/signup")
    Call<Register.Response> PostRequest(
            @Body Register.Request request
    );
}
