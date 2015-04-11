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

        Sales marchSalesEntry1 = new Sales("Chocolate", 2, 3);
        Sales marchSalesEntry2 = new Sales("Apples", 0.20, 15);
        Sales marchSalesEntry3 = new Sales("Beer", 3, 5);

        Sales aprilSalesEntry1 = new Sales("Chocolate", 2, 4);
        Sales aprilSalesEntry2 = new Sales("Apples", 0.20, 17);
        Sales aprilSalesEntry3 = new Sales("Beer", 3, 8);

        Sales juneSalesEntry1 = new Sales("Chocolate", 2, 10);
        Sales juneSalesEntry2 = new Sales("Apples", 0.20, 3);
        Sales juneSalesEntry3 = new Sales("Beer", 3, 3);

        List<Sales> marchSales = asList(marchSalesEntry1, marchSalesEntry2, marchSalesEntry3);
        List<Sales> aprilSales = asList(aprilSalesEntry1, aprilSalesEntry2, aprilSalesEntry3);
        List<Sales> juneSales = asList(juneSalesEntry1, juneSalesEntry2, juneSalesEntry3);

        salesEntryRecorder.writeCsvFile(MARCH_SALES_CSV, marchSales);
        salesEntryRecorder.writeCsvFile(APRIL_SALES_CSV, aprilSales);
        salesEntryRecorder.writeCsvFile(JUNE_SALES_CSV, juneSales);
    }
}
