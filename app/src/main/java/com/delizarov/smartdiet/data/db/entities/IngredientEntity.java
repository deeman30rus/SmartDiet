package com.delizarov.smartdiet.data.db.entities;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class IngredientEntity {

    @PrimaryKey
    public long Id;

    @ColumnInfo(name = "name")
    public String Name;
}
