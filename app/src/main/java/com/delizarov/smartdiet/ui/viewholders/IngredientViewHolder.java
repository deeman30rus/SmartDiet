package com.delizarov.smartdiet.ui.viewholders;


import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.delizarov.smartdiet.R;
import com.delizarov.smartdiet.domain.models.Ingredient;

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
    }
}
