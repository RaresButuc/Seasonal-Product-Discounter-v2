package com.codecool.seasonalproductdiscounter.ui;

import com.codecool.seasonalproductdiscounter.model.products.Product;
import com.codecool.seasonalproductdiscounter.service.products.browser.ProductBrowser;
import com.codecool.seasonalproductdiscounter.service.products.browser.ProductBrowserImpl;
import com.codecool.seasonalproductdiscounter.service.products.provider.ProductProvider;

import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class ProductsUi {
    private ProductBrowser productBrowser;

    public ProductsUi(ProductBrowser productBrowser) {
        this.productBrowser = productBrowser;
    }

    public void run() {
        displayWelcomeMessage();

        int code = 0;

        while (code != 6) {
            displayMenu();
            code = getCode();

            switch (code) {
                case 1 -> allProducts();
                case 2 -> printProducts();
                case 3 -> productsByPriceRange();
                case 4 -> productsOrderedBySeason();
                case 5 -> productsOrderedByPrice();
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
        System.out.println("1 - View Products");
        System.out.println("2 - Search Products by Name");
        System.out.println("3 - Search Products by Price Range");
        System.out.println("4 - Order Products by Season");
        System.out.println("5 - Order Products by Price");
        System.out.println("6 - Exit");
    }

    private int getCode() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private String getInfo() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private Collection<Product> getProducts() {
        return productBrowser.getAll();
    }

    private void allProducts() {
        if (getProducts().size() > 0) {
            System.out.println(getProducts().size());
            for (Product product :
                    getProducts()) {
                System.out.println(product.name() + " - " + product.price());
            }
        } else {
            System.out.println("No products found!");
        }
    }

    private void printProducts() {
        System.out.println("Enter the name of the product:");
        String title = getInfo();
        List<Product> productsByName = (List<Product>) productBrowser.getByName(title);
        if (productsByName.size() > 0) {
            for (Product product : productsByName) {
                System.out.println(product.name());
            }
        } else {
            System.out.println("No products found!");
        }
    }

    private void productsByPriceRange() {
        System.out.println("Enter the minimum price of the product:");
        double minimum = getCode();
        System.out.println("Enter the maximum price of the product:");
        double maximum = getCode();
        List<Product> productsByPriceRange = (List<Product>) productBrowser.getByPriceRange(minimum, maximum);
        if (productsByPriceRange.size() > 0) {
            for (Product product : productsByPriceRange) {
                System.out.println(product.name() + " - " + product.price());
            }
        } else {
            System.out.println("No products found in this price ranges!");
        }
    }

    private void productsOrderedBySeason() {
        List<Product> productsBySeasons = (List<Product>) productBrowser.orderBySeason();
        if (productsBySeasons.size() > 0) {
            for (Product product :
                    productsBySeasons) {
                System.out.println(product.season() + " - " + product.name());
            }
        } else {
            System.out.println("No products found!");
        }
    }

    private void productsOrderedByPrice() {
        List<Product> productsByPrice = (List<Product>) productBrowser.orderByPrice();
        if (productsByPrice.size() > 0) {
            for (Product product :
                    productsByPrice) {
                System.out.println(product.price() + " - " + product.name());
            }
        } else {
            System.out.println("No products found!");
        }
    }
}

