package com.examset;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Helper {

    // Adds a member to the array by copying the old array into a new array with greater length
    static int[] addElement(int[] a, int e) {
        a  = Arrays.copyOf(a, a.length + 1);
        a[a.length - 1] = e;
        return a;
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
