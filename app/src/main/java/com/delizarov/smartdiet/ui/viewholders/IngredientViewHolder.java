package com.delizarov.smartdiet.ui.viewholders;


import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.delizarov.smartdiet.R;
import com.delizarov.smartdiet.domain.models.Ingredient;
import com.delizarov.smartdiet.utils.ColorUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientViewHolder extends ViewHolderBase<Ingredient> {

    @BindView(R.id.marker)
    AppCompatImageView marker;

    @BindView(R.id.name)
    TextView name;

    public IngredientViewHolder(int layoutResource, final ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(layoutResource, parent, false));

        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(Ingredient ingredient) {

        name.setText(ingredient.getName());

        int color = ColorUtils.decodeFromString(ingredient.getName());

        Drawable background = marker.getBackground();

        if (background instanceof ShapeDrawable) {
            ((ShapeDrawable) background).getPaint().setColor(color);
        } else if (background instanceof GradientDrawable) {
            ((GradientDrawable) background).setColor(color);
        }
    }
}
