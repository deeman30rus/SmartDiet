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
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.List;
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

    @BindView(R.id.chart)
    PieChart pieChart;

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

    private float proteins = 30.f;
    private float tryg = 12.f;
    private float carbo = 40.5f;

    private int indigo300 = 0xff7986CB;
    private int indigo500 = 0xff3F51B5;
    private int indigo700 = 0xff303F9F;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recipe_details_info, container, false);

        mUnbinder = ButterKnife.bind(this, view);

        description.setText(mRecipe.getDescription());

        showTags();

        List<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(proteins, "белки"));
        entries.add(new PieEntry(carbo, "жиры"));
        entries.add(new PieEntry(tryg, "углеводы"));

        PieDataSet set = new PieDataSet(entries, "Election Results");
        set.setColors(indigo300, indigo500, indigo700);
        set.setValueTextSize(18);
        set.setValueTextColor(0xffffffff);

        PieData data = new PieData(set);

        pieChart.setData(data);
        pieChart.setCenterText("300 ккал");
        pieChart.setCenterTextSize(24f);
        pieChart.setDescription(null);
        pieChart.setHighlightPerTapEnabled(false);

        pieChart.invalidate(); // refresh

//        calories.setText(String.format(Locale.getDefault(), "Калорийность: %d ккал", mRecipe.getCalories().intValue()));
//
//        proteins.setText(String.format(Locale.getDefault(), "Белки: %f г", mRecipe.getProteins().doubleValue()));
//        triglycerides.setText(String.format(Locale.getDefault(), "Жиры: %f г", mRecipe.getTriglycerides().doubleValue()));
//        carbohydrates.setText(String.format(Locale.getDefault(), "Углеводы: %f г", mRecipe.getCarbohydrates().doubleValue()));

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
