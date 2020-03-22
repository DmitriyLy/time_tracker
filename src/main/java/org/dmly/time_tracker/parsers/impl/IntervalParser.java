package org.dmly.time_tracker.parsers.impl;

import org.apache.commons.lang3.StringUtils;
import org.dmly.time_tracker.parsers.Parser;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class IntervalParser implements Parser {
    @Override
    public Integer parseInterval(final String value) {

        if (Objects.isNull(value)) {
            throw new RuntimeException("Passed value is null.");
        }

        if (StringUtils.isEmpty(value)) {
            return 0;
        }

        String[] tokens = normalizeInputValue(value).split(" ");


        return Stream.of(tokens)
                .map(s -> {
                    int multiplier = getMultiplierFromToken(s);
                    int timeValue = getTimeValueFromToken(s);
                    return multiplier * timeValue;
                })
                .reduce(0, (a, b) -> a + b);
    }

    @Override
    public List<Integer> parseIntervalList(String value) {
        return null;
    }

    private String normalizeInputValue(final String value) {
        return StringUtils.strip(value).toLowerCase();
    }

    private int getMultiplierFromToken(final String token) {
        if (token.endsWith("m")) return 60;
        if (token.endsWith("s")) return 1;
        return 0;
    }

    private int getTimeValueFromToken(final String token) {

        if (token.length() < 2) {
            return 0;
        }

        String initValue = token.substring(0, token.length() - 1);

        try {
            return Integer.parseInt(initValue);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
