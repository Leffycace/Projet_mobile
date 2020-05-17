package com.example.projet_mobile.presentation.model.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.projet_mobile.R;
import com.example.projet_mobile.Singletons;
import com.example.projet_mobile.presentation.model.model.Pokemon;

public class DetailActivity extends AppCompatActivity {
    private TextView txtDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        txtDetail = findViewById(R.id.detail_txt);
        Intent intent = getIntent();
        String pokemonJson = intent.getStringExtra("pokemon_key_name");
        Pokemon pokemon = Singletons.getGson().fromJson(pokemonJson, Pokemon.class);
        showDetail(pokemon);

    }

    private void showDetail(Pokemon pokemon) {
        txtDetail.setText(pokemon.getName());
    }
}
