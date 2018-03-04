package com.examset;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Implementation implements ExchangeAPI {


    @Override
    public String changeNameOfTrader() {
       Helper.traderOne.setName("Michael Moon");
        return Helper.traderOne.getName();
    }

    @Override
    public int addTradeOrderToTraderTwo() {
        Helper.populateTraderObjects();
        int[] temporary = Helper.traderTwo.getTradeHistory();
        temporary = Helper.addElement(temporary, 9);

        return temporary.length;
    }

    @Override
    public int combineTradersHistory() {
        Helper.populateTraderObjects();
        int result = Helper.traderOne.getTradeHistory().length + Helper.traderTwo.getTradeHistory().length;
        return result;
    }

    @Override
    public List<Integer> oddTradesFromCombinedHistory() {
        int[] comb = Helper.concat(Helper.traderOne.getTradeHistory(), Helper.traderTwo.getTradeHistory());
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < comb.length; i++) {
            if(comb[i] % 2 == 1) {
                result.add(comb[i]);
            }
        }

        return result;
    }

    @Override
    public int findTraderHighestBuyOrderInQuantity() {
        int[] comb = Helper.concat(Helper.traderOne.getTradeHistory(), Helper.traderTwo.getTradeHistory());
        int high = 0;
        for (int aComb : comb) {

            if (aComb > high) {
                high = aComb;
            }
        }
        return high;
    }

    @Override
    public void writeTraderHighestBuyOrderToFile() {
        int[] comb = Helper.concat(Helper.traderOne.getTradeHistory(), Helper.traderTwo.getTradeHistory());
        int high = 0;
        for (int aComb : comb) {

            if (aComb > high) {
                high = aComb;
            }
        }
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("highest.txt"), "utf-8"))) {
            writer.write(String.valueOf(high));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String findBitcoin() {
        String BTC;
        try {
            List<Currency> list = Helper.fromJsonToObjectCurrencies();

            for (Currency aList : list) {
                if (aList.getCurrency().equals("BTC")) {
                    BTC = aList.getCurrency();
                    return BTC;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int falseCoinsInTheMarket() {
        try {
            List<Currency> currencies = Helper.fromJsonToObjectCurrencies();
            int index = 0;
            for (Currency c : currencies) {
                if (!c.isActive()) {
                    index++;
                }
            }
            return index;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public String coinWithHighestFee() {
        String coin = null;
        try {
            List<Currency> currencies = Helper.fromJsonToObjectCurrencies();
            double high = 0;

            for (Currency c : currencies) {

                if (c.getTxFree() > high) {
                    high = c.getTxFree();
                    coin = c.getCurrency();
                }
            }
            return coin;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public double[] sortBuyOrdersQuantityDescending() {
        return new double[0];
    }
}
