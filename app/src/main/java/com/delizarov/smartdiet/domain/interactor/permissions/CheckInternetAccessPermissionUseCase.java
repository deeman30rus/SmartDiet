package com.delizarov.smartdiet.domain.interactor.permissions;


import com.delizarov.smartdiet.data.repository.AppRepository;
import com.delizarov.smartdiet.domain.interactor.permissions.CheckPermissionUseCase;

import javax.inject.Inject;

import io.reactivex.Observable;

public class CheckInternetAccessPermissionUseCase extends CheckPermissionUseCase {

    @Inject
    public CheckInternetAccessPermissionUseCase(AppRepository appRepository) {

        super(appRepository);
    }

    @Override
    protected Observable<Boolean> createObservable(Void aVoid) {

        return Observable.just(mAppRepository.checkInternetAccessPermissionGranted());
    }
}
