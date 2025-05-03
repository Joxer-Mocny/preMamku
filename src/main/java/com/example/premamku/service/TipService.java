package com.example.premamku.service;

import com.example.premamku.model.TipResponse;
import com.example.premamku.util.DataFetcher;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TipService {

    public TipResponse generateTip(String hra) {
        List<List<Integer>> allDraws = DataFetcher.loadDrawsFromCsv();

        Map<Integer, Integer> mainNumberFrequency = new HashMap<>();
        Map<Integer, Integer> additionaNumberFrequency = new HashMap<>();

        for (List<Integer> draws : allDraws) {
            for (int i = 0; i < draws.size(); i++) {
                int number = draws.get(i);
                if (i < 6) {
                    mainNumberFrequency.put(number, mainNumberFrequency.getOrDefault(number, 0) + 1);
                } else {
                    additionaNumberFrequency.put(number, additionaNumberFrequency.getOrDefault(number, 0) + 1);
                }
            }
        }

        // Top 6 hlavných čísel
        List<Integer> mainNumbers = mainNumberFrequency.entrySet().stream()
                .sorted((a, b) -> b.getValue() - a.getValue())
                .limit(6)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        // Najčastejšie dodatkové číslo
        Integer additional = additionaNumberFrequency.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(0);

        return new TipResponse(mainNumbers, additional);
    }
}
