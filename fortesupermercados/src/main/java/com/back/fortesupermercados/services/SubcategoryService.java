package com.back.fortesupermercados.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.back.fortesupermercados.dtos.categories.CategoryOutput;
import com.back.fortesupermercados.dtos.products.ProductOutput;
import com.back.fortesupermercados.dtos.subcategories.SubcategoryInput;
import com.back.fortesupermercados.dtos.subcategories.SubcategoryOutput;
import com.back.fortesupermercados.entities.Subcategory;
import com.back.fortesupermercados.repositories.SubcategoryRepository;

@Service
public class SubcategoryService {
    
    @Autowired
    SubcategoryRepository subcategoryRepository;

    @Autowired
    private ProductService productService;

    @Transactional
    public SubcategoryOutput create(SubcategoryInput input){
        Subcategory subcategory = convertInputToSubcategory(input);
        subcategory = subcategoryRepository.save(subcategory);

        return convertSubcategoryToOutput(subcategory);
    }

    public List<SubcategoryOutput> list(){
        return subcategoryRepository
        .findAll()
        .stream()
        .map(subcategory -> convertSubcategoryToOutput(subcategory))
        .toList();
    }

    public List<SubcategoryOutput> getSubcategoriesByCategoryId(Long categoryId) {
        List<Subcategory> subcategories = subcategoryRepository.findByCategoryId(categoryId);
        return subcategories.stream()
                .map(this::convertSubcategoryToOutput)
                .collect(Collectors.toList());
    }

    public List<ProductOutput> getProductsBySubcategoryId(Long subcategoryId) {
        List<ProductOutput> products = productService.getProductsBySubcategoryId(subcategoryId);
        return products;
    }

    public SubcategoryOutput read(Long id){
        Subcategory subcategory = subcategoryRepository.findById(id).orElse(null);
        return convertSubcategoryToOutput(subcategory);
    }

    @Transactional
    public SubcategoryOutput update(Long id, SubcategoryInput input){
        if(subcategoryRepository.existsById(id)){
            Subcategory subcategory = convertInputToSubcategory(input);
            subcategory.setId(id);
            subcategory = subcategoryRepository.save(subcategory);
            return convertSubcategoryToOutput(subcategory);
        }else{
            return null;
        }
    }

    public void delete(Long id){
        subcategoryRepository.deleteById(id);
    }

    private SubcategoryOutput convertSubcategoryToOutput(Subcategory subcategory){
        if(subcategory == null){
            return null;
        }
        SubcategoryOutput output = new SubcategoryOutput(
            subcategory.getId(), 
            subcategory.getName(),
            subcategory.getParentCategory()
        );

        return output;
    }

    private Subcategory convertInputToSubcategory(SubcategoryInput input){
        Subcategory subcategory = new Subcategory();
        subcategory.setName(input.name());
        subcategory.setParentCategory(input.category());

        return subcategory;
    }
}