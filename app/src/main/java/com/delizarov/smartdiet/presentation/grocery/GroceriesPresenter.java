package com.delizarov.smartdiet.presentation.grocery;


import com.delizarov.smartdiet.domain.exceptions.GroceryNameEmptyException;
import com.delizarov.smartdiet.domain.interactor.groceries.GetGroceriesUseCase;
import com.delizarov.smartdiet.domain.interactor.groceries.RemoveGroceryUseCase;
import com.delizarov.smartdiet.domain.interactor.groceries.SaveGroceryUseCase;
import com.delizarov.smartdiet.domain.models.Grocery;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GroceriesPresenter {

    private GroceriesView mView;

    private final GetGroceriesUseCase mGetGroceriesUseCase;
    private final SaveGroceryUseCase mSaveGroceryUseCase;
    private final RemoveGroceryUseCase mRemoveGroceryUseCase;


    private Disposable mGetGroceryDisposable;
    private Disposable mRemoveGroceryDisposable;

    @Inject
    public GroceriesPresenter(GetGroceriesUseCase getGroceriesUseCase, SaveGroceryUseCase saveGroceryUseCase, RemoveGroceryUseCase removeGroceryUseCase) {
        mGetGroceriesUseCase = getGroceriesUseCase;
        mSaveGroceryUseCase = saveGroceryUseCase;
        mRemoveGroceryUseCase = removeGroceryUseCase;
    }

    public void attachView(GroceriesView view) {

        mView = view;
    }


    public void onCreate() {

        updateList();
    }

    private void updateList() {

        mView.clearGroceries();

        if (mGetGroceryDisposable != null)
            mGetGroceryDisposable.dispose();

        mGetGroceryDisposable = mGetGroceriesUseCase
                .observable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(grocery -> mView.renderGrocery(grocery));
    }

    public void onAddButtonClicked() {

        mView.showGroceryDetails(null);
    }

    public void onSaveGrocery(final Grocery instance) {

        mSaveGroceryUseCase
                .observable(instance)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Grocery>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Grocery grocery) {

                        if (instance.getId() == Grocery.UNREGISTERED_GROCERY_ID)
                            mView.addGrocery(grocery);
                        else
                            mView.updateGrocery(grocery);
                    }

                    @Override
                    public void onError(Throwable e) {

                        if (e instanceof GroceryNameEmptyException)
                            mView.showGroceryNameEmptyError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void onGroceryItemClicked(Grocery grocery) {

        mView.showGroceryDetails(grocery);
    }

    public void onSearch(String query) {

        if (query.isEmpty())
            mView.clearFilter();
        else
            mView.filterListMatchingQuery(query);
    }

    public void onGroceryDeleteClicked(final Grocery grocery) {

        if (mRemoveGroceryDisposable != null)
            mRemoveGroceryDisposable.dispose();

        mRemoveGroceryDisposable = mRemoveGroceryUseCase
                .observable(grocery)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(result -> {
                    if (result)
                        mView.removeGrocery(grocery);
                }).subscribe();
    }
}
