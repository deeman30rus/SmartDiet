package com.delizarov.smartdiet.data.db;


import android.arch.persistence.room.TypeConverter;

import com.delizarov.smartdiet.data.db.dao.RecipeDao;
import com.delizarov.smartdiet.data.db.dao.RecipeIngredientDao;
import com.delizarov.smartdiet.data.db.entities.GroceryEntity;
import com.delizarov.smartdiet.data.db.entities.RecipeDirectionEntity;
import com.delizarov.smartdiet.data.db.entities.RecipePictureURIEntity;
import com.delizarov.smartdiet.data.db.entities.RecipeTagEntity;
import com.delizarov.smartdiet.data.db.entities.UnitEntity;
import com.delizarov.smartdiet.domain.models.Grocery;
import com.delizarov.smartdiet.domain.models.Recipe;
import com.delizarov.smartdiet.domain.models.Unit;

import java.math.BigDecimal;
import java.util.List;

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

    @TypeConverter
    public static String toString(RecipeTagEntity entity) {

        return entity.Tag;
    }

    @TypeConverter
    public static String toString(RecipePictureURIEntity entity) {

        return entity.pictureURI;
    }

    @TypeConverter
    public static Recipe.Direction toDirection(RecipeDirectionEntity entity) {

        return new Recipe.Direction(entity.Order, entity.ToDo);
    }

    public static Recipe toRecipe(RecipeDao.ExplicitRecipe recipe, List<Recipe.Ingredient> ingredients) {

        Recipe.Builder builder = new Recipe.Builder();

        builder
                .withId(recipe.Recipe.Id)
                .withTitle(recipe.Recipe.Title)
                .withDescription(recipe.Recipe.Description)
                .withCookTime(recipe.Recipe.CookTime)
                .withCarbohydrates(recipe.Recipe.Carbohydrates)
                .withTriglycerides(recipe.Recipe.Triglycerides)
                .withProteins(recipe.Recipe.Proteins);

        for (RecipeTagEntity tagEntity : recipe.Tags)
            builder.addTag(toString(tagEntity));

        for (RecipePictureURIEntity pictureURIEntity : recipe.PictureURI)
            builder.addPictureURI(toString(pictureURIEntity));

        for (RecipeDirectionEntity directionEntity : recipe.Directions)
            builder.addDirection(toDirection(directionEntity));

        builder.addIngredients(ingredients);

        return builder.build();
    }

    public static Recipe.Ingredient toIngredient(RecipeIngredientDao.ExplicitRecipeIngredient recipeIngredient) {

        Grocery grocery = new Grocery(recipeIngredient.groceryId, recipeIngredient.groceryName);

        return new Recipe.Ingredient(grocery, new BigDecimal(recipeIngredient.amount), Unit.fromLong(recipeIngredient.unit));
    }
}
