package com.example.locationpet;

import com.example.locationpet.dto.HospitalLocation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HospitalLocationInterface {
    String HOSPITAL_URL = "http://112.153.247.72:8080/api/";

    @GET("hospital/all")
    Call<List<HospitalLocation.Response>> HospitalLocationRequest(
            @Query("lot") double lot,
            @Query("lat") double lat,
            @Query("distance") int distance
    );
}
