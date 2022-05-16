package com.example.locationpet;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.locationpet.dto.Login;
import com.example.locationpet.dto.SharedPreferenceHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private EditText join_email, join_password;
    private Button joinBtn;
    private SharedPreferenceHelper preferenceHelper;
    AlertDialog dialog;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(LoginInterface.LOGIN_URL)
            //baseUrl을 등록하는 것으로 반드시 마지막은 / 이여야한다.
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            //GsonConverterFactory.create()는 JSON으로 변환해줄 변환기이다.
            .build();

    LoginInterface loginInterface = retrofit.create(LoginInterface.class);
    // retrofit 인스턴스로 인터페이스 객체 구현 *반드시 인터페이스이어야 함.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        preferenceHelper = new SharedPreferenceHelper(this);

        join_email = findViewById(R.id.join_email);
        join_password = findViewById(R.id.join_password);

        joinBtn = findViewById(R.id.joinBtn);

        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        //.trim() 함수는 오른쪽 왼쪽의 공백을 제거해주는데 유용하게 쓰인다.
        final String userEmail = join_email.getText().toString().trim();
        final String userPassword = join_password.getText().toString().trim();


        Login.Request request = new Login.Request(userEmail, userPassword);
        Call<Login.Response> call = loginInterface.PostRequest(request);
        call.enqueue(new Callback<Login.Response>() {
            private final String TAG = "LOGINACTIVITY";

            @Override
            public void onResponse(Call<Login.Response> call, Response<Login.Response> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Login.Response jsonResponse = response.body();
                    Log.d(TAG, "Success : " + jsonResponse.getUserEmail());
                    Intent intent = new Intent(getApplicationContext(), EnterActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Login.Response> call, Throwable t) {
                Log.e(TAG, "에러 = " + t.getMessage());
            }
        });
    }

    private void autoLogin() {
        final String accesToken = preferenceHelper.getAccessToken();
        final String refreshToken = preferenceHelper.getRefreshToken();

        Login.TokenRequest tokenRequest = new Login.TokenRequest(accesToken, refreshToken);
        Call<Login.Response> call = loginInterface.PostToken(tokenRequest);
        call.enqueue(new Callback<Login.Response>() {
            private final String TAG = "AUTOLOGIN";

            @Override
            public void onResponse(Call<Login.Response> call, Response<Login.Response> response) {
                if (accesToken != null) {
                    //회원정보를 갖고 로그인성공
                    Login.Response jsonResponse = response.body();
                    Log.d(TAG, "Success = " + jsonResponse.getAccesToken());
                    Intent intent = new Intent(getApplicationContext(), EnterActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Login.Response> call, Throwable t) {
                Log.e(TAG, "에러 : " + t.getMessage());
            }
        });
    }

}
