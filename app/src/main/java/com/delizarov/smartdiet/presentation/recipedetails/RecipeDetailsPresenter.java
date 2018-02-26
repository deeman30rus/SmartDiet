package com.delizarov.smartdiet.presentation.recipedetails;


import com.delizarov.smartdiet.domain.interactor.recipes.GetRecipesUseCase;

import java.util.Arrays;
import java.util.Collections;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class RecipeDetailsPresenter {

    private RecipeDetailsView mView;

    private final GetRecipesUseCase mGetRecipesUseCase;
    private Disposable mGetRecipeDisposable;

    @Inject
    public RecipeDetailsPresenter(GetRecipesUseCase getRecipesUseCase) {
        mGetRecipesUseCase = getRecipesUseCase;
    }

    public void attachView(RecipeDetailsView view) {

        mView = view;
    }

    public void beforeViewDisplayed(int resId) {

        mView.applyThemeResource(resId);
    }

    public void onViewCreated(long recipeId) {

        if (mGetRecipeDisposable != null)
            mGetRecipeDisposable.dispose();

        mGetRecipeDisposable = mGetRecipesUseCase
                .observable(Collections.singletonList(recipeId))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(recipe -> mView.displayRecipe(recipe))
                .subscribe();
    }
}
