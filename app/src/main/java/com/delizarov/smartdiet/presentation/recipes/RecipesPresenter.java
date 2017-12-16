package com.delizarov.smartdiet.presentation.recipes;

import com.delizarov.smartdiet.domain.interactor.recipes.GetRecipesUseCase;
import com.delizarov.smartdiet.domain.models.Recipe;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class RecipesPresenter {

    private RecipesView mView;

    private final GetRecipesUseCase mGetRecipesUseCase;

    private Disposable mGetRecipesDisposable;

    @Inject
    public RecipesPresenter(GetRecipesUseCase getRecipesUseCase) {
        mGetRecipesUseCase = getRecipesUseCase;
    }

    public void attachView(RecipesView view) {

        mView = view;
    }

    public void onViewCreated() {

        if (mGetRecipesDisposable != null)
            mGetRecipesDisposable.dispose();

        mGetRecipesDisposable = mGetRecipesUseCase
                .observable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(recipe -> mView.renderRecipe(recipe));
    }

    public void onAddButtonClick() {

    }

    public void onRecipeItemClicked(Recipe recipe) {

        mView.showRecipeDetails(recipe);
    }
}
