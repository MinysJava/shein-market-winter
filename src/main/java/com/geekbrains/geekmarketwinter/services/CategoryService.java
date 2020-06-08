package com.geekbrains.geekmarketwinter.services;

import com.geekbrains.geekmarketwinter.entites.Category;
import com.geekbrains.geekmarketwinter.interfaces.ICategoryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private ICategoryProvider categoryProvider;


    public void setCategoryProvider(ICategoryProvider categoryProvider) {
        this.categoryProvider = categoryProvider;
    }

    public List<Category> getAllCategories() {
        return (List<Category>)categoryProvider.findAll();
    }
}
