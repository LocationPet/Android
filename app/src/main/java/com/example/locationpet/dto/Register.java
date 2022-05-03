package com.example.locationpet.dto;

import com.google.gson.annotations.SerializedName;

public class Register {

    public class info {
        @SerializedName("userNickName")
        private String userNickName;
        @SerializedName("userAge")
        private int userAge;
    }
    public class animal_option {
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
        @SerializedName("animalName")
        private String animalName;
        @SerializedName("animalKind")
        private String animalKind;

        public Request(String userEmail, String userPassword, String animalName, String animalKind) {
            this.userEmail = userEmail;
            this.userPassword = userPassword;
            this.animalName = animalName;
            this.animalKind = animalKind;
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
