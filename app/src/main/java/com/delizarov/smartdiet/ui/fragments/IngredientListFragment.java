package com.delizarov.smartdiet.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.delizarov.smartdiet.R;
import com.delizarov.smartdiet.domain.models.Ingredient;
import com.delizarov.smartdiet.ui.adapters.SortedListAdapter;
import com.delizarov.smartdiet.ui.viewholders.IngredientViewHolder;
import com.delizarov.smartdiet.ui.viewholders.ViewHolderBase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class IngredientListFragment extends BaseFragment {

    public static final String TAG = "IngredientListFragmentTag";

    private Unbinder mUnbinder;

    private IngredientViewHolder.OnItemClickListener mOnItemClickListener;

    @BindView(R.id.ingredients)
    RecyclerView ingredients;

    private SortedListAdapter<Ingredient> mAdapter = new SortedListAdapter<Ingredient>(Ingredient.class, (i1, i2) -> i1.getName().compareToIgnoreCase(i2.getName())) {
        @Override
        public ViewHolderBase<Ingredient> onCreateViewHolder(ViewGroup parent, int viewType) {

            IngredientViewHolder viewHolder = new IngredientViewHolder(R.layout.vh_ingredient, parent);

            viewHolder.setOnItemClickListener(mOnItemClickListener);

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolderBase<Ingredient> holder, int position) {

            holder.bind(get(position));
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ingredient_list, container, false);

        mUnbinder = ButterKnife.bind(this, view);

        getApplicationComponent().inject(this);

        ingredients.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mUnbinder.unbind();
    }

    public void addIngredient(Ingredient ingredient) {

        mAdapter.add(ingredient);
    }

    public static IngredientListFragment newInstance() {

        return new IngredientListFragment();
    }

    public void clearList() {

        mAdapter.clear();
    }

    public void setOnItemClickListener(IngredientViewHolder.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

}
