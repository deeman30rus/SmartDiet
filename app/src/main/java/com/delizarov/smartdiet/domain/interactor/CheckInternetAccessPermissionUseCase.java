package com.delizarov.smartdiet.domain.interactor;


import com.delizarov.smartdiet.data.repository.AppRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class CheckInternetAccessPermissionUseCase extends UseCase<Boolean, Void> {

    private final AppRepository mAppRepository;

    @Inject
    public CheckInternetAccessPermissionUseCase(AppRepository appRepository) {

        mAppRepository = appRepository;
    }

    @Override
    protected Observable<Boolean> createObservable(Void aVoid) {

        return Observable.just(mAppRepository.checkInternetAccessPermissionGranted());
    }
}
