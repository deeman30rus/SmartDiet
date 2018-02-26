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
import com.delizarov.smartdiet.ui.viewholders.RecipeDirectionViewHolder;
import com.delizarov.smartdiet.ui.viewholders.ViewHolderBase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RecipeDetailsDirectionsFragment extends RecipeDetailsFragment {

    @BindView(R.id.recipe_directions)
    RecyclerView directions;

    public static RecipeDetailsDirectionsFragment newInstance() {

        return new RecipeDetailsDirectionsFragment();
    }

    private SortedListAdapter<Recipe.Direction> mAdapter = new SortedListAdapter<Recipe.Direction>(Recipe.Direction.class, (d1, d2) -> Integer.compare(d1.Order, d2.Order)) {
        @Override
        public ViewHolderBase<Recipe.Direction> onCreateViewHolder(ViewGroup parent, int viewType) {

            RecipeDirectionViewHolder viewHolder = new RecipeDirectionViewHolder(R.layout.vh_recipe_direction, parent);

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolderBase<Recipe.Direction> holder, int position) {

            holder.bind(get(position));
        }
    };

    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recipe_details_directions, container, false);

        mUnbinder = ButterKnife.bind(this, view);

        directions.setAdapter(mAdapter);

        mAdapter.addAll(recipe.getDirections());

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mUnbinder.unbind();
    }
}
