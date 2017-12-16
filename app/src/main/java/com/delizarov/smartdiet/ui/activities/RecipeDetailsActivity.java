package com.delizarov.smartdiet.ui.activities;


import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;

import com.delizarov.smartdiet.R;
import com.delizarov.smartdiet.domain.models.Recipe;
import com.delizarov.smartdiet.presentation.recipedetails.RecipeDetailsPresenter;
import com.delizarov.smartdiet.presentation.recipedetails.RecipeDetailsView;

import java.io.IOException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDetailsActivity extends BaseActivity implements RecipeDetailsView {

    public static final String THEME_RESOURCE = "ThemeResource";
    public static final String RECIPE_ID = "RecipeId";

    @Inject
    RecipeDetailsPresenter presenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.image)
    ImageView recipeImage;

    @BindView(R.id.tabs)
    ViewPager tabs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        // т.к. предполагается применять тему, она должна примениться перед вызовом метода super.onCreate, поэтому важен порядок инициализации

        Intent intent = getIntent();
        assert intent != null;

        int resId = intent.getIntExtra(THEME_RESOURCE, R.style.IndigoTheme); // TODO: Тема по умолчанию
        long recipeId = intent.getLongExtra(RECIPE_ID, -1);

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

        toolbar.setTitle(recipe.getTitle());

        displayPicture(recipe.getMainPicture());

        setSupportActionBar(toolbar);
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
}
