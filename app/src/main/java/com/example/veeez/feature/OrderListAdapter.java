package com.example.veeez.feature;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.veeez.R;
import com.example.veeez.common.Utils;

import java.util.ArrayList;
import java.util.List;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.OrderListViewHolder> {
    private List<OrderHistoryObject> objects = new ArrayList<>();

    public void setObjects(List<OrderHistoryObject> objects) {
        this.objects = objects;
    }

    @NonNull
    @Override
    public OrderListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(com.example.veeez.R.layout.item_order, parent, false);
        return new OrderListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderListViewHolder holder, int position) {
        holder.bind(objects.get(position));
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class OrderListViewHolder extends RecyclerView.ViewHolder {
        TextView senderTextAddress;
        TextView receiverTextAddress;
        TextView priceText;

        public OrderListViewHolder(@NonNull View itemView) {
            super(itemView);
            senderTextAddress = itemView.findViewById(R.id.orderSenderAddress);
            receiverTextAddress = itemView.findViewById(R.id.orderReceiverAddress);
            priceText = itemView.findViewById(R.id.orderPrice);
        }

        private void bind(OrderHistoryObject object) {
            senderTextAddress.setText(object.getOrigin());
            receiverTextAddress.setText(object.getDestination());
            priceText.setText(Utils.formatPrice( object.getPrice()));
        }
    }
}
