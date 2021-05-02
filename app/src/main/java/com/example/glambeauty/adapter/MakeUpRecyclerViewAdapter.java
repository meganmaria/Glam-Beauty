package com.example.glambeauty.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.glambeauty.R;
import com.example.glambeauty.model.MakeUpList;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MakeUpRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<MakeUpList> makeUps;
    Context context;

    public MakeUpRecyclerViewAdapter(ArrayList<MakeUpList> makeUpList, Context c) {
        context = c;
        makeUps = makeUpList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ViewHolderMakeUp viewHolderMakeUp = new ViewHolderMakeUp(view);
        return viewHolderMakeUp;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        final ViewHolderMakeUp viewHolderMakeUp = (ViewHolderMakeUp) holder;

        String imageUrl = makeUps.get(position).getProduct_image_link();
        Log.d("imageUrl", "onBindViewHolder: " + imageUrl);

        Picasso.get().load(makeUps.get(position).getProduct_image_link()).into(viewHolderMakeUp.image);
        viewHolderMakeUp.name.setText(makeUps.get(position).getName());
        viewHolderMakeUp.brand.setText(makeUps.get(position).getBrand());
        viewHolderMakeUp.description.setText(makeUps.get(position).getDescription());
        viewHolderMakeUp.price.setText(makeUps.get(position).getPrice() + " " + makeUps.get(position).getPrice_sign());
    }


    @Override
    public int getItemCount() {
        return makeUps.size();
    }

    public class ViewHolderMakeUp extends RecyclerView.ViewHolder {
        ConstraintLayout clMakeUp;
        ImageView image;
        TextView name, brand, description, price;

        public ViewHolderMakeUp(@NonNull View itemView) {
            super(itemView);
            clMakeUp = itemView.findViewById(R.id.clMakeUp);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            brand = itemView.findViewById(R.id.brand);
            description = itemView.findViewById(R.id.description);
            price = itemView.findViewById(R.id.price);
        }
    }
}
