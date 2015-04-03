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
        try {
            List<Sale> sales = new ArrayList();
            String line;

            for(String fileName : fileNames) {
                fileReader = new BufferedReader(new FileReader(fileName));
                fileReader.readLine();
                while ((line = fileReader.readLine()) != null) {
                    String[] salesEntryData = line.split(COMMA_DELIMITER);
                    if (salesEntryData.length > 0) {
                        Sale salesEntry = new Sale(salesEntryData[PRODUCT_NAME_INDEX], Double.valueOf(salesEntryData[PRODUCT_PRICE]), Integer.valueOf(salesEntryData[QUANTITY_SOLD]));
                        sales.add(salesEntry);
                    }
                }
            }

            long totalSales = 0;

            for (Sale salesEntry : sales) {
                totalSales += salesEntry.price() * salesEntry.quanitySold();
            }

            System.out.println("Total sales revenue is £" + totalSales);
        }
        catch (IOException e) {
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
