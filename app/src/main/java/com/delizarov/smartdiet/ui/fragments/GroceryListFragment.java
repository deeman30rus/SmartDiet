package com.delizarov.smartdiet.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.delizarov.smartdiet.R;
import com.delizarov.smartdiet.domain.models.Grocery;
import com.delizarov.smartdiet.ui.adapters.SortedListAdapter;
import com.delizarov.smartdiet.ui.models.Filter;
import com.delizarov.smartdiet.ui.viewholders.GroceryViewHolder;
import com.delizarov.smartdiet.ui.viewholders.ViewHolderBase;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class GroceryListFragment extends BaseFragment {

    public static final String TAG = "IngredientListFragmentTag";

    private Unbinder mUnbinder;

    private GroceryViewHolder.OnItemClickListener mOnItemClickListener;
    private GroceryViewHolder.OnItemDeleteClickListener mOnItemDeleteClickListener;

    private Filter<Grocery> mFilter;

    private List<Grocery> mGroceries = new ArrayList<>();

    @BindView(R.id.groceries)
    RecyclerView groceries;

    private SortedListAdapter<Grocery> mAdapter = new SortedListAdapter<Grocery>(Grocery.class, (i1, i2) -> i1.getName().compareToIgnoreCase(i2.getName())) {
        @Override
        public ViewHolderBase<Grocery> onCreateViewHolder(ViewGroup parent, int viewType) {

            GroceryViewHolder viewHolder = new GroceryViewHolder(R.layout.vh_grocery, parent);

            viewHolder.setOnItemClickListener(mOnItemClickListener);

            viewHolder.setOnItemDeleteClickListener(mOnItemDeleteClickListener);

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolderBase<Grocery> holder, int position) {

            holder.bind(get(position));
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_grocery_list, container, false);

        mUnbinder = ButterKnife.bind(this, view);

        getApplicationComponent().inject(this);

        groceries.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mUnbinder.unbind();
    }

    public void addIngredient(Grocery grocery) {

        mGroceries.add(grocery);
        mAdapter.add(grocery);
    }

    public static GroceryListFragment newInstance() {

        return new GroceryListFragment();
    }

    public void clearList() {

        mAdapter.clear();
    }

    public void setOnItemClickListener(GroceryViewHolder.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemDeleteClickListener(GroceryViewHolder.OnItemDeleteClickListener onItemDeleteClickListener) {
        mOnItemDeleteClickListener = onItemDeleteClickListener;
    }

    public void applyFilter(Filter<Grocery> filter) {

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
     * @param grocery - ингридиент, на который необходимо заменить элемент списка
     */
    public void updateIngredient(Grocery grocery) {

        int size = mAdapter.getItemCount();

        for (int i = 0; i < size; ++i) {

            Grocery toReplace = mAdapter.get(i);

            if (toReplace.getId() != grocery.getId())
                continue;

            mAdapter.updateItemAt(i, grocery);

            break;
        }
    }

    public void removeIngredient(Grocery grocery) {

        mAdapter.remove(grocery);
    }

    private void filterList() {

        if (mFilter == null) {
            mAdapter.addAll(mGroceries);
            return;
        }

        List<Grocery> filtered = new ArrayList<>();

        for (Grocery grocery : mGroceries)
            if (mFilter.match(grocery))
                filtered.add(grocery);

        mAdapter.clear();
        mAdapter.addAll(filtered);
    }
}
