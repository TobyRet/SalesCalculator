package com.codurance;

public class App 
{
    public static void main( String[] args ) {
        CsvFileWriter csvFileWriter = new CsvFileWriter();
        csvFileWriter.writeCsvFile("MarchSales.csv");
    }
}
