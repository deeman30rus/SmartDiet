package com.delizarov.smartdiet.domain.models;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotSame;
import static junit.framework.Assert.assertTrue;

public class RecipeTest {

    @Test
    public void equalsTest() throws JSONException {

        final Recipe expected = expected();

        Recipe wrongId = wrongId();
        Recipe wrongTitle = wrongTitle();
        Recipe wrongDescription = wrongDescription();
        Recipe wrongCookTime = wrongCookTime();
        Recipe wrongCalories = wrongCalories();
        Recipe wrongProteins = wrongProteins();
        Recipe wrongTri = wrongTriglycerides();
        Recipe wrongCarbo = wrongCarbohydrates();
        Recipe missingPictures = missingPicture();
        Recipe wrongPicture = wrongPicture();
        Recipe missingTag = missingTag();
        Recipe wrongTag = wrongTag();
        Recipe missingIngri = missingIngredient();
        Recipe wrongGrocery = wrongGrocery();
        Recipe wrongAmount = wrongAmount();
        Recipe wrongUnit = wrongUnit();
        Recipe missingDir = missingDirection();
        Recipe wrongOrder = wrongOrder();
        Recipe wrongAction = wrongAction();

        assertNotSame(expected, wrongId);
        assertNotSame(expected, wrongTitle);
        assertNotSame(expected, wrongDescription);
        assertNotSame(expected, wrongCookTime);
        assertNotSame(expected, wrongCalories);
        assertNotSame(expected, wrongProteins);
        assertNotSame(expected, wrongTri);
        assertNotSame(expected, wrongCarbo);
        assertNotSame(expected, missingPictures);
        assertNotSame(expected, wrongPicture);
        assertNotSame(expected, missingTag);
        assertNotSame(expected, wrongTag);
        assertNotSame(expected, missingIngri);
        assertNotSame(expected, wrongGrocery);
        assertNotSame(expected, wrongAmount);
        assertNotSame(expected, wrongUnit);
        assertNotSame(expected, missingDir);
        assertNotSame(expected, wrongOrder);
        assertNotSame(expected, wrongAction);
    }

    private static Recipe expected() {

        Recipe.Builder builder = new Recipe.Builder();

        builder
                .withId(1)
                .withTitle("Title")
                .withDescription("Description")
                .withCookTime(20)
                .withCalories(34)
                .withProteins(34.3)
                .withTriglycerides(30.3)
                .withCarbohydrates(20.3);

        String pictures[] = {"pic1", "pic2", "pic3"};

        for (String pictureURI : pictures)
            builder.addPictureURI(pictureURI);

        String tags[] = {"tag1", "tag2", "tag3"};

        for (String tag : tags)
            builder.addTag(tag);

        Grocery grocery1 = new Grocery(1, "g1");
        Grocery grocery2 = new Grocery(2, "g2");
        Grocery grocery3 = new Grocery(3, "g3");

        Recipe.Ingredient ingredient1 = new Recipe.Ingredient(grocery1, new BigDecimal(30), Unit.Gram);
        Recipe.Ingredient ingredient2 = new Recipe.Ingredient(grocery2, new BigDecimal(40), Unit.Kilo);
        Recipe.Ingredient ingredient3 = new Recipe.Ingredient(grocery3, new BigDecimal(50), Unit.Liter);

        builder.addIngredient(ingredient1);
        builder.addIngredient(ingredient2);
        builder.addIngredient(ingredient3);

        Recipe.Direction d1 = new Recipe.Direction(1, "action1");
        Recipe.Direction d2 = new Recipe.Direction(2, "action2");
        Recipe.Direction d3 = new Recipe.Direction(3, "action3");

        builder.addDirection(d1);
        builder.addDirection(d2);
        builder.addDirection(d3);

        return builder.build();
    }

