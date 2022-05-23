package com.example.locationpet;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.locationpet.dto.Recycler;
import com.example.locationpet.dto.SharedPreferenceHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class EnterActivity extends AppCompatActivity {

    Recycler.Response itemList;
    SharedPreferenceHelper preferenceHelper;

    final String TAG = "ENTERACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_activity);

        preferenceHelper = new SharedPreferenceHelper(this);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RecyclerInterface.RECYCLER_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RecyclerInterface recyclerInterface = retrofit.create(RecyclerInterface.class);
        Recycler.Request request = new Recycler.Request(null);
        Call<Recycler.Response> call = recyclerInterface.GetRequest(request);
        call.enqueue(new Callback<Recycler.Response>() {
            @Override
            public void onResponse(Call<Recycler.Response> call, Response<Recycler.Response> response) {
                itemList = response.body();

                Log.d(TAG, itemList.toString());
            }

            @Override
            public void onFailure(Call<Recycler.Response> call, Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        });
    }
}
