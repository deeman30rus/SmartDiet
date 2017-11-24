package com.delizarov.smartdiet.ui.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.delizarov.smartdiet.R;
import com.delizarov.smartdiet.domain.models.Ingredient;
import com.delizarov.smartdiet.presentation.ingredient.IngredientsPresenter;
import com.delizarov.smartdiet.presentation.ingredient.IngredientsView;
import com.delizarov.smartdiet.ui.adapters.SortedListAdapter;
import com.delizarov.smartdiet.ui.viewholders.IngredientViewHolder;
import com.delizarov.smartdiet.ui.viewholders.ViewHolderBase;
import com.github.clans.fab.FloatingActionButton;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientsActivity extends BaseActivity implements IngredientsView {

    @Inject
    IngredientsPresenter presenter;

    @BindView(R.id.ingredients_list)
    RecyclerView ingredientList;

    @BindView(R.id.add_ingredient)
    FloatingActionButton addIngredient;

    private SortedListAdapter<Ingredient> mAdapter = new SortedListAdapter<Ingredient>(Ingredient.class, (i1, i2) -> i1.getName().compareToIgnoreCase(i2.getName())) {
        @Override
        public ViewHolderBase<Ingredient> onCreateViewHolder(ViewGroup parent, int viewType) {
            return new IngredientViewHolder(R.layout.vh_ingredient, parent);
        }

        @Override
        public void onBindViewHolder(ViewHolderBase<Ingredient> holder, int position) {

            holder.bind(get(position));
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ingredients);

        getApplicationComponent().inject(IngredientsActivity.this);

        ButterKnife.bind(IngredientsActivity.this);

        ingredientList.setAdapter(mAdapter);

        presenter.attachView(IngredientsActivity.this);
        presenter.onCreate();

    }

    @Override
    public void renderIngredient(Ingredient ingredient) {

        mAdapter.add(ingredient);
    }
}
