package com.delizarov.smartdiet.presentation.login;


import com.delizarov.smartdiet.domain.models.User;

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

    public void onSuccessLogIn(User user) {

        mView.showDailyMeals();
    }

    public void onFailedLogIn() {

        // TODO: implement when you know what to do
    }
}
