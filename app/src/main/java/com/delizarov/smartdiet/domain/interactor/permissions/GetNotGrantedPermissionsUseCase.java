package com.delizarov.smartdiet.domain.interactor.permissions;


import com.delizarov.smartdiet.data.repository.AppRepository;
import com.delizarov.smartdiet.domain.interactor.UseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetNotGrantedPermissionsUseCase extends UseCase<List<String>, Void> {


    private final AppRepository mAppRepository;

    @Inject
    public GetNotGrantedPermissionsUseCase(AppRepository appRepository) {

        mAppRepository = appRepository;
    }

    @Override
    protected Observable<List<String>> createObservable(Void aVoid) {
        return Observable.just(mAppRepository.getRequiredNotGrantedPermissions());
    }
}
