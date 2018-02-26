package com.delizarov.smartdiet.data.db.entities;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(
        tableName = "groceries"
)
public class GroceryEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public Long Id;

    @ColumnInfo(name = "name")
    public String Name;
}
