package com.delizarov.smartdiet.data.repository.impl;


import com.delizarov.smartdiet.data.db.AppDatabase;
import com.delizarov.smartdiet.data.db.Converters;
import com.delizarov.smartdiet.data.db.entities.GroceryEntity;
import com.delizarov.smartdiet.data.repository.CookbookRepository;
import com.delizarov.smartdiet.domain.models.Grocery;

import javax.inject.Inject;

import io.reactivex.Observable;

public class CookbookRepositoryImpl implements CookbookRepository {

    private final AppDatabase mDb;

    @Inject
    public CookbookRepositoryImpl(AppDatabase db) {
        mDb = db;
    }

    @Override
    public Observable<Grocery> readGroceries() {

        return Observable
                .defer(() -> Observable.fromIterable(mDb.ingredientDao().readGroceries()))
                .map(Converters::toIngredient);
    }

    @Override
    public long saveGrocery(Grocery grocery) {

        GroceryEntity entity = Converters.toIngredientEntity(grocery);

        if (entity.Id == null)
            return mDb.ingredientDao().addNewGrocery(entity);

        // если не вставка значит update => id не поменялся

        mDb.ingredientDao().updateGrocery(entity);

        return grocery.getId();
    }

    @Override
    public Boolean removeGrocery(Grocery grocery) {

        GroceryEntity entity = Converters.toIngredientEntity(grocery);

        mDb.ingredientDao().remove(entity);

        return true;
    }
}
