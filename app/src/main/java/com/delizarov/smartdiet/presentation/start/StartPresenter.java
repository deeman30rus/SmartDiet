package com.delizarov.smartdiet.presentation.start;


import com.delizarov.smartdiet.domain.interactor.permissions.CheckInternetAccessPermissionUseCase;
import com.delizarov.smartdiet.domain.interactor.permissions.GetNotGrantedPermissionsUseCase;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class StartPresenter {

    private StartView mView;

    private final CheckInternetAccessPermissionUseCase mCheckInternetAccessPermissionUseCase;

    private final GetNotGrantedPermissionsUseCase mGetNotGrantedPermissionsUseCase;

    @Inject
    public StartPresenter(CheckInternetAccessPermissionUseCase checkInternetAccessPermissionUseCase, GetNotGrantedPermissionsUseCase getNotGrantedPermissionsUseCase) {

        mCheckInternetAccessPermissionUseCase = checkInternetAccessPermissionUseCase;
        mGetNotGrantedPermissionsUseCase = getNotGrantedPermissionsUseCase;
    }

    public void attachView(StartView view) {

        mView = view;
    }

    public void onStartViewCreated() {

        checkPermissions();
    }

    public void onRequestPermissionResult() {

        checkPermissions();
    }

    private void checkPermissions() {

        final List<String> notGrantedPermissions = new ArrayList<>();

        mGetNotGrantedPermissionsUseCase
                .observable()
                .switchIfEmpty(observer ->mView.showLoginView())
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<String> permissions) {

                        notGrantedPermissions.addAll(permissions);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                        if (notGrantedPermissions.isEmpty())
                            mView.showLoginView();
                        else
                            mView.showPermissionsDialog(notGrantedPermissions);
                    }
                });

//        Observable
//                .merge(mCheckInternetAccessPermissionUseCase.observable(), Observable.just(true))
//                .all(isGranted -> isGranted)
//                .subscribe((isGranted, throwable) -> {
//
//                    if (!isGranted)
//                        mView.showPermissionsDialog(permissions);
//                    else
//                        mView.showLoginView();
//                });
    }
}
