package com.codecool.seasonalproductdiscounter.ui;

import com.codecool.seasonalproductdiscounter.model.offers.Offer;
import com.codecool.seasonalproductdiscounter.model.products.Product;
import com.codecool.seasonalproductdiscounter.service.discounts.DiscountProvider;
import com.codecool.seasonalproductdiscounter.service.discounts.DiscountService;
import com.codecool.seasonalproductdiscounter.service.products.provider.ProductProvider;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SeasonalProductDiscounterUi {
    private final ProductProvider productProvider;
    private final DiscountProvider discountProvider;
    private final DiscountService discountService;
    private final Scanner scanner;

    public SeasonalProductDiscounterUi(
            ProductProvider productProvider,
            DiscountProvider discountProvider,
            DiscountService discounterService) {
        this.productProvider = productProvider;
        this.discountProvider = discountProvider;
        this.discountService = discounterService;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("Welcome to Seasonal Product Discounter!\n");

        printCatalog();
        printPromotions();

        System.out.println("Enter a date to see which products are discounted on that date:");
        LocalDate date = getDate();
        System.out.println();

        printOffers(date);
    }

    private void printCatalog() {
        System.out.println("Current product catalog (without any discounts):");
        printList(productProvider.getProducts());
        System.out.println();
    }

    private void printPromotions() {
        System.out.println("This year's promotions:");
        printList(discountProvider.getDiscounts());
        System.out.println();
    }

    private void printOffers(LocalDate date) {
        List<Offer> discounted = getOffers(date);
        printList(discounted);
    }

    private List<Offer> getOffers(LocalDate date) {
        List<Offer> discounted = new ArrayList<>();

        for (Product product : productProvider.getProducts()) {
            Offer offer = discountService.getOffer(product, date);
            if (!offer.discounts().isEmpty()) {
                discounted.add(offer);
            }
        }

        return discounted;
    }

    private LocalDate getDate() {
        LocalDate date = null;
        while (date == null) {
            try {
                String input = scanner.nextLine();
                date = LocalDate.parse(input);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date!");
            }
        }

        return date;
    }

    private static void printList(List<?> list) {
        for (Object obj : list) {
            System.out.println(obj);
        }
    }
}

