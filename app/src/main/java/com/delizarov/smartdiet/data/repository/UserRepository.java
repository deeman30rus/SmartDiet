package com.delizarov.smartdiet.data.repository;

import com.delizarov.smartdiet.domain.models.User;

/**
 * Интерфейс репозитория данных о пользователе
 * */
public interface UserRepository {

    User getUserInfo();
}
