package com.delizarov.smartdiet.data.repository;


import com.delizarov.smartdiet.domain.models.Grocery;
import com.delizarov.smartdiet.domain.models.Recipe;

import java.util.List;

import io.reactivex.Observable;

public interface CookbookRepository {
    Observable<Grocery> readGroceries();

    long saveGrocery(Grocery grocery);

    Boolean removeGrocery(Grocery grocery);

    Observable<Recipe> readRecipes(List<Long> identifiers);
}
