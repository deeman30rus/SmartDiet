package com.delizarov.smartdiet.domain.interactor.recipes;


import com.delizarov.smartdiet.data.repository.CookbookRepository;
import com.delizarov.smartdiet.domain.interactor.UseCase;
import com.delizarov.smartdiet.domain.models.Recipe;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class GetRecipesUseCase extends UseCase<Recipe, List<Long>> {

    private CookbookRepository mCookbookRepository;

    @Inject
    public GetRecipesUseCase(CookbookRepository cookbookRepository) {
        mCookbookRepository = cookbookRepository;
    }

    @Override
    protected Observable<Recipe> createObservable(List<Long> identifiers) {
        return mCookbookRepository
                .readRecipes(identifiers)
                .subscribeOn(Schedulers.newThread());
    }
}
