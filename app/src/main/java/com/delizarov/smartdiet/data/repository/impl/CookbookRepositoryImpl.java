package com.delizarov.smartdiet.data.repository.impl;


import com.delizarov.smartdiet.data.db.AppDatabase;
import com.delizarov.smartdiet.data.db.Converters;
import com.delizarov.smartdiet.data.db.dao.RecipeDao;
import com.delizarov.smartdiet.data.db.dao.RecipeIngredientDao;
import com.delizarov.smartdiet.data.db.entities.GroceryEntity;
import com.delizarov.smartdiet.data.db.entities.RecipeDirectionEntity;
import com.delizarov.smartdiet.data.db.entities.RecipeEntity;
import com.delizarov.smartdiet.data.db.entities.RecipeIngredientEntity;
import com.delizarov.smartdiet.data.db.entities.RecipePictureURIEntity;
import com.delizarov.smartdiet.data.db.entities.RecipeTagEntity;
import com.delizarov.smartdiet.data.repository.CookbookRepository;
import com.delizarov.smartdiet.domain.models.Grocery;
import com.delizarov.smartdiet.domain.models.Recipe;
import com.delizarov.smartdiet.domain.models.Unit;

import java.math.BigDecimal;
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
    public Observable<Recipe> readRecipes() {

        return Observable.defer(() -> Observable.fromIterable(getRecipes()));
    }

    private Iterable<Recipe> getRecipes() {

        List<Recipe> recipes = new ArrayList<>();

        for (RecipeDao.ExplicitRecipe recipe : mDb.recipeDao().readRecipes()) {

            List<Recipe.Ingredient> ingredients = new ArrayList<>();

            for (RecipeIngredientDao.ExplicitRecipeIngredient ri : mDb.recipeIngredientDao().readIngredientsForRecipe(recipe.Recipe.Id))
                ingredients.add(Converters.toIngredient(ri));

            recipes.add(Converters.toRecipe(recipe, ingredients));
        }

        return recipes;
    }
}
