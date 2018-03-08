package com.examset;

import java.util.List;

/**
 * All the helper methods are defined in helper.java.
 */
public interface ExchangeAPI {
    // Exercise [0]
    // Change the name of Trader One.
    String changeNameOfTrader(Trader trader, String name);

    // Exercise [1]
    // Add a trade order to the traders buy history
    // HINT: You can use the addElement() method from the Helper class
    int addTradeOrderToTraderTwo(Trader trader, int order);

    // Exercise [2]
    // From the helper method, populateTraderObjects, take the two Trader objects out of the array [0] [1],
    // combine their buy histories together, and return the total size.
    int combineTradersHistory(Trader traderOne, Trader traderTwo);

    // Exercise [3]
    // Find all the odd trades from both histories of the traders. Return an array of the odd numbers.
    List<Integer> oddTradesFromCombinedHistory(Trader traderOne, Trader TraderTwo);

    // Exercise [4]
    // Find the highest trade order from the combined history of trader one and two.
    int findTraderHighestBuyOrderInQuantity(Trader trader, Trader TraderTwo);

    // Exercise [5]
    // Get the highest trading quantity and write it to a file
    // Remember to name the file: highest.txt
    void writeTraderHighestBuyOrderToFile(Trader trader, Trader TraderTwo);

    // Exercise [6]
    // Find bitcoin in the chunk of data retrieved from the network request.
    String findBitcoin(List<Currency> currencies);

    // Exercise [7]
    // From the whole market summary, find and filter the USDT-OMG conversion out.
    int falseCoinsInTheMarket(List<Currency> currencies);

    // Exercise [8]
    // Filter all the buy orders out
    String coinWithHighestFee(List<Currency> currencies);

    // Exercise [9]
    // From the filter before with buy order - sort them in quantity bought in descending order.
    double[] sortBuyOrdersQuantityDescending(List<Currency> currencies);
}
