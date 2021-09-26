package org.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SimpleEquals {
    @Test
    @DisplayName("expect 0. testcase: 1<1")
    void t1() {
        long expected = 0;
        long actual = Main.process("1 < 1");
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("expect 0. testcase: 1>1")
    void t2() {
        long expected = 0;
        long actual = Main.process("1 > 1");
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("expect 0. testcase: 1+1 < 1+1")
    void t3() {
        long expected = 0;
        long actual = Main.process("1+1 < 1+1");
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("expect 0. testcase: 1+1 > 1+1")
    void t4() {
        long expected = 0;
        long actual = Main.process("1+1 > 1+1");
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("expect 0. testcase: 2*5+5 > 5*3")
    void t5() {
        long expected = 0;
        long actual = Main.process("2*5+5 > 5*3");
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("expect 0. testcase: 2*5+5 < 5*3")
    void t6() {
        long expected = 0;
        long actual = Main.process("2*5+5 < 5*3");
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("expect 0. testcase: 1_000_000 < 1_000_000")
    void t7() {
        long expected = 0;
        long actual = Main.process("1000000 < 1000000");
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("expect 1. testcase: 4*2 < 4*3 < 4*5")
    void t8() {
        long expected = 1;
        long actual = Main.process("4*2 < 4*3 < 4*5");
        Assertions.assertEquals(expected,actual);
    }


}
