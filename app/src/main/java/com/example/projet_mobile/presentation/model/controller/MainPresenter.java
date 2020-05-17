package com.example.projet_mobile.presentation.model.controller;

import com.example.projet_mobile.data.PokeCallBack;
import com.example.projet_mobile.data.PokeRepository;
import com.example.projet_mobile.presentation.model.model.Pokemon;
import com.example.projet_mobile.presentation.model.view.MainActivity;
import com.google.gson.Gson;

import java.util.List;
import java.util.Observable;

public class MainPresenter {

    private final PokeRepository pokeRepository;
    private MainActivity view;

    //public Observable<List<Pokemon>> observable;

    public MainPresenter(MainActivity mainActivity, PokeRepository pokeRepository) {
        this.view = mainActivity;
        this.pokeRepository = pokeRepository;

    }

    public void onStart() {
        pokeRepository.getPokemonResponse(new PokeCallBack() {
            @Override
            public void onSuccess(List<Pokemon> response) {
                view.showlist(response);
            }

            @Override
            public void onFailed() {
                view.showError();
            }
        });
    }

   /*private void makeApiCall() {

        Call<RestPokemonResponse> call = Singletons.getPokeApi().GetPokemonResponse();
        call.enqueue(new Callback<RestPokemonResponse>() {
            @Override
            public void onResponse(Call<RestPokemonResponse> call, Response<RestPokemonResponse> response) {
                //Inside call back
                if (response.isSuccessful() && response.body() != null) {
                    List<Pokemon> pokemonList = response.body().getResults();
                    saveList(pokemonList);
                    view.showlist(pokemonList);
                } else {
                    view.showError();
                }
            }

            @Override
            public void onFailure(Call<RestPokemonResponse> call, Throwable t) {

                view.showError();

            }


        });
    } */

    public void onItemClick(Pokemon pokemon) {
        view.navigateToDetails(pokemon);
    }

    public void onButtonAClick() {

    }

    public void onButtonBClick() {

    }


}
