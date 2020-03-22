package org.dmly.time_tracker.parsers.impl;

import org.dmly.time_tracker.parsers.Parser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntervalParserTest {
    private Parser parser;

    @BeforeEach
    void setUp() {
        parser = new IntervalParser();
    }

    @AfterEach
    void tearDown() {
        parser = null;
    }

    @Test
    void parseInterval_valueNull() {
        Assertions.assertThrows(RuntimeException.class, () -> parser.parseInterval(null));
    }

    @Test
    void parseInterval_valueEmptyString() {
        Assertions.assertEquals(0, parser.parseInterval(""));
    }
}