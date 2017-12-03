package com.delizarov.smartdiet.data.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(
        tableName = "units"
)
public class UnitEntity {

    @PrimaryKey
    @ColumnInfo(name = "id")
    public long Id;

    @ColumnInfo(name = "type")
    public int Type;

    @ColumnInfo(name = "name")
    @NonNull
    public String Name;
}
