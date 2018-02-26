package com.delizarov.smartdiet.data.db.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.delizarov.smartdiet.data.db.entities.RecipeDirectionEntity;

import java.util.List;

@Dao
public interface RecipeDirectionDao {

    @Insert
    void addRecipeDirection(RecipeDirectionEntity entity);

    @Query("select * from recipe_direction where recipe_id = :recipeId")
    List<RecipeDirectionEntity> readDirectionsForRecipe(long recipeId);
}