    private static Recipe wrongId() {

        Recipe.Builder builder = new Recipe.Builder();

        builder
                .withId(2)
                .withTitle("Title")
                .withDescription("Description")
                .withCookTime(20)
                .withCalories(34)
                .withProteins(34.3)
                .withTriglycerides(30.3)
                .withCarbohydrates(20.3);

        String pictures[] = {"pic1", "pic2", "pic3"};

        for (String pictureURI : pictures)
            builder.addPictureURI(pictureURI);

        String tags[] = {"tag1", "tag2", "tag3"};

        for (String tag : tags)
            builder.addTag(tag);

        Grocery grocery1 = new Grocery(1, "g1");
        Grocery grocery2 = new Grocery(2, "g2");
        Grocery grocery3 = new Grocery(3, "g3");

        Recipe.Ingredient ingredient1 = new Recipe.Ingredient(grocery1, new BigDecimal(30), Unit.Gram);
        Recipe.Ingredient ingredient2 = new Recipe.Ingredient(grocery2, new BigDecimal(40), Unit.Kilo);
        Recipe.Ingredient ingredient3 = new Recipe.Ingredient(grocery3, new BigDecimal(50), Unit.Liter);

        builder.addIngredient(ingredient1);
        builder.addIngredient(ingredient2);
        builder.addIngredient(ingredient3);

        Recipe.Direction d1 = new Recipe.Direction(1, "action1");
        Recipe.Direction d2 = new Recipe.Direction(2, "action2");
        Recipe.Direction d3 = new Recipe.Direction(3, "action3");

        builder.addDirection(d1);
        builder.addDirection(d2);
        builder.addDirection(d3);

        return builder.build();
    }

    private static Recipe wrongTitle() {

        Recipe.Builder builder = new Recipe.Builder();

        builder
                .withId(1)
                .withTitle("Title1")
                .withDescription("Description")
                .withCookTime(20)
                .withCalories(34)
                .withProteins(34.3)
                .withTriglycerides(30.3)
                .withCarbohydrates(20.3);

        String pictures[] = {"pic1", "pic2", "pic3"};

        for (String pictureURI : pictures)
            builder.addPictureURI(pictureURI);

        String tags[] = {"tag1", "tag2", "tag3"};

        for (String tag : tags)
            builder.addTag(tag);

        Grocery grocery1 = new Grocery(1, "g1");
        Grocery grocery2 = new Grocery(2, "g2");
        Grocery grocery3 = new Grocery(3, "g3");

        Recipe.Ingredient ingredient1 = new Recipe.Ingredient(grocery1, new BigDecimal(30), Unit.Gram);
        Recipe.Ingredient ingredient2 = new Recipe.Ingredient(grocery2, new BigDecimal(40), Unit.Kilo);
        Recipe.Ingredient ingredient3 = new Recipe.Ingredient(grocery3, new BigDecimal(50), Unit.Liter);

        builder.addIngredient(ingredient1);
        builder.addIngredient(ingredient2);
        builder.addIngredient(ingredient3);

        Recipe.Direction d1 = new Recipe.Direction(1, "action1");
        Recipe.Direction d2 = new Recipe.Direction(2, "action2");
        Recipe.Direction d3 = new Recipe.Direction(3, "action3");

        builder.addDirection(d1);
        builder.addDirection(d2);
        builder.addDirection(d3);

        return builder.build();
    }

    private static Recipe wrongDescription() {

        Recipe.Builder builder = new Recipe.Builder();

        builder
                .withId(1)
                .withTitle("Title")
                .withDescription("Description1")
                .withCookTime(20)
                .withCalories(34)
                .withProteins(34.3)
                .withTriglycerides(30.3)
                .withCarbohydrates(20.3);

        String pictures[] = {"pic1", "pic2", "pic3"};

        for (String pictureURI : pictures)
            builder.addPictureURI(pictureURI);

        String tags[] = {"tag1", "tag2", "tag3"};

        for (String tag : tags)
            builder.addTag(tag);

        Grocery grocery1 = new Grocery(1, "g1");
        Grocery grocery2 = new Grocery(2, "g2");
        Grocery grocery3 = new Grocery(3, "g3");

        Recipe.Ingredient ingredient1 = new Recipe.Ingredient(grocery1, new BigDecimal(30), Unit.Gram);
        Recipe.Ingredient ingredient2 = new Recipe.Ingredient(grocery2, new BigDecimal(40), Unit.Kilo);
        Recipe.Ingredient ingredient3 = new Recipe.Ingredient(grocery3, new BigDecimal(50), Unit.Liter);

        builder.addIngredient(ingredient1);
        builder.addIngredient(ingredient2);
        builder.addIngredient(ingredient3);

        Recipe.Direction d1 = new Recipe.Direction(1, "action1");
        Recipe.Direction d2 = new Recipe.Direction(2, "action2");
        Recipe.Direction d3 = new Recipe.Direction(3, "action3");

        builder.addDirection(d1);
        builder.addDirection(d2);
        builder.addDirection(d3);

        return builder.build();
    }

