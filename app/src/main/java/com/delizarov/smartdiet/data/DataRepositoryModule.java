package com.delizarov.smartdiet.data;

import com.delizarov.smartdiet.data.repository.AppRepository;
import com.delizarov.smartdiet.data.repository.CookbookRepository;
import com.delizarov.smartdiet.data.repository.UserRepository;
import com.delizarov.smartdiet.data.repository.impl.AppRepositoryImpl;
import com.delizarov.smartdiet.data.repository.impl.CookbookRepositoryImpl;
import com.delizarov.smartdiet.data.repository.impl.UserRepositoryImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class DataRepositoryModule {

    @Provides
    AppRepository provideAppRepository(AppRepositoryImpl appRepository) {
        return appRepository;
    }

    @Provides
    UserRepository provideUserRepository(UserRepositoryImpl userRepository) {
        return userRepository;
    }

    @Provides
    CookbookRepository provideCookbookRepository(CookbookRepositoryImpl cookbookRepository) {

        return cookbookRepository;
    }
}
