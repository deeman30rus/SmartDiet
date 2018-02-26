package com.delizarov.smartdiet.ui.viewholders;


import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.delizarov.smartdiet.R;
import com.delizarov.smartdiet.domain.models.Recipe;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDirectionViewHolder extends ViewHolderBase<Recipe.Direction> {

    @BindView(R.id.direction_order)
    TextView directionOrder;

    @BindView(R.id.direction_action)
    TextView directionAction;

    public RecipeDirectionViewHolder(int layoutResource, final ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(layoutResource, parent, false));

        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(Recipe.Direction direction) {

        directionOrder.setText(String.format(Locale.getDefault(), "%d.", direction.Order));

        directionAction.setText(direction.Action);
    }
}
