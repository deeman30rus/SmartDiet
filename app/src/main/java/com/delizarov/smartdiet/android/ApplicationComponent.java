package com.delizarov.smartdiet.android;


import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
    modules = ApplicationModule.class
)
public interface ApplicationComponent {

    void inject(SmartDietApplication application);
}
