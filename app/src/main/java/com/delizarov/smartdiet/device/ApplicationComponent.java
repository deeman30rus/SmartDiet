package com.delizarov.smartdiet.device;


import com.delizarov.smartdiet.data.DataRepositoryModule;
import com.delizarov.smartdiet.ui.activities.LoginActivity;
import com.delizarov.smartdiet.ui.activities.StartActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
    modules = {
            ApplicationModule.class,
            DataRepositoryModule.class
    }
)
public interface ApplicationComponent {

    void inject(SmartDietApplication application);

    void inject(StartActivity startActivity);

    void inject(LoginActivity loginActivity);
}