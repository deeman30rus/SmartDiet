package com.delizarov.smartdiet.device;


import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Context provideApplicationContext() {

        return mApplication;
    }
}
