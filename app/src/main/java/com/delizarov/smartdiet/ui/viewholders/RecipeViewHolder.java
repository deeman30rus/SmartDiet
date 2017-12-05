package com.delizarov.smartdiet.ui.viewholders;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.delizarov.smartdiet.R;
import com.delizarov.smartdiet.domain.models.Recipe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeViewHolder extends ViewHolderBase<Recipe> {

    @BindView(R.id.title)
    TextView title;

    public RecipeViewHolder(int layoutResource, final ViewGroup parent) {

        super(LayoutInflater.from(parent.getContext()).inflate(layoutResource, parent, false));

        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(Recipe recipe) {

        title.setText(recipe.getTitle());
    }
}
