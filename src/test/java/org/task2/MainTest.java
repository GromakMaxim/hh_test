package org.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.task2.Main.stringPool;

class MainTest {

    @BeforeEach
    void clear(){
        stringPool = new ArrayList<>();
    }

    @Test
    @DisplayName("expect 4")
    void t1() {
        String input = "2+2";
        int actual = Main.doMath(input);
        int expected = 4;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 2")
    void t2() {
        String input = "2+0";
        int actual = Main.doMath(input);
        int expected = 2;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 2")
    void t3() {
        String input = "0+2";
        int actual = Main.doMath(input);
        int expected = 2;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 2")
    void t4() {
        String input = "+2";
        int actual = Main.doMath(input);
        int expected = 2;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect -2")
    void t5() {
        String input = "-2";
        int actual = Main.doMath(input);
        int expected = -2;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect -2")
    void t6() {
        String input = "0-2";
        int actual = Main.doMath(input);
        int expected = -2;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect -2")
    void t7() {
        String input = "-2-0";
        int actual = Main.doMath(input);
        int expected = -2;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect -2")
    void t8() {
        String input = " - 2 - 0 ";
        int actual = Main.doMath(input);
        int expected = -2;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 2")
    void t9() {
        String input = "0+2+0";
        int actual = Main.doMath(input);
        int expected = 2;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 0")
    void t10() {
        String input = "-5-4-3-2-1-0+1+2+3+4+5";
        int actual = Main.doMath(input);
        int expected = 0;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 6")
    void t11() {
        String input = "2+2*2";
        int actual = Main.doMath(input);
        int expected = 6;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 8")
    void t12() {
        String input = "2+2*2+2";
        int actual = Main.doMath(input);
        int expected = 8;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 10")
    void t13() {
        String input = "(2+2)*2+2";
        int actual = Main.doMath(input);
        int expected = 10;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect -36")
    void t14() {
        String input = "(2+2-2)*(2+((2+2-2))-2*(2*2-2+2*2+(2-2+(2*2)+2-2+2-2+2)-2)-2)";
        int actual = Main.doMath(input);
        int expected = -36;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect -34081560")
    void t15() {
        String input = "(13+20-21)*(285+((12+852-22))-5*(275*2000-412+752*25+(27-275+(28*2)+82-55+72-52+62)-72)-92)";
        int actual = Main.doMath(input);
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

    @Test
    @DisplayName("expect 1(true)")
    void t17() {
        String input = "9>4";
        int actual = Main.doMath(input);
        int expected = 1;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("d1 -> 1 100.00")
    void t18() {
        String input = "9>4";
        int actual = Main.doMath(input);
        int expected = 1;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("replacement. expect correct string")
    void t19() {
        String input = "d4+2";
        String[] actual = Main.generateStrings(input);
        String[] expected = {
                "1+2",
                "2+2",
                "3+2",
                "4+2"
        };
        boolean isEqual = Arrays.deepEquals(actual, expected);
        Assertions.assertTrue(isEqual);
    }

    @Test
    @DisplayName("replacement. expect correct string")
    void t20() {
        String input = "2+d4+2";
        String[] actual = Main.generateStrings(input);
        String[] expected = {
                "2+1+2",
                "2+2+2",
                "2+3+2",
                "2+4+2"
        };
        boolean isEqual = Arrays.deepEquals(actual, expected);
        Assertions.assertTrue(isEqual);
    }

    @Test
    @DisplayName("replacement. expect correct string")
    void t21() {
        String input = "d4+d2+d3";
        String[] actual = Main.generateStrings(input);
        String[] expected = {
                "1+d2+d3",
                "2+d2+d3",
                "3+d2+d3",
                "4+d2+d3"
        };
        boolean isEqual = Arrays.deepEquals(actual, expected);
        Assertions.assertTrue(isEqual);
    }

    @Test
    @DisplayName("replacement. expect correct string")
    void t22() {
        String[] input = {
                "d3 + d2"
        };
        Main.gen(input);
        String[] actual = stringPool.toArray(new String[0]);
        String[] expected = {
                "1 + 1",
                "1 + 2",
                "2 + 1",
                "2 + 2",
                "3 + 1",
                "3 + 2",
        };
        boolean isEqual = Arrays.deepEquals(actual, expected);
        Assertions.assertTrue(isEqual);
    }

    @Test
    @DisplayName("replacement. expect correct string")
    void t23() {
        String[] input = {
                "2 + d3 + d2"
        };
        Main.gen(input);
        String[] actual = stringPool.toArray(new String[0]);
        String[] expected = {
                "2 + 1 + 1",
                "2 + 1 + 2",
                "2 + 2 + 1",
                "2 + 2 + 2",
                "2 + 3 + 1",
                "2 + 3 + 2",
        };
        boolean isEqual = Arrays.deepEquals(actual, expected);
        Assertions.assertTrue(isEqual);
    }

    @Test
    @DisplayName("replacement. expect correct string")
    void t24() {
        String[] input = {
                "2 + d3 + 2 + d2"
        };
        Main.gen(input);
        String[] actual = stringPool.toArray(new String[0]);
        String[] expected = {
                "2 + 1 + 2 + 1",
                "2 + 1 + 2 + 2",
                "2 + 2 + 2 + 1",
                "2 + 2 + 2 + 2",
                "2 + 3 + 2 + 1",
                "2 + 3 + 2 + 2",
        };
        boolean isEqual = Arrays.deepEquals(actual, expected);
        Assertions.assertTrue(isEqual);
    }

    @Test
    @DisplayName("replacement. expect correct string")
    void t25() {
        String[] input = {
                "d4 + d4"
        };
        Main.gen(input);
        String[] actual = stringPool.toArray(new String[0]);
        String[] expected = {
                "1 + 1",
                "1 + 2",
                "1 + 3",
                "1 + 4",

                "2 + 1",
                "2 + 2",
                "2 + 3",
                "2 + 4",

                "3 + 1",
                "3 + 2",
                "3 + 3",
                "3 + 4",

                "4 + 1",
                "4 + 2",
                "4 + 3",
                "4 + 4",
        };
        boolean isEqual = Arrays.deepEquals(actual, expected);
        Assertions.assertTrue(isEqual);
    }

}