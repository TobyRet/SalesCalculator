package com.codurance;

import java.io.FileWriter;
import java.io.IOException;

public class SalesPrinter {
    private static final String SALES_REPORT = "./src/main/salesrevenuedata/Sales_Report.txt";
    private FileWriter fileWriter = null;

    public void print(String salesData) {

        try {
            fileWriter = new FileWriter(SALES_REPORT, true);
            fileWriter.write(salesData);
            }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
