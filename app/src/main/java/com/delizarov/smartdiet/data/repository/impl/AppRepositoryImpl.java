package com.delizarov.smartdiet.data.repository.impl;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

import com.delizarov.smartdiet.data.repository.AppRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class AppRepositoryImpl implements AppRepository {

    private static final String[] REQUIRED_PERMISSIONS = {

            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };

    private Context mAppContext;

    @Inject
    public AppRepositoryImpl(Context context) {

        mAppContext = context;
    }

    @Override
    public boolean checkInternetAccessPermissionGranted() {

        return ContextCompat.checkSelfPermission(mAppContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public boolean checkCameraPermissionGranted() {

        return ContextCompat.checkSelfPermission(mAppContext, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public List<String> getRequiredNotGrantedPermissions() {

        List<String> permissions = new ArrayList<>();

        for (String permission : REQUIRED_PERMISSIONS)
            if (ContextCompat.checkSelfPermission(mAppContext, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                permissions.add(permission);

        return permissions;
    }
}
