package com.codecool.seasonalproductdiscounter.products.browser;

import com.codecool.seasonalproductdiscounter.model.enums.Color;
import com.codecool.seasonalproductdiscounter.model.enums.Season;
import com.codecool.seasonalproductdiscounter.model.products.PriceRange;
import com.codecool.seasonalproductdiscounter.model.products.Product;
import com.codecool.seasonalproductdiscounter.service.products.browser.ProductBrowser;
import com.codecool.seasonalproductdiscounter.service.products.browser.ProductBrowserImpl;
import com.codecool.seasonalproductdiscounter.service.products.provider.RandomProductGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class ProductBrowserImplTest {

    private ProductBrowser productBrowser;
    private final RandomProductGenerator provider;

    public ProductBrowserImplTest() {
        this.provider = new RandomProductGenerator(50, 10, 70);
        this.productBrowser = new ProductBrowserImpl(provider);
    }

    @Test
    void findByName() {
        boolean anotherName = false;
        List<Product> sortedbyName = (List<Product>) productBrowser.getByName("skirt");

        for (Product product : sortedbyName) {
            if (!Objects.equals(product.name().split(" ")[1], "skirt")) {
                anotherName = true;
            } else {
                anotherName = false;
            }
        }
        assertFalse(anotherName);
    }

    @Test
    void findByColor() {
        boolean anotherColor = false;
        List<Product> sortedbyColor = (List<Product>) productBrowser.getByColor(Color.BLUE);

        for (Product product : sortedbyColor) {
            if (!product.color().equals(Color.BLUE)) {
                anotherColor = true;
            } else {
                anotherColor = false;
            }
        }
        assertFalse(anotherColor);
    }

    @Test
    void findBySeason() {
        boolean anotherSeason = false;
        List<Product> sortedbySeason = (List<Product>) productBrowser.getBySeason(Season.SUMMER);

        for (Product product : sortedbySeason) {
            if (!product.season().equals(Season.SUMMER)) {
                anotherSeason = true;
            } else {
                anotherSeason = false;
            }
        }
        assertFalse(anotherSeason);
    }

    @Test
    void findBySmallerThan() {
        List<Product> sortedSmallerThan = (List<Product>) productBrowser.getByPriceSmallerThan(40);
        double biggestPrice = sortedSmallerThan.stream().sorted((a, b) -> (int) (a.price() - b.price())).toList().get(sortedSmallerThan.size() - 1).price();

        boolean smallerThanLimit = biggestPrice < 40;

        assertTrue(smallerThanLimit);
    }

    @Test
    void findByGreaterThan() {
        List<Product> sortedGreaterThan = (List<Product>) productBrowser.getByPriceGreaterThan(25);
        double smallestPrice = sortedGreaterThan.stream().sorted((a, b) -> (int) (a.price() - b.price())).toList().get(0).price();

        boolean biggerThanLimit = smallestPrice > 25;

        assertTrue(biggerThanLimit);
    }

    @Test
    void findByPriceRange() {
        List<Product> sortedGreaterThan = (List<Product>) productBrowser.getByPriceRange(20, 25);
        double smallestPrice = sortedGreaterThan.stream().sorted((a, b) -> (int) (a.price() - b.price())).toList().get(0).price();
        double biggestPrice = sortedGreaterThan.stream().sorted((a, b) -> (int) (a.price() - b.price())).toList().get(sortedGreaterThan.size() - 1).price();

        boolean biggerThanLimit = smallestPrice >= 20 && biggestPrice <= 25;

        assertTrue(biggerThanLimit);
    }

    @Test
    void groupbyName() {
        boolean anotherName = false;
        Map<String, List<Product>> grouped = productBrowser.groupByName();

        List<Product> skirts = grouped.get("skirt");
        for (Product product : skirts) {
            if (!Objects.equals(product.name().split(" ")[1], "skirt")) {
                anotherName = true;
            } else {
                anotherName = false;
            }
        }
        assertFalse(anotherName);
    }

    @Test
    void groupbyColor() {
        boolean anotherColor = false;
        Map<Color, List<Product>> grouped = productBrowser.groupByColor();

        List<Product> blueClothes = grouped.get(Color.BLUE);
        for (Product product : blueClothes) {
            if (!Objects.equals(product.color(), Color.BLUE)) {
                anotherColor = true;
            } else {
                anotherColor = false;
            }
        }
        assertFalse(anotherColor);
    }

    @Test
    void groupbySeason() {
        boolean anotherSeason = false;
        Map<Season, List<Product>> grouped = productBrowser.groupBySeason();

        List<Product> summerClothes = grouped.get(Season.SUMMER);
        for (Product product : summerClothes) {
            if (!Objects.equals(product.season(), Season.SUMMER)) {
                anotherSeason = true;
            } else {
                anotherSeason = false;
            }
        }
        assertFalse(anotherSeason);
    }

    //    @Test
//    void groupbyPriceRange() {
//        boolean outsidePrice = false;
//        Map<PriceRange, List<Product>> grouped = new ProductBrowserImpl(provider).groupByPriceRange();
//
//        Set priceRanges = grouped.keySet();
//        System.out.println(priceRanges);
//        for (Object productsPrices : priceRanges) {
//            if (grouped.get(productsPrices).) {
//                anotherSeason = true;
//            } else {
//                anotherSeason = false;
//            }
//        }
////        assertFalse(anotherSeason);
//    }
    @Test
    void orderbyName() {
        boolean sorted = false;
        List<Product> ordered = (List<Product>) productBrowser.orderByName();

        for (int i = 0; i < ordered.size(); i++) {
            for (int j = 0; j < ordered.size(); j++) {
                if (ordered.get(i).name().compareTo(ordered.get(j).name()) > 0) {
                    sorted = true;
                }
            }
        }
        assertTrue(sorted);
    }

    @Test
    void orderbyColor() {
        boolean sorted = false;
        List<Product> ordered = (List<Product>) productBrowser.orderByColor();

        for (int i = 0; i < ordered.size(); i++) {
            for (int j = 0; j < ordered.size(); j++) {
                if (ordered.get(i).color().toString().compareTo(ordered.get(j).name().toString()) > 0) {
                    sorted = true;
                }
            }
        }
        assertTrue(sorted);
    }

    @Test
    void orderbySeason() {
        boolean sorted = false;
        List<Product> ordered = (List<Product>) productBrowser.orderBySeason();

        for (int i = 0; i < ordered.size(); i++) {
            for (int j = 0; j < ordered.size(); j++) {
                if (ordered.get(i).season().toString().compareTo(ordered.get(j).season().toString()) > 0) {
                    sorted = true;
                }
            }
        }
        assertTrue(sorted);
    }

    @Test
    void orderbyPrice() {
        boolean sorted = false;
        List<Product> ordered = (List<Product>) productBrowser.orderByPrice();

        for (int i = 0; i < ordered.size(); i++) {
            for (int j = 0; j < ordered.size(); j++) {
                if (ordered.get(i).price() < ordered.get(j).price()) {
                    sorted = true;
                }
            }
        }
        assertTrue(sorted);
    }
}

