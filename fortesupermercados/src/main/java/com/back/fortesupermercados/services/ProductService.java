package com.back.fortesupermercados.services;

import java.util.List;
import java.util.stream.Collectors;

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
// import com.back.fortesupermercados.repositories.ProductStockRepository;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    // @Autowired
    // ProductStockRepository repositoryStock;

    @Transactional
    public ProductOutput create(ProductInput input) {
        Product product = convertInputToProduct(input);
        product = productRepository.save(product);
        return convertProductToOutput(product);
    }

    public List<ProductOutput> list(Pageable page, Product productExemplo) {

        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(StringMatcher.CONTAINING);

        Example<Product> exemplo = Example.of(productExemplo, matcher);

        return productRepository
                .findAll(exemplo, page)
                .stream()
                .filter(product -> product.getProductStock() != null && product.getProductStock().getQuantity() > 0)
                .map(product -> convertProductToOutput(product))
                .toList();
    }

    @Transactional
    public List<ProductOutput> getProductsByCategoryId(Long categoryId) {
        List<Product> products = productRepository.findByCategoryId(categoryId);
        return products.stream()
                .map(this::convertProductToOutput)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<ProductOutput> getProductsBySubcategoryId(Long subcategoryId) {
        List<Product> products = productRepository.findBySubcategoryId(subcategoryId);
        return products.stream()
                .map(this::convertProductToOutput)
                .collect(Collectors.toList());
    }

    public ProductOutput read(Long id) {
        return productRepository.findById(id)
                .map(this::convertProductToOutput)
                .orElse(null);
    }

    @Transactional
    public ProductOutput update(Long id, ProductInput input) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    Product updatedProduct = convertInputToProduct(input);
                    updatedProduct.setId(id);
                    updatedProduct = productRepository.save(updatedProduct);
                    return convertProductToOutput(updatedProduct);
                })
                .orElse(null);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    private ProductOutput convertProductToOutput(Product product) {
        if (product == null) {
            return null;
        }
        ProductOutput output = new ProductOutput(
                product.getName(),
                product.getValueSale(),
                product.getPromotion(),
                product.getImage(),
                product.getAmount(),
                product.getProductStock()
        );

        return output;
    }

    private Product convertInputToProduct(ProductInput input) {
        Product product = new Product();
        product.setName(input.name());
        product.setValueSale(input.valueSale());
        product.setImage(input.image());
        product.setAmount(input.amount());
        product.setProductStock(input.productStock());

        return product;
    }
}
