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
import com.delizarov.smartdiet.ui.models.Filter;
import com.delizarov.smartdiet.ui.viewholders.IngredientViewHolder;
import com.delizarov.smartdiet.ui.viewholders.ViewHolderBase;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class IngredientListFragment extends BaseFragment {

    public static final String TAG = "IngredientListFragmentTag";

    private Unbinder mUnbinder;

    private IngredientViewHolder.OnItemClickListener mOnItemClickListener;
    private IngredientViewHolder.OnItemDeleteClickListener mOnItemDeleteClickListener;

    private Filter<Ingredient> mFilter;

    private List<Ingredient> mIngredients = new ArrayList<>();

    @BindView(R.id.ingredients)
    RecyclerView ingredients;

    private SortedListAdapter<Ingredient> mAdapter = new SortedListAdapter<Ingredient>(Ingredient.class, (i1, i2) -> i1.getName().compareToIgnoreCase(i2.getName())) {
        @Override
        public ViewHolderBase<Ingredient> onCreateViewHolder(ViewGroup parent, int viewType) {

            IngredientViewHolder viewHolder = new IngredientViewHolder(R.layout.vh_ingredient, parent);

            viewHolder.setOnItemClickListener(mOnItemClickListener);

            viewHolder.setOnItemDeleteClickListener(mOnItemDeleteClickListener);

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

        mIngredients.add(ingredient);
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

    public void setOnItemDeleteClickListener(IngredientViewHolder.OnItemDeleteClickListener onItemDeleteClickListener) {
        mOnItemDeleteClickListener = onItemDeleteClickListener;
    }

    public void applyFilter(Filter<Ingredient> filter) {

        mFilter = filter;

        filterList();
    }

    public void clearFilter() {

        mFilter = null;

        filterList();
    }

    /**
     * Заменяет ингридиент с совпадающим id. В списке будет найден ингридиент с id идентичным параметру
     * и заменён на значение параметра. Если ингридиент не будет найден, то ничего не произойдёт.
     *
     * @param ingredient - ингридиент, на который необходимо заменить элемент списка
     */
    public void updateIngredient(Ingredient ingredient) {

        int size = mAdapter.getItemCount();

        for (int i = 0; i < size; ++i) {

            Ingredient toReplace = mAdapter.get(i);

            if (toReplace.getId() != ingredient.getId())
                continue;

            mAdapter.updateItemAt(i, ingredient);

            break;
        }
    }

    public void removeIngredient(Ingredient ingredient) {

        mAdapter.remove(ingredient);
    }

    private void filterList() {

        if (mFilter == null) {
            mAdapter.addAll(mIngredients);
            return;
        }

        List<Ingredient> filtered = new ArrayList<>();

        for (Ingredient ingredient : mIngredients)
            if (mFilter.match(ingredient))
                filtered.add(ingredient);

        mAdapter.clear();
        mAdapter.addAll(filtered);
    }
}
