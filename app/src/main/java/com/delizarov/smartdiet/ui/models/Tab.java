package com.delizarov.smartdiet.ui.models;


import android.support.v4.app.Fragment;

import com.delizarov.smartdiet.ui.fragments.BaseFragment;

/**
 * Вспомогательный класс для закладок ViewPager'а
 */
public abstract class Tab<T extends BaseFragment> {

    private final int mTitleResourceId;

    public Tab(int mTitleResourceId) {
        this.mTitleResourceId = mTitleResourceId;
    }

    public abstract T fragment();

    public int getTitleResourceId() {

        return mTitleResourceId;
    }
}
