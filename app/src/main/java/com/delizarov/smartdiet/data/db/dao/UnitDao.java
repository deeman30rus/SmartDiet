package com.delizarov.smartdiet.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.delizarov.smartdiet.data.db.entities.UnitEntity;

@Dao
public interface UnitDao {

    @Query("SELECT * FROM units WHERE id = :id")
    UnitEntity getUnit(long id);

    @Insert
    long addUnit(UnitEntity entity);
}