    private static Recipe wrongCookTime() {

        Recipe.Builder builder = new Recipe.Builder();

        builder
                .withId(1)
                .withTitle("Title")
                .withDescription("Description")
                .withCookTime(34)
                .withCalories(34)
                .withProteins(34.3)
                .withTriglycerides(30.3)
                .withCarbohydrates(20.3);

        String pictures[] = {"pic1", "pic2", "pic3"};

        for (String pictureURI : pictures)
            builder.addPictureURI(pictureURI);

        String tags[] = {"tag1", "tag2", "tag3"};

        for (String tag : tags)
            builder.addTag(tag);

        Grocery grocery1 = new Grocery(1, "g1");
        Grocery grocery2 = new Grocery(2, "g2");
        Grocery grocery3 = new Grocery(3, "g3");

        Recipe.Ingredient ingredient1 = new Recipe.Ingredient(grocery1, new BigDecimal(30), Unit.Gram);
        Recipe.Ingredient ingredient2 = new Recipe.Ingredient(grocery2, new BigDecimal(40), Unit.Kilo);
        Recipe.Ingredient ingredient3 = new Recipe.Ingredient(grocery3, new BigDecimal(50), Unit.Liter);

        builder.addIngredient(ingredient1);
        builder.addIngredient(ingredient2);
        builder.addIngredient(ingredient3);

        Recipe.Direction d1 = new Recipe.Direction(1, "action1");
        Recipe.Direction d2 = new Recipe.Direction(2, "action2");
        Recipe.Direction d3 = new Recipe.Direction(3, "action3");

        builder.addDirection(d1);
        builder.addDirection(d2);
        builder.addDirection(d3);

        return builder.build();
    }

    private static Recipe wrongCalories() {

        Recipe.Builder builder = new Recipe.Builder();

        builder
                .withId(1)
                .withTitle("Title")
                .withDescription("Description")
                .withCookTime(20)
                .withCalories(11)
                .withProteins(34.3)
                .withTriglycerides(30.3)
                .withCarbohydrates(20.3);

        String pictures[] = {"pic1", "pic2", "pic3"};

        for (String pictureURI : pictures)
            builder.addPictureURI(pictureURI);

        String tags[] = {"tag1", "tag2", "tag3"};

        for (String tag : tags)
            builder.addTag(tag);

        Grocery grocery1 = new Grocery(1, "g1");
        Grocery grocery2 = new Grocery(2, "g2");
        Grocery grocery3 = new Grocery(3, "g3");

        Recipe.Ingredient ingredient1 = new Recipe.Ingredient(grocery1, new BigDecimal(30), Unit.Gram);
        Recipe.Ingredient ingredient2 = new Recipe.Ingredient(grocery2, new BigDecimal(40), Unit.Kilo);
        Recipe.Ingredient ingredient3 = new Recipe.Ingredient(grocery3, new BigDecimal(50), Unit.Liter);

        builder.addIngredient(ingredient1);
        builder.addIngredient(ingredient2);
        builder.addIngredient(ingredient3);

        Recipe.Direction d1 = new Recipe.Direction(1, "action1");
        Recipe.Direction d2 = new Recipe.Direction(2, "action2");
        Recipe.Direction d3 = new Recipe.Direction(3, "action3");

        builder.addDirection(d1);
        builder.addDirection(d2);
        builder.addDirection(d3);

        return builder.build();
    }

    private static Recipe wrongProteins() {

        Recipe.Builder builder = new Recipe.Builder();

        builder
                .withId(1)
                .withTitle("Title")
                .withDescription("Description")
                .withCookTime(20)
                .withCalories(34)
                .withProteins(11.3)
                .withTriglycerides(30.3)
                .withCarbohydrates(20.3);

        String pictures[] = {"pic1", "pic2", "pic3"};

        for (String pictureURI : pictures)
            builder.addPictureURI(pictureURI);

        String tags[] = {"tag1", "tag2", "tag3"};

        for (String tag : tags)
            builder.addTag(tag);

        Grocery grocery1 = new Grocery(1, "g1");
        Grocery grocery2 = new Grocery(2, "g2");
        Grocery grocery3 = new Grocery(3, "g3");

        Recipe.Ingredient ingredient1 = new Recipe.Ingredient(grocery1, new BigDecimal(30), Unit.Gram);
        Recipe.Ingredient ingredient2 = new Recipe.Ingredient(grocery2, new BigDecimal(40), Unit.Kilo);
        Recipe.Ingredient ingredient3 = new Recipe.Ingredient(grocery3, new BigDecimal(50), Unit.Liter);

        builder.addIngredient(ingredient1);
        builder.addIngredient(ingredient2);
        builder.addIngredient(ingredient3);

        Recipe.Direction d1 = new Recipe.Direction(1, "action1");
        Recipe.Direction d2 = new Recipe.Direction(2, "action2");
        Recipe.Direction d3 = new Recipe.Direction(3, "action3");

        builder.addDirection(d1);
        builder.addDirection(d2);
        builder.addDirection(d3);

        return builder.build();
    }

