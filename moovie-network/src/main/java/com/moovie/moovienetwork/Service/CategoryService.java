package com.moovie.moovienetwork.Service;


import com.moovie.moovienetwork.Dto.CategoryDto;
import com.moovie.moovienetwork.Model.Category;
import com.moovie.moovienetwork.Model.Enums.Role;
import com.moovie.moovienetwork.Model.User;
import com.moovie.moovienetwork.Repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
@Slf4j
public class CategoryService {


    private final CategoryRepository categoryRepository;


    private final ModelMapper modelMapper;

    public CategoryService(CategoryRepository categoryRepository, ModelMapper modelMapper) { // CONSTRUCTOR INJECTION
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    public List<CategoryDto> createAll(List<CategoryDto> categoryDtos) throws AccessDeniedException {

        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (authUser.getRole() == Role.ADMIN) {
            List<Category> categories = categoryDtos.stream().map(categoryDto -> modelMapper.map(categoryDto, Category.class)).toList();
            categoryRepository.saveAll(categories);
            log.info("Tüm kategoriler sisteme eklendi.");
            return categories.stream().map(category -> modelMapper.map(category, CategoryDto.class)).toList();
        } else throw new AccessDeniedException("Erişim iznine sahip değilsiniz");

    }

    public CategoryDto createCategory(CategoryDto categoryDto) throws AccessDeniedException {
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (authUser.getRole() == Role.ADMIN) {
            Category category = modelMapper.map(categoryDto, Category.class);
            log.info(categoryDto.getGenre() + " adlı kategori sisteme eklendi.");
            return modelMapper.map(categoryRepository.save(category), CategoryDto.class);
        } else throw new AccessDeniedException("Erişim iznine sahip değilsiniz");
    }

    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        log.info("Tüm kategoriler görüntülendi.");
        return categories.stream().map(category -> modelMapper.map(category, CategoryDto.class)).toList();
    }

    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) throws AccessDeniedException {

        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (authUser.getRole() == Role.ADMIN) {
            Category category = categoryRepository.findById(id).orElseThrow();

            category.setGenre(categoryDto.getGenre());

            categoryRepository.save(category);

            log.info( category.getId() + " id'li kategori güncellendi.");

            return modelMapper.map(category, CategoryDto.class);
        } else throw new AccessDeniedException("Erişim iznine sahip değilsiniz");

    }

    public String deleteCategory(Long id) throws AccessDeniedException {
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (authUser.getRole() == Role.ADMIN) {
            Category category = categoryRepository.findById(id).orElseThrow();
            categoryRepository.delete(category);

            log.info( category.getId() + " id'li kategori silindi.");

            return id + " id'li kategori silindi.";
        } else throw new AccessDeniedException("Erişim iznine sahip değilsiniz");

    }

    public CategoryDto getCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow();

        log.info( category.getId() + " id'li kategori görüntülendi.");

        return modelMapper.map(category, CategoryDto.class);
    }
}
