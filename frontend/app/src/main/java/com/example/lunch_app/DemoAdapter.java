package com.example.lunch_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DemoAdapter extends RecyclerView.Adapter<DemoVH>{

    List<Integer> items;

    public DemoAdapter(List<Integer> items){
        this.items = items;
    }

    @NonNull
    @Override
    public DemoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new DemoVH(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull DemoVH holder, int position) {
        holder.imageview.setImageAlpha(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

class DemoVH extends RecyclerView.ViewHolder{

    ImageView imageview;
    private DemoAdapter adapter;

    public DemoVH(@NonNull View itemView) {
        super(itemView);

        imageview = itemView.findViewById(R.id.image);
    }

    public DemoVH linkAdapter(DemoAdapter adapter){
        this.adapter = adapter;
        return this;
    }
}
