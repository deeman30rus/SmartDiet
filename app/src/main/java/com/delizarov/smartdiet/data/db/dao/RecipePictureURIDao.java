package com.delizarov.smartdiet.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.delizarov.smartdiet.data.db.entities.RecipePictureURIEntity;

import java.util.List;

@Dao
public interface RecipePictureURIDao {

    @Insert
    long addNewPictureURIEntity(RecipePictureURIEntity entity);

    @Query("select * from recipe_picture_uri where recipe_id = :recipeId")
    List<RecipePictureURIEntity> readPictureURIsForRecipe(long recipeId);
}
