package com.delizarov.smartdiet.presentation.login;


import javax.inject.Inject;

public class LoginPresenter {

    private LoginView mView;

    @Inject
    public LoginPresenter() {
    }

    public void attachView(LoginView view) {

        mView = view;
    }

    public void onViewCreated() {
        
    }
}
