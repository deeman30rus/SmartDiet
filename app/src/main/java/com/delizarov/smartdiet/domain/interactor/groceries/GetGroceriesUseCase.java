package com.delizarov.smartdiet.domain.interactor.groceries;


import com.delizarov.smartdiet.data.repository.CookbookRepository;
import com.delizarov.smartdiet.domain.interactor.UseCase;
import com.delizarov.smartdiet.domain.models.Grocery;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class GetGroceriesUseCase extends UseCase<Grocery, Void> {

    private final CookbookRepository mCookbookRepository;

    @Inject
    public GetGroceriesUseCase(CookbookRepository cookbookRepository) {
        mCookbookRepository = cookbookRepository;
    }


    @Override
    protected Observable<Grocery> createObservable(Void aVoid) {
        return mCookbookRepository
                .readGroceries()
                .subscribeOn(Schedulers.newThread());
    }
}