    private static Recipe wrongTriglycerides() {

        Recipe.Builder builder = new Recipe.Builder();

        builder
                .withId(1)
                .withTitle("Title")
                .withDescription("Description")
                .withCookTime(20)
                .withCalories(34)
                .withProteins(34.3)
                .withTriglycerides(88.3)
                .withCarbohydrates(20.3);

        String pictures[] = {"pic1", "pic2", "pic3"};

        for (String pictureURI : pictures)
            builder.addPictureURI(pictureURI);

        String tags[] = {"tag1", "tag2", "tag3"};

        for (String tag : tags)
            builder.addTag(tag);

        Grocery grocery1 = new Grocery(1, "g1");
        Grocery grocery2 = new Grocery(2, "g2");
        Grocery grocery3 = new Grocery(3, "g3");

        Recipe.Ingredient ingredient1 = new Recipe.Ingredient(grocery1, new BigDecimal(30), Unit.Gram);
        Recipe.Ingredient ingredient2 = new Recipe.Ingredient(grocery2, new BigDecimal(40), Unit.Kilo);
        Recipe.Ingredient ingredient3 = new Recipe.Ingredient(grocery3, new BigDecimal(50), Unit.Liter);

        builder.addIngredient(ingredient1);
        builder.addIngredient(ingredient2);
        builder.addIngredient(ingredient3);

        Recipe.Direction d1 = new Recipe.Direction(1, "action1");
        Recipe.Direction d2 = new Recipe.Direction(2, "action2");
        Recipe.Direction d3 = new Recipe.Direction(3, "action3");

        builder.addDirection(d1);
        builder.addDirection(d2);
        builder.addDirection(d3);

        return builder.build();
    }

    private static Recipe wrongCarbohydrates() {

        Recipe.Builder builder = new Recipe.Builder();

        builder
                .withId(1)
                .withTitle("Title")
                .withDescription("Description")
                .withCookTime(20)
                .withCalories(34)
                .withProteins(34.3)
                .withTriglycerides(30.3)
                .withCarbohydrates(87.3);

        String pictures[] = {"pic1", "pic2", "pic3"};

        for (String pictureURI : pictures)
            builder.addPictureURI(pictureURI);

        String tags[] = {"tag1", "tag2", "tag3"};

        for (String tag : tags)
            builder.addTag(tag);

        Grocery grocery1 = new Grocery(1, "g1");
        Grocery grocery2 = new Grocery(2, "g2");
        Grocery grocery3 = new Grocery(3, "g3");

        Recipe.Ingredient ingredient1 = new Recipe.Ingredient(grocery1, new BigDecimal(30), Unit.Gram);
        Recipe.Ingredient ingredient2 = new Recipe.Ingredient(grocery2, new BigDecimal(40), Unit.Kilo);
        Recipe.Ingredient ingredient3 = new Recipe.Ingredient(grocery3, new BigDecimal(50), Unit.Liter);

        builder.addIngredient(ingredient1);
        builder.addIngredient(ingredient2);
        builder.addIngredient(ingredient3);

        Recipe.Direction d1 = new Recipe.Direction(1, "action1");
        Recipe.Direction d2 = new Recipe.Direction(2, "action2");
        Recipe.Direction d3 = new Recipe.Direction(3, "action3");

        builder.addDirection(d1);
        builder.addDirection(d2);
        builder.addDirection(d3);

        return builder.build();
    }

