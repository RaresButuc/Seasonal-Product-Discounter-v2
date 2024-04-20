package com.codecool.seasonalproductdiscounter.products.provider;

import com.codecool.seasonalproductdiscounter.model.enums.Color;
import com.codecool.seasonalproductdiscounter.model.enums.Season;
import com.codecool.seasonalproductdiscounter.model.products.Product;
import com.codecool.seasonalproductdiscounter.service.products.browser.ProductBrowserImpl;
import com.codecool.seasonalproductdiscounter.service.products.provider.ProductProvider;
import com.codecool.seasonalproductdiscounter.service.products.provider.ProductProviderImpl;
import com.codecool.seasonalproductdiscounter.service.products.provider.RandomProductGenerator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomProductGeneratorTest {
    double minimumPrice = 10;
    double maximumPrice = 90;
    List<Product> actualProducts = new RandomProductGenerator(20, minimumPrice, maximumPrice).getProducts();

    @Test
    void sizeOfList() {
        int listSize = actualProducts.size();

        assertEquals(20, listSize);
    }

    @Test
    void priceRange() {
        Collections.sort(actualProducts, new Comparator<Product>() {
            public int compare(Product o1, Product o2) {
                return (int) o1.price() - (int) o2.price();
            }
        });

        boolean biggerThanMinimumPrice = actualProducts.get(0).price() >= minimumPrice;
        boolean smallerThanMaximumPrice = actualProducts.get(actualProducts.size() - 1).price() <= maximumPrice;

        assertTrue(biggerThanMinimumPrice);
        assertTrue(smallerThanMaximumPrice);
    }

    @Test
    void colorRange() {
        String colors = Arrays.toString(Color.values());

        int anythingNew = actualProducts.stream()
                .filter(p -> !colors.contains(p.color()))
                .toList().size();

        //Test if there is any new Color besides the Enum ones
        assertEquals(0, anythingNew);
    }

    @Test
    void namesLength() {
        int notTwoNames = 0;

        for (Product product : actualProducts) {
            if (product.name().split(" ").length != 2) {
                notTwoNames++;
            }
        }
        assertEquals(0, notTwoNames);
    }
}
