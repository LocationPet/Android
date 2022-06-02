package com.example.locationpet.dto;

import com.google.gson.annotations.SerializedName;

public class Register {

    public static class Info {
        @SerializedName("userNickName")
        private String userNickName;
        @SerializedName("userAge")
        private long userAge;

        public Info(String userNickName, long userAge) {
            this.userNickName = userNickName;
            this.userAge = userAge;
        }
    }

    public static class Location{
        @SerializedName("streetSIDO")
        private String streetSIDO;
        @SerializedName("streetSIGUNGU")
        private String streetSIGUNGU;
        @SerializedName("streetEUPMYENDONG")
        private String streetEUPMYENDONG;

        public Location(String streetSIDO, String streetSIGUNGU, String streetEUPMYENDONG) {
            this.streetSIDO = streetSIDO;
            this.streetSIGUNGU = streetSIGUNGU;
            this.streetEUPMYENDONG = streetEUPMYENDONG;
        }
    }

    public static class AnimalOption {
        @SerializedName("animalName")
        private String animalName;
        @SerializedName("animalKind")
        private String animalKind;
        @SerializedName("animalBirth")
        private long animalBirth;

        public AnimalOption(String animalName, String animalKind, long animalBirth) {
            this.animalName = animalName;
            this.animalKind = animalKind;
            this.animalBirth = animalBirth;
        }
    }

    public static class Request {
        @SerializedName("userEmail")
        private String userEmail;
        @SerializedName("userPassword")
        private String userPassword;
        @SerializedName("Info")
        private Info info;
        @SerializedName("animalOption")
        private AnimalOption animalOption;
        @SerializedName("location")
        private Location location;

        public Request(String userEmail) {
            this.userEmail = userEmail;
        }

        public Request(String userEmail, String userPassword, Info info, AnimalOption animalOption, Location location) {
            this.userEmail = userEmail;
            this.userPassword = userPassword;
            this.info = info;
            this.animalOption = animalOption;
            this.location = location;
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
