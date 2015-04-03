package com.codurance;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SalesEntryWriter {

    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";

    private static final String FILE_HEADER = "product, price, quantity sold";

    public void writeCsvFile(String fileName, List<Sale> sales) {
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
