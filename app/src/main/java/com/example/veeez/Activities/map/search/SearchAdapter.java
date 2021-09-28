package com.example.veeez.Activities.map.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veeez.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
private List<SearchModel> searches = new ArrayList<>();
private OnSearchEventListener onSearchEventListener = null;

    public void setOnSearchEventListener(OnSearchEventListener onSearchEventListener) {
        this.onSearchEventListener = onSearchEventListener;
    }

    public List<SearchModel> getSearches() {
        return searches;
    }

    public void setSearches(List<SearchModel> searches) {
        this.searches = searches;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SearchAdapter.ViewHolder holder, int position) {
        holder.bind(searches.get(position));
    }

    @Override
    public int getItemCount() {
        return searches.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView addressTv;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            addressTv = itemView.findViewById(R.id.address_tv);

        }

        public void bind(SearchModel search){
            addressTv.setText(search.getDisplayName());

            itemView.setOnClickListener(view->{
                onSearchEventListener.onSearchClick(search);
            });
        }

    }

    public interface OnSearchEventListener{
        public void onSearchClick(SearchModel search);
    }
}
