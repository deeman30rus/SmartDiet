package com.delizarov.smartdiet.data.repository;

/**
 * Интерфес репозитория данных приложения
 * тут находится все платформозависимая информация.
 */
public interface AppRepository {

    boolean checkInternetAccessPermissionGranted();
}
