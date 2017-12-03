package com.delizarov.smartdiet.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.delizarov.smartdiet.data.db.dao.GroceryDao;
import com.delizarov.smartdiet.data.db.entities.GroceryEntity;
import com.delizarov.smartdiet.data.db.entities.RecipeDirectionEntity;
import com.delizarov.smartdiet.data.db.entities.RecipeEntity;
import com.delizarov.smartdiet.data.db.entities.RecipeIngredientEntity;
import com.delizarov.smartdiet.data.db.entities.RecipePictureURIEntity;
import com.delizarov.smartdiet.data.db.entities.RecipeTagEntity;
import com.delizarov.smartdiet.data.db.entities.TagEntity;
import com.delizarov.smartdiet.data.db.entities.UnitEntity;
import com.delizarov.smartdiet.domain.models.Recipe;

@Database(
        entities = {
                GroceryEntity.class,
                RecipeEntity.class,
                TagEntity.class,
                UnitEntity.class,
                RecipeTagEntity.class,
                RecipePictureURIEntity.class,
                RecipeDirectionEntity.class,
                RecipeIngredientEntity.class
        },
        version = 1
)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract GroceryDao ingredientDao();
}
