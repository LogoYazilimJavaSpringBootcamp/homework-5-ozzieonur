package com.example.moovienetwork.Mapper;

import com.example.moovienetwork.Dto.CategoryDto;
import com.example.moovienetwork.Model.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface CategoryMapper {

    Category dtoToEntity(CategoryDto categoryDto);
    CategoryDto entityToDto(Category category);

    List<Category> dtoToEntity(List<CategoryDto> categoryDtos);
    List<CategoryDto> entityToDto(List<Category> categories);

}
