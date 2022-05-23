package com.example.locationpet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.locationpet.datepicker.Dpicker;
import com.example.locationpet.dto.Register;
import com.example.locationpet.dto.SharedPreferenceHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RegisterActivity extends AppCompatActivity {
    private EditText create_email, create_pwd, response_pwd, create_animalName, create_animalKind, create_nickName;
    private TextView tv_yob, tv_date;
    private Button create_btn;
    private SharedPreferenceHelper preferenceHelper;

    final String TAG = "REGISTERACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        String year, month, day;

        preferenceHelper = new SharedPreferenceHelper(this);

        create_email = (EditText) findViewById(R.id.create_email);
        create_pwd = (EditText) findViewById(R.id.create_pwd);
        response_pwd = (EditText) findViewById(R.id.response_pwd);
        create_animalName = (EditText) findViewById(R.id.create_animalName);
        create_animalKind = (EditText) findViewById(R.id.create_aniamlKind);
        create_nickName = (EditText) findViewById(R.id.create_nickName);

//        tv_yob = (TextView) findViewById(R.id.tv_yob);
//        tv_date = (TextView) findViewById(R.id.tv_date);
        create_btn = (Button) findViewById(R.id.create_btn);


        tv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tv_date.isClickable() == true) {
                    Intent intent = new Intent(getApplicationContext(), Dpicker.class);
                    startActivityForResult(intent, 1000);
                }
            }
        });

        if (Dpicker.check == true) {
            Intent pickerData = getIntent();
            if (pickerData.getExtras().getString("yy") != null &&
                    pickerData.getExtras().getString("mm") != null &&
                    pickerData.getExtras().getString("dd") != null) {
                year = pickerData.getExtras().getString("yy");
                month = pickerData.getExtras().getString("mm");
                day = pickerData.getExtras().getString("dd");
                tv_date.setText(year + "년 " + month + "월 " + day + "일");
            } else {
                Toast.makeText(RegisterActivity.this, "생년월일을 다시 입력해주세요.",
                        Toast.LENGTH_SHORT).show();
            }
        }

        create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerMe();
            }
        });
    }

    private void registerMe() {
        final String userEmail = create_email.getText().toString().trim();
        final String userPassword = create_pwd.getText().toString().trim();
        final String re_userPassword = response_pwd.getText().toString().trim();
        final String userNickName = create_nickName.getText().toString();
        final String animalName = create_animalName.getText().toString().trim();
        final String animalKind = create_animalKind.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RegisterInterface.LOGIN_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RegisterInterface registerInterface = retrofit.create(RegisterInterface.class);

        Register.AnimalOption animalOption = new Register.AnimalOption(animalName, animalKind, 1652293871245L);
        Register.Info info = new Register.Info(userNickName, 1652293871245L);
        Register.Location location = new Register.Location("a","b","c");
        Register.Request request = new Register.Request(userEmail, userPassword, info, animalOption, location);
        Call<Register.Response> call = registerInterface.PostRequest(request);
        call.enqueue(new Callback<Register.Response>() {
            @Override
            public void onResponse(Call<Register.Response> call, Response<Register.Response> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Register.Response jsonResponse = response.body();
                    Log.d(TAG, "Success : " + jsonResponse.toString());
                    //버튼 이벤트 여기서 생성해주고 하면 될듯?
                    //동일한 아이디라면?
                    if (userPassword == re_userPassword) {
                        Toast.makeText(RegisterActivity.this, "비밀번호가 일치합니다.",
                                Toast.LENGTH_SHORT).show();
                    }
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Register.Response> call, Throwable t) {
                Log.e(TAG, "에러 : " + t.getMessage());
            }
        });

    }
}
