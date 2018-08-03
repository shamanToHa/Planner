package com.example.home.planner.ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.home.planner.R;
import com.example.home.planner.adapter.NoteAdapter;
import com.example.home.planner.data.CardViewModel;

import java.util.ArrayList;

public class NotesFragment extends AbstractTabFragment {

    private CardViewModel cardViewModel;
    private NoteAdapter adapter;

    public static NotesFragment newInstance(Context context) {
        NotesFragment fragment = new NotesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.tab_item_note));
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, container, false);
        cardViewModel = ViewModelProviders.of(this).get(CardViewModel.class);
        adapter = new NoteAdapter(new ArrayList<>(), context, cardViewModel);

        RecyclerView rv = view.findViewById(R.id.notes_recycler_view);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(adapter);

        cardViewModel.getAllNotes().observe(NotesFragment.this,adapter::setData);

        return view;
    }


    public void setContext(Context context) {
        this.context = context;
    }
}
