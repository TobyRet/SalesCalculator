package com.codurance;

import java.util.List;

import static java.util.Arrays.asList;

public class App {
    public static final String MARCH_SALES_CSV = "./src/main/salesentries/March_Sales.csv";
    public static final String APRIL_SALES_CSV = "./src/main/salesentries/April_Sales.csv";
    public static final String JUNE_SALES_CSV = "./src/main/salesentries/June_Sales.csv";

    public static void main( String[] args ) {
        createSalesEntries();

        SalesPrinter salesPrinter = new SalesPrinter();
        Calculator salesCalculator = new Calculator(salesPrinter);
        List<String> sales = asList(MARCH_SALES_CSV, APRIL_SALES_CSV, JUNE_SALES_CSV);
        salesCalculator.calculateSalesDataFor(sales);
    }

    private static void createSalesEntries() {
        SalesEntryRecorder salesEntryRecorder = new SalesEntryRecorder();

        Sale marchSalesEntry1 = new Sale("Chocolate", 2, 3);
        Sale marchSalesEntry2 = new Sale("Apples", 0.20, 15);
        Sale marchSalesEntry3 = new Sale("Beer", 3, 5);

        Sale aprilSalesEntry1 = new Sale("Chocolate", 2, 4);
        Sale aprilSalesEntry2 = new Sale("Apples", 0.20, 17);
        Sale aprilSalesEntry3 = new Sale("Beer", 3, 8);

        Sale juneSalesEntry1 = new Sale("Chocolate", 2, 10);
        Sale juneSalesEntry2 = new Sale("Apples", 0.20, 3);
        Sale juneSalesEntry3 = new Sale("Beer", 3, 3);

        List<Sale> marchSales = asList(marchSalesEntry1, marchSalesEntry2, marchSalesEntry3);
        List<Sale> aprilSales = asList(aprilSalesEntry1, aprilSalesEntry2, aprilSalesEntry3);
        List<Sale> juneSales = asList(juneSalesEntry1, juneSalesEntry2, juneSalesEntry3);

        salesEntryRecorder.writeCsvFile(MARCH_SALES_CSV, marchSales);
        salesEntryRecorder.writeCsvFile(APRIL_SALES_CSV, aprilSales);
        salesEntryRecorder.writeCsvFile(JUNE_SALES_CSV, juneSales);
    }
}
