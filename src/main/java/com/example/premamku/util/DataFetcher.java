package com.example.premamku.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DataFetcher {

    public static List<List<Integer>> loadDrawsFromCsv() {
        List<List<Integer>> draws = new ArrayList<>();

        try {
            InputStream is = DataFetcher.class.getClassLoader().getResourceAsStream("data/sportka.csv");

            if (is == null) {
                throw new RuntimeException("Súbor sportka.csv nebol nájdený!");
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                List<Integer> numbers = new ArrayList<>();
                for (String token : tokens) {
                    numbers.add(Integer.parseInt(token.trim()));
                }
                draws.add(numbers);
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return draws;
    }
}
