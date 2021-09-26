package org.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class AdvTests {

    @BeforeEach
    void clear() {
        Main.stringPool = new ArrayList<>();
    }

    @Test
    void t1() {
        String input = "d4+(d6>2)";
        Main.parse(input);
        String[] expected = {
                "1+(1>2)", "1+(2>2)", "1+(3>2)", "1+(4>2)", "1+(5>2)", "1+(6>2)",
                "2+(1>2)", "2+(2>2)", "2+(3>2)", "2+(4>2)", "2+(5>2)", "2+(6>2)",
                "3+(1>2)", "3+(2>2)", "3+(3>2)", "3+(4>2)", "3+(5>2)", "3+(6>2)",
                "4+(1>2)", "4+(2>2)", "4+(3>2)", "4+(4>2)", "4+(5>2)", "4+(6>2)",


        };
        String[] actual = Main.stringPool.toArray(new String[0]);
        boolean isEqual = Arrays.deepEquals(actual, expected);
        Assertions.assertTrue(isEqual);
    }

    @Test
    void t2() {
        String input = "d4+d4+d4";
        Main.parse(input);
        String[] expected = {
                "1+1+1", "1+1+2", "1+1+3", "1+1+4",
                "1+2+1", "1+2+2", "1+2+3", "1+2+4",
                "1+3+1", "1+3+2", "1+3+3", "1+3+4",
                "1+4+1", "1+4+2", "1+4+3", "1+4+4",

                "2+1+1", "2+1+2", "2+1+3", "2+1+4",
                "2+2+1", "2+2+2", "2+2+3", "2+2+4",
                "2+3+1", "2+3+2", "2+3+3", "2+3+4",
                "2+4+1", "2+4+2", "2+4+3", "2+4+4",

                "3+1+1", "3+1+2", "3+1+3", "3+1+4",
                "3+2+1", "3+2+2", "3+2+3", "3+2+4",
                "3+3+1", "3+3+2", "3+3+3", "3+3+4",
                "3+4+1", "3+4+2", "3+4+3", "3+4+4",

                "4+1+1", "4+1+2", "4+1+3", "4+1+4",
                "4+2+1", "4+2+2", "4+2+3", "4+2+4",
                "4+3+1", "4+3+2", "4+3+3", "4+3+4",
                "4+4+1", "4+4+2", "4+4+3", "4+4+4",


        };
        String[] actual = Main.stringPool.toArray(new String[0]);
        boolean isEqual = Arrays.deepEquals(actual, expected);
        Assertions.assertTrue(isEqual);
    }

    @Test
    void t3() {
        String input = "d100";
        Main.parse(input);
        String[] expected = {
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
                "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
                "31", "32", "33", "34", "35", "36", "37", "38", "39", "40",
                "41", "42", "43", "44", "45", "46", "47", "48", "49", "50",
                "51", "52", "53", "54", "55", "56", "57", "58", "59", "60",
                "61", "62", "63", "64", "65", "66", "67", "68", "69", "70",
                "71", "72", "73", "74", "75", "76", "77", "78", "79", "80",
                "81", "82", "83", "84", "85", "86", "87", "88", "89", "90",
                "91", "92", "93", "94", "95", "96", "97", "98", "99", "100",

        };
        String[] actual = Main.stringPool.toArray(new String[0]);
        boolean isEqual = Arrays.deepEquals(actual, expected);
        Assertions.assertTrue(isEqual);
    }

    @Test
    void t4() {
        String input = "-(5+3)";
        int actual = Main.doMath(input);
        int expected = -6;
        Assertions.assertEquals(expected, actual);
    }
}