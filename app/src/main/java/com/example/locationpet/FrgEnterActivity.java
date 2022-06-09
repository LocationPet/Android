package com.example.locationpet;

import static com.example.locationpet.HPTActivity.markerFlag;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.locationpet.adapter.RecyclerAdapter;
import com.example.locationpet.dto.Recycler;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class FrgEnterActivity extends Fragment {

    final String TAG = "ENTERACTIVITY2";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Recycler.Response> dataList;

    ImageButton main_home, main_newPost, main_hpt, main_myPage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.enter_activity2, container, false);

        main_home = (ImageButton) view.findViewById(R.id.main_home);
        main_newPost = (ImageButton) view.findViewById(R.id.main_newPost);
        main_hpt = (ImageButton) view.findViewById(R.id.main_hpt);
        main_myPage = (ImageButton) view.findViewById(R.id.main_myPage);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView1);
        recyclerView.setHasFixedSize(true); // 리사이클러뷰 성능 강화
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        dataList = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RecyclerInterface.RECYCLER_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RecyclerInterface recyclerInterface = retrofit.create(RecyclerInterface.class);
        Call<List<Recycler.Response>> call = recyclerInterface.GetRequest();
        call.enqueue(new Callback<List<Recycler.Response>>() {
            @Override
            public void onResponse(Call<List<Recycler.Response>> call, Response<List<Recycler.Response>> response) {
                dataList.clear(); // 기존 배열이 존재하지 않게 초기화
                List<Recycler.Response> recyclerData = new ArrayList<>(response.body());
                for (Recycler.Response res : recyclerData) {

                    dataList.add(res);

                }
            }

            @Override
            public void onFailure(Call<List<Recycler.Response>> call, Throwable t) {

            }
        });

        adapter = new RecyclerAdapter(dataList, getContext());

        main_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTransaction(v);
            }
        });

        main_newPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTransaction(v);
            }
        });

        main_hpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (markerFlag) {
                    markerFlag = false;
                }
                startTransaction(v);
            }
        });

        main_myPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTransaction(v);
            }
        });



        return view;
    }
    private void startTransaction(View view) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        switch (view.getId()) {
            case R.id.main_home:
                FrgEnterActivity frgEnterActivity = new FrgEnterActivity();
                transaction.replace(R.id.tapFrame, frgEnterActivity);
                transaction.addToBackStack(null);
                transaction.commit();
                break;

            case R.id.main_newPost:
                FrgNewPost frgNewPost = new FrgNewPost();
                transaction.replace(R.id.tapFrame, frgNewPost);
                transaction.addToBackStack(null);
                transaction.commit();
                break;

            case R.id.main_hpt:
                HPTActivity hptActivity2 = new HPTActivity();
                transaction.replace(R.id.tapFrame, hptActivity2);
                transaction.addToBackStack(null);
                transaction.commit();

            case R.id.main_myPage:
                FrgMyPage frgMyPage = new FrgMyPage();
                transaction.replace(R.id.tapFrame, frgMyPage);
                transaction.addToBackStack(null);
                transaction.commit();

        }
    }
}
