package com.delizarov.smartdiet.ui.activities;


import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.delizarov.smartdiet.R;
import com.delizarov.smartdiet.domain.models.Recipe;
import com.delizarov.smartdiet.presentation.recipedetails.RecipeDetailsPresenter;
import com.delizarov.smartdiet.presentation.recipedetails.RecipeDetailsView;
import com.delizarov.smartdiet.ui.fragments.RecipeDetailsDirectionsFragment;
import com.delizarov.smartdiet.ui.fragments.RecipeDetailsInfoFragment;
import com.delizarov.smartdiet.ui.fragments.RecipeDetailsIngredientsFragment;
import com.delizarov.smartdiet.ui.fragments.RecipeDetailsFragment;
import com.delizarov.smartdiet.ui.models.Tab;
import com.delizarov.smartdiet.utils.models.MaterialShade;

import java.io.IOException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDetailsActivity extends BaseActivity implements RecipeDetailsView {

    public static final String THEME_RESOURCE = "ThemeResource";
    public static final String PRIMARY_COLOR = "PrimaryColor";
    public static final String RECIPE_ID = "RecipeId";

    @Inject
    RecipeDetailsPresenter presenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.image)
    ImageView recipeImage;

    @BindView(R.id.tabs)
    ViewPager tabs;

    private Recipe mRecipe;

    private int mPrimaryColor;

    private final Tab[] TABS = {
            new Tab<RecipeDetailsFragment>(R.string.recipe_details_tab_info_title) {
                @Override
                public RecipeDetailsFragment fragment() {

                    assert mRecipe != null;

                    Bundle args = new Bundle();

                    args.putInt(PRIMARY_COLOR, mPrimaryColor);

                    RecipeDetailsFragment fragment = RecipeDetailsInfoFragment.newInstance();
                    fragment.setRecipe(mRecipe);

                    fragment.setArguments(args);

                    return fragment;
                }
            },
            new Tab<RecipeDetailsFragment>(R.string.recipe_details_tab_ingredients_title) {
                @Override
                public RecipeDetailsFragment fragment() {

                    assert mRecipe != null;

                    RecipeDetailsFragment fragment = RecipeDetailsIngredientsFragment.newInstance();
                    fragment.setRecipe(mRecipe);

                    return fragment;
                }
            },
            new Tab<RecipeDetailsFragment>(R.string.recipe_details_tab_directions_title) {
                @Override
                public RecipeDetailsFragment fragment() {
                    assert mRecipe != null;

                    RecipeDetailsFragment fragment = RecipeDetailsDirectionsFragment.newInstance();
                    fragment.setRecipe(mRecipe);

                    return fragment;
                }
            }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        // т.к. предполагается применять тему, она должна примениться перед вызовом метода super.onCreate, поэтому важен порядок инициализации

        Intent intent = getIntent();
        assert intent != null;

        int resId = intent.getIntExtra(THEME_RESOURCE, R.style.IndigoTheme); // TODO: Тема по умолчанию
        long recipeId = intent.getLongExtra(RECIPE_ID, -1);

        mPrimaryColor = intent.getIntExtra(PRIMARY_COLOR, R.color.indigo_500);

        getApplicationComponent().inject(this);

        presenter.attachView(RecipeDetailsActivity.this);
        presenter.beforeViewDisplayed(resId);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        ButterKnife.bind(this);

        presenter.onViewCreated(recipeId);
    }

    @Override
    public void applyThemeResource(int resId) {

        setTheme(resId);
    }

    @Override
    public void displayRecipe(Recipe recipe) {

        mRecipe = recipe;

        toolbar.setTitle(recipe.getTitle());

        displayPicture(recipe.getMainPicture());

        setSupportActionBar(toolbar);

        tabs.setAdapter(new RecipePagerAdapter(getSupportFragmentManager()));
    }

    private void displayPicture(String pictureURI) {

        AssetManager am = getAssets();

        try {
            Drawable drawable = Drawable.createFromStream(am.open(pictureURI), null);

            recipeImage.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class RecipePagerAdapter extends FragmentPagerAdapter {

        public RecipePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return TABS[position].fragment();
        }

        @Override
        public int getCount() {

            return TABS.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            Context context = getApplicationContext();

            return context.getResources().getString(TABS[position].getTitleResourceId());
        }
    }

}
