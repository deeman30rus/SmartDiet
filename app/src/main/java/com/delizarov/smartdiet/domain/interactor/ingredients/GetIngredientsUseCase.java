package com.delizarov.smartdiet.domain.interactor.ingredients;


import com.delizarov.smartdiet.data.repository.CookbookRepository;
import com.delizarov.smartdiet.domain.interactor.UseCase;
import com.delizarov.smartdiet.domain.models.Ingredient;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class GetIngredientsUseCase extends UseCase<Ingredient, Void> {

    private final CookbookRepository mCookbookRepository;

    @Inject
    public GetIngredientsUseCase(CookbookRepository cookbookRepository) {
        mCookbookRepository = cookbookRepository;
    }


    @Override
    protected Observable<Ingredient> createObservable(Void aVoid) {
        return mCookbookRepository
                .readIngredients()
                .subscribeOn(Schedulers.newThread());
    }
}
