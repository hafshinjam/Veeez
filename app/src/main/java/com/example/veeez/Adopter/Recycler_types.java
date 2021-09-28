package com.example.veeez.Adopter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veeez.R;

public class Recycler_types extends RecyclerView.Adapter<Recycler_types.TypesVH> {

    Context context;
    public Recycler_types(Context context){
        this.context=context;

    }

    @NonNull
    @Override
    public TypesVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.type_row,null);
        return new TypesVH(view);

    }

    @Override
    public void onBindViewHolder(@NonNull TypesVH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class TypesVH extends RecyclerView.ViewHolder {



        public TypesVH(@NonNull View itemView) {
            super(itemView);
        }
    }

}
