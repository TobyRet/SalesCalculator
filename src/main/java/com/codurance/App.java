package com.codurance;

import java.util.List;

import static java.util.Arrays.asList;

public class App {
    public static void main( String[] args ) {
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

        salesEntryRecorder.writeCsvFile("MarchSales.csv", marchSales);
        salesEntryRecorder.writeCsvFile("AprilSales.csv", aprilSales);
        salesEntryRecorder.writeCsvFile("JuneSales.csv", juneSales);

        SalesCalculator salesCalculator = new SalesCalculator();
        List<String> sales = asList("MarchSales.csv", "AprilSales.csv", "JuneSales.csv");
        salesCalculator.calculateSales(sales);
    }
}
