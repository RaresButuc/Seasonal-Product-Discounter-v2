package com.codecool.seasonalproductdiscounter.service.products.browser;

import com.codecool.seasonalproductdiscounter.model.enums.Color;
import com.codecool.seasonalproductdiscounter.model.enums.Season;
import com.codecool.seasonalproductdiscounter.model.products.PriceRange;
import com.codecool.seasonalproductdiscounter.model.products.Product;
import com.codecool.seasonalproductdiscounter.service.products.provider.ProductProvider;

import java.util.*;

public class ProductBrowserImpl implements ProductBrowser {

    private final ProductProvider products;

    public ProductBrowserImpl(ProductProvider products) {
        this.products = products;
    }

    public Collection<Product> getAll() {
        return products.getProducts();
    }

    @Override
    public Collection<Product> getByName(String name) {
        return getAll().stream().filter(p -> p.name().contains(name)).toList();
    }

    @Override
    public Collection<Product> getByColor(Color color) {
        return getAll().stream().filter(p -> p.color().equals(color)).toList();
    }

    @Override
    public Collection<Product> getBySeason(Season season) {
        return getAll().stream().filter(p -> p.season().equals(season)).toList();
    }

    @Override
    public Collection<Product> getByPriceSmallerThan(double price) {
        return getAll().stream().filter(p -> p.price() < price).toList();
    }

    @Override
    public Collection<Product> getByPriceGreaterThan(double price) {
        return getAll().stream().filter(p -> p.price() > price).toList();
    }

    @Override
    public Collection<Product> getByPriceRange(double minimumPrice, double maximumPrice) {
        return getAll().stream().filter(p -> p.price() >= minimumPrice && p.price() <= maximumPrice).toList();
    }

    @Override
    public Map<String, List<Product>> groupByName() {
        HashMap<String, List<Product>> paired = new HashMap<>();
        Set<String> setOfNames = new HashSet<>(getAll().size());

        for (Product product : getAll()) {
            setOfNames.add(product.name().split(" ")[1]);
        }
        for (String name : setOfNames) {
            paired.put(name, getAll().stream().filter(p -> p.name().split(" ")[1].equals(name)).toList());
        }
        return paired;
    }

    @Override
    public Map<Color, List<Product>> groupByColor() {
        Color[] colors = Color.values();
        HashMap<Color, List<Product>> paired = new HashMap<>();
        for (Color color : colors) {
            paired.put(color, getAll().stream().filter(p -> p.color().equals(color)).toList());
        }
        return paired;
    }

    @Override
    public Map<Season, List<Product>> groupBySeason() {
        Season[] seasons = Season.values();
        HashMap<Season, List<Product>> paired = new HashMap<>();
        for (Season season : seasons) {
            paired.put(season, getAll().stream().filter(p -> p.season().equals(season)).toList());
        }
        return paired;
    }

    @Override
    public Map<PriceRange, List<Product>> groupByPriceRange() {
        List<Product> sortedByPrice = orderByPrice().stream().toList();
        HashMap<PriceRange, List<Product>> paired = new HashMap<>();

        PriceRange firstHalf;
        PriceRange secondHalf;

        double difference = (sortedByPrice.get(sortedByPrice.size() - 1).price() - sortedByPrice.get(0).price());
        if (difference % 2 == 0) {
            double halfOfThePriceRange = sortedByPrice.get(0).price() + (difference / 2);

            firstHalf = new PriceRange(sortedByPrice.get(0).price() - 1, halfOfThePriceRange + 1);
            secondHalf = new PriceRange(firstHalf.maximum() - 1, sortedByPrice.get(sortedByPrice.size() - 1).price() + 1);

            paired.put(secondHalf, getAll().stream().filter(p -> secondHalf.contains(p.price())).toList());
            paired.put(firstHalf, getAll().stream().filter(p -> firstHalf.contains(p.price())).toList());

        } else {
            double halfOfThePriceRange = sortedByPrice.get(0).price() + Math.floor(difference / 2);

            firstHalf = new PriceRange(sortedByPrice.get(0).price() - 1, halfOfThePriceRange + 1);
            secondHalf = new PriceRange(firstHalf.maximum() - 1, sortedByPrice.get(sortedByPrice.size() - 1).price() + 1);

            paired.put(secondHalf, getAll().stream().filter(p -> secondHalf.contains(p.price())).toList());
            paired.put(firstHalf, getAll().stream().filter(p -> firstHalf.contains(p.price())).toList());
        }
        return paired;
    }

    @Override
    public Collection<Product> orderByName() {
        return getAll().stream().sorted((a, b) -> a.name().compareTo(b.name())).toList();

    }

    @Override
    public Collection<Product> orderByColor() {
        return getAll().stream().sorted((a, b) -> a.color().toString().compareTo(b.color().toString())).toList();
    }

    @Override
    public Collection<Product> orderBySeason() {
        return getAll().stream().sorted((a, b) -> a.season().toString().compareTo(b.season().toString())).toList();
    }

    @Override
    public Collection<Product> orderByPrice() {
        return getAll().stream().sorted((a, b) -> (int) (a.price() - b.price())).toList();
    }
}
