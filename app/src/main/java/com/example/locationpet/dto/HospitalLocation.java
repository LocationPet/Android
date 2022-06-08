package com.example.locationpet.dto;

import com.google.gson.annotations.SerializedName;

public class HospitalLocation {

    public static class HospitalData {
        @SerializedName("id")
        private String id;
        @SerializedName("hospitalName")
        private String hospitalName;
        @SerializedName("hospitalAddress")
        private String hospitalAddress;
        @SerializedName("hospitalTime")
        private String hospitalTime;
        @SerializedName("hosptialLat")
        private double hospitalLat;
        @SerializedName("hosptialLot")
        private double hospitalLot;
        @SerializedName("type")
        private String type;
    }

    public static class Request {
        @SerializedName("id")
        private String id;
        @SerializedName("hospitalName")
        private String hospitalName;
        @SerializedName("hospitalAddress")
        private String hospitalAddress;
        @SerializedName("hospitalTime")
        private String hospitalTime;
        @SerializedName("hosptialLat")
        private double hospitalLat;
        @SerializedName("hosptialLot")
        private double hospitalLot;
        @SerializedName("type")
        private String type;

        public Request(String id, String hospitalName, String hospitalAddress, String hospitalTime, double hospitalLat, double hospitalLot, String type) {
            this.id = id;
            this.hospitalName = hospitalName;
            this.hospitalAddress = hospitalAddress;
            this.hospitalTime = hospitalTime;
            this.hospitalLat = hospitalLat;
            this.hospitalLot = hospitalLot;
            this.type = type;
        }
    }

    public static class Response {
        @SerializedName("id")
        private String id;
        @SerializedName("hospitalName")
        private String hospitalName;
        @SerializedName("hospitalAddress")
        private String hospitalAddress;
        @SerializedName("hospitalTime")
        private String hospitalTime;
        @SerializedName("hospitalLat")
        private double hospitalLat;
        @SerializedName("hospitalLot")
        private double hospitalLot;
        @SerializedName("type")
        private String type;

        public Response(String id, String hospitalName, String hospitalAddress, String hospitalTime, double hospitalLat, double hospitalLot, String type) {
            this.id = id;
            this.hospitalName = hospitalName;
            this.hospitalAddress = hospitalAddress;
            this.hospitalTime = hospitalTime;
            this.hospitalLat = hospitalLat;
            this.hospitalLot = hospitalLot;
            this.type = type;
        }

        public String getHospitalName() {
            return hospitalName;
        }

        public String getId() {
            return id;
        }

        public String getHospitalAddress() {
            return hospitalAddress;
        }

        public String getHospitalTime() {
            return hospitalTime;
        }

        public String getType() {
            return type;
        }

        public double getHospitalLat() {
            return hospitalLat;
        }

        public double getHospitalLot() {
            return hospitalLot;
        }
    }
}