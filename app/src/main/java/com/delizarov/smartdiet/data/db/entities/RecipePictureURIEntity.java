package com.delizarov.smartdiet.data.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(
        tableName = "recipe_picture_uri",
        foreignKeys = {
                @ForeignKey(entity = RecipeEntity.class,
                        parentColumns = "id",
                        childColumns = "recipe_id")
        }
)
public class RecipePictureURIEntity {

    @PrimaryKey
    @ColumnInfo(name = "id")
    public long Id;

    @ColumnInfo(name = "picture_uri")
    @NonNull
    public String pictureURI;

    @ColumnInfo(name = "recipe_id")
    public long RecipeId;
}
