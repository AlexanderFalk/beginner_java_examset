package com.examset;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Implementation implements ExchangeAPI {


    @Override
    public String changeNameOfTrader(Trader trader, String name) {
        trader.setName(name);
        return trader.getName();
    }

    @Override
    public Trader addTradeOrder(Trader trader, int order) {

        trader.setTradeHistory(Helper.addElement(trader.getTradeHistory(), order));

        return trader;
    }

    @Override
    public int combineTradersHistory(Trader traderOne, Trader traderTwo) {
        int result = traderOne.getTradeHistory().length + traderTwo.getTradeHistory().length;
        return result;
    }

    @Override
    public List<Integer> oddTradesFromCombinedHistory(Trader traderOne, Trader traderTwo) {
        int[] comb = Helper.concat(traderOne.getTradeHistory(), traderTwo.getTradeHistory());
        List<Integer> result = new ArrayList<>();
        for (int aComb : comb) {
            if (aComb % 2 == 1) {
                System.out.println(aComb);
                result.add(aComb);
            }
        }

        return result;
    }

    @Override
    public int findTraderHighestBuyOrderInQuantity(Trader traderOne, Trader traderTwo) {
        int[] comb = Helper.concat(traderOne.getTradeHistory(), traderTwo.getTradeHistory());
        int high = 0;
        for (int aComb : comb) {

            if (aComb > high) {
                high = aComb;
            }
        }
        return high;
    }

    @Override
    public void writeTraderHighestBuyOrderToFile(Trader traderOne, Trader traderTwo) {
        int[] comb = Helper.concat(traderOne.getTradeHistory(), traderTwo.getTradeHistory());
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
    public String findBitcoin(List<Currency> currencies) {
        String BTC;
        for (Currency aList : currencies) {
            if (aList.getCurrency().equals("BTC")) {
                BTC = aList.getCurrency();
                return BTC;
            }
        }
        return null;
    }

    @Override
    public int falseCoinsInTheMarket(List<Currency> currencies) {
        int index = 0;
        for (Currency c : currencies) {
            if (!c.isActive()) {
                index++;
            }
        }
        return index;
    }

    @Override
    public String coinWithHighestFee(List<Currency> currencies) {
        String coin = null;
        double high = 0;

        for (Currency c : currencies) {

            if (c.getTxFree() > high) {
                high = c.getTxFree();
                coin = c.getCurrency();
            }
        }
        return coin;
    }

    @Override
    public double[] sortBuyOrdersQuantityDescending(List<Currency> currencies) {
        return new double[0];
    }
}
