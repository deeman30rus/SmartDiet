package com.delizarov.smartdiet.data.repository.impl;


import com.delizarov.smartdiet.data.db.AppDatabase;
import com.delizarov.smartdiet.data.db.Converters;
import com.delizarov.smartdiet.data.db.entities.IngredientEntity;
import com.delizarov.smartdiet.data.repository.CookbookRepository;
import com.delizarov.smartdiet.domain.models.Ingredient;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class CookbookRepositoryImpl implements CookbookRepository {

    private final AppDatabase mDb;

    @Inject
    public CookbookRepositoryImpl(AppDatabase db) {
        mDb = db;

        (new Thread(() -> mDb.ingredientDao().readIngredients())).start();

    }

    @Override
    public Observable<Ingredient> readIngredients() {

        return Observable
                .defer(() -> Observable.fromIterable(mDb.ingredientDao().readIngredients()))
                .map(Converters::toIngredient);
    }
}
