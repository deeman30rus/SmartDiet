package com.delizarov.smartdiet.data.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.support.annotation.NonNull;

@Entity(tableName = "recipe_tag",
        primaryKeys = {"tag", "recipe_id"},
        foreignKeys = {
                @ForeignKey(entity = RecipeEntity.class,
                        parentColumns = "id",
                        childColumns = "recipe_id")
        },
        indices = {@Index(value = {"recipe_id", "tag"})}
)
public class RecipeTagEntity {

    @ColumnInfo(name = "recipe_id")
    @NonNull
    public Long RecipeId;

    @ColumnInfo(name = "tag")
    @NonNull
    public String Tag;
}
