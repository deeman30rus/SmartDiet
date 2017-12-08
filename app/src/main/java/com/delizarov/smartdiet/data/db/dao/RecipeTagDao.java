package com.delizarov.smartdiet.data.db.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.delizarov.smartdiet.data.db.entities.RecipeTagEntity;

import java.util.List;

@Dao
public interface RecipeTagDao {

    @Insert
    void addRecipeTag(RecipeTagEntity entity);

    @Query("select * from recipe_tag where recipe_id = :recipeId")
    List<RecipeTagEntity> readTagsForRecipe(long recipeId);
}
