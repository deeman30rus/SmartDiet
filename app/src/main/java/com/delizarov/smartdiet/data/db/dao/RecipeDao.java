package com.delizarov.smartdiet.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import com.delizarov.smartdiet.data.db.entities.RecipeEntity;

@Dao
public interface RecipeDao {

    @Insert
    Long addRecipe(RecipeEntity entity);
}
