package com.codecool.seasonalproductdiscounter;

import com.codecool.seasonalproductdiscounter.service.products.browser.ProductBrowser;
import com.codecool.seasonalproductdiscounter.service.products.browser.ProductBrowserImpl;
import com.codecool.seasonalproductdiscounter.service.products.provider.RandomProductGenerator;
import com.codecool.seasonalproductdiscounter.service.products.statistics.ProductStatistics;
import com.codecool.seasonalproductdiscounter.service.products.statistics.ProductStatisticsImpl;
import com.codecool.seasonalproductdiscounter.ui.ProductsUi;
import com.codecool.seasonalproductdiscounter.ui.StatisticsUi;

public class Application {
    public static void main(String[] args) {
        var productProvider = new RandomProductGenerator(100, 10, 50);
        ProductBrowser productBrowser = new ProductBrowserImpl(productProvider);
        ProductStatistics productStatistics = new ProductStatisticsImpl(productProvider, productBrowser);

        var productsUi = new ProductsUi(productBrowser);
        var statisticsUi = new StatisticsUi(productStatistics);

        productsUi.run();
        statisticsUi.run();
    }
}
