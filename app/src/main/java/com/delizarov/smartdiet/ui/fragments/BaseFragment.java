package com.delizarov.smartdiet.ui.fragments;


import com.delizarov.smartdiet.android.ApplicationComponent;
import com.delizarov.smartdiet.android.SmartDietApplication;

public class BaseFragment extends android.support.v4.app.Fragment {

    /**
     * Возвращает общий для приложения объект {@link ApplicationComponent}
     */
    protected ApplicationComponent getApplicationComponent() {
        SmartDietApplication applicationContext = (SmartDietApplication) getActivity().getApplicationContext();
        return applicationContext.getApplicationComponent();
    }
}
