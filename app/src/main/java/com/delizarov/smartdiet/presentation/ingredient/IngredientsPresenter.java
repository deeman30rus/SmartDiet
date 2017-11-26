package com.delizarov.smartdiet.presentation.ingredient;


import com.delizarov.smartdiet.domain.interactor.ingredients.GetIngredientsUseCase;
import com.delizarov.smartdiet.domain.interactor.ingredients.SaveIngredientUseCase;
import com.delizarov.smartdiet.domain.models.Ingredient;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class IngredientsPresenter {

    private IngredientsView mView;

    private final GetIngredientsUseCase mGetIngredientsUseCase;
    private final SaveIngredientUseCase mSaveIngredientUseCase;

    private Disposable mGetIngredientDisposable;
    private Disposable mSaveIngredientDisposable;

    @Inject
    public IngredientsPresenter(GetIngredientsUseCase getIngredientsUseCase, SaveIngredientUseCase saveIngredientUseCase) {
        mGetIngredientsUseCase = getIngredientsUseCase;
        mSaveIngredientUseCase = saveIngredientUseCase;
    }

    public void attachView(IngredientsView view) {

        mView = view;
    }


    public void onCreate() {

        updateList();
    }

    private void updateList() {

        mView.clearIngredients();

        if (mGetIngredientDisposable != null)
            mGetIngredientDisposable.dispose();

        mGetIngredientDisposable = mGetIngredientsUseCase
                .observable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(ingredient -> mView.renderIngredient(ingredient));
    }

    public void onAddButtonClicked() {

        mView.showIngredientDetails(null);
    }

    public void onSaveIngredient(final Ingredient instance) {

        if (mSaveIngredientDisposable != null)
            mSaveIngredientDisposable.dispose();

        mSaveIngredientDisposable = mSaveIngredientUseCase.observable(instance)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(this::updateList)
                .subscribe();
    }

    public void onIngredientItemClicked(Ingredient ingredient) {

        mView.showIngredientDetails(ingredient);
    }
}
