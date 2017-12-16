package com.delizarov.smartdiet.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Relation;

import com.delizarov.smartdiet.data.db.entities.RecipeDirectionEntity;
import com.delizarov.smartdiet.data.db.entities.RecipeEntity;
import com.delizarov.smartdiet.data.db.entities.RecipePictureURIEntity;
import com.delizarov.smartdiet.data.db.entities.RecipeTagEntity;

import java.util.List;

@Dao
public interface RecipeDao {

    @Insert
    Long addRecipe(RecipeEntity entity);

    @Query("select * from recipes")
    List<ExplicitRecipe> readAllRecipes();

    @Query("select * from recipes where id in (:recipeIds)")
    List<ExplicitRecipe> readRecipes(Long[] recipeIds);

    class ExplicitRecipe {

        @Embedded
        public RecipeEntity Recipe;

        @Relation(parentColumn = "id", entityColumn = "recipe_id", entity = RecipeTagEntity.class)
        public List<RecipeTagEntity> Tags;

        @Relation(parentColumn = "id", entityColumn = "recipe_id", entity = RecipePictureURIEntity.class)
        public List<RecipePictureURIEntity> PictureURI;

        @Relation(parentColumn = "id", entityColumn = "recipe_id", entity = RecipeDirectionEntity.class)
        public List<RecipeDirectionEntity> Directions;
    }
}
