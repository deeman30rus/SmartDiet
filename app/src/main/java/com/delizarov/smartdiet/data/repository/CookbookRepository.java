package com.delizarov.smartdiet.data.repository;


import com.delizarov.smartdiet.domain.models.Grocery;

import io.reactivex.Observable;

public interface CookbookRepository {
    Observable<Grocery> readGroceries();

    long saveGrocery(Grocery grocery);

    Boolean removeGrocery(Grocery grocery);
}
