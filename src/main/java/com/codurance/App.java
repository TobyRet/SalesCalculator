package com.codurance;

import java.util.List;

import static java.util.Arrays.asList;

public class App
{
    public static void main( String[] args ) {
        Sale marchSalesEntry1 = new Sale("Chocolate", 2.20, 3);
        Sale marchSalesEntry2 = new Sale("Apples", 0.20, 15);
        Sale marchSalesEntry3 = new Sale("Beer", 3.00, 5);

        List<Sale> marchSales = asList(marchSalesEntry1, marchSalesEntry2, marchSalesEntry3);

        SalesEntryWriter salesEntryWriter = new SalesEntryWriter();
        salesEntryWriter.writeCsvFile("MarchSales.csv", marchSales);
    }
}
