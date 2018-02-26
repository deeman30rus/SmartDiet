package com.delizarov.smartdiet.ui.models;


import com.delizarov.smartdiet.ui.fragments.BaseFragment;

/**
 * Вспомогательный класс для закладок ViewPager'а
 */
public abstract class Tab<T extends BaseFragment> {

    private final int mTitleResourceId;
    private final int mIconResourceId;

    public Tab(int mTitleResourceId, int iconResourceId) {
        this.mTitleResourceId = mTitleResourceId;
        mIconResourceId = iconResourceId;
    }

    public abstract T fragment();

    public int getTitleResourceId() {

        return mTitleResourceId;
    }

    public int getIconResourceId() {
        return mIconResourceId;
    }
}
