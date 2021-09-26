package org.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SimpleLess {
    @Test
    @DisplayName("expect 0. testcase: 5 < 1")
    void t1() {
        long expected = 0;
        long actual = Main.process("5<1");
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("expect 0. testcase: 5+3 < 1")
    void t2() {
        long expected = 0;
        long actual = Main.process("5+3<1");
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("expect 0. testcase 5+5 < 1+1")
    void t3() {
        long expected = 0;
        long actual = Main.process("5+5<1+1");
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("expect 1. testcase: 5+5 < 10+10")
    void t4() {
        long expected = 1;
        long actual = Main.process("5+5<10+10");
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("expect 0. testcase: 5+3*2 < 1")
    void t5() {
        long expected = 0;
        long actual = Main.process("5+3*2<1");
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("expect 0. testcase: 1 < 1-1")
    void t6() {
        long expected = 0;
        long actual = Main.process("1 < 1-1");
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("expect 1. testcase: 1 < 1*1+2")
    void t7() {
        long expected = 1;
        long actual = Main.process("1 < 1*1+2");
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("expect 0. testcase: 1*2+(3-(1*2)) < 1")
    void t8() {
        long expected = 0;
        long actual = Main.process("1*2+(3-(1*2)) < 1");
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("expect 0. testcase: 5<2<0")
    void t9() {
        long expected = 0;
        long actual = Main.process("5<2<0");
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("expect 0. testcase: 5+5 < 2*2 < 0-5")
    void t10() {
        long expected = 0;
        long actual = Main.process("5+5 < 2*2 < 0-5");
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("expect 1. testcase: 1*(2+2*(1*10-5*2)) < 3")
    void t11() {
        long expected = 1;
        long actual = Main.process("1*(2+2*(1*10-5*2)) < 3");
        Assertions.assertEquals(expected,actual);
    }
    
}