    private static Recipe missingPicture() {

        Recipe.Builder builder = new Recipe.Builder();

        builder
                .withId(1)
                .withTitle("Title")
                .withDescription("Description")
                .withCookTime(20)
                .withCalories(34)
                .withProteins(34.3)
                .withTriglycerides(30.3)
                .withCarbohydrates(20.3);

        String pictures[] = {"pic1", "pic2"};

        for (String pictureURI : pictures)
            builder.addPictureURI(pictureURI);

        String tags[] = {"tag1", "tag2", "tag3"};

        for (String tag : tags)
            builder.addTag(tag);

        Grocery grocery1 = new Grocery(1, "g1");
        Grocery grocery2 = new Grocery(2, "g2");
        Grocery grocery3 = new Grocery(3, "g3");

        Recipe.Ingredient ingredient1 = new Recipe.Ingredient(grocery1, new BigDecimal(30), Unit.Gram);
        Recipe.Ingredient ingredient2 = new Recipe.Ingredient(grocery2, new BigDecimal(40), Unit.Kilo);
        Recipe.Ingredient ingredient3 = new Recipe.Ingredient(grocery3, new BigDecimal(50), Unit.Liter);

        builder.addIngredient(ingredient1);
        builder.addIngredient(ingredient2);
        builder.addIngredient(ingredient3);

        Recipe.Direction d1 = new Recipe.Direction(1, "action1");
        Recipe.Direction d2 = new Recipe.Direction(2, "action2");
        Recipe.Direction d3 = new Recipe.Direction(3, "action3");

        builder.addDirection(d1);
        builder.addDirection(d2);
        builder.addDirection(d3);

        return builder.build();
    }

    private static Recipe wrongPicture() {

        Recipe.Builder builder = new Recipe.Builder();

        builder
                .withId(1)
                .withTitle("Title")
                .withDescription("Description")
                .withCookTime(20)
                .withCalories(34)
                .withProteins(34.3)
                .withTriglycerides(30.3)
                .withCarbohydrates(20.3);

        String pictures[] = {"pic1", "pic2", "pic4"};

        for (String pictureURI : pictures)
            builder.addPictureURI(pictureURI);

        String tags[] = {"tag1", "tag2", "tag3"};

        for (String tag : tags)
            builder.addTag(tag);

        Grocery grocery1 = new Grocery(1, "g1");
        Grocery grocery2 = new Grocery(2, "g2");
        Grocery grocery3 = new Grocery(3, "g3");

        Recipe.Ingredient ingredient1 = new Recipe.Ingredient(grocery1, new BigDecimal(30), Unit.Gram);
        Recipe.Ingredient ingredient2 = new Recipe.Ingredient(grocery2, new BigDecimal(40), Unit.Kilo);
        Recipe.Ingredient ingredient3 = new Recipe.Ingredient(grocery3, new BigDecimal(50), Unit.Liter);

        builder.addIngredient(ingredient1);
        builder.addIngredient(ingredient2);
        builder.addIngredient(ingredient3);

        Recipe.Direction d1 = new Recipe.Direction(1, "action1");
        Recipe.Direction d2 = new Recipe.Direction(2, "action2");
        Recipe.Direction d3 = new Recipe.Direction(3, "action3");

        builder.addDirection(d1);
        builder.addDirection(d2);
        builder.addDirection(d3);

        return builder.build();
    }

    private static Recipe missingTag() {

        Recipe.Builder builder = new Recipe.Builder();

        builder
                .withId(1)
                .withTitle("Title")
                .withDescription("Description")
                .withCookTime(20)
                .withCalories(34)
                .withProteins(34.3)
                .withTriglycerides(30.3)
                .withCarbohydrates(20.3);

        String pictures[] = {"pic1", "pic2", "pic3"};

        for (String pictureURI : pictures)
            builder.addPictureURI(pictureURI);

        String tags[] = {"tag1", "tag2"};

        for (String tag : tags)
            builder.addTag(tag);

        Grocery grocery1 = new Grocery(1, "g1");
        Grocery grocery2 = new Grocery(2, "g2");
        Grocery grocery3 = new Grocery(3, "g3");

        Recipe.Ingredient ingredient1 = new Recipe.Ingredient(grocery1, new BigDecimal(30), Unit.Gram);
        Recipe.Ingredient ingredient2 = new Recipe.Ingredient(grocery2, new BigDecimal(40), Unit.Kilo);
        Recipe.Ingredient ingredient3 = new Recipe.Ingredient(grocery3, new BigDecimal(50), Unit.Liter);

        builder.addIngredient(ingredient1);
        builder.addIngredient(ingredient2);
        builder.addIngredient(ingredient3);

        Recipe.Direction d1 = new Recipe.Direction(1, "action1");
        Recipe.Direction d2 = new Recipe.Direction(2, "action2");
        Recipe.Direction d3 = new Recipe.Direction(3, "action3");

        builder.addDirection(d1);
        builder.addDirection(d2);
        builder.addDirection(d3);

        return builder.build();
    }

