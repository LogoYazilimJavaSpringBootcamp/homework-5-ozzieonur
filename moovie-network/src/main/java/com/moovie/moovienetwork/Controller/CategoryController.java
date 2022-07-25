package com.moovie.moovienetwork.Controller;

import com.moovie.moovienetwork.Dto.CategoryDto;
import com.moovie.moovienetwork.Service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    final
    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryDto> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public CategoryDto getCategory(@PathVariable Long id){
        return categoryService.getCategory(id);
    }

    @PostMapping
    public CategoryDto createCategory(@RequestBody CategoryDto categoryDto) throws AccessDeniedException {
        return  categoryService.createCategory(categoryDto);
    }

    @PostMapping("/saveAll")
    public List<CategoryDto> createAll(@RequestBody List<CategoryDto> categories) throws AccessDeniedException {
        return categoryService.createAll(categories);
    }

    @PutMapping("/{id}")
    public CategoryDto updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) throws AccessDeniedException {
        return categoryService.updateCategory(id,categoryDto);
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id) throws AccessDeniedException {
        return categoryService.deleteCategory(id);
    }
}
