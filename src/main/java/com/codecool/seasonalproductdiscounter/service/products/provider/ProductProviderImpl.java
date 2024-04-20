package com.codecool.seasonalproductdiscounter.service.products.provider;

import com.codecool.seasonalproductdiscounter.model.enums.Color;
import com.codecool.seasonalproductdiscounter.model.enums.Season;
import com.codecool.seasonalproductdiscounter.model.products.Product;

import java.util.List;

public class ProductProviderImpl implements ProductProvider {
    private final List<Product> products;

    public ProductProviderImpl() {
        products = List.of(
                new Product(1,"Yellow skirt", Color.YELLOW, Season.SPRING, 15.00),
                new Product(2,"Blue skirt", Color.BLUE, Season.SPRING, 15.00),
                new Product(3,"Red T-Shirt", Color.RED, Season.SUMMER, 10.00),
                new Product(4,"Brown T-Shirt", Color.BROWN, Season.SUMMER, 10.00),
                new Product(5,"Brown jacket", Color.BROWN, Season.AUTUMN, 40.00),
                new Product(6,"Violet shirt", Color.VIOLET, Season.AUTUMN, 30.00),
                new Product(7,"Blue jacket", Color.BLUE, Season.AUTUMN, 40.00),
                new Product(8,"Blue hat", Color.BLUE, Season.WINTER, 10.00),
                new Product(9,"Pink hat", Color.PINK, Season.WINTER, 10.00),
                new Product(10,"Red gloves", Color.RED, Season.WINTER, 8.00),
                new Product(11,"Yellow gloves", Color.YELLOW, Season.WINTER, 8.00)
        );
    }

    public List<Product> getProducts() {
        return products;
    }
}

