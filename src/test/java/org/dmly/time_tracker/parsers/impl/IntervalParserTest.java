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
    void valueNull() {
        Assertions.assertThrows(RuntimeException.class, () -> parser.parseInterval(null));
    }

    @Test
    void valueEmptyString() {
        Assertions.assertEquals(0, parser.parseInterval(""));
    }

    @Test
    void correctSeconds() {
        Assertions.assertEquals(1, parser.parseInterval("1s"));
        Assertions.assertEquals(2, parser.parseInterval("2s"));
        Assertions.assertEquals(21, parser.parseInterval("21s"));
        Assertions.assertEquals(213, parser.parseInterval("213s"));

        Assertions.assertEquals(125, parser.parseInterval("  125s "));
    }

    @Test
    void correctMinutes() {
        Assertions.assertEquals(60, parser.parseInterval("1m"));
        Assertions.assertEquals(2 * 60, parser.parseInterval("2m"));
        Assertions.assertEquals(21 * 60, parser.parseInterval("21m"));
        Assertions.assertEquals(213 * 60, parser.parseInterval("213m"));

        Assertions.assertEquals(125 * 60, parser.parseInterval("  125m "));
    }

    @Test
    void correctMinutesAndSeconds() {
        Assertions.assertEquals(10 * 60 + 21, parser.parseInterval("10m 21s"));
        Assertions.assertEquals(7 * 60 + 1, parser.parseInterval("7m 1s"));
        Assertions.assertEquals(125 * 60 + 125, parser.parseInterval("  125m 125s   "));
    }

    @Test
    void incorrectValues() {
        Assertions.assertEquals(0, parser.parseInterval(" "));
        Assertions.assertEquals(0, parser.parseInterval(" test"));
        Assertions.assertEquals(0, parser.parseInterval(" m s"));
        Assertions.assertEquals(0, parser.parseInterval(" ~Ghyijo.'.wf,"));

        Assertions.assertEquals(7 * 60, parser.parseInterval("7m s"));
        Assertions.assertEquals(25, parser.parseInterval(" m 25s"));

    }
}