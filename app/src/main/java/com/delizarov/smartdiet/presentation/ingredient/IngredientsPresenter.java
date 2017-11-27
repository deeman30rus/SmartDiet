package com.delizarov.smartdiet.presentation.ingredient;


import com.delizarov.smartdiet.domain.exceptions.IngredientNameEmptyException;
import com.delizarov.smartdiet.domain.interactor.ingredients.GetIngredientsUseCase;
import com.delizarov.smartdiet.domain.interactor.ingredients.SaveIngredientUseCase;
import com.delizarov.smartdiet.domain.models.Ingredient;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class IngredientsPresenter {

    private IngredientsView mView;

    private final GetIngredientsUseCase mGetIngredientsUseCase;
    private final SaveIngredientUseCase mSaveIngredientUseCase;

    private Disposable mGetIngredientDisposable;
    private Disposable mSaveIngredientDisposable;

    @Inject
    public IngredientsPresenter(GetIngredientsUseCase getIngredientsUseCase, SaveIngredientUseCase saveIngredientUseCase) {
        mGetIngredientsUseCase = getIngredientsUseCase;
        mSaveIngredientUseCase = saveIngredientUseCase;
    }

    public void attachView(IngredientsView view) {

        mView = view;
    }


    public void onCreate() {

        updateList();
    }

    private void updateList() {

        mView.clearIngredients();

        if (mGetIngredientDisposable != null)
            mGetIngredientDisposable.dispose();

        mGetIngredientDisposable = mGetIngredientsUseCase
                .observable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(ingredient -> mView.renderIngredient(ingredient));
    }

    public void onAddButtonClicked() {

        mView.showIngredientDetails(null);
    }

    public void onSaveIngredient(final Ingredient instance) {

        if (mSaveIngredientDisposable != null)
            mSaveIngredientDisposable.dispose();

        mSaveIngredientUseCase
                .observable(instance)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Ingredient>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Ingredient ingredient) {

                        if (instance.getId() == Ingredient.UNREGISTERED_INGREDIENT_ID)
                            mView.addIngredient(ingredient);
                        else
                            mView.updateIngredient(ingredient);
                    }

                    @Override
                    public void onError(Throwable e) {

                        if (e instanceof IngredientNameEmptyException)
                            mView.showIngredientNameEmptyError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void onIngredientItemClicked(Ingredient ingredient) {

        mView.showIngredientDetails(ingredient);
    }

    public void onSearch(String query) {

        if (query.isEmpty())
            mView.clearFilter();
        else
            mView.filterListMatchingQuery(query);
    }
}
