package com.back.fortesupermercados.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.back.fortesupermercados.dtos.products.ProductInput;
import com.back.fortesupermercados.dtos.products.ProductOutput;
import com.back.fortesupermercados.entities.Product;
import com.back.fortesupermercados.repositories.ProductRepository;

@Service
public class ProductService {
    @Autowired
    ProductRepository repository;

    @Transactional
    public ProductOutput create(ProductInput input){
        Product product = convertInputToProduct(input);
        product = repository.save(product);

        return convertProductToOutput(product);
    }

    public List<ProductOutput> list(Pageable page, Product productExemplo){

        ExampleMatcher matcher = ExampleMatcher
                                        .matching()
                                        .withIgnoreCase()
                                        .withStringMatcher(StringMatcher.CONTAINING);

        Example<Product> exemplo = Example.of(productExemplo, matcher);

        return repository
        .findAll(exemplo, page)
        .stream()
        .map(product -> convertProductToOutput(product))
        .toList();
    }

    public ProductOutput read(Long id){
        Product product = repository.findById(id).orElse(null);
        return convertProductToOutput(product);
    }

    @Transactional
    public ProductOutput update(Long id, ProductInput input){
        if(repository.existsById(id)){
            Product product = convertInputToProduct(input);
            product.setId(id);
            product = repository.save(product);
            return convertProductToOutput(product);
        }else{
            return null;
        }
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    private ProductOutput convertProductToOutput(Product product){
        if(product == null){
            return null;
        }
        ProductOutput output = new ProductOutput(
            // product.getCodeProduct(), 
            product.getName(),
            product.getValueSale(), 
            product.getPromotion(), 
            product.getImage(), 
            product.getStock(), 
            product.getAmount()
        );

        return output;
    }

    private Product convertInputToProduct(ProductInput input){
        Product product = new Product();
        // product.setInternalCode(input.internalCode());
        // product.setCodeProduct(input.codeProduct());
        product.setName(input.name());
        product.setValueSale(input.valueSale());
        product.setValuePurchase(input.valuePurchase());
        product.setImage(input.image());
        product.setStock(input.stock());
        product.setAmount(input.amount());

        return product;
    }
}
