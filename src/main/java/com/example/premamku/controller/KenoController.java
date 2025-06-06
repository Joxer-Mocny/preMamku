package com.example.premamku.controller;

import com.example.premamku.service.Keno5AnalyzerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/keno5")
public class KenoController {

    private final Keno5AnalyzerService service;

    public KenoController(Keno5AnalyzerService service) {
        this.service = service;
    }

    @GetMapping("/unseen")
    public List<List<Integer>> getUnseenCombinations() {
        return service.findUnseenCombinations();
    }
}
