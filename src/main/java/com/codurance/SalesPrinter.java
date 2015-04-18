package com.codurance;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SalesPrinter {
    private final String SALES_REPORT = "./src/main/salesrevenuedata/Sales_Report.txt";

    public void printTotalSales(String totalSales) {
        try(BufferedWriter fileWriter = new BufferedWriter(new FileWriter(SALES_REPORT))) {
            fileWriter.write("Total sales: " + totalSales + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printProductSales(String product, String productRevenue) {
        try(BufferedWriter fileWriter = new BufferedWriter(new FileWriter(SALES_REPORT))) {
            fileWriter.write("Total product sales for " + product +": " + productRevenue + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
