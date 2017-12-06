package com.delizarov.smartdiet.data.db;


import android.arch.persistence.room.TypeConverter;

import com.delizarov.smartdiet.data.db.entities.GroceryEntity;
import com.delizarov.smartdiet.data.db.entities.UnitEntity;
import com.delizarov.smartdiet.domain.models.Grocery;
import com.delizarov.smartdiet.domain.models.Unit;

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

    @TypeConverter
    public static Unit toUnit(UnitEntity entity) {

        return Unit.fromLong(entity.Id);
    }

    @TypeConverter
    public static UnitEntity toUnitEntity(Unit unit) {

        UnitEntity entity = new UnitEntity();

        entity.Id = unit.type();

        return entity;
    }
}
