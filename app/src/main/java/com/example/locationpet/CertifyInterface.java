package com.example.locationpet;

import com.example.locationpet.dto.Register;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CertifyInterface {
    String CERTIFY_URL = "http://112.153.247.72:8080/api/";
    @GET("auth/check/check-email")
    Call<Register.Response> GetRequestEmail(
            @Query("userEmail") String userEmail
    );

    @GET("auth/check/nickname")
    Call<Register.Response> GetRequestName(
            @Query("userNickName") String userNickName

    );
}
