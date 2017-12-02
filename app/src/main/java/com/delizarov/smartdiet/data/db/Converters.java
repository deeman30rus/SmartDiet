package com.delizarov.smartdiet.data.db;


import android.arch.persistence.room.TypeConverter;

import com.delizarov.smartdiet.data.db.entities.GroceryEntity;
import com.delizarov.smartdiet.domain.models.Grocery;

public class Converters {

    @TypeConverter
    public static GroceryEntity toIngredientEntity(Grocery grocery) {

        GroceryEntity entity = new GroceryEntity();

        entity.Id = grocery.getId() == Grocery.UNREGISTERED_GROCERY_ID ? null : grocery.getId();
        entity.Name = grocery.getName();

        return entity;
    }

    @TypeConverter
    public static Grocery toIngredient(GroceryEntity entity) {

        return new Grocery(entity.Id, entity.Name);
    }


}
