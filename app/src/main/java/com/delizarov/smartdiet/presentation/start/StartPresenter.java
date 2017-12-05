package com.delizarov.smartdiet.presentation.start;


import com.delizarov.smartdiet.domain.interactor.GetCurrentUserUseCase;
import com.delizarov.smartdiet.domain.interactor.permissions.GetNotGrantedPermissionsUseCase;
import com.delizarov.smartdiet.domain.models.User;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class StartPresenter {

    private StartView mView;

    private final GetNotGrantedPermissionsUseCase mGetNotGrantedPermissionsUseCase;
    private final GetCurrentUserUseCase mGetCurrentUserUseCase;

    @Inject
    public StartPresenter(GetNotGrantedPermissionsUseCase getNotGrantedPermissionsUseCase, GetCurrentUserUseCase getCurrentUserUseCase) {

        mGetNotGrantedPermissionsUseCase = getNotGrantedPermissionsUseCase;
        mGetCurrentUserUseCase = getCurrentUserUseCase;
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
                .switchIfEmpty(observer -> mView.showLoginView())
                .doOnNext(notGrantedPermissions::addAll)
                .doOnComplete(() -> {
                    if (!notGrantedPermissions.isEmpty()) {
                        mView.showPermissionsDialog(notGrantedPermissions);
                        return;
                    }

                    mGetCurrentUserUseCase
                            .observable()
                            .subscribe(user -> {

                                if (user == User.UNATHORIZED_USER)
                                    mView.showLoginView();
                                else
                                    mView.showDailyMealsView(user);
                            });
                })
                .subscribe();
    }
}
