package org.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;


class SimpleVariations {

    @BeforeEach
    void clear() {
        Main.stringPool = new ArrayList<>();
    }

    @Test
    @DisplayName("replacement. expect correct string")
    void t1() {
        String input = "d4+2";
        Main.parse(input);
        String[] expected = {
                "1+2",
                "2+2",
                "3+2",
                "4+2"
        };
        String[] actual = Main.stringPool.toArray(new String[0]);
        boolean isEqual = Arrays.deepEquals(actual, expected);
        Assertions.assertTrue(isEqual);
    }

    @Test
    @DisplayName("replacement. expect correct string")
    void t2() {
        String input = "d4+d4";
        Main.parse(input);
        String[] expected = {
                "1+1", "1+2", "1+3", "1+4",
                "2+1", "2+2", "2+3", "2+4",
                "3+1", "3+2", "3+3", "3+4",
                "4+1", "4+2", "4+3", "4+4",
        };
        String[] actual = Main.stringPool.toArray(new String[0]);
        boolean isEqual = Arrays.deepEquals(actual, expected);
        Assertions.assertTrue(isEqual);
    }

    @Test
    @DisplayName("replacement. expect correct string")
    void t3() {
        String input = "d4*d4";
        Main.parse(input);
        String[] expected = {
                "1*1","1*2","1*3","1*4",
                "2*1","2*2","2*3","2*4",
                "3*1","3*2","3*3","3*4",
                "4*1","4*2","4*3","4*4",
        };
        String[] actual = Main.stringPool.toArray(new String[0]);
        boolean isEqual = Arrays.deepEquals(actual, expected);
        Assertions.assertTrue(isEqual);
    }

    @Test
    @DisplayName("replacement. expect correct string")
    void t4() {
        String input = "d4-d4";
        Main.parse(input);
        String[] expected = {
                "1-1","1-2","1-3","1-4",
                "2-1","2-2","2-3","2-4",
                "3-1","3-2","3-3","3-4",
                "4-1","4-2","4-3","4-4",
        };
        String[] actual = Main.stringPool.toArray(new String[0]);
        boolean isEqual = Arrays.deepEquals(actual, expected);
        Assertions.assertTrue(isEqual);
    }

    @Test
    @DisplayName("replacement. expect correct string")
    void t5() {
        String input = "d3+d4+d2";
        Main.parse(input);
        String[] expected = {
                "1+1+1","1+1+2","1+2+1","1+2+2","1+3+1","1+3+2","1+4+1","1+4+2",
                "2+1+1","2+1+2","2+2+1","2+2+2","2+3+1","2+3+2","2+4+1","2+4+2",
                "3+1+1","3+1+2","3+2+1","3+2+2","3+3+1","3+3+2","3+4+1","3+4+2",
        };
        String[] actual = Main.stringPool.toArray(new String[0]);
        boolean isEqual = Arrays.deepEquals(actual, expected);
        Assertions.assertTrue(isEqual);
    }

    @Test
    @DisplayName("replacement. expect correct string")
    void t6() {
        String input = "d3+d4*d2";
        Main.parse(input);
        String[] expected = {
                "1+1*1","1+1*2","1+2*1","1+2*2","1+3*1","1+3*2","1+4*1","1+4*2",
                "2+1*1","2+1*2","2+2*1","2+2*2","2+3*1","2+3*2","2+4*1","2+4*2",
                "3+1*1","3+1*2","3+2*1","3+2*2","3+3*1","3+3*2","3+4*1","3+4*2",
        };
        String[] actual = Main.stringPool.toArray(new String[0]);
        boolean isEqual = Arrays.deepEquals(actual, expected);
        Assertions.assertTrue(isEqual);
    }


    @Test
    @DisplayName("replacement. expect correct string")
    void t7() {
        String input = "d3+d4*(d2+2)";
        Main.parse(input);
        String[] expected = {
                "1+1*(1+2)", "1+1*(2+2)", "1+2*(1+2)", "1+2*(2+2)",
                "1+3*(1+2)", "1+3*(2+2)", "1+4*(1+2)", "1+4*(2+2)",

                "2+1*(1+2)", "2+1*(2+2)", "2+2*(1+2)", "2+2*(2+2)",
                "2+3*(1+2)", "2+3*(2+2)", "2+4*(1+2)", "2+4*(2+2)",

                "3+1*(1+2)", "3+1*(2+2)", "3+2*(1+2)", "3+2*(2+2)",
                "3+3*(1+2)", "3+3*(2+2)", "3+4*(1+2)", "3+4*(2+2)"
        };
        String[] actual = Main.stringPool.toArray(new String[0]);
        boolean isEqual = Arrays.deepEquals(actual, expected);
        Assertions.assertTrue(isEqual);
    }


}