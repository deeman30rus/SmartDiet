package com.delizarov.smartdiet.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.delizarov.smartdiet.data.db.dao.GroceryDao;
import com.delizarov.smartdiet.data.db.entities.GroceryEntity;

@Database(
        entities = {
                GroceryEntity.class
        },
        version = 1
)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract GroceryDao ingredientDao();
}
