package com.delizarov.smartdiet.domain.interactor.ingredients;


import com.delizarov.smartdiet.data.repository.CookbookRepository;
import com.delizarov.smartdiet.domain.interactor.UseCase;
import com.delizarov.smartdiet.domain.models.Ingredient;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class SaveIngredientUseCase extends UseCase<Boolean, Ingredient> {

    private final CookbookRepository mCookbookRepository;

    @Inject
    public SaveIngredientUseCase(CookbookRepository cookbookRepository) {
        mCookbookRepository = cookbookRepository;
    }

    @Override
    protected Observable<Boolean> createObservable(Ingredient ingredient) {

        return Observable
                .defer(() -> Observable.just(mCookbookRepository.saveIngredient(ingredient)))
                .subscribeOn(Schedulers.io());
    }
}
