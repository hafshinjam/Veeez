package com.example.veeez.feature.financial;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veeez.R;
import com.example.veeez.common.Utils;

public class FinancialListAdapter extends RecyclerView.Adapter<FinancialListAdapter.FinancialViewHolder> {
    private FinancialResponse mFinancialResponse;

    public FinancialListAdapter(FinancialResponse response) {
        mFinancialResponse = response;
    }

    @NonNull
    @Override
    public FinancialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_financial, parent, false);
        return new FinancialViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FinancialViewHolder holder, int position) {
        holder.bind(mFinancialResponse.getItems().get(position));
    }

    @Override
    public int getItemCount() {
        return mFinancialResponse.getItems().size();
    }

    public class FinancialViewHolder extends RecyclerView.ViewHolder {
        TextView titleFinancial;
        TextView descriptionFinancial;
        TextView amountFinancial;
        TextView dateFinancial;

        public FinancialViewHolder(@NonNull View itemView) {
            super(itemView);
            titleFinancial = itemView.findViewById(R.id.titleFinancialItem);
            descriptionFinancial = itemView.findViewById(R.id.descriptionFinancialItem);
            amountFinancial = itemView.findViewById(R.id.amountFinancialItem);
            dateFinancial = itemView.findViewById(R.id.dateFinancialItem);
        }

        public void bind(FinancialItem item) {
            titleFinancial.setText(item.getTitle());
            descriptionFinancial.setText(item.getDescription());
            amountFinancial.setText(String.valueOf(Utils.formatPrice(item.getAmount())));
            String date = item.getDateTime().substring(0,item.getDateTime().lastIndexOf('-'));
            dateFinancial.setText(date);
        }
    }
}
