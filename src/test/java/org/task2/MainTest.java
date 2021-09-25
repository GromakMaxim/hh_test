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
    @DisplayName("expect 1(true)")
    void t17() {
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