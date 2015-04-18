package com.codurance;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class Calculator {

    private static final String COMMA_DELIMITER = ",";
    private static final int PRODUCT_NAME_INDEX = 0;
    private static final int PRODUCT_PRICE_INDEX = 1;
    private static final int QUANTITY_SOLD_INDEX = 2;
    private SalesPrinter salesPrinter;
    private List<Sales> sales = new ArrayList<>();

    public Calculator(SalesPrinter salesPrinter) {
        this.salesPrinter = salesPrinter;
    }

    public void createReportFor(List<String> fileNames) {
        fileNames.stream().forEach(this::readSalesEntries);
        calculateTotalSalesRevenue(sales);
        calculateSalesRevenuePerProduct(sales);
    }

    private void readSalesEntries(String fileName) {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {
            fileReader.lines()
                    .filter(row -> !(row.contains("product") && row.contains("price")) || !row.contains("quantity"))
                    .map(line -> asList(line.split(COMMA_DELIMITER)))
                    .map(entry -> new Sales(
                            entry.get(PRODUCT_NAME_INDEX),
                            Double.valueOf(entry.get(PRODUCT_PRICE_INDEX)),
                            Integer.valueOf(entry.get(QUANTITY_SOLD_INDEX))))
                    .forEach(sales::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void calculateSalesRevenuePerProduct(List<Sales> sales) {
        Set<String> uniqueProductNameList = createUniqueProductNameList(sales);
        uniqueProductNameList.stream().forEach(productName -> calculateSalesFor(productName, sales));
    }

    private void calculateSalesFor(String productName, List<Sales> sales) {
        double productSalesRevenue = sales.stream()
                .filter(sale -> sale.productName().equals(productName))
                .mapToDouble(sale -> sale.quantitySold() * sale.price())
                .sum();

        salesPrinter.printProductSales(productName, String.format("%.2f", productSalesRevenue));
    }

    private void calculateTotalSalesRevenue(List<Sales> sales) {
        double totalSales = sales.stream().mapToDouble(sale -> sale.price() * sale.quantitySold()).sum();
        salesPrinter.printTotalSales(String.format("%.2f", totalSales));
    }

    private Set<String> createUniqueProductNameList(List<Sales> sales) {
        List<String> productNameList = sales.stream().map(Sales::productName).collect(toList());
        return new HashSet<>(productNameList);
    }
}
