package com.delizarov.smartdiet.domain.interactor.permissions;


import com.delizarov.smartdiet.data.repository.AppRepository;
import com.delizarov.smartdiet.domain.interactor.UseCase;

import javax.inject.Inject;

import io.reactivex.Observable;

public class CheckCameraPermissionUseCase extends CheckPermissionUseCase {

    @Inject
    public CheckCameraPermissionUseCase(AppRepository appRepository) {
        super(appRepository);
    }

    @Override
    protected Observable<Boolean> createObservable(Void aVoid) {
        return Observable.just(mAppRepository.checkCameraPermissionGranted());
    }
}
