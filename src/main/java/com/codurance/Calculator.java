package com.codurance;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Calculator {

    private static final String COMMA_DELIMITER = ",";
    private static final int PRODUCT_NAME_INDEX = 0;
    private static final int PRODUCT_PRICE_INDEX = 1;
    private static final int QUANTITY_SOLD_INDEX = 2;
    private List<Sale> sales;
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
        sales = new ArrayList();

        for(String fileName : fileNames) {
            try {
                String line;
                fileReader = new BufferedReader(new FileReader(fileName));
                fileReader.readLine();

                while ((line = fileReader.readLine()) != null) {
                    String[] salesEntryData = line.split(COMMA_DELIMITER);
                    if (salesEntryData.length > 0) {
                        Sale salesEntry = new Sale(salesEntryData[PRODUCT_NAME_INDEX], Double.valueOf(salesEntryData[PRODUCT_PRICE_INDEX]), Integer.valueOf(salesEntryData[QUANTITY_SOLD_INDEX]));
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

    private void calculateSalesRevenuePerProduct(List<Sale> sales) {
        Set<String> uniqueProductNameList = createUniqueProductNameList(sales);

        for(String productName : uniqueProductNameList) {
            calculateSalesFor(productName, sales);
        }
    }

    private void calculateSalesFor(String productName, List<Sale> sales) {
        long productSalesRevenue = 0;
        for(Sale sale : sales) {
            if (sale.productName().equals(productName)) {
                productSalesRevenue += sale.quanitySold() * sale.price();
            }
        }
        salesPrinter.print("Total sales of " + productName + ": £" + productSalesRevenue + "\n");
    }

    private void calculateTotalSalesRevenue(List<Sale> sales) {
        long totalSales = 0;

        for (Sale sale : sales) {
            totalSales += sale.price() * sale.quanitySold();
        }
        salesPrinter.print("Total sales revenue is £" + totalSales + "\n");
    }

    private Set<String> createUniqueProductNameList(List<Sale> sales) {
        ArrayList productNameList = new ArrayList();

        for(Sale sale : sales) {
            productNameList.add(sale.productName());
        }
        return new HashSet<String>(productNameList);
    }
}
