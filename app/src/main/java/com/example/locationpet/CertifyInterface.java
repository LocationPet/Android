package com.example.locationpet;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CertifyInterface {
    String CERTIFY_URL = "http://112.153.247.72:8080/api/";
    @GET("auth/check/email-check")
    Call<String> GetRequestEmail(
            @Query("email") String userEmail
    );

    @GET("auth/check/nickname")
    Call<String> GetRequestName(
            @Query("nickname") String userNickName
    );
}
