package com.delizarov.smartdiet.android;


import android.app.Application;
import android.content.Context;

import com.delizarov.smartdiet.data.db.AppDatabase;

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

    @Provides
    AppDatabase provideAppDatabase() {

        return ((SmartDietApplication) mApplication).getDb();
    }
}
