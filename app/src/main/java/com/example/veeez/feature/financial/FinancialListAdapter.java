package com.example.veeez.feature.financial;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veeez.R;

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
        holder.bind(mFinancialResponse.getFinancialItems().get(position));
    }

    @Override
    public int getItemCount() {
        return mFinancialResponse.getFinancialItems().size();
    }

    public class FinancialViewHolder extends RecyclerView.ViewHolder {
        public FinancialViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(FinancialItem item) {

        }
    }
}
