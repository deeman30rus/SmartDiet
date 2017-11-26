package com.delizarov.smartdiet.data.repository.impl;


import com.delizarov.smartdiet.data.db.AppDatabase;
import com.delizarov.smartdiet.data.db.Converters;
import com.delizarov.smartdiet.data.db.entities.IngredientEntity;
import com.delizarov.smartdiet.data.repository.CookbookRepository;
import com.delizarov.smartdiet.domain.models.Ingredient;

import javax.inject.Inject;

import io.reactivex.Observable;

public class CookbookRepositoryImpl implements CookbookRepository {

    private final AppDatabase mDb;

    @Inject
    public CookbookRepositoryImpl(AppDatabase db) {
        mDb = db;
    }

    @Override
    public Observable<Ingredient> readIngredients() {

        return Observable
                .defer(() -> Observable.fromIterable(mDb.ingredientDao().readIngredients()))
                .map(Converters::toIngredient);
    }

    @Override
    public boolean saveIngredient(Ingredient ingredient) {

        IngredientEntity entity = Converters.toIngredientEntity(ingredient);

        if (entity.Id == null)
            mDb.ingredientDao().addNewIngredient(entity);
        else
            mDb.ingredientDao().updateIngredient(entity);

        return true;
    }
}
