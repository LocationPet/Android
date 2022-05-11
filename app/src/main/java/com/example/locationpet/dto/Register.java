package com.example.locationpet.dto;

import com.google.gson.annotations.SerializedName;

public class Register {

    public class Info {
        @SerializedName("userNickName")
        private String userNickName;
        @SerializedName("userAge")
        private Date userAge;
    }
    public class AnimalOption {
        @SerializedName("animalName")
        private String animalName;
        @SerializedName("animalKind")
        private String animalKind;
        @SerializedName("animalBirth")
        private String animalBirth;
    }

    public static class Request {
        @SerializedName("userEmail")
        private String userEmail;
        @SerializedName("userPassword")
        private String userPassword;
        
        private Info info;
        private AnimalOption animalOption;

        public Request(String userEmail, String userPassword, String info, String animalOption) {
            this.userEmail = userEmail;
            this.userPassword = userPassword;
            this.Info = info;
            this.AnimalOption = animalOption;
        }
    }

    public static class Response {
        @SerializedName("userNickName")
        private String userNickName;
        private String getUserNickName() { return this.userNickName; }

        @SerializedName("userAge")
        private int userAge;
        private int getUserAge() { return this.userAge; }

        @SerializedName("userEmail")
        private String userEmail;
        private String getUserEmail() { return this.userEmail; }

        @SerializedName("userPassword")
        private String userPassword;
        private String getUserPassword() { return this.userPassword; }
    }
}
