package com.delizarov.smartdiet.data.db.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.delizarov.smartdiet.data.db.entities.GroceryEntity;

import java.util.List;

@Dao
public interface GroceryDao {

    @Query("SELECT * FROM groceries")
    List<GroceryEntity> readGroceries();

    @Insert
    long addNewGrocery(GroceryEntity entity);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateGrocery(GroceryEntity entity);

    @Delete
    void remove(GroceryEntity entity);
}
