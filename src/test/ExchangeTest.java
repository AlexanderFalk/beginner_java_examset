package test;


import com.examset.DataDriver;
import com.examset.Helper;
import com.examset.Implementation;
import com.examset.Trader;
import org.hamcrest.MatcherAssert;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.List;

public class ExchangeTest {


    private Implementation imp = new Implementation();
    private static Trader traderOne = new Trader("Roger Dawn", 29);
    private static Trader traderTwo = new Trader("John Song", 32);
    private static int repetition;
    @BeforeAll
    static void beforeEach(){
        int[] historyOne = {
                2,
                9,
                5,
                7,
                12,
                1
        };

        int[] historyTwo = {
                2,
                3,
                2,
                20,
                13,
                25
        };

        traderOne.setTradeHistory(historyOne);
        traderTwo.setTradeHistory(historyTwo);

        repetition = DataDriver.numberOfRowsCSVData();
    }

    @Test
    void testNameChange() {
        String oldname = traderOne.getName();
        String newName = imp.changeNameOfTrader(traderOne, "Sally Mother");
        Assertions.assertNotEquals(oldname, newName);
    }

    // After all is set to avoid the tradertwo being changed during the other tests
    @Test
    @AfterAll
    static void testTradeHistoryAppend() {
        Implementation impl = new Implementation();
        int expected = traderTwo.getTradeHistory().length + 1;
        traderTwo = impl.addTradeOrder(traderTwo, 77);
        Assertions.assertEquals(expected, traderTwo.getTradeHistory().length);
    }

    // Data Driven Test 1
    @Test
    void testDataDrivenTradeHistoryAppend() {
        //int expected = traderTwo.getTradeHistory().length + 1;
        int actualLength;
        // Arrange
        Trader testTrader = new Trader("Roger Dawn", 29);
        int[] history = {
                2,
                9,
                5,
                7,
                12,
                1
        };
        testTrader.setTradeHistory(history);
        String csv = "datafile1.csv";
        String line;
        String split = ";";
        String[] values;

        // Act
        try(BufferedReader reader = new BufferedReader(new FileReader(csv))) {
            while ((line = reader.readLine()) != null) {
                values = line.split(split);
                if (!values[2].equals("Value")) {
                    testTrader = imp.addTradeOrder(testTrader, Integer.parseInt(values[2]));
                    // Assert
                    System.out.println("Expected: " + values[3] + " - Actual: " + testTrader.getTradeHistory().length);
                    Assertions.assertEquals(Integer.parseInt(values[3]), testTrader.getTradeHistory().length);
                }
            }

        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testCombiningOfTradersHistory() {
        int expected = traderTwo.getTradeHistory().length + traderOne.getTradeHistory().length;
        int actual = imp.combineTradersHistory(traderOne, traderTwo);
        Assertions.assertEquals(expected, actual);
    }

    @RepeatedTest(1)
    void testOddTradeHistories(RepetitionInfo repetitionInfo) {
        List<Integer> actual = imp.oddTradesFromCombinedHistory(traderOne, traderTwo);
        int expected = 6 + repetitionInfo.getTotalRepetitions();

        Assertions.assertEquals(expected, actual.size());
    }

    // Hamcrest list test - 1
    @Test
    void testOddTradeHistoriesHamcrest() {
        List<Integer> actual = imp.oddTradesFromCombinedHistory(traderOne, traderTwo);

        MatcherAssert.assertThat(actual, containsInAnyOrder(9, 5, 7, 1, 3, 13, 25));
    }

    @Test
    void testHighestBuyOrder() {
        Implementation impl = new Implementation();
        int expected = 25;
        int actual = impl.findTraderHighestBuyOrderInQuantity(traderOne, traderTwo);
        Assertions.assertEquals(expected, actual);
    }

    // Data Driven Test 2
    @Test
    void testDataDrivenHighestBuyOrder() {
        // Arrange
        Trader testTrader = new Trader("Roger Dawn", 29);
        int[] history = {
                2,
                9,
                5,
                7,
                12,
                1
        };
        testTrader.setTradeHistory(history);
        String csv = "datafile2.csv";
        String line;
        String split = ";";
        String[] values;
        int actual;
        // Act
        try(BufferedReader reader = new BufferedReader(new FileReader(csv))) {
            while ((line = reader.readLine()) != null) {
                values = line.split(split);
                if (!values[2].equals("Value")) {
                    testTrader = imp.addTradeOrder(testTrader, Integer.parseInt(values[2]));
                    actual = imp.findTraderHighestBuyOrderInQuantity(testTrader, traderTwo);
                    // Assert
                    System.out.println("Expected: " + values[3] + " - Actual: " + actual);
                    Assertions.assertEquals(Integer.parseInt(values[3]), actual);
                }
            }

        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }


    @Test
    void testFileWritingHighestBuyOrder() {
        imp.writeTraderHighestBuyOrderToFile(traderOne, traderTwo);
        File checkFile = new File("highest.txt");
        int expected = 25;
        if(checkFile.exists() && !checkFile.isDirectory()) {
            Assertions.assertTrue(true);
            try {
                BufferedReader reader = new BufferedReader(new FileReader(checkFile));
                String actual = reader.readLine();

                Assertions.assertEquals(java.util.Optional.of(expected), java.util.Optional.of(Integer.valueOf(actual)));
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Failure - no file exists with name: highest.txt");
        }

    }

    @Test
    void testFindBitcoin() {
        String expected = "BTC";
        try {
            String actual = imp.findBitcoin(Helper.fromJsonToObjectCurrencies());
            Assertions.assertEquals(expected, actual);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Test with Hamcrest tool - 2
    @Test
    void testFindBitcoinHamcrest() {
        String expected = "BTC";
        try {
            String actual = imp.findBitcoin(Helper.fromJsonToObjectCurrencies());
            MatcherAssert.assertThat(actual, is(equalTo(expected)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    void testNumberOfNotActiveCoins() {
        try {
            int expected = 9;
            int actual = imp.falseCoinsInTheMarket(Helper.fromJsonToObjectCurrencies());
            Assertions.assertEquals(expected, actual);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Test with Hamcrest tool - 3
    @Test
    void testNumberOfNotActiveCoinsHamcrest() {
        try {
            int expected = 9;
            int actual = imp.falseCoinsInTheMarket(Helper.fromJsonToObjectCurrencies());
            MatcherAssert.assertThat(actual, is(equalTo(expected)));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    void testCoinWithHighestFee() {
        try {
            String expected = "COVAL";
            String actual = imp.coinWithHighestFee(Helper.fromJsonToObjectCurrencies());
            Assertions.assertEquals(expected, actual);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Data driven testing
    @RepeatedTest(10)
    private void testRollingData() {

    }
}
