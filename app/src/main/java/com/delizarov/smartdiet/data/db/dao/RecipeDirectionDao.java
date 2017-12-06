package com.delizarov.smartdiet.data.db.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import com.delizarov.smartdiet.data.db.entities.RecipeDirectionEntity;

@Dao
public interface RecipeDirectionDao {

    @Insert
    void addRecipeDirection(RecipeDirectionEntity entity);
}
