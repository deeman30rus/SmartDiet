package com.delizarov.smartdiet.data.repository;


import com.delizarov.smartdiet.domain.models.Ingredient;

import io.reactivex.Observable;

public interface CookbookRepository {
    Observable<Ingredient> readIngredients();
}
