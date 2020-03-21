package org.dmly.time_tracker.parsers;

import java.util.List;

public interface Parser {
    Integer parseInterval(String value);
    List<Integer> parseIntervalList(String value);
}
