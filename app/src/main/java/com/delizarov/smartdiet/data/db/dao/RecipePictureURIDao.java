package com.delizarov.smartdiet.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import com.delizarov.smartdiet.data.db.entities.RecipePictureURIEntity;

@Dao
public interface RecipePictureURIDao {

    @Insert
    long addNewPictureURIEntity(RecipePictureURIEntity entity);
}
