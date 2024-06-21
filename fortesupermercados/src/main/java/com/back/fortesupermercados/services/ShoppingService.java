package com.back.fortesupermercados.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.back.fortesupermercados.dtos.shopping.ShoppingInput;
import com.back.fortesupermercados.dtos.shopping.ShoppingOutput;
import com.back.fortesupermercados.entities.Product;
import com.back.fortesupermercados.entities.Shopping;
import com.back.fortesupermercados.repositories.ProductStockRepository;
import com.back.fortesupermercados.repositories.ShoppingRepository;

@Service
public class ShoppingService {

    @Autowired
    ShoppingRepository repository;

    @Autowired
    ProductStockRepository productStockRepository;

    @Transactional
    public ShoppingOutput create(ShoppingInput input) {

        List<Product> products = input.product();
        for (Product product : products) {
            if (!productStockRepository.isProductInStock(product.getId())) {
                throw new IllegalArgumentException("Product with id " + product.getId() + " is not in stock");
            }

            int availableStock = productStockRepository.getProductStockQuantity(product.getId());
            if (availableStock <= 0) {
                throw new IllegalArgumentException("Product with id " + product.getId() + " has no stock available");
            }
        }

        Shopping shopping = convertInputToShopping(input);
        shopping = repository.save(shopping);

        return convertShoppingToOutput(shopping);
    }

    public List<ShoppingOutput> list(Pageable page, Shopping shoppingExemplo) {

        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(StringMatcher.CONTAINING);

        Example<Shopping> exemplo = Example.of(shoppingExemplo, matcher);

        return repository
                .findAll(exemplo, page)
                .stream()
                .map(shopping -> convertShoppingToOutput(shopping))
                .toList();
    }

    public ShoppingOutput read(Long id) {
        Shopping shopping = repository.findById(id).orElse(null);
        return convertShoppingToOutput(shopping);
    }

    @Transactional
    public ShoppingOutput update(Long id, ShoppingInput input) {
        if (repository.existsById(id)) {
            Shopping shopping = convertInputToShopping(input);
            shopping.setId(id);
            shopping = repository.save(shopping);
            return convertShoppingToOutput(shopping);
        } else {
            return null;
        }
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private ShoppingOutput convertShoppingToOutput(Shopping shopping) {
        if (shopping == null) {
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

    private Shopping convertInputToShopping(ShoppingInput input) {
        Shopping shopping = new Shopping();
        shopping.setProduct(input.product());
        shopping.setTotalPrice(input.totalPrice());
        shopping.setQuantityProducts(input.quantityProducts());

        return shopping;
    }
}
