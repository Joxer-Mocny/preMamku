package com.example.premamku.controller;

import com.example.premamku.model.TipResponse;
import com.example.premamku.service.TipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TipController {

    private final TipService tipService;

    @Autowired
    public TipController(TipService tipService) {
        this.tipService = tipService;
    }

    @GetMapping("/tip")
    public ResponseEntity<TipResponse> getTip(
            @RequestParam String game,
            @RequestParam(defaultValue = "frequent") String mode
    ) {
        return ResponseEntity.ok(tipService.generateTip(game, mode));
    }
}
