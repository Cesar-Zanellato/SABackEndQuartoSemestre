package com.back.fortesupermercados.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.back.fortesupermercados.dtos.subcategories.SubcategoryInput;
import com.back.fortesupermercados.dtos.subcategories.SubcategoryOutput;
import com.back.fortesupermercados.entities.Subcategory;
import com.back.fortesupermercados.repositories.SubcategoryRepository;

@Service
public class SubcategoryService {
    @Autowired
    SubcategoryRepository repository;

    @Transactional
    public SubcategoryOutput create(SubcategoryInput input){
        Subcategory subcategory = convertInputToSubcategory(input);
        subcategory = repository.save(subcategory);

        return convertSubcategoryToOutput(subcategory);
    }

    public List<SubcategoryOutput> list(){
        return repository
        .findAll()
        .stream()
        .map(subcategory -> convertSubcategoryToOutput(subcategory))
        .toList();
    }

    public SubcategoryOutput read(Long id){
        Subcategory subcategory = repository.findById(id).orElse(null);
        return convertSubcategoryToOutput(subcategory);
    }

    @Transactional
    public SubcategoryOutput update(Long id, SubcategoryInput input){
        if(repository.existsById(id)){
            Subcategory subcategory = convertInputToSubcategory(input);
            subcategory.setId(id);
            subcategory = repository.save(subcategory);
            return convertSubcategoryToOutput(subcategory);
        }else{
            return null;
        }
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    private SubcategoryOutput convertSubcategoryToOutput(Subcategory subcategory){
        if(subcategory == null){
            return null;
        }
        SubcategoryOutput output = new SubcategoryOutput(
            subcategory.getId(), 
            subcategory.getName()
        );

        return output;
    }

    private Subcategory convertInputToSubcategory(SubcategoryInput input){
        Subcategory subcategory = new Subcategory();
        subcategory.setName(input.name());

        return subcategory;
    }
}