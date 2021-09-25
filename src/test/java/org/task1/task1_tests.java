package org.task1;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;


public class task1_tests {
    public static int[] arr;

    @BeforeAll
    public static void createArr() {
       arr = new int[100_000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        arr = Arrays.stream(arr)
                .filter(i->i!=0)
                .boxed()
                .sorted(Collections.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();
    }

    @Test
    @DisplayName("very very big arr. check long time")
    public void test40_1() {
        int[] big = arr;
        int managers = 100_000;

        long actual = Main.check(big, managers);
        long expected = 33_333;
        Assertions.assertEquals(expected, actual);
    }



    @Test
    @DisplayName("should return 200")
    public void test1() {
        int[] testArr = {199, 453, 220, 601};
        int managers = 6;
        long actual = Main.check(testArr, managers);
        long expected = 200;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should return 200")
    public void test1_1() {
        int[] testArr = {453, 220, 601};
        int managers = 6;
        long actual = Main.check(testArr, managers);
        long expected = 200;
        Assertions.assertEquals(expected, actual);
    }


    @Test
    @DisplayName("should return 50")
    public void test2() {
        int[] testArr = {100, 100, 100, 100};
        int managers = 6;
        long actual = Main.check(testArr, managers);
        long expected = 50;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should return 1")
    public void test3() {
        int[] testArr = {1, 99};
        int managers = 100;
        long actual = Main.check(testArr, managers);
        long expected = 1;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should return 1")
    public void test4() {
        int[] testArr = {99, 1};
        int managers = 100;
        long actual = Main.check(testArr, managers);
        long expected = 1;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should return 0")
    public void test5() {
        int[] testArr = {0, 0, 0};
        int managers = 100;
        long actual = Main.check(testArr, managers);
        long expected = 0;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should return 0")
    public void test6() {
        int[] testArr = {0, 0, 10};
        int managers = 100;
        long actual = Main.check(testArr, managers);
        long expected = 0;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should return 1")
    public void test7() {
        int[] testArr = {1, 1, 1};
        int managers = 3;
        long actual = Main.check(testArr, managers);
        long expected = 1;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should return 1")
    public void test8() {
        int[] testArr = {98, 1, 0};
        int managers = 100;
        long actual = Main.check(testArr, managers);
        long expected = 0;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should return 17")
    public void test9() {
        int[] testArr = {127, 5, 17, 8};
        int managers = 8;
        long actual = Main.check(testArr, managers);
        long expected = 17;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should return 151")
    public void test10() {
        int[] testArr = {100, 302, 401, 543, 72, 33, 28};
        int managers = 7;
        long actual = Main.check(testArr, managers);
        long expected = 151;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should return 250")
    public void test11() {
        int[] testArr = {600, 500, 600};
        int managers = 6;
        long actual = Main.check(testArr, managers);
        long expected = 250;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should return 200")
    public void test12() {
        int[] testArr = {10_000_000, 10_000_000};
        int managers = 100_000;
        long actual = Main.check(testArr, managers);
        long expected = 200;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should return 10_000_000")
    public void test13() {
        int[] testArr = {10_000_000, 10_000_000};
        int managers = 1;
        long actual = Main.check(testArr, managers);
        long expected = 10_000_000;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should return 1")
    public void test14() {
        int[] testArr = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        int managers = 1;
        long actual = Main.check(testArr, managers);
        long expected = 1;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should return 0")
    public void test15() {
        int[] testArr = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        int managers = 11;
        long actual = Main.check(testArr, managers);
        long expected = 0;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should return 100_000_000")
    public void test16() {
        int[] testArr = {100_000_000, 100_000_000, 100_000_000, 100_000_000, 100_000_000,
                100_000_000, 100_000_000, 100_000_000, 100_000_000, 100_000_000};
        int managers = 1;
        long actual = Main.check(testArr, managers);
        long expected = 100_000_000;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should return 100_000_000")
    public void test17() {
        int[] testArr = {100_000_000, 100_000_000, 100_000_000, 100_000_000, 100_000_000,
                100_000_000, 100_000_000, 100_000_000, 100_000_000, 100_000_000};
        int managers = 10;
        long actual = Main.check(testArr, managers);
        long expected = 100_000_000;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should return 100")
    public void test18() {
        int[] testArr = {100, 100, 100, 100, 100, 100, 100, 100, 100, 100};//1000
        int managers = 10;
        long actual = Main.check(testArr, managers);
        long expected = 100;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should return 50")
    public void test19() {
        int[] testArr = {100, 100, 100, 100, 100, 100, 100, 100, 100, 100};//1000
        int managers = 11;
        long actual = Main.check(testArr, managers);
        long expected = 50;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should return 100")
    public void test20() {
        int[] testArr = {100, 100, 100};//300
        int managers = 3;
        long actual = Main.check(testArr, managers);
        long expected = 100;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should return 50")
    public void test22() {
        int[] testArr = {0, 100, 100, 100, 0};//300
        int managers = 4;
        long actual = Main.check(testArr, managers);
        long expected = 50;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should return 1000")
    public void test23() {
        int[] testArr = {0, 1, 10, 1_000, 0};//1011
        int managers = 1;
        long actual = Main.check(testArr, managers);
        long expected = 1000;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should return 500")
    public void test24() {
        int[] testArr = {0, 1, 10, 1_000, 0};//1011
        int managers = 2;
        long actual = Main.check(testArr, managers);
        long expected = 500;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should return 200")
    public void test25() {
        int[] testArr = {0, 1, 10, 1_000, 0};//1011
        int managers = 5;
        long actual = Main.check(testArr, managers);
        long expected = 200;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should return 100")
    public void test26() {
        int[] testArr = {0, 1, 10, 1_000, 0};//1011
        int managers = 10;
        long actual = Main.check(testArr, managers);
        long expected = 100;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should return 90")
    public void test27() {
        int[] testArr = {0, 1, 10, 1_000, 0};//1011
        int managers = 11;
        long actual = Main.check(testArr, managers);
        long expected = 90;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should return 50")
    public void test28() {
        int[] testArr = {0, 1, 10, 1_000, 0};//1011
        int managers = 20;
        long actual = Main.check(testArr, managers);
        long expected = 50;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should return 33")
    public void test29() {
        int[] testArr = {0, 1, 10, 1_000, 0};//1011
        int managers = 30;
        long actual = Main.check(testArr, managers);
        long expected = 33;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should return 10")
    public void test30() {
        int[] testArr = {0, 1, 10, 1_000, 0};//1011
        int managers = 100;
        long actual = Main.check(testArr, managers);
        long expected = 10;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should return 10")
    public void test31() {
        int[] testArr = {0, 1, 10, 1_000, 0};//1011
        int managers = 101;
        long actual = Main.check(testArr, managers);
        long expected = 10;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should return 990_099")
    public void test32() {
        int[] testArr = {0, 1, 100_000_000};
        int managers = 101;
        long actual = Main.check(testArr, managers);
        long expected = 990_099;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should return 2")
    public void test33() {
        int[] testArr = {0, 2, 6};
        int managers = 3;
        long actual = Main.check(testArr, managers);
        long expected = 2;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should return 2")
    public void test34() {
        int[] testArr = {0, 2, 6};
        int managers = 4;
        long actual = Main.check(testArr, managers);
        long expected = 2;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should return 2")
    public void test35() {
        int[] testArr = {0, 0, 2, 2, 5};
        int managers = 4;
        long actual = Main.check(testArr, managers);
        long expected = 2;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should return 0")
    public void test36() {
        int[] testArr = {0, 0, 2, 2, 5};
        int managers = 400;
        long actual = Main.check(testArr, managers);
        long expected = 0;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should return 0")
    public void test37() {
        int[] testArr = {0, 1, 2, 3, 4};
        int managers = 5;
        long actual = Main.check(testArr, managers);
        long expected = 1;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("very very big arr. expect 100_000_000")
    public void test38() {
        int[] big = new int[100_000];
        Arrays.fill(big, 100_000_000);
        int managers = 1;

        long actual = Main.check(big, managers);
        long expected = 100_000_000;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("very very big arr. expect 100_000_000")
    public void test39() {
        int[] big = new int[100_000];
        Arrays.fill(big, 100_000_000);
        int managers = 100_000;

        long actual = Main.check(big, managers);
        long expected = 100_000_000;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("very very big arr. expect 100_000_000")
    public void test40() {
        int[] big = new int[100_000];
        Arrays.fill(big, 100_000_000);
        int managers = 99_999;

        long actual = Main.check(big, managers);
        long expected = 100_000_000;
        Assertions.assertEquals(expected, actual);
    }


    @Test
    @DisplayName("should return 1")
    public void test41() {
        int[] testArr = {0};
        int managers = 1;
        long actual = Main.check(testArr, managers);
        long expected = 0;
        Assertions.assertEquals(expected, actual);
    }
}
