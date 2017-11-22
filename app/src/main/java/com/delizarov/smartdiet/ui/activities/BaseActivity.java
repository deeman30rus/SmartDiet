package com.delizarov.smartdiet.ui.activities;


import android.support.v7.app.AppCompatActivity;

import com.delizarov.smartdiet.android.ApplicationComponent;
import com.delizarov.smartdiet.android.SmartDietApplication;

public class BaseActivity extends AppCompatActivity {

    /**
     * Возвращает общий для приложения объект {@link ApplicationComponent}
     */
    protected ApplicationComponent getApplicationComponent() {
        SmartDietApplication application = (SmartDietApplication) getApplicationContext();

        return application.getApplicationComponent();
    }
}
