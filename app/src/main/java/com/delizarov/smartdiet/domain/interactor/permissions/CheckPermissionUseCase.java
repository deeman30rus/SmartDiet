package com.delizarov.smartdiet.domain.interactor.permissions;


import com.delizarov.smartdiet.data.repository.AppRepository;
import com.delizarov.smartdiet.domain.interactor.UseCase;

public abstract class CheckPermissionUseCase extends UseCase<Boolean, Void> {

    protected final AppRepository mAppRepository;

    public CheckPermissionUseCase(AppRepository appRepository) {
        mAppRepository = appRepository;
    }
}
