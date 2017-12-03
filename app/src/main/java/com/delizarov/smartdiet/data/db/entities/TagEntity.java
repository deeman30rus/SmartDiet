package com.delizarov.smartdiet.data.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(
        tableName = "tags"
)
public class TagEntity {

    @PrimaryKey
    @ColumnInfo(name = "tag")
    @NonNull
    public String Tag;
}
