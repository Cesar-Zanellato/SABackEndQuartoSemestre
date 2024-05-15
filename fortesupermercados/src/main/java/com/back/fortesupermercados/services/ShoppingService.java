package com.back.fortesupermercados.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.back.fortesupermercados.dtos.shopping.ShoppingInput;
import com.back.fortesupermercados.dtos.shopping.ShoppingOutput;
import com.back.fortesupermercados.entities.Shopping;
import com.back.fortesupermercados.repositories.ShoppingRepository;

@Service
public class ShoppingService {
    
    @Autowired
    ShoppingRepository repository;

    @Transactional
    public ShoppingOutput create(ShoppingInput input){
        Shopping shopping = convertInputToShopping(input);
        shopping = repository.save(shopping);

        return convertShoppingToOutput(shopping);
    }

    public List<ShoppingOutput> list(){
        return repository
        .findAll()
        .stream()
        .map(shopping -> convertShoppingToOutput(shopping))
        .toList();
    }

    public ShoppingOutput read(Long id){
        Shopping shopping = repository.findById(id).orElse(null);
        return convertShoppingToOutput(shopping);
    }

    @Transactional
    public ShoppingOutput update(Long id, ShoppingInput input){
        if(repository.existsById(id)){
            Shopping shopping = convertInputToShopping(input);
            shopping.setId(id);
            shopping = repository.save(shopping);
            return convertShoppingToOutput(shopping);
        }else{
            return null;
        }
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    private ShoppingOutput convertShoppingToOutput(Shopping shopping){
        if(shopping == null){
            return null;
        }
        ShoppingOutput output = new ShoppingOutput(
            shopping.getId(), 
            shopping.getProduct(), 
            shopping.getTotalPrice(), 
            shopping.getQuantityProducts()
        );

        return output;
    }

    private Shopping convertInputToShopping(ShoppingInput input){
        Shopping shopping = new Shopping();
        shopping.setProduct(input.product());
        shopping.setTotalPrice(input.totalPrice());
        shopping.setQuantityProducts(input.quantityProducts());

        return shopping;
    }
}