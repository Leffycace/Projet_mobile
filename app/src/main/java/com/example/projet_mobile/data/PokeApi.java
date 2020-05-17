package com.example.projet_mobile.data;

import com.example.projet_mobile.presentation.model.model.RestPokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokeApi {
    @GET("/api/v2/pokemon")
    Call<RestPokemonResponse> GetPokemonResponse();
}
