package com.delizarov.smartdiet.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.delizarov.smartdiet.R;
import com.delizarov.smartdiet.domain.models.Grocery;
import com.delizarov.smartdiet.domain.models.Recipe;
import com.delizarov.smartdiet.ui.adapters.SortedListAdapter;
import com.delizarov.smartdiet.ui.viewholders.GroceryViewHolder;
import com.delizarov.smartdiet.ui.viewholders.RecipeViewHolder;
import com.delizarov.smartdiet.ui.viewholders.ViewHolderBase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RecipeListFragment extends BaseFragment {

    public static final String TAG = "RecipeListFragmentTag";

    @BindView(R.id.recipes)
    RecyclerView recipes;

    private Unbinder mUnbinder;

    private SortedListAdapter<Recipe> mAdapter = new SortedListAdapter<Recipe>(Recipe.class, (r1, r2) -> Long.compare(r1.getId(), r2.getId())) {
        @Override
        public ViewHolderBase<Recipe> onCreateViewHolder(ViewGroup parent, int viewType) {

            RecipeViewHolder viewHolder = new RecipeViewHolder(R.layout.vh_recipe, parent);

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolderBase<Recipe> holder, int position) {

            holder.bind(get(position));
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recipe_list, container, false);

        mUnbinder = ButterKnife.bind(this, view);

        getApplicationComponent().inject(this);

        recipes.setAdapter(mAdapter);

        return view;
    }

    public static RecipeListFragment newInstance() {
        return new RecipeListFragment();
    }

    public void addRecipe(Recipe recipe) {

        mAdapter.add(recipe);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mUnbinder.unbind();
    }
}
