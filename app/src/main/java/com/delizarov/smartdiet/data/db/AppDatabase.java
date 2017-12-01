package com.delizarov.smartdiet.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.delizarov.smartdiet.data.db.dao.IngredientDao;
import com.delizarov.smartdiet.data.db.entities.IngredientEntity;
import com.delizarov.smartdiet.domain.models.Ingredient;

@Database(
        entities = {
                IngredientEntity.class
        },
        version = 1
)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract IngredientDao ingredientDao();
}
