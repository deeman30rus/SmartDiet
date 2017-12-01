package com.delizarov.smartdiet.domain.interactor.ingredients;


import com.delizarov.smartdiet.data.repository.CookbookRepository;
import com.delizarov.smartdiet.domain.interactor.UseCase;
import com.delizarov.smartdiet.domain.models.Ingredient;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

public class RemoveIngredientUseCase extends UseCase<Boolean, Ingredient> {

    private final CookbookRepository mCookbookRepository;

    @Inject
    public RemoveIngredientUseCase(CookbookRepository cookbookRepository) {
        mCookbookRepository = cookbookRepository;
    }

    @Override
    protected Observable<Boolean> createObservable(Ingredient ingredient) {
        return Observable.create((ObservableOnSubscribe<Boolean>) e -> {

            e.onNext(mCookbookRepository.removeIngredient(ingredient));
            e.onComplete();
        }).subscribeOn(Schedulers.io());
    }
}
