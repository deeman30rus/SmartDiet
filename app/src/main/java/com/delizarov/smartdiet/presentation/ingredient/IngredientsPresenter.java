package com.delizarov.smartdiet.presentation.ingredient;


import com.delizarov.smartdiet.domain.interactor.ingredients.GetIngredientsUseCase;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class IngredientsPresenter {

    private IngredientsView mView;

    private final GetIngredientsUseCase mGetIngredientsUseCase;

    private Disposable mGetIngredientDisposable;

    @Inject
    public IngredientsPresenter(GetIngredientsUseCase getIngredientsUseCase) {
        mGetIngredientsUseCase = getIngredientsUseCase;
    }

    public void attachView(IngredientsView view) {

        mView = view;
    }


    public void onCreate() {

        updateList();
    }

    private void updateList() {

        if (mGetIngredientDisposable != null)
            mGetIngredientDisposable.dispose();

        mGetIngredientDisposable = mGetIngredientsUseCase
                .observable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(ingredient -> mView.renderIngredient(ingredient));
    }
}
