package com.delizarov.smartdiet.data.db.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import com.delizarov.smartdiet.data.db.entities.RecipeTagEntity;

@Dao
public interface RecipeTagDao {

    @Insert
    void addRecipeTag(RecipeTagEntity entity);
}