package com.example.premamku.controller;

import com.example.premamku.model.TipResponse;
import com.example.premamku.service.TipService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TipController {

    private final TipService tipService ;

    public TipController(TipService tipService){
        this.tipService = tipService;
    }

    @GetMapping("/tip")
    public TipResponse getTipResponse (@RequestParam String hra){
        return tipService.generateTip(hra);
    }
}
