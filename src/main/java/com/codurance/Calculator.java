package com.codurance;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Calculator {

    private static final String COMMA_DELIMITER = ",";
    private static final int PRODUCT_NAME_INDEX = 0;
    private static final int PRODUCT_PRICE_INDEX = 1;
    private static final int QUANTITY_SOLD_INDEX = 2;
    private List<Sales> sales = new ArrayList<>();
    private SalesPrinter salesPrinter;

    public Calculator(SalesPrinter salesPrinter) {
        this.salesPrinter = salesPrinter;
    }

    public void calculateSalesDataFor(List<String> fileNames) {
        readSalesEntries(fileNames);
        calculateTotalSalesRevenue(sales);
        calculateSalesRevenuePerProduct(sales);
    }

    private void readSalesEntries(List<String> fileNames) {
        BufferedReader fileReader = null;

        for(String fileName : fileNames) {
            try {
                String line;
                fileReader = new BufferedReader(new FileReader(fileName));
                fileReader.readLine();

                while ((line = fileReader.readLine()) != null) {
                    String[] salesEntryData = line.split(COMMA_DELIMITER);
                    if (salesEntryData.length > 0) {
                        Sales salesEntry = new Sales(salesEntryData[PRODUCT_NAME_INDEX], Double.valueOf(salesEntryData[PRODUCT_PRICE_INDEX]), Integer.valueOf(salesEntryData[QUANTITY_SOLD_INDEX]));
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
    }

    private void calculateSalesRevenuePerProduct(List<Sales> sales) {
        Set<String> uniqueProductNameList = createUniqueProductNameList(sales);

        for(String productName : uniqueProductNameList) {
            calculateSalesFor(productName, sales);
        }
    }

    private void calculateSalesFor(String productName, List<Sales> sales) {
        double productSalesRevenue = sales
                                        .stream()
                                        .filter(sale -> sale.productName().equals(productName))
                                        .mapToDouble(sale -> sale.quantitySold() * sale.price())
                                        .sum();

        salesPrinter.print("Total sales of " + productName + ": £" + String.format("%.2f", productSalesRevenue) + "\n");
    }

    private void calculateTotalSalesRevenue(List<Sales> sales) {
        double totalSales = sales
                                .stream()
                                .mapToDouble(sale -> sale.price() * sale.quantitySold())
                                .sum();

        salesPrinter.print("Total sales revenue is £" + String.format("%.2f", totalSales) + "\n");
    }

    private Set<String> createUniqueProductNameList(List<Sales> sales) {
        List<String> productNameList = sales
                                            .stream()
                                            .map(Sales::productName)
                                            .collect(Collectors.toList());

        return new HashSet<String>(productNameList);
    }
}
