package com.delizarov.smartdiet.ui.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.delizarov.smartdiet.presentation.start.StartPresenter;
import com.delizarov.smartdiet.presentation.start.StartView;

import javax.inject.Inject;

public class StartActivity extends BaseActivity implements StartView {

    private static final int PERMISSION_REQUEST_INTERNET = 1;

    @Inject
    StartPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getApplicationComponent().inject(StartActivity.this);

        presenter.attachView(StartActivity.this);
        presenter.onStartViewCreated();
    }

    @Override
    public void showInternetPermissionsDialog() {

        ActivityCompat.requestPermissions(StartActivity.this,
                new String[]{Manifest.permission.INTERNET},
                PERMISSION_REQUEST_INTERNET);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        presenter.onRequestPermissionResult();
    }
}
