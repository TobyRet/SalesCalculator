package com.codurance;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorShould {

    @Mock SalesPrinter salesPrinter;
    private Calculator calculator;

    @Before
    public void setup() {
        calculator = new Calculator(salesPrinter);
    }

    @Test
    public void calculate_total_sales_revenue() {
        SalesEntryRecorder marchSalesEntryRecorder = new SalesEntryRecorder("./src/test/csvtestfiles/salesentries/March_Sales.csv");
        SalesEntryRecorder aprilSalesEntryRecorder = new SalesEntryRecorder("./src/test/csvtestfiles/salesentries/April_Sales.csv");
        SalesEntryRecorder maySalesEntryRecorder = new SalesEntryRecorder("./src/test/csvtestfiles/salesentries/May_Sales.csv");

        Sales marchSalesEntry1 = new Sales("Chocolate", 2, 5); // 10
        Sales marchSalesEntry2 = new Sales("Apples", 0.20, 10); // 2
        Sales marchSalesEntry3 = new Sales("Beer", 3, 5); //15
                                        // 27

        Sales aprilSalesEntry1 = new Sales("Chocolate", 2, 10); // 20
        Sales aprilSalesEntry2 = new Sales("Apples", 0.20, 10); // 2
        Sales aprilSalesEntry3 = new Sales("Beer", 3, 10); // 30
                                        // 52
        Sales maySalesEntry1 = new Sales("Chocolate", 2, 10); // 20
        Sales maySalesEntry2 = new Sales("Apples", 0.20, 5); // 1
        Sales maySalesEntry3 = new Sales("Beer", 3, 5); // 15
                                                    // 36

                                                    // 79 + 115
        List<Sales> marchSales = asList(marchSalesEntry1, marchSalesEntry2, marchSalesEntry3);
        List<Sales> aprilSales = asList(aprilSalesEntry1, aprilSalesEntry2, aprilSalesEntry3);
        List<Sales> maySales = asList(maySalesEntry1, maySalesEntry2, maySalesEntry3);

        marchSalesEntryRecorder.writeToCSV(marchSales);
        aprilSalesEntryRecorder.writeToCSV(aprilSales);
        maySalesEntryRecorder.writeToCSV(maySales);

        List<String> allSalesEntries = asList(marchSalesEntryRecorder.fileName(), aprilSalesEntryRecorder.fileName(), maySalesEntryRecorder.fileName());

        calculator.createReportFor(allSalesEntries);

        verify(salesPrinter).printTotalSales("115.00");
        verify(salesPrinter).printProductSales("Chocolate", "50.00");
        verify(salesPrinter).printProductSales("Apples", "5.00");
        verify(salesPrinter).printProductSales("Beer", "60.00");
    }
}
