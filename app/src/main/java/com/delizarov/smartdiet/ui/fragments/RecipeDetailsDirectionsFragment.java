package com.delizarov.smartdiet.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.delizarov.smartdiet.R;

public class RecipeDetailsDirectionsFragment extends RecipeDetailsFragment {

    public static RecipeDetailsDirectionsFragment newInstance() {

        return new RecipeDetailsDirectionsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recipe_details_directions, container, false);

        return view;
    }
}
