package com.geekbrains.geekmarketwinter.interfaces;

import com.geekbrains.geekmarketwinter.entites.Category;

import java.util.List;

public interface ICategoryProvider {

    List<Category> findAll();
}
