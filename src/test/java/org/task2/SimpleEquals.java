package org.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SimpleEquals {
    @Test
    @DisplayName("expect 0. testcase: 1<1")
    void t1() {
        int expected = 0;
        int actual = Main.process("1 < 1");
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("expect 0. testcase: 1>1")
    void t2() {
        int expected = 0;
        int actual = Main.process("1 > 1");
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("expect 0. testcase: 1+1 < 1+1")
    void t3() {
        int expected = 0;
        int actual = Main.process("1+1 < 1+1");
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("expect 0. testcase: 1+1 > 1+1")
    void t4() {
        int expected = 0;
        int actual = Main.process("1+1 > 1+1");
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("expect 0. testcase: 2*5+5 > 5*3")
    void t5() {
        int expected = 0;
        int actual = Main.process("2*5+5 > 5*3");
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("expect 0. testcase: 2*5+5 < 5*3")
    void t6() {
        int expected = 0;
        int actual = Main.process("2*5+5 < 5*3");
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("expect 0. testcase: 1_000_000 < 1_000_000")
    void t7() {
        int expected = 0;
        int actual = Main.process("1000000 < 1000000");
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("expect 1. testcase: 4*2 < 4*3 < 4*5")
    void t8() {
        int expected = 1;
        int actual = Main.process("4*2 < 4*3 < 4*5");
        Assertions.assertEquals(expected,actual);
    }


}
