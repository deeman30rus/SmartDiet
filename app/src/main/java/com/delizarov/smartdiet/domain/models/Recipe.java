package com.delizarov.smartdiet.domain.models;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Recipe {

    private long mId;

    private String mTitle;
    private String mDescription;

    private List<String> mPictureURIs;

    private Integer mCookTime;

    private BigDecimal mCalories;

    private BigDecimal mProteins;
    private BigDecimal mTriglycerides;
    private BigDecimal mCarbohydrates;

    private List<Ingredient> mIngredients;
    private List<Direction> mDirections;

    private Set<String> mTags;

    public long getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public List<String> getPictureURIs() {
        return mPictureURIs;
    }

    public String getMainPicture() {

        // TODO: сделать что-нибудь когда картинки нет

        return mPictureURIs.get(0);
    }

    public Integer getCookTime() {
        return mCookTime;
    }

    public BigDecimal getCalories() {
        return mCalories;
    }

    public BigDecimal getProteins() {
        return mProteins;
    }

    public BigDecimal getTriglycerides() {
        return mTriglycerides;
    }

    public BigDecimal getCarbohydrates() {
        return mCarbohydrates;
    }

    public List<Ingredient> getIngredients() {
        return mIngredients;
    }

    public List<Direction> getDirections() {
        return mDirections;
    }

    public Set<String> getTags() {
        return mTags;
    }

    @Override
    public int hashCode() {

        // TODO: implement

        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null || !(obj instanceof Recipe))
            return false;

        Recipe toCompare = (Recipe) obj;

        return this.mId == toCompare.mId &&
                this.mTitle.equals(toCompare.mTitle) &&
                this.mDescription.equals(toCompare.mDescription) &&
                this.mPictureURIs.equals(toCompare.mPictureURIs) &&
                this.mCookTime.equals(toCompare.mCookTime) &&
                this.mCalories.equals(toCompare.mCalories) &&
                this.mProteins.equals(toCompare.mProteins) &&
                this.mTriglycerides.equals(toCompare.mTriglycerides) &&
                this.mCarbohydrates.equals(toCompare.mCarbohydrates) &&
                this.mIngredients.equals(toCompare.mIngredients) &&
                this.mDirections.equals(toCompare.mDirections) &&
                this.mTags.equals(toCompare.mTags);
    }

    public void addIngredient(Ingredient ingredient) {
        mIngredients.add(ingredient);
    }

    public void addDirection(Direction direction) {

        mDirections.add(direction);
    }

    public void addTag(String tag) {

        mTags.add(tag);
    }

    private Recipe() {

        mPictureURIs = new ArrayList<>();

        mIngredients = new ArrayList<>();
        mDirections = new ArrayList<>();

        mTags = new HashSet<>();
    }

    public static class Direction {

        public int Order;
        public String Action;

        public Direction(int order, String action) {
            Order = order;
            Action = action;
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {

            if (obj == null || !(obj instanceof Direction))
                return false;

            Direction toCompare = (Direction) obj;

            return Direction.this.Order == toCompare.Order &&
                    Direction.this.Action.equals(toCompare.Action);
        }
    }

    public static class Ingredient {

        public Grocery Grocery;
        public BigDecimal Amount;
        public Unit Unit;

        public Ingredient(Grocery grocery, BigDecimal amount, Unit unit) {
            Grocery = grocery;
            Amount = amount;
            Unit = unit;
        }

        @Override
        public boolean equals(Object obj) {

            if (obj == null || !(obj instanceof Ingredient))
                return false;

            Ingredient toCompare = (Ingredient) obj;

            return Ingredient.this.Grocery.equals(toCompare.Grocery) &&
                    Ingredient.this.Amount.equals(toCompare.Amount) &&
                    Ingredient.this.Unit.equals(toCompare.Unit);
        }

        @Override
        public int hashCode() {

            // TODO: 02.12.2017 implement
            return super.hashCode();
        }
    }

    public static class Builder {

        private Recipe mRecipe;

        public Builder() {

            mRecipe = new Recipe();
        }

        public Builder withId(long id) {

            mRecipe.mId = id;

            return Builder.this;
        }

        public Builder withTitle(String title) {

            mRecipe.mTitle = title;

            return Builder.this;
        }

        public Builder withDescription(String description) {

            mRecipe.mDescription = description;

            return Builder.this;
        }

        public Builder addPictureURI(String pictureURI) {

            mRecipe.mPictureURIs.add(pictureURI);

            return Builder.this;
        }

        public Builder addPictureURIs(Iterable<String> pictureURIs) {

            for (String pictureURI : pictureURIs)
                addPictureURI(pictureURI);

            return Builder.this;
        }

        public Builder withCookTime(Integer cookTime) {

            mRecipe.mCookTime = cookTime;

            return Builder.this;
        }

        public Builder withCalories(BigDecimal calories) {

            mRecipe.mCalories = calories;

            return Builder.this;
        }

        public Builder withCalories(double calories) {

            return withCalories(new BigDecimal(calories));
        }

        public Builder withCalories(int calories) {

            return withCalories(new BigDecimal(calories));
        }

        public Builder withProteins(BigDecimal proteins) {

            mRecipe.mProteins = proteins;

            return Builder.this;
        }

        public Builder withProteins(double proteins) {

            return withProteins(new BigDecimal(proteins));
        }

        public Builder withCarbohydrates(BigDecimal carbohydrates) {

            mRecipe.mCarbohydrates = carbohydrates;

            return Builder.this;
        }

        public Builder withCarbohydrates(double carbohydrates) {

            return withCarbohydrates(new BigDecimal(carbohydrates));
        }

        public Builder withTriglycerides(BigDecimal triglycerides) {

            mRecipe.mTriglycerides = triglycerides;

            return Builder.this;
        }

        public Builder withTriglycerides(double triglycerides) {

            return withTriglycerides(new BigDecimal(triglycerides));
        }

        public Builder addIngredient(Ingredient ingredient) {

            mRecipe.addIngredient(ingredient);

            return Builder.this;
        }

        public Builder addIngredients(Iterable<Ingredient> ingredients) {

            for (Ingredient ingredient : ingredients)
                addIngredient(ingredient);

            return Builder.this;
        }

        public Builder addDirection(Direction direction) {

            mRecipe.addDirection(direction);

            return Builder.this;
        }

        public Builder addDirections(Iterable<Direction> directions) {

            for (Direction direction : directions)
                addDirection(direction);

            return Builder.this;
        }

        public Builder addTag(String tag) {

            mRecipe.addTag(tag);

            return Builder.this;
        }

        public Builder addTags(Iterable<String> tags) {

            for (String tag : tags)
                addTag(tag);

            return Builder.this;
        }

        public Recipe build() {

            return mRecipe;
        }
    }
}
