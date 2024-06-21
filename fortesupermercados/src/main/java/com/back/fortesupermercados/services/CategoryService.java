package com.back.fortesupermercados.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.back.fortesupermercados.dtos.categories.CategoryInput;
import com.back.fortesupermercados.dtos.categories.CategoryOutput;
import com.back.fortesupermercados.dtos.products.ProductOutput;
import com.back.fortesupermercados.entities.Category;
import com.back.fortesupermercados.repositories.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    private ProductService productService;

    @Transactional
    public CategoryOutput create(CategoryInput input) {
        Category category = convertInputToCategory(input);
        category = categoryRepository.save(category);
        return convertCategoryToOutput(category);
    }

    public List<CategoryOutput> list() {
        return categoryRepository.findAll()
                .stream()
                .map(this::convertCategoryToOutput)
                .collect(Collectors.toList());
    }

    public List<CategoryOutput> getSubcategoriesByCategoryId(Long categoryId) {
        List<Category> subcategories = categoryRepository.findByParentCategoryId(categoryId);
        return subcategories.stream()
                .map(this::convertCategoryToOutput)
                .collect(Collectors.toList());
    }

    public List<ProductOutput> getProductsByCategoryId(Long categoryId) {
        List<ProductOutput> products = productService.getProductsByCategoryId(categoryId);
        return products;
    }

    public CategoryOutput read(Long id) {
        return categoryRepository.findById(id)
                .map(this::convertCategoryToOutput)
                .orElse(null);
    }

    @Transactional
    public CategoryOutput update(Long id, CategoryInput input) {
        return categoryRepository.findById(id)
                .map(existingCategory -> {
                    Category updatedCategory = convertInputToCategory(input);
                    updatedCategory.setId(id);
                    updatedCategory = categoryRepository.save(updatedCategory);
                    return convertCategoryToOutput(updatedCategory);
                })
                .orElse(null);
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    private CategoryOutput convertCategoryToOutput(Category category) {
        if (category == null) {
            return null;
        }
        CategoryOutput output = new CategoryOutput(
                category.getId(),
                category.getName()
        );
        return output;
    }

    private Category convertInputToCategory(CategoryInput input) {
        Category category = new Category();
        category.setName(input.name());
        return category;
    }
}
