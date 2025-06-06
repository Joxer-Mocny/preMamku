package com.example.premamku.service;

import com.example.premamku.util.DataFetcher;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class Keno5AnalyzerService {

    public List<List<Integer>> findUnseenCombinations() {
        List<List<Integer>> pastDraws = DataFetcher.loadDrawsFromTipos("LOTO_5_Z_35");

        Set<Set<Integer>> seen = pastDraws.stream()
                .map(HashSet::new)
                .collect(Collectors.toSet());

        List<List<Integer>> unseen = new ArrayList<>();

        List<Integer> allNumbers = new ArrayList<>();
        for (int i = 1; i <= 35; i++) allNumbers.add(i);

        // Vygeneruj všetky kombinácie 5 z 35
        generateCombinations(allNumbers, 5, 0, new ArrayList<>(), unseen, seen);

        return unseen;
    }

    private void generateCombinations(List<Integer> nums, int k, int start,
                                      List<Integer> current,
                                      List<List<Integer>> result,
                                      Set<Set<Integer>> seen) {

        if (current.size() == k) {
            Set<Integer> comboSet = new HashSet<>(current);
            if (!seen.contains(comboSet)) {
                result.add(new ArrayList<>(current));
            }
            return;
        }

        for (int i = start; i < nums.size(); i++) {
            current.add(nums.get(i));
            generateCombinations(nums, k, i + 1, current, result, seen);
            current.remove(current.size() - 1);
        }
    }
}
