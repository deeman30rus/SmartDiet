package com.delizarov.smartdiet.data.repository.impl;

import android.content.Context;

import com.delizarov.smartdiet.data.repository.UserRepository;
import com.delizarov.smartdiet.domain.models.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import javax.inject.Inject;


public class UserRepositoryImpl implements UserRepository {


    private Context mContext;

    @Inject
    public UserRepositoryImpl(Context context) {

        mContext = context;
    }

    @Override
    public User getUserInfo() {

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(mContext);

        if (account == null)
            return User.UNATHORIZED_USER;

        return new User(account.getId(), account.getDisplayName());
    }
}
