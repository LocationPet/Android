package com.example.locationpet.dto;

import com.google.gson.annotations.SerializedName;

public class Login {

    public class Info{
        @SerializedName("userUid")
        private long userUid;
        @SerializedName("userNickName")
        private String userNickName;
        @SerializedName("userAge")
        private int userAge;
    }

    public class Animal{
        @SerializedName("animalName")
        private String animalName;
        @SerializedName("animalKind")
        private String animalKind;
    }

    public class Location{
        @SerializedName("userLat")
        private double userLat;
        @SerializedName("userLot")
        private double userLot;
    }

    public class Token{
        @SerializedName("accesToken")
        private String accesToken;
        @SerializedName("refreshToken")
        private String refreshToken;
    }


    public static class Request{
        // DTO 모델 - retroRequest Class 선언
        // SerializedName 을 붙이는 이유는 Response <-> DTO(Data Transfer Object)간의 Variable맵핑을 위해 사용.
        // Response를 DTO로 변환할 떄, SerializedName에 같은 변수명으로 할당 된 곳에 옮겨 담아준다.
        @SerializedName("userEmail")
        private String userEmail;

        @SerializedName("userPassword")
        private String userPassword;

        public Request(String userEmail, String userPassword){ // SETTER 객체변수에 값을 설정함..

            this.userEmail = userEmail;
            this.userPassword = userPassword;
        }
    }

    public static class Response{
        @SerializedName("info")
        private Info info;
        @SerializedName("animal")
        private Animal animal;
        @SerializedName("location")
        private Location location;
        @SerializedName("token")
        private String token;
        private String userEmail;
        public String getUserEmail (){
            return this.userEmail;
        }

        public Response(String token) {
            this.token = token;
        }
        public String getToken () {return this.token; }
    }
}
