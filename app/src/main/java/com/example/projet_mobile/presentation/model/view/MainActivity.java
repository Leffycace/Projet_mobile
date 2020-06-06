package com.example.projet_mobile.presentation.model.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.projet_mobile.R;
import com.example.projet_mobile.Singletons;
import com.example.projet_mobile.presentation.model.controller.MainPresenter;
import com.example.projet_mobile.presentation.model.model.Pokemon;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private MainPresenter controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new MainPresenter(this, Singletons.getPokeRepository(this));
        controller.onStart();

    }

    public void showlist(List<Pokemon> pokemonList) {

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setLayoutManager(layoutManager);


        // define an adapter
        mAdapter = new ListAdapter(pokemonList, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Pokemon item) {
                controller.onItemClick(item);
            }
        });
        recyclerView.setAdapter(mAdapter);
    }

    public void showError() {
        Toast.makeText(MainActivity.this, "API ERROR", Toast.LENGTH_SHORT).show();
    }

    public void navigateToDetails(Pokemon pokemon) {
        Intent myIntent = new Intent(MainActivity.this, DetailActivity.class);
        myIntent.putExtra("pokemon_key_name", Singletons.getGson().toJson(pokemon));
        MainActivity.this.startActivity(myIntent);

        Toast.makeText(MainActivity.this, "Todo Navigate", Toast.LENGTH_SHORT).show();

    }
}
