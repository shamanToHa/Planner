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
import com.example.home.planner.model.Note;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private List<Note> data;
    private Context context;
    private CardViewModel model;

    public NoteAdapter(List<Note> data, Context context, CardViewModel model) {
        this.data = data;
        this.context = context;
        this.model = model;
    }

    public void setData(List<Note> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(data.get(position).getTitle());
        holder.message.setText(data.get(position).getMessage());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView title;
        TextView message;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.item_note_card);
            title = itemView.findViewById(R.id.item_note_title);
            message = itemView.findViewById(R.id.item_note_text);
            itemView.setOnLongClickListener(v -> {
                PopupMenu menu = new PopupMenu(context, v);
                menu.inflate(R.menu.menu_card);
                menu.setOnMenuItemClickListener(item -> {
                    long tmp = data.get(getAdapterPosition()).getId();
                    model.deleteNote(tmp);
                    return false;
                });
                menu.show();
                return false;
            });
        }
    }
}
