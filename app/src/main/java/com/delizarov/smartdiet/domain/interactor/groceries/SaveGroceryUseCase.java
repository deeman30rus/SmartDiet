package com.delizarov.smartdiet.domain.interactor.groceries;


import com.delizarov.smartdiet.data.repository.CookbookRepository;
import com.delizarov.smartdiet.domain.exceptions.GroceryNameEmptyException;
import com.delizarov.smartdiet.domain.interactor.UseCase;
import com.delizarov.smartdiet.domain.models.Grocery;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

public class SaveGroceryUseCase extends UseCase<Grocery, Grocery> {

    private final CookbookRepository mCookbookRepository;

    @Inject
    public SaveGroceryUseCase(CookbookRepository cookbookRepository) {
        mCookbookRepository = cookbookRepository;
    }

    @Override
    protected Observable<Grocery> createObservable(Grocery grocery) {

        return Observable.create((ObservableOnSubscribe<Grocery>) e -> {

            if (grocery.getName().isEmpty())
                e.onError(new GroceryNameEmptyException());

            Grocery copy = new Grocery(grocery.getId(), grocery.getName());

            String name = copy.getName();
            copy.setName(name.replace('\n', ' '));

            copy.setId(mCookbookRepository.saveGrocery(copy));

            e.onNext(copy);
            e.onComplete();
        }).subscribeOn(Schedulers.newThread());
    }
}
