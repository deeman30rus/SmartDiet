package com.delizarov.smartdiet.domain.models;


import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotSame;
import static junit.framework.Assert.assertTrue;

public class GroceryTest {

    @Test
    public void equalsTest() {

        long id_1 = 1;
        long id_2 = 2;

        String name_1 = "ingredient";
        String name_2 = "ingredient2";

        Grocery expected = new Grocery(id_1, name_1);

        Grocery eq = new Grocery(id_1, name_1);

        Grocery withOtherId = new Grocery(id_2, name_1);
        Grocery withOtherName = new Grocery(id_2, name_1);
        Grocery completelyDifferent = new Grocery(id_2, name_2);

        assertEquals(expected, eq);

        assertNotSame(expected, withOtherId);
        assertNotSame(expected, withOtherName);
        assertNotSame(expected, completelyDifferent);
    }
}
