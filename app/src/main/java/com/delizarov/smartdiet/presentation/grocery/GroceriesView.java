package com.delizarov.smartdiet.presentation.grocery;


import com.delizarov.smartdiet.domain.models.Grocery;

public interface GroceriesView {
    void renderGrocery(Grocery grocery);

    void showGroceryDetails(Grocery grocery);

    void clearGroceries();

    void updateGrocery(Grocery grocery);

    void showGroceryNameEmptyError();

    void addGrocery(Grocery grocery);

    void filterListMatchingQuery(String query);

    void clearFilter();

    void removeGrocery(Grocery grocery);
}
