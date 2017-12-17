package com.delizarov.smartdiet.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.delizarov.smartdiet.R;
import com.delizarov.smartdiet.domain.models.Recipe;
import com.delizarov.smartdiet.ui.adapters.SortedListAdapter;
import com.delizarov.smartdiet.ui.viewholders.GroceryViewHolder;
import com.delizarov.smartdiet.ui.viewholders.RecipeIngredientViewHolder;
import com.delizarov.smartdiet.ui.viewholders.ViewHolderBase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class RecipeDetailsIngredientsFragment extends RecipeDetailsFragment {

    @BindView(R.id.recipe_ingredients)
    RecyclerView ingredients;

    private SortedListAdapter<Recipe.Ingredient> mAdapter = new SortedListAdapter<Recipe.Ingredient>(Recipe.Ingredient.class, (i1, i2) -> i1.Grocery.getName().compareToIgnoreCase(i2.Grocery.getName())) {
        @Override
        public ViewHolderBase<Recipe.Ingredient> onCreateViewHolder(ViewGroup parent, int viewType) {

            RecipeIngredientViewHolder viewHolder = new RecipeIngredientViewHolder(R.layout.vh_recipe_ingredient, parent);

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolderBase<Recipe.Ingredient> holder, int position) {

            holder.bind(get(position));
        }
    };

    private Unbinder mUnbinder;

    public static RecipeDetailsIngredientsFragment newInstance() {

        return new RecipeDetailsIngredientsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recipe_details_ingredients, container, false);

        mUnbinder = ButterKnife.bind(this, view);

        ingredients.setAdapter(mAdapter);

        mAdapter.addAll(mRecipe.getIngredients());

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mUnbinder.unbind();
    }
}
