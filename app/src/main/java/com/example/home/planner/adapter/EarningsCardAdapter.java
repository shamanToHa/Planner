package com.example.home.planner.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.home.planner.R;
import com.example.home.planner.model.EarningCard;

import java.util.List;

public class EarningsCardAdapter extends RecyclerView.Adapter<EarningsCardAdapter.EarningViewHolder> {

    private List<EarningCard> data;

    public EarningsCardAdapter(List<EarningCard> data) {
        this.data = data;
    }

    public void setData(List<EarningCard> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EarningViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.earning_item, parent, false);
        return new EarningViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EarningViewHolder holder, int position) {
        holder.title.setText(data.get(position).getTitle());
        holder.amount.setText(String.valueOf(data.get(position).getAmount()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class EarningViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView title;
        TextView amount;

        public EarningViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.item_earning_card);
            title = itemView.findViewById(R.id.item_earning_title);
            amount = itemView.findViewById(R.id.item_earning_amount);

        }
    }
}
