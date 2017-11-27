package com.delizarov.smartdiet.ui.models;


public interface Filter<T> {

    boolean match(T obj);
}
