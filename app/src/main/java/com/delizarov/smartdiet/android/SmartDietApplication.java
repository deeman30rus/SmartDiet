package com.delizarov.smartdiet.android;


import android.app.Application;
import android.arch.persistence.room.Room;

import com.delizarov.smartdiet.data.db.AppDatabase;
import com.delizarov.smartdiet.data.db.entities.IngredientEntity;
import com.delizarov.smartdiet.domain.models.Ingredient;
import com.facebook.stetho.Stetho;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.inject.Inject;

public class SmartDietApplication extends Application {

    private static final String DB_NAME = "smart-diet-db";

    private ApplicationComponent mApplicationComponent;
    private AppDatabase mDb;

    @Inject
    public SmartDietApplication() {
        super();
    }

    @Override
    public void onCreate() {

        super.onCreate();

        Stetho.initializeWithDefaults(this);

        mDb = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, DB_NAME).build();

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

    public AppDatabase getDb() {
        return mDb;
    }
}
