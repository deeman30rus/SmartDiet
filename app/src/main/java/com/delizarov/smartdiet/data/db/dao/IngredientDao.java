package com.delizarov.smartdiet.data.db.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.delizarov.smartdiet.data.db.entities.IngredientEntity;
import com.delizarov.smartdiet.domain.models.Ingredient;

import java.util.List;

@Dao
public interface IngredientDao {

    @Query("SELECT * FROM ingredients")
    List<IngredientEntity> readIngredients();

    @Insert
    void addNewIngredient(IngredientEntity entity);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateIngredient(IngredientEntity entity);

    @Delete
    void remove(IngredientEntity entity);
}
