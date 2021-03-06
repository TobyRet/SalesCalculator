package com.codurance;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SalesEntryRecorder {

    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";

    private static final String FILE_HEADER = "productName, price, quantity sold";

    private String fileName;

    public SalesEntryRecorder(String fileName) {
        this.fileName = fileName;
    }

    public String fileName() {
        return fileName;
    }

    public void writeToCSV(List<Sales> sales) {
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(fileName);
            fileWriter.append(FILE_HEADER.toString());
            fileWriter.append(NEW_LINE_SEPARATOR);

            for (Sales salesEntry : sales) {
                fileWriter.append(salesEntry.productName());
                fileWriter.append(COMMA_DELIMITER);

                fileWriter.append(String.valueOf(salesEntry.price()));
                fileWriter.append(COMMA_DELIMITER);

                fileWriter.append(String.valueOf(salesEntry.quantitySold()));
                fileWriter.append(NEW_LINE_SEPARATOR);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
