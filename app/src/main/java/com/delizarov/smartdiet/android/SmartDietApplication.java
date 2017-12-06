package com.delizarov.smartdiet.android;


import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;

import com.delizarov.smartdiet.data.db.AppDatabase;
import com.delizarov.smartdiet.data.db.Converters;
import com.delizarov.smartdiet.data.db.entities.GroceryEntity;
import com.delizarov.smartdiet.data.db.entities.RecipeDirectionEntity;
import com.delizarov.smartdiet.data.db.entities.RecipeEntity;
import com.delizarov.smartdiet.data.db.entities.RecipeIngredientEntity;
import com.delizarov.smartdiet.data.db.entities.RecipePictureURIEntity;
import com.delizarov.smartdiet.data.db.entities.RecipeTagEntity;
import com.delizarov.smartdiet.domain.models.Grocery;
import com.delizarov.smartdiet.domain.models.Recipe;
import com.delizarov.smartdiet.domain.models.Unit;
import com.facebook.stetho.Stetho;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

public class SmartDietApplication extends Application {

    private static final String DB_NAME = "smart-diet-db";

    private ApplicationComponent mApplicationComponent;
    private AppDatabase mDb;

    @Inject
    public SmartDietApplication() {
        super();
    }

    @Override
    public void onCreate() {

        super.onCreate();

        Stetho.initializeWithDefaults(this);

        mDb = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, DB_NAME).build();

        initializeInjector();

        getApplicationComponent().inject(this);

        Thread thread = new Thread(this::checkAndCreateDb);

        thread.start();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    private void initializeInjector() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public AppDatabase getDb() {
        return mDb;
    }

    private void checkAndCreateDb() {

        final String isFirst = "isFirst";

        SharedPreferences sp = getSharedPreferences("first_start", Context.MODE_PRIVATE);

        if (sp.getBoolean(isFirst, true))
            createDb();

        sp.edit()
                .putBoolean(isFirst, false)
                .apply();
    }

