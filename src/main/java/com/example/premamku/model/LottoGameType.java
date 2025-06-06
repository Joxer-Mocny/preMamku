package com.example.premamku.model;

import java.util.Arrays;
import java.util.Optional;

public enum LottoGameType {
    LOTO_5_Z_35("https://www.tipos.sk/loterie/ciselne-loterie/informacie-k-loteriam/archiv-vyzrebovanych-cisel?file=loto5z35", 5, 0, 35),
    EXTRA_VYPLATA("https://www.tipos.sk/loterie/ciselne-loterie/informacie-k-loteriam/archiv-vyzrebovanych-cisel?file=extravyplata", 7, 0, 33),
    LOTO_1("https://www.tipos.sk/loterie/ciselne-loterie/informacie-k-loteriam/archiv-vyzrebovanych-cisel?file=loto1", 6, 1, 49),
    LOTO_2("https://www.tipos.sk/loterie/ciselne-loterie/informacie-k-loteriam/archiv-vyzrebovanych-cisel?file=loto2", 6, 1, 49),
    KENO_10("https://www.tipos.sk/loterie/ciselne-loterie/informacie-k-loteriam/archiv-vyzrebovanych-cisel?file=keno10", 10, 0, 80),
    KENO_JOKER("https://www.tipos.sk/loterie/ciselne-loterie/informacie-k-loteriam/archiv-vyzrebovanych-cisel?file=kenojoker", 5, 0, 90),
    JOKER("https://www.tipos.sk/loterie/ciselne-loterie/informacie-k-loteriam/archiv-vyzrebovanych-cisel?file=joker", 6, 0, 9), // 6-miestne čísla
    EUROJACKPOT("https://www.tipos.sk/loterie/ciselne-loterie/informacie-k-loteriam/archiv-vyzrebovanych-cisel?file=eurojackpot", 5, 2, 50), // plus dodatkové 1–12
    EUROJACKPOT_JOKER("https://www.tipos.sk/loterie/ciselne-loterie/informacie-k-loteriam/archiv-vyzrebovanych-cisel?file=eurojackpotjoker", 6, 0, 9),
    EUROMILIONY("https://www.tipos.sk/loterie/ciselne-loterie/informacie-k-loteriam/archiv-vyzrebovanych-cisel?file=euromillions", 5, 2, 50),
    EUROMILIONY_JOKER("https://www.tipos.sk/loterie/ciselne-loterie/informacie-k-loteriam/archiv-vyzrebovanych-cisel?file=eurojackpotjoker", 6, 0, 9);;

    private final String csvUrl;
    private final int mainCount;
    private final int extraCount;
    private final int maxNumber;

    LottoGameType(String csvUrl, int mainCount, int extraCount, int maxNumber) {
        this.csvUrl = csvUrl;
        this.mainCount = mainCount;
        this.extraCount = extraCount;
        this.maxNumber = maxNumber;
    }

    public String getCsvUrl() {
        return csvUrl;
    }

    public int getMainCount() {
        return mainCount;
    }

    public int getExtraCount() {
        return extraCount;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public static Optional<LottoGameType> fromName(String name) {
        return Arrays.stream(values())
                .filter(gt -> gt.name().equalsIgnoreCase(name))
                .findFirst();
    }
}
