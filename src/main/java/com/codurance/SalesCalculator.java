package com.codurance;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SalesCalculator {

    private static final String COMMA_DELIMITER = ",";
    private static final int PRODUCT_NAME_INDEX = 0;
    private static final int PRODUCT_PRICE = 1;
    private static final int QUANTITY_SOLD = 2;

    public void calculateSales(List<String> fileNames) {
        BufferedReader fileReader = null;
        List<Sale> sales = new ArrayList();

        for(String fileName : fileNames) {
            try {
                String line;
                fileReader = new BufferedReader(new FileReader(fileName));
                fileReader.readLine();

                while ((line = fileReader.readLine()) != null) {
                    String[] salesEntryData = line.split(COMMA_DELIMITER);
                    if (salesEntryData.length > 0) {
                        Sale salesEntry = new Sale(salesEntryData[PRODUCT_NAME_INDEX], Double.valueOf(salesEntryData[PRODUCT_PRICE]), Integer.valueOf(salesEntryData[QUANTITY_SOLD]));
                        sales.add(salesEntry);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error calculating sales");
                e.printStackTrace();
            } finally {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    System.out.println("Error while closing sales calculator");
                    e.printStackTrace();
                }
            }
        }

        calculateTotalSalesRevenue(sales);
        calculateTotalProductSales("chocolate", sales);
        calculateTotalProductSales("apples", sales);
        calculateTotalProductSales("beer", sales);
    }

    private void calculateTotalProductSales(String productName, List<Sale> sales) {
        long productSalesRevenue = 0;
        for(Sale sale : sales) {
            if (sale.product().toLowerCase().equals(productName)) {
                productSalesRevenue += sale.quanitySold() * sale.price();
            }
        }
        System.out.println("Total sales of " + productName + ": £" + productSalesRevenue);
    }

    private void calculateTotalSalesRevenue(List<Sale> sales) {
        long totalSales = 0;

        for (Sale sale : sales) {
            totalSales += sale.price() * sale.quanitySold();
        }
        System.out.println("Total sales revenue is £" + totalSales);
    }
}
