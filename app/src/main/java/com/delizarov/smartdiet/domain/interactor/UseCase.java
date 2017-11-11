package com.delizarov.smartdiet.domain.interactor;

import io.reactivex.Observable;

public abstract class UseCase<TResult, TParams> {

    protected UseCase() {
    }

    /**
     * Создание нового экземпляра класса {@link Observable <TResult>} для указанных параметров
     *
     * @param params Параметры для создания {@link Observable<TResult>}
     * @return Новый объект {@link Observable<TResult>}
     */
    protected abstract Observable<TResult> createObservable(TParams params);

    /**
     * {@link Observable<TResult>}, который исполняет логику данного Use Case для указанных параметров.
     *
     * @param params Входные параметры для создания {@link Observable<TResult>}
     * @return Объект {@link Observable<TResult>}, который исполняет логику данного Use Case для указанных параметров.
     */
    public final Observable<TResult> observable(TParams params) {
        return createObservable(params)
                .doOnError(throwable -> {

                });
    }

    /**
     * {@link Observable<TResult>} с параметрами null
     */
    public final Observable<TResult> observable() {
        return observable(null);
    }
}


