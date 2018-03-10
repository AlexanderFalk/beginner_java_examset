package com.examset;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DataDriver {
    public static JSONObject getData() {
        JSONObject jsonObject = null;
        try {
            URL url = new URL("https://bittrex.com/api/v1.1/public/getcurrencies");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            JSONArray currencyArray = new JSONArray(content.toString());


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
            return jsonObject;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String readCSVData() {
        String csv = "datafile1.csv";
        String line;
        StringBuilder result = null;
        try(BufferedReader reader = new BufferedReader(new FileReader(csv))) {
            while ((line = reader.readLine()) != null) {

                result.append(line);
            }
            return Objects.requireNonNull(result).toString();

        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }

        return null;
    }
    public static int numberOfRowsCSVData() {
        String csv = "datafile1.csv";
        int index = 0;
        try(BufferedReader reader = new BufferedReader(new FileReader(csv))) {
            while (reader.readLine() != null) {
                index++;
            }
            return index;

        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }

        return index;
    }

}
