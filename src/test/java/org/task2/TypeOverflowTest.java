package org.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TypeOverflowTest {
    @Test
    @DisplayName("expect 1. testcase: 100_000_000_000")
    void t1() {
        long expected = 100000000000L;
        long actual = Main.process("100000000000");
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("expect 1. testcase: -100_000_000_000")
    void t1_1() {
        long expected = -100000000000L;
        long actual = Main.process("-100000000000");
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("expect 1. testcase: 1")
    void t2() {
        long expected = 1;
        long actual = Main.process("1");
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("expect 1. testcase: 100_000_000_000_000")
    void t3() {
        long expected = 100000000000000L;
        long actual = Main.process("100000000000000");
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("expect 1. testcase: 100_000_000_000_000_000")
    void t4() {
        long expected = 100000000000000000L;
        long actual = Main.process("100000000000000000");
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("expect 1. testcase: 1_000_000_000_000_000_000")
    void t5() {
        long expected = 1_000_000_000_000_000_000L;
        long actual = Main.process("1000000000000000000");
        Assertions.assertEquals(expected,actual);
    }

}
