package com.delizarov.smartdiet.ui.viewholders;


import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.delizarov.smartdiet.R;
import com.delizarov.smartdiet.domain.models.Ingredient;
import com.delizarov.smartdiet.utils.ColorUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientViewHolder extends ViewHolderBase<Ingredient> {

    @BindView(R.id.item)
    SwipeLayout item;

    @BindView(R.id.delete_section)
    FrameLayout delete;

    @BindView(R.id.item_container)
    ConstraintLayout itemContainer;

    @BindView(R.id.marker)
    AppCompatImageView marker;

    @BindView(R.id.name)
    TextView name;

    private OnItemClickListener mOnItemClickListener;
    private OnItemDeleteClickListener mOnItemDeleteClickListener;

    public IngredientViewHolder(int layoutResource, final ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(layoutResource, parent, false));

        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(Ingredient ingredient) {

        item.setShowMode(SwipeLayout.ShowMode.PullOut);
        item.addDrag(SwipeLayout.DragEdge.Left, delete);

        name.setText(ingredient.getName());

        int color = ColorUtils.decodeFromString(ingredient.getName());

        Drawable background = marker.getBackground();

        if (background instanceof ShapeDrawable) {
            ((ShapeDrawable) background).getPaint().setColor(color);
        } else if (background instanceof GradientDrawable) {
            ((GradientDrawable) background).setColor(color);
        }

        itemContainer.setOnClickListener(view -> {
            if (mOnItemClickListener != null)
                mOnItemClickListener.onItemClicked(ingredient);
        });

        delete.setOnClickListener(view -> {
            if (mOnItemDeleteClickListener != null)
                mOnItemDeleteClickListener.onItemDeleteClicked(ingredient);
        });
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemDeleteClickListener(OnItemDeleteClickListener onItemDeleteClickListener) {
        mOnItemDeleteClickListener = onItemDeleteClickListener;
    }

    public interface OnItemClickListener {

        void onItemClicked(Ingredient ingredient);
    }

    public interface OnItemDeleteClickListener {

        void onItemDeleteClicked(Ingredient ingredient);
    }
}
