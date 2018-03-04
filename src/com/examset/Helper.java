package com.examset;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Helper {

    private static final String CURRENCY_LOCATION = "currencies.json";
    private final String MARKETDATA_LOCATION = "/resources/marketdata.json";
    private final String MARKETSUMMARY_LOCATION = "/resources/marketsummary.json";

    public static Trader traderOne = new Trader("Roger Dawn", 29);
    public static Trader traderTwo = new Trader("John Song", 32);

    // Can be used to populate the histories of the two traders. Use it at as first step in a method
    public static Trader[] populateTraderObjects() {

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

        Trader[] traders = new Trader[] {
                traderOne,
                traderTwo
        };

        return traders;
    }

    // Adds a member to the array by copying the old array into a new array with greater length
    static int[] addElement(int[] a, int e) {
        a  = Arrays.copyOf(a, a.length + 1);
        a[a.length - 1] = e;
        return a;
    }


    public static void fillMarketObjectsToArray() {

    }

    // Concatenates two int arrays.
    public static int[] concat(int[] first, int[] second) {
        int[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }


    // Convert the Currency JSON data from the currency-file into a List of Currency Objects.
    public static List<Currency> fromJsonToObjectCurrencies() throws IOException {
        File checkFile = new File("currencies");
        BufferedReader br = new BufferedReader(new FileReader(checkFile));
        StringBuilder out = new StringBuilder();
        String line;

        while ((line = br.readLine()) != null) {
            out.append(line);

        }
        JSONArray currencyArray = new JSONArray(out.toString());
        JSONObject jsonObject;

        Currency currencyObject;
        List<Currency> currencies = new ArrayList<>();
        for (int i = 0; i < currencyArray.length(); i++) {
            jsonObject = currencyArray.getJSONObject(i);
            currencyObject = new Currency(
                    jsonObject.get("Currency").toString(),
                    jsonObject.get("CurrencyLong").toString(),
                    Integer.parseInt(jsonObject.get("MinConfirmation").toString()),
                    Double.parseDouble(jsonObject.get("TxFee").toString()),
                    Boolean.parseBoolean(jsonObject.get("IsActive").toString()),
                    jsonObject.get("CoinType").toString(),
                    jsonObject.get("BaseAddress").toString(),
                    jsonObject.get("Notice").toString()
            );
            currencies.add(currencyObject);
        }
        //System.out.println(currencies.get(1).getCurrency());

        return currencies;
    }
}
