package com.delizarov.smartdiet.ui.activities;


import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
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

    public static final String COLOR_SHADE = "COLOR_SHADE";
    public static final String RECIPE_ID = "RecipeId";

    @Inject
    RecipeDetailsPresenter presenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.image)
    ImageView recipeImage;

    @BindView(R.id.tabs)
    ViewPager tabs;

    @BindView(R.id.tab_header)
    TabLayout headers;

    private Recipe recipe;

    private MaterialShade primaryShade;

    private final Tab[] TABS = {
            new Tab<RecipeDetailsFragment>(R.string.recipe_details_tab_info_title, R.drawable.icon_info_grey_500) {
                @Override
                public RecipeDetailsFragment fragment() {

                    assert recipe != null;

                    Bundle args = new Bundle();

                    args.putSerializable(COLOR_SHADE, primaryShade);

                    RecipeDetailsFragment fragment = RecipeDetailsInfoFragment.newInstance();
                    fragment.setRecipe(recipe);

                    fragment.setArguments(args);

                    return fragment;
                }
            },
            new Tab<RecipeDetailsFragment>(R.string.recipe_details_tab_ingredients_title, R.drawable.icon_apple_grey_500) {
                @Override
                public RecipeDetailsFragment fragment() {

                    assert recipe != null;

                    RecipeDetailsFragment fragment = RecipeDetailsIngredientsFragment.newInstance();
                    fragment.setRecipe(recipe);

                    return fragment;
                }
            },
            new Tab<RecipeDetailsFragment>(R.string.recipe_details_tab_directions_title, R.drawable.icon_actions_grey_500) {
                @Override
                public RecipeDetailsFragment fragment() {
                    assert recipe != null;

                    RecipeDetailsFragment fragment = RecipeDetailsDirectionsFragment.newInstance();
                    fragment.setRecipe(recipe);

                    return fragment;
                }
            }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        // т.к. предполагается применять тему, она должна примениться перед вызовом метода super.onCreate, поэтому важен порядок инициализации

        Intent intent = getIntent();
        assert intent != null;

        primaryShade = (MaterialShade) intent.getSerializableExtra(COLOR_SHADE);

        int resId = getSuitableThemeId(primaryShade);
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

        this.recipe = recipe;

        toolbar.setTitle(recipe.getTitle());

        displayPicture(recipe.getMainPicture());

        setSupportActionBar(toolbar);

        tabs.setAdapter(new RecipePagerAdapter(getSupportFragmentManager()));

        headers.setupWithViewPager(tabs);

        for (int i = 0; i < TABS.length; ++i)
            headers.getTabAt(i).setIcon(TABS[i].getIconResourceId()); // вызывать после установки адаптера иначе NPE

        setIconColor(headers.getTabAt(0), primaryShade.getColor());

        headers.addOnTabSelectedListener(new TabSelectedListener(tabs));

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

            return null;
        }
    }

    private class TabSelectedListener extends TabLayout.ViewPagerOnTabSelectedListener {

        private final int selectedColor = primaryShade.getColor();
        private final int unselectedColor = RecipeDetailsActivity.this.getResources().getColor(R.color.grey_500);

        public TabSelectedListener(ViewPager viewPager) {
            super(viewPager);
        }

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            super.onTabSelected(tab);

            setIconColor(tab, selectedColor);
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
            super.onTabUnselected(tab);

            setIconColor(tab, unselectedColor);
        }
    }

    private static void setIconColor(TabLayout.Tab tab, int color) {

        if (tab.getIcon() == null)
            return;

        tab.getIcon().setColorFilter(color, PorterDuff.Mode.SRC_IN);
    }

    private int getSuitableThemeId(MaterialShade primaryColor) {

        return primaryColor == MaterialShade.Red500 ? R.style.RedTheme :
               primaryColor == MaterialShade.Pink500 ? R.style.PinkTheme :
               primaryColor == MaterialShade.Purple500 ? R.style.PurpleTheme :
               primaryColor == MaterialShade.DeepPurple500 ? R.style.DeepOrangeTheme :
               primaryColor == MaterialShade.Indigo500 ? R.style.IndigoTheme :
               primaryColor == MaterialShade.Blue500 ? R.style.BlueTheme :
               primaryColor == MaterialShade.lightBlue500 ? R.style.LightBlueTheme :
               primaryColor == MaterialShade.Cyan500 ? R.style.CyanTheme :
               primaryColor == MaterialShade.Teal500 ? R.style.TealTheme :
               primaryColor == MaterialShade.Green500 ? R.style.GreenTheme :
               primaryColor == MaterialShade.LightGreen500 ? R.style.LightGreenTheme :
               primaryColor == MaterialShade.Lime500 ? R.style.LimeTheme :
               primaryColor == MaterialShade.Yellow500 ? R.style.YellowTheme :
               primaryColor == MaterialShade.Amber500 ? R.style.AmberTheme :
               primaryColor == MaterialShade.Orange500 ? R.style.OrangeTheme :
               primaryColor == MaterialShade.DeepOrange500 ? R.style.DeepOrangeTheme :
               primaryColor == MaterialShade.Brown500 ? R.style.BrownTheme : R.style.BlueGreyTheme;

    }
}
