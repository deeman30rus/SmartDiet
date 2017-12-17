package com.delizarov.smartdiet.ui.viewholders;


import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.delizarov.smartdiet.R;
import com.delizarov.smartdiet.domain.models.Recipe;
import com.delizarov.smartdiet.domain.models.Unit;
import com.delizarov.smartdiet.utils.ColorUtils;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeIngredientViewHolder extends ViewHolderBase<Recipe.Ingredient>{

    @BindView(R.id.marker)
    AppCompatImageView marker;

    @BindView(R.id.ingredient_name)
    TextView ingredientName;

    @BindView(R.id.ingredient_amount)
    TextView ingredientAmount;

    @BindView(R.id.ingredient_units)
    TextView ingredientUnits;

    public RecipeIngredientViewHolder(int layoutResource, final ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(layoutResource, parent, false));

        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(Recipe.Ingredient ingredient) {

        int color = ColorUtils.decodeFromString(ingredient.Grocery.getName());

        Drawable background = marker.getBackground();

        if (background instanceof ShapeDrawable) {
            ((ShapeDrawable) background).getPaint().setColor(color);
        } else if (background instanceof GradientDrawable) {
            ((GradientDrawable) background).setColor(color);
        }

        ingredientName.setText(ingredient.Grocery.getName());

        if (ingredient.Unit == Unit.Optional) {
            ingredientAmount.setText("");
        } else {

            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(2);
            df.setMinimumFractionDigits(0);
            df.setGroupingUsed(false);

            ingredientAmount.setText(df.format(ingredient.Amount));
        }

        displayUnits(ingredient.Unit);
    }

    private void displayUnits(Unit unit) {

        String unitStr = unit == Unit.Kilo ? "кг." :
                unit == Unit.Gram  ? "г." :
                        unit  == Unit.Liter ? "л." :
                                unit == Unit.MilliLiter ? "мл." :
                                        unit == Unit.TeaSpoon ? "ч. л." :
                                                unit == Unit.TableSpoon ? "ст. л." :
                                                        unit == Unit.Piece ? "шт." : "по вусу";

        ingredientUnits.setText(unitStr);

    }
}
