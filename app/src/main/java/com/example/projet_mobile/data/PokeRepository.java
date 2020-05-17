package com.example.projet_mobile.data;

import android.content.SharedPreferences;

import com.example.projet_mobile.Constante;
import com.example.projet_mobile.presentation.model.model.Pokemon;
import com.example.projet_mobile.presentation.model.model.RestPokemonResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokeRepository {

    private PokeApi pokeApi;
    private SharedPreferences sharedPreferences;
    private final Gson gson;

    public PokeRepository(PokeApi pokeApi, SharedPreferences sharedPreferences, Gson gson) {
        this.pokeApi = pokeApi;
        this.sharedPreferences = sharedPreferences;
        this.gson = gson;
    }

    public void getPokemonResponse(final PokeCallBack callBack) {
        List<Pokemon> list = getDataFromCache();
        if (list != null) {
            callBack.onSuccess(list);
        } else {
            pokeApi.GetPokemonResponse().enqueue(new Callback<RestPokemonResponse>() {
                @Override
                public void onResponse(Call<RestPokemonResponse> call, Response<RestPokemonResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        callBack.onSuccess(response.body().getResults());
                    } else {
                        callBack.onFailed();
                    }
                }

                @Override
                public void onFailure(Call<RestPokemonResponse> call, Throwable t) {
                    callBack.onFailed();
                }
            });
        }


    }

    private List<Pokemon> getDataFromCache() {
        String jsonPokemon = sharedPreferences.getString(Constante.Key_Pokemon_List, null);

        if (jsonPokemon == null) {
            return null;
        } else {
            Type listType = new TypeToken<List<Pokemon>>() {
            }.getType();
            return gson.fromJson(jsonPokemon, listType);

        }
    }

    private void saveList(List<Pokemon> pokemonList) {
        String jsonString = gson.toJson(pokemonList);

        sharedPreferences
                .edit()
                .putString(Constante.Key_Pokemon_List, jsonString)
                .apply();
    }

}
