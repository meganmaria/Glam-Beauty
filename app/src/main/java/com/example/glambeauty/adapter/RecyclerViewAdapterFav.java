package com.example.glambeauty.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.glambeauty.R;

import java.util.ArrayList;

public class RecyclerViewAdapterFav extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<String> dataset;

    private int listFavItem;

    public RecyclerViewAdapterFav(ArrayList<String> lista, int item) {
        dataset = lista;
        listFavItem = item;
        Log.d("ispis", "RecyclerViewAdapterFav" + listFavItem);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_fav_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        String item = (position + 1) + "." + (dataset.get(position));
        viewHolder.textView.setText(item);
        if (listFavItem == R.layout.list_fav_item) {
            viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dataset.remove(dataset.get(position));
                    notifyDataSetChanged();
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            textView = itemView.findViewById(R.id.tvItem);
            if(listFavItem == R.layout.list_fav_item){
                imageView = itemView.findViewById(R.id.btnObrisi);
            }
        }
    }
}
