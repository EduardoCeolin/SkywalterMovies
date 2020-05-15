package com.skywalter.SkywalterMovieDatabase.service;

import com.skywalter.SkywalterMovieDatabase.dto.MovieDTO;
import com.skywalter.SkywalterMovieDatabase.utils.Counter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LetterCounter {

    private final List<MovieDTO> movies;

    public LetterCounter(List<MovieDTO> movies) {
        this.movies = movies;
    }

    public List<Counter> count() {
        String joinnedTitles = joinTitlesAsString();
        Map<Character, Integer> counterMap = createCounterMap(joinnedTitles);
        return getTop10Letter(counterMap);
    }

    private List<Counter> getTop10Letter(Map<Character, Integer> counterMap) {
        return counterMap.entrySet().stream()
                .map(character -> new Counter(character.getKey(), character.getValue()))
                .sorted()
                .limit(10)
                .collect(Collectors.toList());
    }

    private Map<Character, Integer> createCounterMap(String joinnedTitles) {
        Map<Character, Integer> counter = new HashMap<>();
        for (char character : joinnedTitles.toCharArray()) {
            counter.put(character, counter.containsKey(character) ?
                    (counter.get(character) + 1) : 1);
        }
        return counter;
    }

    private String joinTitlesAsString() {
        return movies.stream()
                .map(MovieDTO::getTitle)
                .collect(Collectors.joining(""))
                .trim()
                .toLowerCase();
    }

}
