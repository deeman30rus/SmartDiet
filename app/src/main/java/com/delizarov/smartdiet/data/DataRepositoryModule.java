package com.delizarov.smartdiet.data;

import com.delizarov.smartdiet.data.repository.AppRepository;
import com.delizarov.smartdiet.data.repository.impl.AppRepositoryImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class DataRepositoryModule {

    @Provides
    AppRepository provideAppRepository(AppRepositoryImpl appRepository) {
        return appRepository;
    }
}
