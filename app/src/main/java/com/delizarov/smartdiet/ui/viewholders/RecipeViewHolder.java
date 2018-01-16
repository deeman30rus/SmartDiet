package com.delizarov.smartdiet.ui.viewholders;


import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.delizarov.smartdiet.R;
import com.delizarov.smartdiet.domain.models.Recipe;
import com.delizarov.smartdiet.utils.StringResourceUtils;

import java.io.IOException;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeViewHolder extends ViewHolderBase<Recipe> {

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.recipe_pic)
    ImageView recipePic;

    @BindView(R.id.description)
    TextView description;

    @BindView(R.id.cook_time)
    TextView cookTime;

    @BindView(R.id.calories)
    TextView calories;

    @BindView(R.id.container)
    ConstraintLayout container;

    private Context mContext;

    public RecipeViewHolder(Context context, int layoutResource, final ViewGroup parent) {

        super(LayoutInflater.from(parent.getContext()).inflate(layoutResource, parent, false));

        mContext = context;

        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(Recipe recipe) {

        title.setText(recipe.getTitle());

        setPicture(recipe.getMainPicture());

        description.setText(recipe.getDescription());

        cookTime.setText(StringResourceUtils.getMinutesString(mContext, recipe.getCookTime()));

        calories.setText(StringResourceUtils.getCaloriesString(mContext, recipe.getCalories().intValue()));
    }

    private void setPicture(String pictureURI) {

        AssetManager am = mContext.getAssets();

        try {
            Drawable drawable = Drawable.createFromStream(am.open(pictureURI), null);

            recipePic.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
