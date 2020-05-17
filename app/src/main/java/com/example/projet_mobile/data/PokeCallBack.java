package com.example.projet_mobile.data;

import com.example.projet_mobile.presentation.model.model.Pokemon;
import java.util.List;

public interface PokeCallBack {
    void onSuccess(List<Pokemon> response);

    void onFailed();
}
