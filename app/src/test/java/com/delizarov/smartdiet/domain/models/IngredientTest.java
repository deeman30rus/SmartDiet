package com.delizarov.smartdiet.domain.models;


import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotSame;
import static junit.framework.Assert.assertTrue;

public class IngredientTest {

    @Test
    public void equalsTest() {

        long id_1 = 1;
        long id_2 = 2;

        String name_1 = "ingredient";
        String name_2 = "ingredient2";

        Ingredient expected = new Ingredient(id_1, name_1);

        Ingredient eq = new Ingredient(id_1, name_1);

        Ingredient withOtherId = new Ingredient(id_2, name_1);
        Ingredient withOtherName = new Ingredient(id_2, name_1);
        Ingredient completelyDifferent = new Ingredient(id_2, name_2);

        assertEquals(expected, eq);

        assertNotSame(expected, withOtherId);
        assertNotSame(expected, withOtherName);
        assertNotSame(expected, completelyDifferent);
    }
}
