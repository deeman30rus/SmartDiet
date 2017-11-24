package com.delizarov.smartdiet.data.db;


import android.arch.persistence.room.TypeConverter;

import com.delizarov.smartdiet.data.db.entities.IngredientEntity;
import com.delizarov.smartdiet.domain.models.Ingredient;

public class Converters {

    @TypeConverter
    public static IngredientEntity toIngredientEntity(Ingredient ingredient) {

        IngredientEntity entity = new IngredientEntity();

        entity.Id = ingredient.getId();
        entity.Name = ingredient.getName();

        return entity;
    }

    @TypeConverter
    public static Ingredient toIngredient(IngredientEntity entity) {

        return new Ingredient(entity.Id, entity.Name);
    }


}