    private static Recipe wrongTag() {

        Recipe.Builder builder = new Recipe.Builder();

        builder
                .withId(1)
                .withTitle("Title")
                .withDescription("Description")
                .withCookTime(20)
                .withCalories(34)
                .withProteins(34.3)
                .withTriglycerides(30.3)
                .withCarbohydrates(20.3);

        String pictures[] = {"pic1", "pic2", "pic3"};

        for (String pictureURI : pictures)
            builder.addPictureURI(pictureURI);

        String tags[] = {"tag1", "tag2", "tag4"};

        for (String tag : tags)
            builder.addTag(tag);

        Grocery grocery1 = new Grocery(1, "g1");
        Grocery grocery2 = new Grocery(2, "g2");
        Grocery grocery3 = new Grocery(3, "g3");

        Recipe.Ingredient ingredient1 = new Recipe.Ingredient(grocery1, new BigDecimal(30), Unit.Gram);
        Recipe.Ingredient ingredient2 = new Recipe.Ingredient(grocery2, new BigDecimal(40), Unit.Kilo);
        Recipe.Ingredient ingredient3 = new Recipe.Ingredient(grocery3, new BigDecimal(50), Unit.Liter);

        builder.addIngredient(ingredient1);
        builder.addIngredient(ingredient2);
        builder.addIngredient(ingredient3);

        Recipe.Direction d1 = new Recipe.Direction(1, "action1");
        Recipe.Direction d2 = new Recipe.Direction(2, "action2");
        Recipe.Direction d3 = new Recipe.Direction(3, "action3");

        builder.addDirection(d1);
        builder.addDirection(d2);
        builder.addDirection(d3);

        return builder.build();
    }

    private static Recipe missingIngredient() {

        Recipe.Builder builder = new Recipe.Builder();

        builder
                .withId(1)
                .withTitle("Title")
                .withDescription("Description")
                .withCookTime(20)
                .withCalories(34)
                .withProteins(34.3)
                .withTriglycerides(30.3)
                .withCarbohydrates(20.3);

        String pictures[] = {"pic1", "pic2", "pic3"};

        for (String pictureURI : pictures)
            builder.addPictureURI(pictureURI);

        String tags[] = {"tag1", "tag2", "tag3"};

        for (String tag : tags)
            builder.addTag(tag);

        Grocery grocery1 = new Grocery(1, "g1");
        Grocery grocery2 = new Grocery(2, "g2");
        Grocery grocery3 = new Grocery(3, "g3");

        Recipe.Ingredient ingredient1 = new Recipe.Ingredient(grocery1, new BigDecimal(30), Unit.Gram);
        Recipe.Ingredient ingredient2 = new Recipe.Ingredient(grocery2, new BigDecimal(40), Unit.Kilo);

        builder.addIngredient(ingredient1);
        builder.addIngredient(ingredient2);


        Recipe.Direction d1 = new Recipe.Direction(1, "action1");
        Recipe.Direction d2 = new Recipe.Direction(2, "action2");
        Recipe.Direction d3 = new Recipe.Direction(3, "action3");

        builder.addDirection(d1);
        builder.addDirection(d2);
        builder.addDirection(d3);

        return builder.build();
    }

    private static Recipe wrongGrocery() {

        Recipe.Builder builder = new Recipe.Builder();

        builder
                .withId(1)
                .withTitle("Title")
                .withDescription("Description")
                .withCookTime(20)
                .withCalories(34)
                .withProteins(34.3)
                .withTriglycerides(30.3)
                .withCarbohydrates(20.3);

        String pictures[] = {"pic1", "pic2", "pic3"};

        for (String pictureURI : pictures)
            builder.addPictureURI(pictureURI);

        String tags[] = {"tag1", "tag2", "tag3"};

        for (String tag : tags)
            builder.addTag(tag);

        Grocery grocery1 = new Grocery(4, "g1");
        Grocery grocery2 = new Grocery(2, "g2");
        Grocery grocery3 = new Grocery(3, "g3");

        Recipe.Ingredient ingredient1 = new Recipe.Ingredient(grocery1, new BigDecimal(30), Unit.Gram);
        Recipe.Ingredient ingredient2 = new Recipe.Ingredient(grocery2, new BigDecimal(40), Unit.Kilo);
        Recipe.Ingredient ingredient3 = new Recipe.Ingredient(grocery3, new BigDecimal(50), Unit.Liter);

        builder.addIngredient(ingredient1);
        builder.addIngredient(ingredient2);
        builder.addIngredient(ingredient3);

        Recipe.Direction d1 = new Recipe.Direction(1, "action1");
        Recipe.Direction d2 = new Recipe.Direction(2, "action2");
        Recipe.Direction d3 = new Recipe.Direction(3, "action3");

        builder.addDirection(d1);
        builder.addDirection(d2);
        builder.addDirection(d3);

        return builder.build();
    }

