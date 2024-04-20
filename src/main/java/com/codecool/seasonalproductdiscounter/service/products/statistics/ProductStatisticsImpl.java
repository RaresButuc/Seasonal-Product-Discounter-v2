package com.codecool.seasonalproductdiscounter.service.products.statistics;

import com.codecool.seasonalproductdiscounter.model.enums.Color;
import com.codecool.seasonalproductdiscounter.model.enums.Season;
import com.codecool.seasonalproductdiscounter.model.products.PriceRange;
import com.codecool.seasonalproductdiscounter.model.products.Product;
import com.codecool.seasonalproductdiscounter.service.products.browser.ProductBrowser;
import com.codecool.seasonalproductdiscounter.service.products.browser.ProductBrowserImpl;
import com.codecool.seasonalproductdiscounter.service.products.provider.ProductProvider;

import java.util.*;

public class ProductStatisticsImpl implements ProductStatistics {
    private final ProductProvider products;
    private final ProductBrowserImpl methods;

    public ProductStatisticsImpl(ProductProvider products, ProductBrowser methods) {
        this.products = products;
        this.methods = new ProductBrowserImpl(products);
    }

    public Collection<Product> getAll() {
        return products.getProducts();
    }

    @Override
    public Optional<Product> getMostExpensive() {
        Product mostExpensive = getAll().stream().sorted((a, b) -> (int) (b.price() - a.price())).toList().get(0);
        return Optional.of(mostExpensive);
    }

    @Override
    public Optional<Product> getCheapest() {
        Product cheapest = getAll().stream().sorted((a, b) -> (int) (a.price() - b.price())).toList().get(0);
        return Optional.of(cheapest);
    }

    @Override
    public OptionalDouble getAveragePrice() {
        int length = getAll().size();
        double sumOfAllPrices = 0;
        for (Product product :
                getAll()) {
            sumOfAllPrices += product.price();
        }
        return OptionalDouble.of(Math.floor(sumOfAllPrices / length));
    }

    @Override
    public Map<String, Double> getAveragePricesByName() {
        Map<String, List<Product>> pairedByName = methods.groupByName();
        HashMap<String, Double> pairedWithAveragePrice = new HashMap<>();
        for (String value : pairedByName.keySet()) {
            double totalPrice = 0;
            for (Product product :
                    pairedByName.get(value)) {
                totalPrice += product.price();
            }
            pairedWithAveragePrice.put(value, Math.floor(totalPrice / pairedByName.get(value).size()));
        }
        return pairedWithAveragePrice;
    }

    @Override
    public Map<Color, Double> getAveragePricesByColor() {
        Map<Color, List<Product>> pairedByColor = methods.groupByColor();
        HashMap<Color, Double> pairedWithAveragePrice = new HashMap<>();
        for (Color value : pairedByColor.keySet()) {
            double totalPrice = 0;
            for (Product product : pairedByColor.get(value)) {
                totalPrice += product.price();
            }
            pairedWithAveragePrice.put(value, Math.floor(totalPrice / pairedByColor.get(value).size()));
        }
        return pairedWithAveragePrice;
    }

    @Override
    public Map<Season, Double> getAveragePricesBySeason() {
        Map<Season, List<Product>> pairedBySeason = methods.groupBySeason();
        HashMap<Season, Double> pairedWithAveragePrice = new HashMap<>();
        for (Season value : pairedBySeason.keySet()) {
            double totalPrice = 0;
            for (Product product : pairedBySeason.get(value)) {
                totalPrice += product.price();
            }
            pairedWithAveragePrice.put(value, Math.floor(totalPrice / pairedBySeason.get(value).size()));
        }
        return pairedWithAveragePrice;
    }

    @Override
    public Map<PriceRange, Double> getAveragePricesByPriceRange() {
        Map<PriceRange, List<Product>> pairedBySeason = methods.groupByPriceRange();
        HashMap<PriceRange, Double> pairedWithAveragePrice = new HashMap<>();
        for (PriceRange value : pairedBySeason.keySet()) {
            double totalPrice = 0;
            for (Product product : pairedBySeason.get(value)) {
                totalPrice += product.price();
            }
            pairedWithAveragePrice.put(value, Math.floor(totalPrice / pairedBySeason.get(value).size()));
        }
        return pairedWithAveragePrice;
    }

    @Override
    public Map<String, Integer> getCountByName() {
        Map<String, List<Product>> pairedByName = methods.groupByName();
        HashMap<String, Integer> pairedWithCounter = new HashMap<>();

        for (String value : pairedByName.keySet()) {
            int counter = 0;
            for (Product product : pairedByName.get(value)) {
                counter += 1;
            }
            pairedWithCounter.put(value, counter);
        }
        return pairedWithCounter;
    }

    @Override
    public Map<Color, Integer> getCountByColor() {
        Map<Color, List<Product>> pairedByColor = methods.groupByColor();
        HashMap<Color, Integer> pairedWithCounter = new HashMap<>();

        for (Color value : pairedByColor.keySet()) {
            int counter = 0;
            for (Product product : pairedByColor.get(value)) {
                counter += 1;
            }
            pairedWithCounter.put(value, counter);
        }
        return pairedWithCounter;
    }

    @Override
    public Map<Season, Integer> getCountBySeason() {
        Map<Season, List<Product>> pairedBySeason = methods.groupBySeason();
        HashMap<Season, Integer> pairedWithCounter = new HashMap<>();

        for (Season value : pairedBySeason.keySet()) {
            int counter = 0;
            for (Product product : pairedBySeason.get(value)) {
                counter += 1;
            }
            pairedWithCounter.put(value, counter);
        }
        return pairedWithCounter;
    }

    @Override
    public Map<PriceRange, Integer> getCountByPriceRange() {
        Map<PriceRange, List<Product>> pairedByPriceRange = methods.groupByPriceRange();
        HashMap<PriceRange, Integer> pairedWithCounter = new HashMap<>();

        for (PriceRange value : pairedByPriceRange.keySet()) {
            int counter = 0;
            for (Product product : pairedByPriceRange.get(value)) {
                counter += 1;
            }
            pairedWithCounter.put(value, counter);
        }
        return pairedWithCounter;
    }
}
