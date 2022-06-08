package com.example.locationpet;

import static com.example.locationpet.HPTActivity.markerFlag;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.locationpet.adapter.RecyclerAdapter;
import com.example.locationpet.dto.Recycler;
import com.example.locationpet.dto.SharedPreferenceHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class EnterActivity extends AppCompatActivity {

    Recycler.Response itemList;
    SharedPreferenceHelper preferenceHelper;

    private RecyclerAdapter adapter;
    RecyclerView recyclerView;

    ImageButton main_home, main_newPost, main_hpt, main_myPage;

    final String TAG = "ENTERACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_activity);

        preferenceHelper = new SharedPreferenceHelper(this);

        main_home = (ImageButton) findViewById(R.id.main_home);
        main_newPost = (ImageButton) findViewById(R.id.main_newPost);
        main_hpt = (ImageButton) findViewById(R.id.main_hpt);
        main_myPage = (ImageButton) findViewById(R.id.main_myPage);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView1);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RecyclerInterface.RECYCLER_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RecyclerInterface recyclerInterface = retrofit.create(RecyclerInterface.class);
        Recycler.Request request = new Recycler.Request(null);
        Call<Recycler.Response> call = recyclerInterface.GetRequest();
        call.enqueue(new Callback<Recycler.Response>() {
            @Override
            public void onResponse(Call<Recycler.Response> call, Response<Recycler.Response> response) {
                if (response.isSuccessful() && response.body() != null) {
                    itemList = response.body();

                    Log.d(TAG, itemList.toString());

                    adapter = new RecyclerAdapter(getApplicationContext(), (List<Recycler.Response>) itemList);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<Recycler.Response> call, Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        });

        main_hpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (markerFlag) {
                    markerFlag = false;
                }
                Intent intent = new Intent(getApplicationContext(), HPTActivity.class);
                startActivity(intent);
            }
        });
    }
}
