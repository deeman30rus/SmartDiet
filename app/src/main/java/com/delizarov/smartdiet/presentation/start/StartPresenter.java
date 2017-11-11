package com.delizarov.smartdiet.presentation.start;


import com.delizarov.smartdiet.domain.interactor.CheckInternetAccessPermissionUseCase;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class StartPresenter {

    private StartView mView;

    private final CheckInternetAccessPermissionUseCase mCheckInternetAccessPermissionUseCase;

    private Disposable mCheckPermissionDisposable;

    @Inject
    public StartPresenter(CheckInternetAccessPermissionUseCase checkInternetAccessPermissionUseCase) {

        mCheckInternetAccessPermissionUseCase = checkInternetAccessPermissionUseCase;
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

        if (mCheckPermissionDisposable != null)
            mCheckPermissionDisposable.dispose();

        mCheckPermissionDisposable = mCheckInternetAccessPermissionUseCase
                .observable()
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(isChecked -> {

                    if (!isChecked)
                        mView.showInternetPermissionsDialog();
                });
    }
}
