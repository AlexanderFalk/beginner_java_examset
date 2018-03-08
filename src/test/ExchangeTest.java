package test;

import com.examset.Currency;
import com.examset.Helper;
import com.examset.Implementation;
import com.examset.Trader;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.List;

public class ExchangeTest {


    private Implementation imp = new Implementation();
    private static Trader traderOne = new Trader("Roger Dawn", 29);
    private static Trader traderTwo = new Trader("John Song", 32);

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

    }

    @Test
    private void testNameChange() {
        String newName = imp.changeNameOfTrader(traderOne, "Sally");
        Assertions.assertNotEquals(traderOne.getName(), newName);
    }

    @Test
    @AfterEach
    private void testTradeHistoryAppend() {
        int expected = traderTwo.getTradeHistory().length + 1;
        int actualLength = imp.addTradeOrderToTraderTwo(traderOne, 77);
        Assertions.assertEquals(expected, actualLength);
    }

    @Test
    private void testCombiningOfTradersHistory() {
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


    @Test
    @AfterEach
    void testHighestBuyOrder() {
        int expected = 25;
        int actual = imp.findTraderHighestBuyOrderInQuantity(traderOne, traderTwo);
        Assertions.assertEquals(expected, actual);
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
}
