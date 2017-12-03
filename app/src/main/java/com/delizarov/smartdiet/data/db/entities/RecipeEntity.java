package com.delizarov.smartdiet.data.db.entities;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(
        tableName = "recipes"
)
public class RecipeEntity {

    @PrimaryKey
    @ColumnInfo(name = "id")
    public Long Id;

    @ColumnInfo(name = "title")
    public String Title;

    @ColumnInfo(name = "description")
    public String Description;

    @ColumnInfo(name = "cook_time")
    public Integer CookTime;

    @ColumnInfo(name = "calories")
    public Double Calories;

    @ColumnInfo(name = "proteins")
    public Double Proteins;

    @ColumnInfo(name = "triglycerides")
    public Double Triglycerides;

    @ColumnInfo(name = "carbohydrates")
    public Double Carbohydrates;
}
