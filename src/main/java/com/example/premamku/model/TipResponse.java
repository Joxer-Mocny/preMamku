package com.example.premamku.model;

import java.util.List;

public class TipResponse {

    private List<Integer> tip;
    private Integer additional;

    public TipResponse(List<Integer> tip, Integer additional) {
        this.tip = tip;
        this.additional = additional;
    }

    public List<Integer> getTip(){
        return tip;
    }

    public Integer getAdditional(){
        return additional;
    }

    public void setTip(List<Integer> tip){
        this.tip = tip;
    }

    public void setAdditional(Integer additional){
        this.additional = additional;
    }
}
