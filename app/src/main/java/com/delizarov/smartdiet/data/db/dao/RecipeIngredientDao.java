package com.delizarov.smartdiet.data.db.dao;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.delizarov.smartdiet.data.db.entities.RecipeIngredientEntity;

import java.util.List;

@Dao
public interface RecipeIngredientDao {

    @Insert
    void addRecipeIngredientEntity(RecipeIngredientEntity entity);

    @Query("select groceries.id as groceryId, groceries.name as groceryName, recipe_ingredient.amount as amount, recipe_ingredient.unit_id as unit from recipe_ingredient inner join groceries on recipe_ingredient.grocery_id = groceries.id where recipe_id = :recipeId")
    List<ExplicitRecipeIngredient> readIngredientsForRecipe(long recipeId);

    class ExplicitRecipeIngredient {

        @ColumnInfo(name = "groceryId")
        public Long groceryId;

        @ColumnInfo(name = "groceryName")
        public String groceryName;

        @ColumnInfo(name = "amount")
        public double amount;

        @ColumnInfo(name = "unit")
        public long unit;
    }
}
