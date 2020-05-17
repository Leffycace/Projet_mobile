package com.example.projet_mobile.presentation.model.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.projet_mobile.Constante;
import com.example.projet_mobile.data.PokeApi;
import com.example.projet_mobile.presentation.model.model.Pokemon;
import com.example.projet_mobile.presentation.model.model.RestPokemonResponse;
import com.example.projet_mobile.presentation.model.view.MainActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainController {

     private SharedPreferences sharedPreferences;
     private Gson gson;
     private MainActivity view;

    public MainController(MainActivity mainActivity,Gson gson,SharedPreferences sharedPreferences){
        this.view = mainActivity;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;

    }

    public void onStart(){
        List<Pokemon> pokemonList = getDataFromCache();
        if (pokemonList != null){
            view.showlist(pokemonList);

        }else {
            makeApiCall();
        }
    }

    private void makeApiCall(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constante.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        PokeApi pokeApi = retrofit.create(PokeApi.class);

//Before Call back
        Call<RestPokemonResponse> call = pokeApi.GetPokemonResponse();
        call.enqueue(new Callback<RestPokemonResponse>() {
            @Override
            public void onResponse(Call<RestPokemonResponse> call, Response<RestPokemonResponse> response) {
                //Inside call back
                if (response.isSuccessful() && response.body() != null) {
                    List<Pokemon> pokemonList = response.body().getResults();
                    saveList(pokemonList);
                    view.showlist(pokemonList );
                }
                else {
                    view.showError();
                }
            }

            @Override
            public void onFailure(Call<RestPokemonResponse> call, Throwable t) {

                view.showError();

            }


        }) ;

    }
    private void saveList(List<Pokemon> pokemonList) {
        String jsonString = gson.toJson(pokemonList);

        sharedPreferences
                .edit()
                .putInt("cle_integer", 3)
                .putString(Constante.Key_Pokemon_List, jsonString)
                .apply();

        Toast.makeText(MainActivity.this, "List Sauvegarder", Toast.LENGTH_SHORT).show();
    }

    private List<Pokemon> getDataFromCache(){
        String jsonPokemon = sharedPreferences.getString(Constante.Key_Pokemon_List,null);

        if (jsonPokemon == null){
            return null;
        } else {
            Type listType = new TypeToken<List<Pokemon>>(){}.getType();
            return gson.fromJson(jsonPokemon,listType);

        }
    }


    public void onItemClick (Pokemon pokemon){

    }
    public void onButtonAClick(){

    }
    public void onButtonBClick(){

    }


}
