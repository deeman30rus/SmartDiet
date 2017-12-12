package com.delizarov.smartdiet.ui.viewholders;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.delizarov.smartdiet.R;
import com.delizarov.smartdiet.domain.models.Recipe;

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

    public RecipeViewHolder(int layoutResource, final ViewGroup parent) {

        super(LayoutInflater.from(parent.getContext()).inflate(layoutResource, parent, false));

        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(Recipe recipe) {

        title.setText(recipe.getTitle());

        recipePic.setImageResource(R.drawable.pancakes_pic);

        description.setText(recipe.getDescription());

        cookTime.setText(minutesText(recipe.getCookTime()));

        calories.setText(String.format(Locale.getDefault(), "%d ккал", recipe.getCookTime()));
    }

    private static String minutesText(int minutes) {

        if (minutes == 1)
            return "1 минута";

        int r10 = minutes % 10;
        int r100 = minutes & 100;

        if (r100 >= 5 && r100 < 20)
            return String.format(Locale.getDefault(), "%d минут", minutes);

        return String.format(Locale.getDefault(), "%d %s", minutes, (r10 < 5 ? "минуты" : "минут"));

    }
}
