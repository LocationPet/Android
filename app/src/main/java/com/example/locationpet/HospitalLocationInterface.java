package com.example.locationpet;

import com.example.locationpet.dto.HospitalLocation;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HospitalLocationInterface {
    String HOSPITAL_URL = "http://112.153.247.72:8080/api/";

    @GET("api/hospital/all")
    Call<HospitalLocation.Response> HospitalLocationRequest(
            @Query("distance") int distance,
            @Query("lat") double lat,
            @Query("lot") double lot
    );
}
