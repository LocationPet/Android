package com.example.locationpet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.locationpet.R;
import com.example.locationpet.dto.Recycler;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private Context c;
    private List<Recycler.Response> dataList;

    public RecyclerAdapter(Context c, List<Recycler.Response> dataList) {
        this.c = c;
        this.dataList = new ArrayList<>(dataList);
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recyclerlist, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.postText.setText("" + dataList.get(position).getPostDesc());// 여기 오류 발생 예상
        holder.create_at.setText("" + dataList.get(position).getCreate_at());

        Glide.with(c)
                .load(getItemId(position))
                .skipMemoryCache(true)
                .circleCrop()
                .skipMemoryCache(true)
                .error(R.drawable.textdesign)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(holder.postImg);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class  MyViewHolder extends RecyclerView.ViewHolder {

        ImageView postImg;
        TextView postText, create_at;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            postImg = (ImageView) itemView.findViewById(R.id.postImg);
            postText = (TextView) itemView.findViewById(R.id.postText);
            create_at = (TextView) itemView.findViewById(R.id.create_at);
        }
    }
}
