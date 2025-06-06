package com.example.premamku.util;

import com.example.premamku.model.LottoGameType;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DataFetcher {

    public static List<List<Integer>> loadDrawsFromTipos(String game) {
        List<List<Integer>> draws = new ArrayList<>();

        try {
            LottoGameType gameType = LottoGameType.fromName(game)
                    .orElseThrow(() -> new IllegalArgumentException("Neznáma hra: " + game));

            URL url = new URL(gameType.getCsvUrl());
            InputStream input = url.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // skip header
                    continue;
                }

                String[] tokens = line.split(";");
                List<Integer> numbers = new ArrayList<>();

                // Predpokladáme, že čísla sú v stĺpcoch 2 až 6 (index 1-5), môžeš upraviť podľa CSV
                for (int i = 1; i <= 5 && i < tokens.length; i++) {
                    try {
                        numbers.add(Integer.parseInt(tokens[i].trim()));
                    } catch (NumberFormatException ignored) {
                        // nie každé pole musí byť číselné, napr. prázdne na konci
                    }
                }

                if (!numbers.isEmpty()) {
                    draws.add(numbers);
                }
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return draws;
    }
}
