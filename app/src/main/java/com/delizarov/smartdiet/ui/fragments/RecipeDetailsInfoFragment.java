package com.delizarov.smartdiet.ui.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.delizarov.smartdiet.R;
import com.delizarov.smartdiet.ui.activities.RecipeDetailsActivity;
import com.google.android.flexbox.FlexboxLayout;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fisk.chipcloud.ChipCloud;
import fisk.chipcloud.ChipCloudConfig;

public class RecipeDetailsInfoFragment extends RecipeDetailsFragment {

    @BindView(R.id.description)
    TextView description;

    @BindView(R.id.tags)
    FlexboxLayout tags;

    @BindView(R.id.calories)
    TextView calories;

    @BindView(R.id.proteins)
    TextView proteins;

    @BindView(R.id.triglycerides)
    TextView triglycerides;

    @BindView(R.id.carbohydrates)
    TextView carbohydrates;

    private Unbinder mUnbinder;

    private int mPrimaryColor;

    public static RecipeDetailsInfoFragment newInstance() {

        return new RecipeDetailsInfoFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();

        mPrimaryColor = args.getInt(RecipeDetailsActivity.PRIMARY_COLOR);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recipe_details_info, container, false);

        mUnbinder = ButterKnife.bind(this, view);

        description.setText(mRecipe.getDescription());

        showTags();

        calories.setText(String.format(Locale.getDefault(), "Калорийность: %d ккал", mRecipe.getCalories().intValue()));

        proteins.setText(String.format(Locale.getDefault(), "Белки: %f г", mRecipe.getProteins().doubleValue()));
        triglycerides.setText(String.format(Locale.getDefault(), "Жиры: %f г", mRecipe.getTriglycerides().doubleValue()));
        carbohydrates.setText(String.format(Locale.getDefault(), "Углеводы: %f г", mRecipe.getCarbohydrates().doubleValue()));

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mUnbinder.unbind();
    }

    private void showTags() {

        ChipCloudConfig config = new ChipCloudConfig()
                .selectMode(ChipCloud.SelectMode.none)
                .uncheckedChipColor(0xff000000 | mPrimaryColor)
                .uncheckedTextColor(Color.parseColor("#ffffff"))
                .useInsetPadding(true);

        ChipCloud chipCloud = new ChipCloud(getActivity().getApplicationContext(), tags, config);

        for (String tag : mRecipe.getTags())
            chipCloud.addChip(tag);
    }
}
