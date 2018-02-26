package com.delizarov.smartdiet.data.repository.impl;


import com.delizarov.smartdiet.data.db.AppDatabase;
import com.delizarov.smartdiet.data.db.Converters;
import com.delizarov.smartdiet.data.db.dao.RecipeDao;
import com.delizarov.smartdiet.data.db.dao.RecipeIngredientDao;
import com.delizarov.smartdiet.data.db.entities.GroceryEntity;
import com.delizarov.smartdiet.data.repository.CookbookRepository;
import com.delizarov.smartdiet.domain.models.Grocery;
import com.delizarov.smartdiet.domain.models.Recipe;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public Observable<Recipe> readRecipes(List<Long> identifiers) {

        return Observable.defer(() -> Observable.fromIterable(getRecipes(identifiers)));
    }

    private Iterable<Recipe> getRecipes(List<Long> identifiers) {

        List<Recipe> recipes = new ArrayList<>();

        List<RecipeDao.ExplicitRecipe> recipeEntities;
        Long[] recipeIds;

        if (identifiers != null) {
            recipeIds = identifiers.toArray(new Long[0]);
        } else
            recipeIds = new Long[0];

        recipeEntities = identifiers == null ? mDb.recipeDao().readAllRecipes() : mDb.recipeDao().readRecipes(recipeIds);

        for (RecipeDao.ExplicitRecipe recipe : recipeEntities) {

            List<Recipe.Ingredient> ingredients = new ArrayList<>();

            for (RecipeIngredientDao.ExplicitRecipeIngredient ri : mDb.recipeIngredientDao().readIngredientsForRecipe(recipe.Recipe.Id))
                ingredients.add(Converters.toIngredient(ri));

            recipes.add(Converters.toRecipe(recipe, ingredients));
        }

        return recipes;
    }
}
