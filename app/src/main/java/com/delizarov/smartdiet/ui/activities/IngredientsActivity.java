package com.delizarov.smartdiet.ui.activities;


import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.delizarov.smartdiet.R;
import com.delizarov.smartdiet.domain.models.Ingredient;
import com.delizarov.smartdiet.presentation.ingredient.IngredientsPresenter;
import com.delizarov.smartdiet.presentation.ingredient.IngredientsView;
import com.delizarov.smartdiet.ui.fragments.IngredientListFragment;
import com.delizarov.smartdiet.ui.models.Filter;
import com.github.clans.fab.FloatingActionButton;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class IngredientsActivity extends BaseActivity implements IngredientsView {

    @Inject
    IngredientsPresenter presenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.add_ingredient)
    FloatingActionButton addIngredient;

    @BindView(R.id.container)
    ConstraintLayout container;

    private IngredientListFragment mIngredientsFragment;

    private SearchManager mSearchManager;

    private Observable<String> mSearchQueryObservable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ingredients);

        getApplicationComponent().inject(IngredientsActivity.this);

        ButterKnife.bind(IngredientsActivity.this);

        // toolbar

        toolbar.setTitle(R.string.activity_ingredients_title);

        setSupportActionBar(toolbar);

        // ingredients list
        mIngredientsFragment = IngredientListFragment.newInstance();

        mIngredientsFragment.setOnItemClickListener(ingredient -> presenter.onIngredientItemClicked(ingredient));
        mIngredientsFragment.setOnItemDeleteClickListener(ingredient -> presenter.onIngredientDeleteClicked(ingredient));

        FragmentManager fm = getSupportFragmentManager();

        fm.beginTransaction()
                .replace(R.id.ingredients_list, mIngredientsFragment, IngredientListFragment.TAG)
                .commit();

        mSearchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        //

        addIngredient.setOnClickListener(v -> presenter.onAddButtonClicked());

        presenter.attachView(IngredientsActivity.this);
        presenter.onCreate();
    }


    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.ingredients_menu, menu);

        // search
        MenuItem searchItem = menu.findItem(R.id.search);

        final SearchView searchView = (SearchView) searchItem.getActionView();

        ObservableOnSubscribe<String> searchQuerySubscribe = e -> {

            searchView.setSearchableInfo(mSearchManager.getSearchableInfo(getComponentName()));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {

                    e.onNext(query);

                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {

                    e.onNext(newText);

                    return false;
                }
            });
        };

        mSearchQueryObservable = Observable
                .create(searchQuerySubscribe)
                .debounce(300, TimeUnit.MILLISECONDS);

        mSearchQueryObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(query -> presenter.onSearch(query));

        return true;
    }

    @Override
    public void renderIngredient(Ingredient ingredient) {

        mIngredientsFragment.addIngredient(ingredient);
    }

    @Override
    public void showIngredientDetails(Ingredient ingredient) {

        String ingredientName = ingredient == null ? "" : ingredient.getName();

        AlertDialog.Builder builder = new AlertDialog.Builder(IngredientsActivity.this);

        final AlertDialog alertDialog = builder
                .setTitle(R.string.dialog_ingredient_details_title)
                .setView(R.layout.dialog_edit_ingredient)
                .setPositiveButton(R.string.dialog_ingredient_details_positive_button_text, null)
                .create();

        alertDialog.setOnShowListener(dialog -> {

            final Button button = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);

            final EditText ingredientNameInput = ButterKnife.findById(alertDialog, R.id.ingredient_name);

            ingredientNameInput.setText(ingredientName);
            ingredientNameInput.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    button.setEnabled(charSequence.length() != 0);
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            button.setOnClickListener(v -> {

                String name = ingredientNameInput.getText().toString();

                long id = (ingredient == null ? Ingredient.UNREGISTERED_INGREDIENT_ID : ingredient.getId());

                presenter.onSaveIngredient(new Ingredient(id, name));

                alertDialog.dismiss();
            });
        });

        alertDialog.show();
    }

    @Override
    public void clearIngredients() {

        mIngredientsFragment.clearList();
    }

    @Override
    public void updateIngredient(Ingredient ingredient) {

        mIngredientsFragment.updateIngredient(ingredient);
    }

    @Override
    public void showIngredientNameEmptyError() {

        Snackbar.make(container, "Имя ингридиента не может быть пустым", Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public void addIngredient(Ingredient ingredient) {

        mIngredientsFragment.addIngredient(ingredient);
    }

    @Override
    public void filterListMatchingQuery(String query) {

        mIngredientsFragment.applyFilter(new NameMatchingFilter(query));
    }

    @Override
    public void clearFilter() {
        mIngredientsFragment.clearFilter();
    }

    @Override
    public void removeIngredient(Ingredient ingredient) {
        mIngredientsFragment.removeIngredient(ingredient);
    }

    private static class NameMatchingFilter implements Filter<Ingredient> {

        private String mNameToMatch;

        public NameMatchingFilter(String nameToMatch) {
            mNameToMatch = nameToMatch;
        }

        @Override
        public boolean match(Ingredient ingredient) {
            return ingredient.getName().contains(mNameToMatch);
        }

    }
}
