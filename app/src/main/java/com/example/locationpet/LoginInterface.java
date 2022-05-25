package com.example.locationpet;

import com.example.locationpet.dto.Login;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginInterface {
    String LOGIN_URL = "http://112.153.247.72:8080/api/";
    @POST("auth/")
        //반환타입 Call<retroRequest> - Call은 응답이 왔을때 Callback으로 불려질 타입
        //retroRequest - 요청GET에 대한 응답데이터를 받아서 DTO 객체화할 클래스 타입 지정
        //매개변수 '@Path("post") String post' - 매개변수 post가 @Path("post")를 보고 @GET 내부 {post}에 대입
    Call<Login.Response> PostRequest(
            @Body Login.Request request
    );

    Call<Login.Response> PostToken (
            @Body Login.TokenRequest tokenRequest
    );
}
