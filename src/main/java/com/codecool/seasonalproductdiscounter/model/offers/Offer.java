package com.codecool.seasonalproductdiscounter.model.offers;

import com.codecool.seasonalproductdiscounter.model.discounts.Discount;
import com.codecool.seasonalproductdiscounter.model.products.Product;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record Offer(Product product, LocalDate date, List<Discount> discounts, double price) {

    @Override
    public String toString() {
        return String.format("%s: %s, %s: %s, %s: %s, %s: %s",
                "Product", product,
                "Date", date,
                "Discounts", discounts.stream().map(Object::toString).collect(Collectors.joining(", ")),
                "Price", price);
    }
}

