package com.example.glambeauty.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.glambeauty.R;
import com.example.glambeauty.model.ProductList;
import com.example.glambeauty.model.User;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RecyclerViewAdapterListFav extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<ProductList> listProduct;
    String id;
    Context context;
    String user_id;


    public RecyclerViewAdapterListFav(ArrayList<ProductList> allProducts, Context c){
        listProduct = allProducts;
        context = c;
        user_id = User.GetUserId(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_item, parent, false);
        ViewHolderProductList viewHolderProductList = new ViewHolderProductList(view);

        return viewHolderProductList;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final ViewHolderProductList viewHolderProductList = (ViewHolderProductList) holder;

        String sastojciProizvoda = listProduct.get(position).getListaSastojci().toString().replace("[", "").replace("]", "");
        String koraciProizvoda = listProduct.get(position).getListaKoraci().toString().replace("[", "").replace("]", "");


        viewHolderProductList.tvImeProizvoda.setText(listProduct.get(position).getNazivProizvoda());
        viewHolderProductList.tvSastojciProizvoda.setText(sastojciProizvoda);
        viewHolderProductList.tvKoraciProizvoda.setText(koraciProizvoda);

        if (user_id.matches(listProduct.get(position).getId())){
            viewHolderProductList.optionsBtn.setVisibility(View.VISIBLE);

            viewHolderProductList.optionsBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupMenu popupMenu = new PopupMenu(context, viewHolderProductList.optionsBtn);
                    popupMenu.inflate(R.menu.product_menu);

                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()){

                                case R.id.obrisiProizvod:
                                    FirebaseDatabase.getInstance().getReference("ProductList").child(listProduct.get(position).getnId()).removeValue();
                                    return true;
                            }
                            return false;
                        }
                    });

                    popupMenu.show();
                }
            });
        }
        else {
            viewHolderProductList.optionsBtn.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
         return listProduct.size();
    }

    public class ViewHolderProductList extends RecyclerView.ViewHolder{
        ConstraintLayout clProduct;
        ImageView optionsBtn;
        TextView tvImeProizvoda;
        TextView tvSastojciProizvoda;
        TextView tvKoraciProizvoda;

        public ViewHolderProductList(@NonNull View itemView){
            super(itemView);
            optionsBtn = itemView.findViewById(R.id.optionsBtn);
            tvImeProizvoda = itemView.findViewById(R.id.productPrikazImena);
            tvSastojciProizvoda = itemView.findViewById(R.id.productPrikazSastojaka);
            tvKoraciProizvoda = itemView.findViewById(R.id.productPrikazKoraka);
            clProduct = itemView.findViewById(R.id.clProduct);
        }
    }
}

