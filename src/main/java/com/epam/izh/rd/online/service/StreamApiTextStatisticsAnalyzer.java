package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Данный класс обязан использовать StreamApi из функционала Java 8. Функциональность должна быть идентична
 * {@link SimpleTextStatisticsAnalyzer}.
 */
public class StreamApiTextStatisticsAnalyzer implements TextStatisticsAnalyzer {

    private final String regx = "[\\s,.!\"-]+";


    @Override
    public int countSumLengthOfWords(String text) {
        return Stream.of(text)
                .map(s -> s.split(regx))
                .flatMap(Arrays::stream)
                .mapToInt(String::length)
                .sum();
    }

    @Override
    public int countNumberOfWords(String text) {
        return Stream.of(text)
                .map(s -> s.split(regx))
                .mapToInt(value -> value.length)
                .sum();
    }

    @Override
    public int countNumberOfUniqueWords(String text) {
        return Stream.of(text)
                .map(s -> s.split(regx))
                .flatMap(Arrays::stream)
                .distinct()
                .mapToInt(s -> 1)
                .sum();
    }

    @Override
    public List<String> getWords(String text) {
        return Stream.of(text)
                .map(s -> s.split(regx))
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
    }

    @Override
    public Set<String> getUniqueWords(String text) {
        return Stream.of(text)
                .map(s -> s.split(regx))
                .flatMap(Arrays::stream)
                .collect(Collectors.toSet());
    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {
        return Stream.of(text)
                .map(s -> s.split(regx))
                .flatMap(Arrays::stream)
                .collect(Collectors.toMap(s -> s, i -> 1, Integer::sum));
    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {
        switch (direction) {
            case ASC:
                return Stream.of(text)
                        .map(s -> s.split(regx))
                        .flatMap(Arrays::stream)
                        .sorted(Comparator.comparingInt(String::length))
                        .collect(Collectors.toList());
            case DESC:
                return Stream.of(text)
                        .map(s -> s.split(regx))
                        .flatMap(Arrays::stream)
                        .sorted((a, b) -> Integer.compare(b.length(), a.length()))
                        .collect(Collectors.toList());
            default:
                return new ArrayList<>();
        }
    }
}
