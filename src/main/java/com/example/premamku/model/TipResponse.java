package com.example.premamku.model;

import java.util.List;

public class TipResponse {
    private List<Integer> mainNumbers;
    private List<Integer> extraNumbers;

    public TipResponse(List<Integer> mainNumbers, List<Integer> extraNumbers) {
        this.mainNumbers = mainNumbers;
        this.extraNumbers = extraNumbers;
    }

    public List<Integer> getMainNumbers() {
        return mainNumbers;
    }

    public void setMainNumbers(List<Integer> mainNumbers) {
        this.mainNumbers = mainNumbers;
    }

    public List<Integer> getExtraNumbers() {
        return extraNumbers;
    }

    public void setExtraNumbers(List<Integer> extraNumbers) {
        this.extraNumbers = extraNumbers;
    }

    @Override
    public String toString() {
        return "TipResponse{" +
                "mainNumbers=" + mainNumbers +
                ", extraNumbers=" + extraNumbers +
                '}';
    }
}
