package org.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.task2.Main;

class SimpleMath {

    @Test
    @DisplayName("expect 4")
    void t1() {
        String input = "2+2";
        int actual = org.task2.Main.doMath(input);
        int expected = 4;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 2")
    void t2() {
        String input = "2+0";
        int actual = org.task2.Main.doMath(input);
        int expected = 2;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 2")
    void t3() {
        String input = "0+2";
        int actual = org.task2.Main.doMath(input);
        int expected = 2;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect -2")
    void t5() {
        String input = "-2";
        int actual = org.task2.Main.doMath(input);
        int expected = -2;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect -2")
    void t6() {
        String input = "0-2";
        int actual = org.task2.Main.doMath(input);
        int expected = -2;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect -2")
    void t7() {
        String input = "-2-0";
        int actual = org.task2.Main.doMath(input);
        int expected = -2;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect -2")
    void t8() {
        String input = " - 2 - 0 ";
        int actual = org.task2.Main.doMath(input);
        int expected = -2;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 2")
    void t9() {
        String input = "0+2+0";
        int actual = org.task2.Main.doMath(input);
        int expected = 2;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 0")
    void t10() {
        String input = "-5-4-3-2-1-0+1+2+3+4+5";
        int actual = org.task2.Main.doMath(input);
        int expected = 0;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 6")
    void t11() {
        String input = "2+2*2";
        int actual = org.task2.Main.doMath(input);
        int expected = 6;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 8")
    void t12() {
        String input = "2+2*2+2";
        int actual = org.task2.Main.doMath(input);
        int expected = 8;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 10")
    void t13() {
        String input = "(2+2)*2+2";
        int actual = org.task2.Main.doMath(input);
        int expected = 10;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect -36")
    void t14() {
        String input = "(2+2-2)*(2+((2+2-2))-2*(2*2-2+2*2+(2-2+(2*2)+2-2+2-2+2)-2)-2)";
        int actual = org.task2.Main.doMath(input);
        int expected = -36;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect -34081560")
    void t15() {
        String input = "(13+20-21)*(285+((12+852-22))-5*(275*2000-412+752*25+(27-275+(28*2)+82-55+72-52+62)-72)-92)";
        int actual = org.task2.Main.doMath(input);
        int expected = -34081560;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect -13981560")
    void t16() {
        String input = "((13+20-21)*(285+((12+852-22))-5*(275*2000-412+752*25+(27-275+(28*2)+82-55+72-52+62)-72)-92)+100000)+(10000000*2)";
        int actual = Main.doMath(input);
        int expected = -13981560;
        Assertions.assertEquals(expected, actual);
    }
}