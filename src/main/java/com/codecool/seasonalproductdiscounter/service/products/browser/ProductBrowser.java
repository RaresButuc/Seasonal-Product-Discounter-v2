package com.codecool.seasonalproductdiscounter.service.products.browser;

import com.codecool.seasonalproductdiscounter.model.enums.Color;
import com.codecool.seasonalproductdiscounter.model.enums.Season;
import com.codecool.seasonalproductdiscounter.model.products.PriceRange;
import com.codecool.seasonalproductdiscounter.model.products.Product;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface ProductBrowser {

    Collection<Product> getAll();
    Collection<Product> getByName(String name);
    Collection<Product> getByColor(Color color);
    Collection<Product> getBySeason(Season season);

    Collection<Product> getByPriceSmallerThan(double price);
    Collection<Product> getByPriceGreaterThan(double price);
    Collection<Product> getByPriceRange(double minimumPrice, double maximumPrice);

    Map<String, List<Product>> groupByName();
    Map<Color, List<Product>> groupByColor();
    Map<Season, List<Product>> groupBySeason();
    Map<PriceRange, List<Product>> groupByPriceRange();

    Collection<Product> orderByName();
    Collection<Product> orderByColor();
    Collection<Product> orderBySeason();
    Collection<Product> orderByPrice();
}

