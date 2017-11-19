package com.delizarov.smartdiet.presentation.start;


import com.delizarov.smartdiet.domain.models.User;

import java.util.List;

public interface StartView {

    void showPermissionsDialog(List<String> permissions);

    void showLoginView();

    void showDailyMealsView(User user);
}
