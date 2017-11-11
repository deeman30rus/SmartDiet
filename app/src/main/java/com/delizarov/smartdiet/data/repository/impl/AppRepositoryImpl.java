package com.delizarov.smartdiet.data.repository.impl;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

import com.delizarov.smartdiet.data.repository.AppRepository;

import javax.inject.Inject;


public class AppRepositoryImpl implements AppRepository {

    private static final String PERMISSION_INTERNET = "android.permission.INTERNET";

    private Context mAppContext;

    @Inject
    public AppRepositoryImpl(Context context) {

        mAppContext = context;
    }

    @Override
    public boolean checkInternetAccessPermissionGranted() {

        return ContextCompat.checkSelfPermission(mAppContext, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED;
    }
}
