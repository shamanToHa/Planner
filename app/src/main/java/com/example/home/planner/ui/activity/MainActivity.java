package com.example.home.planner.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.home.planner.R;
import com.example.home.planner.adapter.TabsFragmentAdapter;
import com.example.home.planner.data.CardViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    private CardViewModel viewModel;

    public static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this).get(CardViewModel.class);
        setSupportActionBar(toolbar);

        initFloatingActionButton();

        initNavigationView();
        initTabs();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_delete_earnings) {
            viewModel.deleteAllCards();
            return true;
        } else if (id == R.id.action_delete_notes) {
            viewModel.deleteAllNotes();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_earnings) {
            viewPager.setCurrentItem(0);

        } else if (id == R.id.nav_expenses) {
            viewPager.setCurrentItem(1);

        } else if (id == R.id.nav_todo) {
            viewPager.setCurrentItem(2);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void initNavigationView() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initTabs() {
        TabsFragmentAdapter adapter = new TabsFragmentAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initFloatingActionButton() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> CreateCardActivity.start(this));
    }
}
