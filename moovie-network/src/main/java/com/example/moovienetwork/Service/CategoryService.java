package com.example.moovienetwork.Service;


import com.example.moovienetwork.Model.Category;
import com.example.moovienetwork.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CategoryService {


    final
    CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) { // CONSTRUCTOR INJECTION
        this.categoryRepository = categoryRepository;
    }

    public Set<Category> createAll(Set<Category> categories) {
        categoryRepository.saveAll(categories);

        return categories;
    }
}
