package com.back.fortesupermercados.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.back.fortesupermercados.dtos.categories.CategoryInput;
import com.back.fortesupermercados.dtos.categories.CategoryOutput;
import com.back.fortesupermercados.entities.Category;
import com.back.fortesupermercados.repositories.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository repository;

    @Transactional
    public CategoryOutput create(CategoryInput input){
        Category category = convertInputToCategory(input);
        category = repository.save(category);

        return convertCategoryToOutput(category);
    }

    public List<CategoryOutput> list(){
        return repository
        .findAll()
        .stream()
        .map(category -> convertCategoryToOutput(category))
        .toList();
    }

    public CategoryOutput read(Long id){
        Category category = repository.findById(id).orElse(null);
        return convertCategoryToOutput(category);
    }

    @Transactional
    public CategoryOutput update(Long id, CategoryInput input){
        if(repository.existsById(id)){
            Category category = convertInputToCategory(input);
            category.setId(id);
            category = repository.save(category);
            return convertCategoryToOutput(category);
        }else{
            return null;
        }
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    private CategoryOutput convertCategoryToOutput(Category category){
        if(category == null){
            return null;
        }
        CategoryOutput output = new CategoryOutput(
            category.getId(), 
            category.getName()
        );

        return output;
    }

    private Category convertInputToCategory(CategoryInput input){
        Category category = new Category();
        category.setName(input.name());

        return category;
    }
}