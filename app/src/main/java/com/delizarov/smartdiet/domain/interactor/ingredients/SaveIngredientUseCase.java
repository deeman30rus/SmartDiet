package com.delizarov.smartdiet.domain.interactor.ingredients;


import com.delizarov.smartdiet.data.repository.CookbookRepository;
import com.delizarov.smartdiet.domain.exceptions.IngredientNameEmptyException;
import com.delizarov.smartdiet.domain.interactor.UseCase;
import com.delizarov.smartdiet.domain.models.Ingredient;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

public class SaveIngredientUseCase extends UseCase<Ingredient, Ingredient> {

    private final CookbookRepository mCookbookRepository;

    @Inject
    public SaveIngredientUseCase(CookbookRepository cookbookRepository) {
        mCookbookRepository = cookbookRepository;
    }

    @Override
    protected Observable<Ingredient> createObservable(Ingredient ingredient) {

        return Observable.create((ObservableOnSubscribe<Ingredient>) e -> {

            if (ingredient.getName().isEmpty())
                e.onError(new IngredientNameEmptyException());

            String name = ingredient.getName();
            ingredient.setName(name.replace('\n', ' '));

            mCookbookRepository.saveIngredient(ingredient);

            e.onNext(ingredient);
            e.onComplete();
        }).subscribeOn(Schedulers.newThread());
    }
}
