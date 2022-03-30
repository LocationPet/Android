package com.example.locationpet.dto;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceHelper {
    private final String INTRO = "intro"; // boolean형 조건을 걸기 위함.
    private final String userEmail = "userEmail";
    private final String userPassword = "userPassword";
    //api info 부분
    private final String userUid = "userUid";
    private final String userNickName = "userNickName";
    private final String userAge = "userAge";
    //api animal 부분
    private final String animalName = "animalName";
    private final String animalKind = "animalKind";
    //api Location 부분 PATH로 전송
    private final String userLat = "userLat";
    private final String userLot = "userLot";
    //api Token 부분 PATH로 전송
    private final String accessToken = "accessToken";
    private final String refreshToken = "refreshToken";

    private SharedPreferences preferences;
    private Context context;

    public SharedPreferenceHelper(Context context)
    {
        preferences = context.getSharedPreferences("shared", 0);
    }

    public void putIsLogin(boolean loginOrOut) // boolean type의 loginOrOut
    {
        SharedPreferences.Editor edit = preferences.edit();
        edit.putBoolean(INTRO, loginOrOut);
        edit.apply(); // 반드시 apply() 해주어야 적용이 된다.
    }

    public void putUserEmail(String loginOrOut) //string type의 loginOrOut
    {
        SharedPreferences.Editor edit = preferences.edit(); // edit을 사용한 데이터 저장
        edit.putString(userEmail, loginOrOut); // String타입 데이터 저장
        edit.apply(); // apply() 로 저장
    }

    public String getUserEmail()
    {
        return preferences.getString(userEmail, "");
    }

    public void putUserPassword(String loginOrOut)
    {
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString(userPassword, loginOrOut);
        edit.apply();
    }

    public String getUserPassword() {
        return preferences.getString(userPassword, "");
    }

    public void putUserUid(long loginOrOut)
    {
        SharedPreferences.Editor edit = preferences.edit();
        edit.putLong(userUid, loginOrOut);
        edit.apply();
    }

    public long getUserUid() {
        return Long.parseLong(preferences.getString(userUid, ""));
    }

    public void putUserNickName(String loginOrOut)
    {
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString(userNickName, loginOrOut);
        edit.apply();
    }

    public String getUserNickName()
    {
        return preferences.getString(userNickName, "");
    }

    public void putUserAge (int loginOrOut)
    {
        SharedPreferences.Editor edit = preferences.edit();
        edit.putInt(userNickName, loginOrOut);
        edit.apply();
    }

    public int getUserAge ()
    {
        return Integer.parseInt(preferences.getString(userAge, ""));
    }

    public void putAnimalName(int loginOrOut)
    {
        SharedPreferences.Editor edit = preferences.edit();
        edit.putInt(animalName, loginOrOut);
        edit.apply();
    }

    public String getAnimalName()
    {
        return preferences.getString(animalName, "");
    }

    public void putAnimalKind(int loginOrOut)
    {
        SharedPreferences.Editor edit = preferences.edit();
        edit.putInt(animalKind, loginOrOut);
        edit.apply();
    }

    public String getAnimalKind()
    {
        return preferences.getString(animalKind, "");
    }

    public void putUserLat(int loginOrOut)
    {
        SharedPreferences.Editor edit = preferences.edit();
        edit.putInt(userUid, loginOrOut);
        edit.apply();
    }

    public long getUserLat()
    {
        return Long.parseLong(preferences.getString(userUid, ""));
    }

    public void putUserLot(int loginOrOut)
    {
        SharedPreferences.Editor edit = preferences.edit();
        edit.putInt(userLot, loginOrOut);
        edit.apply();
    }

    public double getUserLot()
    {
        return Double.parseDouble(preferences.getString(userLat, ""));
    }

    public void putAccessToken(int loginOrOut)
    {
        SharedPreferences.Editor edit = preferences.edit();
        edit.putInt(accessToken, loginOrOut);
        edit.apply();
    }

    public String getAccessToken()
    {
        return preferences.getString(accessToken, "");
    }

    public void putRefreshToken(int loginOrOut)
    {
        SharedPreferences.Editor edit = preferences.edit();
        edit.putInt(animalName, loginOrOut);
        edit.apply();
    }

    public String getRefreshToken()
    {
        return preferences.getString(refreshToken, "");
    }
}
