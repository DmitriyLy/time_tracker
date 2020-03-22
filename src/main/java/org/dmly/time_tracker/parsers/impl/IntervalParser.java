package org.dmly.time_tracker.parsers.impl;

import org.apache.commons.lang3.StringUtils;
import org.dmly.time_tracker.parsers.Parser;

import java.util.List;
import java.util.Objects;

public class IntervalParser implements Parser {
    @Override
    public Integer parseInterval(String value) {

        if (Objects.isNull(value)) {
            throw new RuntimeException("Passed value is null.");
        }

        if (StringUtils.isEmpty(value)) {
            return 0;
        }



        return 0;
    }

    @Override
    public List<Integer> parseIntervalList(String value) {
        return null;
    }
}
