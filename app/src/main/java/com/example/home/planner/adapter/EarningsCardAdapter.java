package com.example.home.planner.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.home.planner.R;
import com.example.home.planner.data.CardViewModel;
import com.example.home.planner.model.EarningCard;

import java.util.List;

public class EarningsCardAdapter extends RecyclerView.Adapter<EarningsCardAdapter.EarningViewHolder> {

    private List<EarningCard> data;
    private Context context;
    private CardViewModel model;

    public EarningsCardAdapter(List<EarningCard> data, Context context, CardViewModel model) {
        this.data = data;
        this.context = context;
        this.model = model;
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

        EarningViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.item_earning_card);
            title = itemView.findViewById(R.id.item_earning_title);
            amount = itemView.findViewById(R.id.item_earning_amount);
            itemView.setOnLongClickListener(v -> {
                PopupMenu menu = new PopupMenu(context, v);
                menu.inflate(R.menu.menu_card);
                menu.setOnMenuItemClickListener(item -> {
                    long tmp = data.get(getAdapterPosition()).getId();
                    model.deleteCard(tmp);
                    return false;
                });
                menu.show();
                return false;
            });

        }
    }
}
