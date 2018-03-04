package test;

import com.examset.Currency;
import com.examset.Helper;
import com.examset.Implementation;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.List;

public class ExchangeTest {


    Implementation imp = new Implementation();

    @BeforeAll
    static void initAll(){
        Helper.populateTraderObjects();
    }

    @Test
    public void testNameChange() {
        Assertions.assertNotEquals(Helper.traderOne.getName(), imp.changeNameOfTrader());
    }

    @Test
    public void testTradeHistoryAppend() {
        int actualLength = imp.addTradeOrderToTraderTwo();
        Assertions.assertEquals(Helper.traderTwo.getTradeHistory().length + 1, actualLength);
    }

    @Test
    public void testCombiningOfTradersHistory() {
        int expected = Helper.traderTwo.getTradeHistory().length + Helper.traderOne.getTradeHistory().length;
        int actual = imp.combineTradersHistory();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testOddTradeHistories() {
        List<Integer> temp = imp.oddTradesFromCombinedHistory();
        int actual = 0;
        for (int i = 0; i < temp.size(); i++) {
            if (temp.get(i) % 2 == 1) {
                actual ++;
            }

        }

        Assertions.assertEquals(temp.size(), actual);
    }

    @Test
    @AfterEach
    void testHighestBuyOrder() {
        int[] combineHistories = Helper.concat(Helper.traderOne.getTradeHistory(), Helper.traderTwo.getTradeHistory());
        int expected = 0;
        for (int i = 0; i < combineHistories.length; i++) {
            if (combineHistories[i] > expected) {
                expected = combineHistories[i];
            }
        }

        System.out.println(expected);
        Assertions.assertEquals(expected, imp.findTraderHighestBuyOrderInQuantity());
    }

    @Test
    void testFileWritingHighestBuyOrder() {
        imp.writeTraderHighestBuyOrderToFile();
        File checkFile = new File("highest.txt");
        //Finding highest value
        int[] combineHistories = Helper.concat(Helper.traderOne.getTradeHistory(), Helper.traderTwo.getTradeHistory());
        int expected = 0;
        for (int i = 0; i < combineHistories.length; i++) {
            if (combineHistories[i] > expected) {
                expected = combineHistories[i];
            }
        }
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
    void testFindBitcoin(TestInfo info) {
        try {
            List<Currency> allCurrencies = Helper.fromJsonToObjectCurrencies();
            String BTC = null;
            for (Currency c : allCurrencies) {
                if(c.getCurrency().equals("BTC")) {
                    BTC = c.getCurrency();
                }
            }
            Assertions.assertEquals(BTC, imp.findBitcoin());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    void testNumberOfNotActiveCoins() {
        /*List<Currency> currencies = null;
        try {
            currencies = Helper.fromJsonToObjectCurrencies();

        int index = 0;
        for (Currency c : currencies) {
            if (!c.isActive()) {
                index++;
            }
        }
        */
        Assertions.assertEquals(9, imp.falseCoinsInTheMarket());
    }
}