    private static Recipe wrongAmount() {

        Recipe.Builder builder = new Recipe.Builder();

        builder
                .withId(1)
                .withTitle("Title")
                .withDescription("Description")
                .withCookTime(20)
                .withCalories(34)
                .withProteins(34.3)
                .withTriglycerides(30.3)
                .withCarbohydrates(20.3);

        String pictures[] = {"pic1", "pic2", "pic3"};

        for (String pictureURI : pictures)
            builder.addPictureURI(pictureURI);

        String tags[] = {"tag1", "tag2", "tag3"};

        for (String tag : tags)
            builder.addTag(tag);

        Grocery grocery1 = new Grocery(1, "g1");
        Grocery grocery2 = new Grocery(2, "g2");
        Grocery grocery3 = new Grocery(3, "g3");

        Recipe.Ingredient ingredient1 = new Recipe.Ingredient(grocery1, new BigDecimal(50), Unit.Gram);
        Recipe.Ingredient ingredient2 = new Recipe.Ingredient(grocery2, new BigDecimal(40), Unit.Kilo);
        Recipe.Ingredient ingredient3 = new Recipe.Ingredient(grocery3, new BigDecimal(50), Unit.Liter);

        builder.addIngredient(ingredient1);
        builder.addIngredient(ingredient2);
        builder.addIngredient(ingredient3);

        Recipe.Direction d1 = new Recipe.Direction(1, "action1");
        Recipe.Direction d2 = new Recipe.Direction(2, "action2");
        Recipe.Direction d3 = new Recipe.Direction(3, "action3");

        builder.addDirection(d1);
        builder.addDirection(d2);
        builder.addDirection(d3);

        return builder.build();
    }

    private static Recipe wrongUnit() {

        Recipe.Builder builder = new Recipe.Builder();

        builder
                .withId(1)
                .withTitle("Title")
                .withDescription("Description")
                .withCookTime(20)
                .withCalories(34)
                .withProteins(34.3)
                .withTriglycerides(30.3)
                .withCarbohydrates(20.3);

        String pictures[] = {"pic1", "pic2", "pic3"};

        for (String pictureURI : pictures)
            builder.addPictureURI(pictureURI);

        String tags[] = {"tag1", "tag2", "tag3"};

        for (String tag : tags)
            builder.addTag(tag);

        Grocery grocery1 = new Grocery(1, "g1");
        Grocery grocery2 = new Grocery(2, "g2");
        Grocery grocery3 = new Grocery(3, "g3");

        Recipe.Ingredient ingredient1 = new Recipe.Ingredient(grocery1, new BigDecimal(30), Unit.Pich);
        Recipe.Ingredient ingredient2 = new Recipe.Ingredient(grocery2, new BigDecimal(40), Unit.Kilo);
        Recipe.Ingredient ingredient3 = new Recipe.Ingredient(grocery3, new BigDecimal(50), Unit.Liter);

        builder.addIngredient(ingredient1);
        builder.addIngredient(ingredient2);
        builder.addIngredient(ingredient3);

        Recipe.Direction d1 = new Recipe.Direction(1, "action1");
        Recipe.Direction d2 = new Recipe.Direction(2, "action2");
        Recipe.Direction d3 = new Recipe.Direction(3, "action3");

        builder.addDirection(d1);
        builder.addDirection(d2);
        builder.addDirection(d3);

        return builder.build();
    }

