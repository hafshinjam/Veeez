package com.example.veeez.feature.address;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veeez.R;
import com.example.veeez.data.Object;
import com.example.veeez.data.UserAddressResponse;
import com.example.veeez.feature.map.search.SearchModel;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

public class AddressRecyclerAdapter extends RecyclerView.Adapter<AddressRecyclerAdapter.ViewHolder> {

    private UserAddressResponse userAddressResponse;

    public AddressRecyclerAdapter(UserAddressResponse userAddressResponse) {
        this.userAddressResponse = userAddressResponse;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_address, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AddressRecyclerAdapter.ViewHolder holder, int position) {
        holder.bind(userAddressResponse.getObject().get(position));
    }

    @Override
    public int getItemCount() {
        return Integer.parseInt(String.valueOf(userAddressResponse.getAddressCount()));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView addressTv;
        private TextView titleTv;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            titleTv = itemView.findViewById(R.id.address_title);
            addressTv = itemView.findViewById(R.id.address_cap);
        }

        public void bind(Object address) {
                titleTv.setText(address.getFullAddress());
                addressTv.setText(String.valueOf(address.getTitle()));
        }

    }


}

