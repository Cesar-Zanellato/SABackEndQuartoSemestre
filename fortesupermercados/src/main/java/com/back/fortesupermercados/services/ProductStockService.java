package com.back.fortesupermercados.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.back.fortesupermercados.dtos.productsStocks.ProductStockInput;
import com.back.fortesupermercados.dtos.productsStocks.ProductStockOutput;
import com.back.fortesupermercados.entities.ProductStock;
import com.back.fortesupermercados.repositories.ProductStockRepository;

@Service
public class ProductStockService {
    @Autowired
    ProductStockRepository repository;

    @Transactional
    public ProductStockOutput create(ProductStockInput input){
        ProductStock productStock = convertInputToProductStock(input);
        productStock = repository.save(productStock);

        return convertProductStockToOutput(productStock);
    }

    public List<ProductStockOutput> list(Pageable page, ProductStock productStockExemplo){
        

        ExampleMatcher matcher = ExampleMatcher
                                        .matching()
                                        .withIgnoreCase()
                                        .withStringMatcher(StringMatcher.CONTAINING);

        Example<ProductStock> exemplo = Example.of(productStockExemplo, matcher);

        return repository
        .findAll(exemplo, page)
        .stream()
        .map(productStock -> convertProductStockToOutput(productStock))
        .toList();
    }

    public ProductStockOutput read(Long id){
        ProductStock productStock = repository.findById(id).orElse(null);
        return convertProductStockToOutput(productStock);
    }

    @Transactional
    public ProductStockOutput update(Long id, ProductStockInput input){
        if(repository.existsById(id)){
            ProductStock productStock = convertInputToProductStock(input);
            productStock.setId(id);
            productStock = repository.save(productStock);
            return convertProductStockToOutput(productStock);
        }else{
            return null;
        }
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    private ProductStockOutput convertProductStockToOutput(ProductStock productStock){
        if(productStock == null){
            return null;
        }
        ProductStockOutput output = new ProductStockOutput(
            productStock.getQuantity()
        );

        return output;
    }

    private ProductStock convertInputToProductStock(ProductStockInput input){
        ProductStock productStock = new ProductStock();
        productStock.setQuantity(input.quantity());

        return productStock;
    }
}
