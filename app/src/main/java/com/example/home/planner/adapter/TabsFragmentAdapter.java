package com.example.home.planner.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.home.planner.ui.fragment.AbstractTabFragment;
import com.example.home.planner.ui.fragment.EarningsFragment;
import com.example.home.planner.ui.fragment.ExpensesFragment;
import com.example.home.planner.ui.fragment.NotesFragment;

import java.util.ArrayList;

public class TabsFragmentAdapter extends FragmentPagerAdapter {

    private ArrayList<AbstractTabFragment> tabs;
    Context context;

    public TabsFragmentAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        tabs = new ArrayList<>();
        tabs.add(EarningsFragment.newInstance(context));
        tabs.add(ExpensesFragment.newInstance(context));
        tabs.add(NotesFragment.newInstance(context));
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position).getTitle();
    }

    @Override
    public Fragment getItem(int position) {
        return tabs.get(position);
    }

    @Override
    public int getCount() {
        return tabs.size();
    }
}
