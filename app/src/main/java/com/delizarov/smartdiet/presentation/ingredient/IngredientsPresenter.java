package com.delizarov.smartdiet.presentation.ingredient;


import com.delizarov.smartdiet.domain.interactor.ingredients.GetIngredientsUseCase;

import javax.inject.Inject;

public class IngredientsPresenter {

    private IngredientsView mView;

    private final GetIngredientsUseCase mGetIngredientsUseCase;

    @Inject
    public IngredientsPresenter(GetIngredientsUseCase getIngredientsUseCase) {
        mGetIngredientsUseCase = getIngredientsUseCase;
    }

    public void attachView(IngredientsView view) {

        mView = view;
    }


    public void onCreate() {

    }
}
