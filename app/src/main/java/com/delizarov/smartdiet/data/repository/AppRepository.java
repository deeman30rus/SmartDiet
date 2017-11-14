package com.delizarov.smartdiet.data.repository;

import java.util.List;

/**
 * Интерфес репозитория данных приложения
 * тут находится все платформозависимая информация.
 */
public interface AppRepository {

    boolean checkInternetAccessPermissionGranted();

    boolean checkCameraPermissionGranted();

    /**
     * Возвращает список требуемых разрешений, которые не были получены
     */
    List<String> getRequiredNotGrantedPermissions();
}