    private static Recipe missingDirection() {

        Recipe.Builder builder = new Recipe.Builder();

        builder
                .withId(1)
                .withTitle("Title")
                .withDescription("Description")
                .withCookTime(20)
                .withCalories(34)
                .withProteins(34.3)
                .withTriglycerides(30.3)
                .withCarbohydrates(20.3);

        String pictures[] = {"pic1", "pic2", "pic3"};

        for (String pictureURI : pictures)
            builder.addPictureURI(pictureURI);

        String tags[] = {"tag1", "tag2", "tag3"};

        for (String tag : tags)
            builder.addTag(tag);

        Grocery grocery1 = new Grocery(1, "g1");
        Grocery grocery2 = new Grocery(2, "g2");
        Grocery grocery3 = new Grocery(3, "g3");

        Recipe.Ingredient ingredient1 = new Recipe.Ingredient(grocery1, new BigDecimal(30), Unit.Gram);
        Recipe.Ingredient ingredient2 = new Recipe.Ingredient(grocery2, new BigDecimal(40), Unit.Kilo);
        Recipe.Ingredient ingredient3 = new Recipe.Ingredient(grocery3, new BigDecimal(50), Unit.Liter);

        builder.addIngredient(ingredient1);
        builder.addIngredient(ingredient2);
        builder.addIngredient(ingredient3);

        Recipe.Direction d1 = new Recipe.Direction(1, "action1");
        Recipe.Direction d2 = new Recipe.Direction(2, "action2");

        builder.addDirection(d1);
        builder.addDirection(d2);

        return builder.build();
    }

    private static Recipe wrongOrder() {

        Recipe.Builder builder = new Recipe.Builder();

        builder
                .withId(1)
                .withTitle("Title")
                .withDescription("Description")
                .withCookTime(20)
                .withCalories(34)
                .withProteins(34.3)
                .withTriglycerides(30.3)
                .withCarbohydrates(20.3);

        String pictures[] = {"pic1", "pic2", "pic3"};

        for (String pictureURI : pictures)
            builder.addPictureURI(pictureURI);

        String tags[] = {"tag1", "tag2", "tag3"};

        for (String tag : tags)
            builder.addTag(tag);

        Grocery grocery1 = new Grocery(1, "g1");
        Grocery grocery2 = new Grocery(2, "g2");
        Grocery grocery3 = new Grocery(3, "g3");

        Recipe.Ingredient ingredient1 = new Recipe.Ingredient(grocery1, new BigDecimal(30), Unit.Gram);
        Recipe.Ingredient ingredient2 = new Recipe.Ingredient(grocery2, new BigDecimal(40), Unit.Kilo);
        Recipe.Ingredient ingredient3 = new Recipe.Ingredient(grocery3, new BigDecimal(50), Unit.Liter);

        builder.addIngredient(ingredient1);
        builder.addIngredient(ingredient2);
        builder.addIngredient(ingredient3);

        Recipe.Direction d1 = new Recipe.Direction(5, "action1");
        Recipe.Direction d2 = new Recipe.Direction(2, "action2");
        Recipe.Direction d3 = new Recipe.Direction(3, "action3");

        builder.addDirection(d1);
        builder.addDirection(d2);
        builder.addDirection(d3);

        return builder.build();
    }

    private static Recipe wrongAction() {

        Recipe.Builder builder = new Recipe.Builder();

        builder
                .withId(1)
                .withTitle("Title")
                .withDescription("Description")
                .withCookTime(20)
                .withCalories(34)
                .withProteins(34.3)
                .withTriglycerides(30.3)
                .withCarbohydrates(20.3);

        String pictures[] = {"pic1", "pic2", "pic3"};

        for (String pictureURI : pictures)
            builder.addPictureURI(pictureURI);

        String tags[] = {"tag1", "tag2", "tag3"};

        for (String tag : tags)
            builder.addTag(tag);

        Grocery grocery1 = new Grocery(1, "g1");
        Grocery grocery2 = new Grocery(2, "g2");
        Grocery grocery3 = new Grocery(3, "g3");

        Recipe.Ingredient ingredient1 = new Recipe.Ingredient(grocery1, new BigDecimal(30), Unit.Gram);
        Recipe.Ingredient ingredient2 = new Recipe.Ingredient(grocery2, new BigDecimal(40), Unit.Kilo);
        Recipe.Ingredient ingredient3 = new Recipe.Ingredient(grocery3, new BigDecimal(50), Unit.Liter);

        builder.addIngredient(ingredient1);
        builder.addIngredient(ingredient2);
        builder.addIngredient(ingredient3);

        Recipe.Direction d1 = new Recipe.Direction(1, "action5");
        Recipe.Direction d2 = new Recipe.Direction(2, "action2");
        Recipe.Direction d3 = new Recipe.Direction(3, "action3");

        builder.addDirection(d1);
        builder.addDirection(d2);
        builder.addDirection(d3);

        return builder.build();
    }
}
