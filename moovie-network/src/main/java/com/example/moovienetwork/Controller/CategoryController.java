package com.example.moovienetwork.Controller;

import com.example.moovienetwork.Model.Category;
import com.example.moovienetwork.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/category")
public class CategoryController {

    final
    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/saveAll")
    public Set<Category> createAll(@RequestBody Set<Category> categories){
        return categoryService.createAll(categories);
    }
}
