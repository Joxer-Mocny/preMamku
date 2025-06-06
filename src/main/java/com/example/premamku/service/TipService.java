package com.example.premamku.service;

import com.example.premamku.model.LottoGameType;
import com.example.premamku.model.TipResponse;
import com.example.premamku.model.GenerationMode;
import com.example.premamku.util.DataFetcher;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TipService {

    public TipResponse generateTip(String gameName, String modeStr) {
        LottoGameType gameType = LottoGameType.fromName(gameName)
                .orElseThrow(() -> new IllegalArgumentException("Neznáma hra: " + gameName));

        GenerationMode mode = GenerationMode.valueOf(modeStr.toUpperCase());
        List<List<Integer>> allDraws = DataFetcher.loadDrawsFromTipos(gameName);

        Map<Integer, Integer> frequency = new HashMap<>();
        for (List<Integer> draw : allDraws) {
            for (Integer number : draw) {
                frequency.put(number, frequency.getOrDefault(number, 0) + 1);
            }
        }

        List<Map.Entry<Integer, Integer>> sorted = frequency.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .toList();

        List<Integer> result;

        switch (mode) {
            case FREQUENT -> result = sorted.stream()
                    .sorted((a, b) -> b.getValue() - a.getValue())
                    .limit(gameType.getMainCount())
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());

            case RARE -> result = sorted.stream()
                    .limit(gameType.getMainCount())
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());

            case SMART -> {
                int half = gameType.getMainCount() / 2;
                List<Integer> mostCommon = sorted.stream()
                        .sorted((a, b) -> b.getValue() - a.getValue())
                        .limit(half * 2)
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toList());

                List<Integer> leastCommon = sorted.stream()
                        .limit(half * 2)
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toList());

                Set<Integer> pool = new HashSet<>();
                pool.addAll(mostCommon);
                pool.addAll(leastCommon);

                List<Integer> mixedPool = new ArrayList<>(pool);
                Collections.shuffle(mixedPool);

                result = mixedPool.stream()
                        .limit(gameType.getMainCount())
                        .sorted()
                        .collect(Collectors.toList());
            }

            default -> throw new IllegalStateException("Nepodporovaný mód generovania");
        }

        // ✨ Výber dodatkových čísel
        List<Integer> extraNumbers = new ArrayList<>();
        if (gameType.getExtraCount() > 0) {
            extraNumbers = sorted.stream()
                    .map(Map.Entry::getKey)
                    .filter(n -> !result.contains(n))
                    .limit(gameType.getExtraCount())
                    .collect(Collectors.toList());
        }

        return new TipResponse(result, extraNumbers);
    }
}
