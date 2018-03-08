package com.examset;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Trader traderOne = new Trader("Roger Dawn", 29);
        Trader traderTwo = new Trader("John Song", 32);
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
}
