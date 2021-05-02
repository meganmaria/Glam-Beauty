package com.example.glambeauty.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.example.glambeauty.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewAdapterFavoriti extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    FirebaseDatabase firebaseDatabase;
    ArrayList<MakeUpList> makeUpLists;
    String id;
    Context context;
    String user_id;

    public RecyclerViewAdapterFavoriti(ArrayList<MakeUpList> allMakeup, Context c){
        makeUpLists = allMakeup;
        context = c;
        user_id = User.GetUserId(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favoriti_list_item, parent, false);
        ViewHolderMakeUpList viewHolderMakeUpList = new ViewHolderMakeUpList(view);

        return viewHolderMakeUpList;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        final ViewHolderMakeUpList viewHolderMakeUpList = (ViewHolderMakeUpList) holder;
        String imageUrl = makeUpLists.get(position).getProduct_image_link();
        Log.d("imageUrl", "onBindViewHolder: " + imageUrl);

        Picasso.get().load(makeUpLists.get(position).getProduct_image_link()).into(viewHolderMakeUpList.image);
        viewHolderMakeUpList.name.setText(makeUpLists.get(position).getName());
        viewHolderMakeUpList.brand.setText(makeUpLists.get(position).getBrand());
        viewHolderMakeUpList.description.setText(makeUpLists.get(position).getDescription());
        viewHolderMakeUpList.price.setText(makeUpLists.get(position).getPrice() + " " + makeUpLists.get(position).getPrice_sign());

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("users");

        if(user_id.matches(makeUpLists.get(position).getProduct_image_link())){

        }
        else{
            viewHolderMakeUpList.image.setVisibility(View.INVISIBLE);
        }



        viewHolderMakeUpList.clMakeUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = makeUpLists.get(position).getProduct_image_link();
                Log.d("oznake", "onClick: " + id);
                Intent intent = new Intent(v.getContext(), MakeUpRecyclerViewAdapter.class);
                intent.putExtra("id", id);
                v.getContext().startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return makeUpLists.size();
    }

    public class ViewHolderMakeUpList extends RecyclerView.ViewHolder {
        ConstraintLayout clMakeUp;
        ImageView image;
        TextView name, brand, description, price;
        

        public ViewHolderMakeUpList(@NonNull View itemView) {
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


