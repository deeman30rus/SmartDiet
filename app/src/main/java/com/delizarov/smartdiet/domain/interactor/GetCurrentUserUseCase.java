package com.delizarov.smartdiet.domain.interactor;


import com.delizarov.smartdiet.data.repository.UserRepository;
import com.delizarov.smartdiet.domain.models.User;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetCurrentUserUseCase extends UseCase<User, Void> {

    private final UserRepository mUserRepository;

    @Inject
    public GetCurrentUserUseCase(UserRepository userRepository) {
        mUserRepository = userRepository;
    }

    @Override
    protected Observable<User> createObservable(Void aVoid) {
        return Observable.just(mUserRepository.getUserInfo());
    }
}
