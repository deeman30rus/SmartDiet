package com.delizarov.smartdiet.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import com.delizarov.smartdiet.data.db.entities.RecipeIngredientEntity;

@Dao
public interface RecipeIngredientDao {

    @Insert
    void addRecipeIngredientEntity(RecipeIngredientEntity entity);
}
