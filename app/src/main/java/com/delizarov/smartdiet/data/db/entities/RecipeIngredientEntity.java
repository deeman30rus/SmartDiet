package com.delizarov.smartdiet.data.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(
        tableName = "recipe_ingredient",
        foreignKeys = {
                @ForeignKey(entity = RecipeEntity.class,
                        parentColumns = "id",
                        childColumns = "recipe_id"),
                @ForeignKey(entity = GroceryEntity.class,
                        parentColumns = "id",
                        childColumns = "grocery_id"),
                @ForeignKey(entity = UnitEntity.class,
                        parentColumns = "id",
                        childColumns = "unit_id")
        }
)
public class RecipeIngredientEntity {

    @PrimaryKey
    @ColumnInfo(name = "id")
    public Long Id;

    @ColumnInfo(name = "grocery_id")
    public long GroceryId;

    @ColumnInfo(name = "recipe_id")
    public long RecipeId;

    @ColumnInfo(name = "amount")
    public double Amount;

    @ColumnInfo(name = "unit_id")
    public long UnitId;
}
