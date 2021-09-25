package org.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class SimpleMore {

    @Test
    @DisplayName("expect 1. testcase: 5 > 1")
    void t1() {
        int expected = 1;
        int actual = org.task2.Main.doMath("5>1");
        Assertions.assertEquals(expected,actual);
    }


    @Test
    @DisplayName("expect 1. testcase 5+3 > 1")
    void t2() {
        int expected = 1;
        int actual = org.task2.Main.doMath("5+3>1");
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("expect 1. testcase 5+5 > 1+1")
    void t3() {
        int expected = 1;
        int actual = org.task2.Main.doMath("5+5>1+1");
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("expect 0. testcase: 5+5 > 10+10")
    void t4() {
        int expected = 0;
        int actual = org.task2.Main.doMath("5+5>10+10");
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("expect 1. testcase: 5+3*2 > 1")
    void t5() {
        int expected = 1;
        int actual = org.task2.Main.doMath("5+3*2>1");
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("expect 1. testcase: 1 > 1-1")
    void t6() {
        int expected = 1;
        int actual = org.task2.Main.doMath("1 > 1-1");
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("expect 0. testcase: 1 > 1*1+2")
    void t7() {
        int expected = 0;
        int actual = org.task2.Main.doMath("1 > 1*1+2");
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("expect 1. testcase: 1*2+(3-(1*2)) > 1")
    void t8() {
        int expected = 1;
        int actual = org.task2.Main.doMath("1*2+(3-(1*2)) > 1");
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("expect 1. testcase: 1*2+(3-(1*2)) > 1")
    void t9() {
        int expected = 1;
        int actual = org.task2.Main.doMath("1*2+(3-(1*2)) > 1");
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("expect 1. testcase: 5>2>0")
    void t10() {
        int expected = 1;
        int actual = org.task2.Main.doMath("5>2>0");
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("expect 1. testcase: 5+5>2*2>0-5")
    void t11() {
        int expected = 1;
        int actual = org.task2.Main.doMath("5+5>2*2>0-5");
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("expect 0. testcase: 1*(2+2*(1*10-5*2)) > 3")
    void t12() {
        int expected = 0;
        int actual = org.task2.Main.doMath("1*(2+2*(1*10-5*2)) > 3");
        Assertions.assertEquals(expected,actual);
    }
}
