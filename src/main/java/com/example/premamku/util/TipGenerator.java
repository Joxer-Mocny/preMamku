package com.example.premamku.util;

import java.util.*;

public class TipGenerator {

    public static List<Integer> generateMostFrequentTip(List<List<Integer>> draws) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        for (List<Integer> draw : draws) {
            for (int i = 0; i < 5 && i < draw.size(); i++) { // prvých 5 čísel z každého ťahu
                int num = draw.get(i);
                frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
            }
        }

        return frequencyMap.entrySet()
                .stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .limit(5)
                .map(Map.Entry::getKey)
                .sorted()
                .toList();
    }
}
