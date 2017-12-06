package com.delizarov.smartdiet.data.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(
        tableName = "recipe_direction",
        foreignKeys = {
                @ForeignKey(entity = RecipeEntity.class,
                        parentColumns = "id",
                        childColumns = "recipe_id")
        }
)
public class RecipeDirectionEntity {

    @PrimaryKey
    @ColumnInfo(name = "id")
    public Long Id;

    @ColumnInfo(name = "to_do")
    @NonNull
    public String ToDo;

    @ColumnInfo(name = "order")
    public int Order;

    @ColumnInfo(name = "recipe_id")
    public long RecipeId;
}
