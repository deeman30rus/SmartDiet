package com.delizarov.smartdiet.domain.interactor.groceries;


import com.delizarov.smartdiet.data.repository.CookbookRepository;
import com.delizarov.smartdiet.domain.interactor.UseCase;
import com.delizarov.smartdiet.domain.models.Grocery;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

public class RemoveGroceryUseCase extends UseCase<Boolean, Grocery> {

    private final CookbookRepository mCookbookRepository;

    @Inject
    public RemoveGroceryUseCase(CookbookRepository cookbookRepository) {
        mCookbookRepository = cookbookRepository;
    }

    @Override
    protected Observable<Boolean> createObservable(Grocery grocery) {
        return Observable.create((ObservableOnSubscribe<Boolean>) e -> {

            e.onNext(mCookbookRepository.removeGrocery(grocery));
            e.onComplete();
        }).subscribeOn(Schedulers.io());
    }
}
