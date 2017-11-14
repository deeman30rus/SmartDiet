package com.delizarov.smartdiet.presentation.start;


import java.util.List;

public interface StartView {

    void showPermissionsDialog(List<String> permissions);

    void showLoginView();
}
