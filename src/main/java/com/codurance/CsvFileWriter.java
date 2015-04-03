package com.codurance;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;

public class CsvFileWriter {

    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";

    private static final String FILE_HEADER = "product, price, quantity sold";

    public void writeCsvFile(String fileName) {
        Sale salesEntry1 = new Sale("Chocolate", 2.20, 3);
        Sale salesEntry2 = new Sale("Apples", 0.20, 15);
        Sale salesEntry3 = new Sale("Beer", 3.00, 5);

        List<Sale> sales = asList(salesEntry1, salesEntry2, salesEntry3);

        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(fileName);
            fileWriter.append(FILE_HEADER.toString());
            fileWriter.append(NEW_LINE_SEPARATOR);

            for (Sale salesEntry : sales) {
                fileWriter.append(salesEntry.product());
                fileWriter.append(COMMA_DELIMITER);

                fileWriter.append(String.valueOf(salesEntry.price()));
                fileWriter.append(COMMA_DELIMITER);

                fileWriter.append(String.valueOf(salesEntry.quanitySold()));
                fileWriter.append(NEW_LINE_SEPARATOR);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("CSV file was created successfully");
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