    private void createDb() {

        AssetManager am = getAssets();

        InputStream is;

        try {
            is = am.open("r.json");

            int size = is.available();
            byte[] buf = new byte[size];

            is.read(buf);

            JSONArray jsonRecipes = new JSONArray(new String(buf));

            is.close();

            Map<Unit, Long> unitMap = writeUnits();

            Set<Grocery> groceries = getAllGroceries(jsonRecipes);

            writeGroceries(groceries);

            for (int i = 0; i < jsonRecipes.length(); ++i) {

                JSONObject jsonRecipe = jsonRecipes.getJSONObject(i);

                Recipe recipe = toRecipe(i, jsonRecipe, toMap(groceries));

                writeRecipe(recipe, unitMap);
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    private void writeRecipe(Recipe recipe, Map<Unit, Long> unitMap) {

        RecipeEntity entity = new RecipeEntity();

        entity.Id = null;

        entity.Title = recipe.getTitle();
        entity.Description = recipe.getDescription();

        entity.CookTime = recipe.getCookTime();
        entity.Calories = recipe.getCalories().doubleValue();
        entity.Proteins = recipe.getProteins().doubleValue();
        entity.Triglycerides = recipe.getTriglycerides().doubleValue();
        entity.Carbohydrates = recipe.getCarbohydrates().doubleValue();

        long recipeId = mDb.recipeDao().addRecipe(entity);

        for (String pictureURI : recipe.getPictureURIs()) {

            RecipePictureURIEntity pictureEntity = new RecipePictureURIEntity();

            pictureEntity.Id = null;
            pictureEntity.pictureURI = pictureURI;
            pictureEntity.RecipeId = recipeId;

            mDb.recipePictureURIDao().addNewPictureURIEntity(pictureEntity);
        }

        for (String tag : recipe.getTags()) {

            RecipeTagEntity rtEntity = new RecipeTagEntity();

            rtEntity.RecipeId = recipeId;
            rtEntity.Tag = tag;

            mDb.recipeTagDao().addRecipeTag(rtEntity);
        }

        for (Recipe.Direction direction : recipe.getDirections()) {

            RecipeDirectionEntity rdEntity = new RecipeDirectionEntity();

            rdEntity.Id = null;
            rdEntity.Order = direction.Order;
            rdEntity.ToDo = direction.Action;
            rdEntity.RecipeId = recipeId;

            mDb.recipeDirectionDao().addRecipeDirection(rdEntity);
        }

        for (Recipe.Ingredient ingredient : recipe.getIngredients()) {

            RecipeIngredientEntity riEntity = new RecipeIngredientEntity();

            riEntity.Id = null;
            riEntity.GroceryId = ingredient.Grocery.getId();
            riEntity.RecipeId = recipeId;
            riEntity.Amount = ingredient.Amount.doubleValue();
            riEntity.UnitId = unitMap.get(ingredient.Unit);

            mDb.recipeIngredientDao().addRecipeIngredientEntity(riEntity);
        }
    }

    private Map<Unit, Long> writeUnits() {

        Map<Unit, Long> map = new HashMap<>();

        for (Unit unit : Unit.values()) {

            long id = mDb.unitDao().addUnit(Converters.toUnitEntity(unit));

            map.put(unit, id);
        }

        return map;
    }

    private void writeGroceries(Set<Grocery> groceries) {

        for (Grocery grocery : groceries) {

            GroceryEntity entity = new GroceryEntity();
            entity.Id = null;
            entity.Name = grocery.getName();

            long id = mDb.ingredientDao().addNewGrocery(entity);
            grocery.setId(id);
        }
    }

    private static Set<Grocery> getAllGroceries(JSONArray jsonRecipes) throws JSONException {

        Set<Grocery> groceries = new HashSet<>();

        for (int i = 0; i < jsonRecipes.length(); i++) {

            JSONObject jsonRecipe = jsonRecipes.getJSONObject(i);

            JSONArray jsonIngredients = jsonRecipe.getJSONArray("ingredients");

            for (int j = 0; j < jsonIngredients.length(); j++) {

                JSONObject json = jsonIngredients.getJSONObject(j);

                groceries.add(new Grocery(-1, json.getString("name")));
            }
        }

        return groceries;
    }

    private static Map<String, Grocery> toMap(Set<Grocery> groceries) {

        Map<String, Grocery> map = new HashMap<>();

        for (Grocery grocery : groceries)
            map.put(grocery.getName(), grocery);


        return map;
    }

    private static Recipe toRecipe(int i, JSONObject json, Map<String, Grocery> map) throws JSONException {

        Recipe.Builder builder = new Recipe.Builder();

        builder.withId(i)
                .withTitle(json.getString("title"))
                .withDescription(json.getString("description"))
                .withCookTime(json.getInt("cook_time"))
                .withCalories(json.getDouble("food_energy"))
                .withProteins(json.getDouble("proteins"))
                .withCarbohydrates(json.getDouble("carbohydrates"))
                .withTriglycerides(json.getDouble("triglycerides"));

        JSONArray jsonPictures = json.getJSONArray("pictures");

        for (int j = 0; j < jsonPictures.length(); ++j) {
            String picURI = jsonPictures.getString(j);

            builder.addPictureURI(picURI);
        }

        JSONArray jsonTags = json.getJSONArray("tags");

        for (int j = 0; j < jsonTags.length(); j++) {
            String tag = jsonTags.getString(j);

            builder.addTag(tag);
        }

        JSONArray jsonDirections = json.getJSONArray("directions");

        for (int j = 0; j < jsonDirections.length(); j++) {

            JSONObject jsonDirection = jsonDirections.getJSONObject(j);

            builder.addDirection(new Recipe.Direction(jsonDirection.getInt("order"), jsonDirection.getString("action")));
        }

        JSONArray jsonIngredients = json.getJSONArray("ingredients");

        for (int j = 0; j < jsonIngredients.length(); j++) {
            JSONObject jsonIngredient = jsonIngredients.getJSONObject(j);

            Grocery grocery = map.get(jsonIngredient.getString("name"));

            double amount = jsonIngredient.getDouble("amount");

            Unit unit = unitFromString(jsonIngredient.getString("unit"));

            assert unit != null;

            builder.addIngredient(new Recipe.Ingredient(grocery, new BigDecimal(amount), unit));
        }


        return builder.build();
    }

    private static Unit unitFromString(String str) {

        return str.equals("кг") ? Unit.Kilo :
                str.equals("г") ? Unit.Gram :
                        str.equals("л") ? Unit.Liter :
                                str.equals("мл") ? Unit.MilliLiter :
                                        str.equals("ч. л") ? Unit.TeaSpoon :
                                                str.equals("ст. л") ? Unit.TableSpoon :
                                                        str.equals("шт") ? Unit.Piece :
                                                                str.equals("по вкусу") ? Unit.Optional : null;
    }
}
