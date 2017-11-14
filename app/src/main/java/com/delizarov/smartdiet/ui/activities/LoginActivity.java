package com.delizarov.smartdiet.ui.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getApplicationComponent().inject(LoginActivity.this);
    }
}
