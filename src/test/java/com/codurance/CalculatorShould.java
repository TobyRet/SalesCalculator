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

    private static final String FIRST_MONTH_SALES_CSV = "./src/test/csvtestfiles/FirstMonthSales_Test.csv";
    private static final String SECOND_MONTH_SALES_CSV = "./src/test/csvtestfiles/SecondMonthSales_Test.csv";
    @Mock SalesPrinter salesPrinter;
    private Calculator calculator;
    private List<String> salesEntries;

    @Before
    public void setup() {
        createSalesEntries();
        calculator = new Calculator(salesPrinter);
        salesEntries = asList(FIRST_MONTH_SALES_CSV, SECOND_MONTH_SALES_CSV);
    }

    @Test
    public void calculate_total_sales_revenue() {
        String expectedTotalSales = "27.00";
        String expectedChocolateSalesRevenue = "21.00";
        String expectedApplesSalesRevenue = "6.00";

        calculator.calculateSalesDataFor(salesEntries);
        verify(salesPrinter).print("Total sales revenue is £" + expectedTotalSales + "\n");
        verify(salesPrinter).print("Total sales of Chocolate: £" + expectedChocolateSalesRevenue + "\n");
        verify(salesPrinter).print("Total sales of Apples: £" + expectedApplesSalesRevenue + "\n");
    }

    private void createSalesEntries() {
        Sales firstMonthSalesEntry1 = new Sales("Chocolate", 2, 3);
        Sales firstMonthSalesEntry2 = new Sales("Apples", 0.20, 10);

        Sales secondMonthSalesEntry1 = new Sales("Chocolate", 5, 3);
        Sales secondMonthSalesEntry2 = new Sales("Apples", 0.20, 20);

        List<Sales> firstMonthSales = asList(firstMonthSalesEntry1, firstMonthSalesEntry2);
        List<Sales> secondMonthSales = asList(secondMonthSalesEntry1, secondMonthSalesEntry2);

        SalesEntryRecorder salesEntryRecorder = new SalesEntryRecorder();

        salesEntryRecorder.writeCsvFile(FIRST_MONTH_SALES_CSV, firstMonthSales);
        salesEntryRecorder.writeCsvFile(SECOND_MONTH_SALES_CSV, secondMonthSales);
    }
}
