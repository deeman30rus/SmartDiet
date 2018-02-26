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
import com.delizarov.smartdiet.domain.models.Grocery;
import com.delizarov.smartdiet.utils.ColorUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GroceryViewHolder extends ViewHolderBase<Grocery> {

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

    public GroceryViewHolder(int layoutResource, final ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(layoutResource, parent, false));

        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(Grocery grocery) {

        item.setShowMode(SwipeLayout.ShowMode.PullOut);
        item.addDrag(SwipeLayout.DragEdge.Left, delete);

        name.setText(grocery.getName());

        int color = ColorUtils.decodeFromString(grocery.getName());

        Drawable background = marker.getBackground();

        if (background instanceof ShapeDrawable) {
            ((ShapeDrawable) background).getPaint().setColor(color);
        } else if (background instanceof GradientDrawable) {
            ((GradientDrawable) background).setColor(color);
        }

        itemContainer.setOnClickListener(view -> {
            if (mOnItemClickListener != null)
                mOnItemClickListener.onItemClicked(grocery);
        });

        delete.setOnClickListener(view -> {
            if (mOnItemDeleteClickListener != null)
                mOnItemDeleteClickListener.onItemDeleteClicked(grocery);
        });
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemDeleteClickListener(OnItemDeleteClickListener onItemDeleteClickListener) {
        mOnItemDeleteClickListener = onItemDeleteClickListener;
    }

    public interface OnItemClickListener {

        void onItemClicked(Grocery grocery);
    }

    public interface OnItemDeleteClickListener {

        void onItemDeleteClicked(Grocery grocery);
    }
}
