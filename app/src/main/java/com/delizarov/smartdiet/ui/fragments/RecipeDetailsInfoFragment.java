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
import com.delizarov.smartdiet.utils.models.MaterialShade;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.Arrays;
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

    private Unbinder unbinder;

    MaterialShade primaryColor;

    public static RecipeDetailsInfoFragment newInstance() {

        return new RecipeDetailsInfoFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();

        primaryColor = (MaterialShade) args.getSerializable(RecipeDetailsActivity.COLOR_SHADE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recipe_details_info, container, false);

        unbinder = ButterKnife.bind(this, view);

        description.setText(recipe.getDescription());

        showTags();

        List<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(recipe.getProteins().floatValue(), "белки"));
        entries.add(new PieEntry(recipe.getTriglycerides().floatValue(), "жиры"));
        entries.add(new PieEntry(recipe.getCarbohydrates().floatValue(), "углеводы"));

        PieDataSet set = new PieDataSet(entries, "Пищевая ценность");
        set.setColors(getColorPalette(primaryColor));
        set.setValueTextSize(18);
        set.setValueTextColor(0xffffffff);

        PieData data = new PieData(set);

        pieChart.setData(data);
        pieChart.setCenterText(String.format(Locale.getDefault(), "%d ккал", recipe.getCalories().intValue()));
        pieChart.setCenterTextSize(24f);
        pieChart.setDescription(null);
        pieChart.setHighlightPerTapEnabled(false);
        pieChart.getLegend().setEnabled(false);

        pieChart.invalidate(); // refresh

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        unbinder.unbind();
    }

    private void showTags() {

        ChipCloudConfig config = new ChipCloudConfig()
                .selectMode(ChipCloud.SelectMode.none)
                .uncheckedChipColor(primaryColor.getColor())
                .uncheckedTextColor(Color.parseColor("#ffffff"))
                .useInsetPadding(true);

        ChipCloud chipCloud = new ChipCloud(getActivity().getApplicationContext(), tags, config);

        for (String tag : recipe.getTags())
            chipCloud.addChip(tag);
    }

    private List<Integer> getColorPalette(MaterialShade mainColor) {

        List<Integer> colors = null;
        if (mainColor == MaterialShade.Red500) {
            colors = Arrays.asList(getResourceColor(R.color.red_300), getResourceColor(R.color.red_500), getResourceColor(R.color.red_700));
        } else if (mainColor == MaterialShade.Pink500) {
            colors = Arrays.asList(getResourceColor(R.color.pink_300), getResourceColor(R.color.pink_500), getResourceColor(R.color.pink_700));
        } else if (mainColor == MaterialShade.Purple500) {
            colors = Arrays.asList(getResourceColor(R.color.purple_300), getResourceColor(R.color.purple_500), getResourceColor(R.color.purple_700));
        } else if (mainColor == MaterialShade.DeepPurple500) {
            colors = Arrays.asList(getResourceColor(R.color.deep_purple_300), getResourceColor(R.color.deep_purple_500), getResourceColor(R.color.deep_purple_700));
        } else if (mainColor == MaterialShade.Indigo500) {
            colors = Arrays.asList(getResourceColor(R.color.indigo_300), getResourceColor(R.color.indigo_500), getResourceColor(R.color.indigo_700));
        } else if (mainColor == MaterialShade.Blue500) {
            colors = Arrays.asList(getResourceColor(R.color.blue_300), getResourceColor(R.color.blue_500), getResourceColor(R.color.blue_700));
        } else if (mainColor == MaterialShade.lightBlue500) {
            colors = Arrays.asList(getResourceColor(R.color.light_blue_300), getResourceColor(R.color.light_blue_500), getResourceColor(R.color.light_blue_700));
        } else if (mainColor == MaterialShade.Cyan500) {
            colors = Arrays.asList(getResourceColor(R.color.cyan_300), getResourceColor(R.color.cyan_500), getResourceColor(R.color.cyan_700));
        } else if (mainColor == MaterialShade.Teal500) {
            colors = Arrays.asList(getResourceColor(R.color.teal_300), getResourceColor(R.color.teal_500), getResourceColor(R.color.teal_700));
        } else if (mainColor == MaterialShade.Green500) {
            colors = Arrays.asList(getResourceColor(R.color.green_300), getResourceColor(R.color.green_500), getResourceColor(R.color.green_700));
        } else if (mainColor == MaterialShade.LightGreen500) {
            colors = Arrays.asList(getResourceColor(R.color.light_green_300), getResourceColor(R.color.light_green_500), getResourceColor(R.color.light_green_700));
        } else if (mainColor == MaterialShade.Lime500) {
            colors = Arrays.asList(getResourceColor(R.color.lime_300), getResourceColor(R.color.lime_500), getResourceColor(R.color.lime_700));
        } else if (mainColor == MaterialShade.Yellow500) {
            colors = Arrays.asList(getResourceColor(R.color.yellow_300), getResourceColor(R.color.yellow_500), getResourceColor(R.color.yellow_700));
        } else if (mainColor == MaterialShade.Amber500) {
            colors = Arrays.asList(getResourceColor(R.color.amber_300), getResourceColor(R.color.amber_500), getResourceColor(R.color.amber_700));
        } else if (mainColor == MaterialShade.Orange500) {
            colors = Arrays.asList(getResourceColor(R.color.orange_300), getResourceColor(R.color.orange_500), getResourceColor(R.color.orange_700));
        } else if (mainColor == MaterialShade.DeepOrange500) {
            colors = Arrays.asList(getResourceColor(R.color.deep_orange_300), getResourceColor(R.color.deep_orange_500), getResourceColor(R.color.deep_orange_700));
        } else if (mainColor == MaterialShade.Brown500) {
            colors = Arrays.asList(getResourceColor(R.color.brown_300), getResourceColor(R.color.brown_500), getResourceColor(R.color.brown_700));
        }

        return colors;
    }

    private int getResourceColor(int resId) {

        return getResources().getColor(resId);
    }
}
