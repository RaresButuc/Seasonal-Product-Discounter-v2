package com.codecool.seasonalproductdiscounter.ui;

import com.codecool.seasonalproductdiscounter.model.enums.Color;
import com.codecool.seasonalproductdiscounter.model.enums.Season;
import com.codecool.seasonalproductdiscounter.model.products.Product;
import com.codecool.seasonalproductdiscounter.service.products.statistics.ProductStatistics;

import java.util.*;

public class StatisticsUi {
    private final ProductStatistics productStatistics;

    public StatisticsUi(ProductStatistics productStatistics) {
        this.productStatistics = productStatistics;
    }

    //    public void run() {
//        for (Map.Entry<Color, Double> entry : productStatistics.getAveragePricesByColor().entrySet()) {
//            System.out.println(entry.getKey() + ": " + entry.getValue());
//        }
//    }
    public void run() {
        displayWelcomeMessage();

        int code = 0;

        while (code != 6) {
            displayMenu();
            code = getCode();

            switch (code) {
                case 1 -> averageOfAllProducts();
                case 2 -> mostExpensiveProduct();
                case 3 -> cheapestProduct();
                case 4 -> averagePriceByName();
                case 5 -> productsPerSeason();
                case 6 -> {
                }
                default -> System.out.println("Please try again!");
            }
        }
    }

    private void displayWelcomeMessage() {
        System.out.println("Welcome to Codecool Shop");
    }

    private void displayMenu() {
        System.out.println("1 - Average Price of All Products");
        System.out.println("2 - Most Expensive Product");
        System.out.println("3 - Cheapest Product");
        System.out.println("4 - Average Price By Name");
        System.out.println("5 - Products per Season");
        System.out.println("6 - Exit");
    }

    private int getCode() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private void averageOfAllProducts() {
        OptionalDouble avPrice = productStatistics.getAveragePrice();
        System.out.println("Average Price of all Products is: " + avPrice.toString());
    }

    private void mostExpensiveProduct() {
        Optional<Product> product = productStatistics.getMostExpensive();
        System.out.println("Most Expensive Product is: " + product.get().name() + " - " + product.get().price());
    }

    private void cheapestProduct() {
        Optional<Product> product = productStatistics.getCheapest();
        System.out.println("Cheapest Product is: " + product.get().name() + " - " + product.get().price());
    }

    private void averagePriceByName() {
        Map<String, Double> productsByName = productStatistics.getAveragePricesByName();
        for (String value : productsByName.keySet()) {
            System.out.println("Average Price of: " + value + " - " + productsByName.get(value));
        }
    }

    private void productsPerSeason() {
        Map<Season, Integer> productsBySeason = productStatistics.getCountBySeason();
        for (Season value : productsBySeason.keySet()) {
            System.out.println("Products for the " + value + " season " + " - " + productsBySeason.get(value));
        }
    }
}

