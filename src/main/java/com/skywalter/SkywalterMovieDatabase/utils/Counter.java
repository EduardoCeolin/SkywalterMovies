package com.skywalter.SkywalterMovieDatabase.utils;

import lombok.Data;

@Data
public class Counter implements Comparable<Counter> {

    private Character letra;
    private Integer quantidade;

    public Counter(){}

    public Counter(Character letra, Integer quantidade) {
        this.letra = letra;
        this.quantidade = quantidade;
    }

    @Override
    public int compareTo(Counter o) {
        return o.quantidade.compareTo(quantidade);
    }

}
