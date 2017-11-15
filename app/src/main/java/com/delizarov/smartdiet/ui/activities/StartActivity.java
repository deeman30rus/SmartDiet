package com.delizarov.smartdiet.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import com.delizarov.smartdiet.domain.models.User;
import com.delizarov.smartdiet.presentation.start.StartPresenter;
import com.delizarov.smartdiet.presentation.start.StartView;

import java.util.List;

import javax.inject.Inject;

public class StartActivity extends BaseActivity implements StartView {

    private static final int PERMISSIONS_REQUEST = 1;

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
    public void showPermissionsDialog(List<String> permissions) {

        ActivityCompat.requestPermissions(StartActivity.this,
                permissions.toArray(new String[0]),
                PERMISSIONS_REQUEST);
    }

    @Override
    public void showLoginView() {

        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);

        startActivity(intent);
        finish();
    }

    @Override
    public void showDailyMealsView(User user) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSIONS_REQUEST)
            presenter.onRequestPermissionResult();
    }
}
