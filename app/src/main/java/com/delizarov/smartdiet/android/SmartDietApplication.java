package com.delizarov.smartdiet.android;


import android.app.Application;

import com.facebook.stetho.Stetho;

import javax.inject.Inject;

public class SmartDietApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Inject
    public SmartDietApplication() {
        super();
    }

    @Override
    public void onCreate() {

        super.onCreate();

        Stetho.initializeWithDefaults(this);

        initializeInjector();

        getApplicationComponent().inject(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    private void initializeInjector() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }
}
