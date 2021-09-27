package org.task2;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class AdvTests {
    private ByteArrayOutputStream output;

    @BeforeEach
    void clear() {
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    void t1() {
        String input = "d4+(d6>2)";
        Main.parse(input);
        String actual = output.toString();
        String expected = "1 8.33\r\n" +
                "2 25.00\r\n" +
                "3 25.00\r\n" +
                "4 25.00\r\n" +
                "5 16.67\r\n";

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void t2() {
        String input = "d4+d4+d4";
        Main.parse(input);
        String actual = output.toString();
        String expected = "3 1.56\r\n" +
                "4 4.69\r\n" +
                "5 9.38\r\n" +
                "6 15.63\r\n" +
                "7 18.75\r\n" +
                "8 18.75\r\n" +
                "9 15.63\r\n" +
                "10 9.38\r\n" +
                "11 4.69\r\n" +
                "12 1.56\r\n";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void t3() {
        String input = "d100";
        Main.parse(input);
        String actual = output.toString();
        String expected = "1 1.00\r\n2 1.00\r\n3 1.00\r\n4 1.00\r\n5 1.00\r\n" +
                "6 1.00\r\n7 1.00\r\n8 1.00\r\n9 1.00\r\n10 1.00\r\n" +
                "11 1.00\r\n12 1.00\r\n13 1.00\r\n14 1.00\r\n15 1.00\r\n" +
                "16 1.00\r\n17 1.00\r\n18 1.00\r\n19 1.00\r\n20 1.00\r\n" +
                "21 1.00\r\n22 1.00\r\n23 1.00\r\n24 1.00\r\n25 1.00\r\n" +
                "26 1.00\r\n27 1.00\r\n28 1.00\r\n29 1.00\r\n30 1.00\r\n" +
                "31 1.00\r\n32 1.00\r\n33 1.00\r\n34 1.00\r\n35 1.00\r\n" +
                "36 1.00\r\n37 1.00\r\n38 1.00\r\n39 1.00\r\n40 1.00\r\n" +
                "41 1.00\r\n42 1.00\r\n43 1.00\r\n44 1.00\r\n45 1.00\r\n" +
                "46 1.00\r\n47 1.00\r\n48 1.00\r\n49 1.00\r\n50 1.00\r\n" +
                "51 1.00\r\n52 1.00\r\n53 1.00\r\n54 1.00\r\n55 1.00\r\n" +
                "56 1.00\r\n57 1.00\r\n58 1.00\r\n59 1.00\r\n60 1.00\r\n" +
                "61 1.00\r\n62 1.00\r\n63 1.00\r\n64 1.00\r\n65 1.00\r\n" +
                "66 1.00\r\n67 1.00\r\n68 1.00\r\n69 1.00\r\n70 1.00\r\n" +
                "71 1.00\r\n72 1.00\r\n73 1.00\r\n74 1.00\r\n75 1.00\r\n" +
                "76 1.00\r\n77 1.00\r\n78 1.00\r\n79 1.00\r\n80 1.00\r\n" +
                "81 1.00\r\n82 1.00\r\n83 1.00\r\n84 1.00\r\n85 1.00\r\n" +
                "86 1.00\r\n87 1.00\r\n88 1.00\r\n89 1.00\r\n90 1.00\r\n" +
                "91 1.00\r\n92 1.00\r\n93 1.00\r\n94 1.00\r\n95 1.00\r\n" +
                "96 1.00\r\n97 1.00\r\n98 1.00\r\n99 1.00\r\n100 1.00\r\n";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void t4() {
        String input = "-d100";
        Main.parse(input);
        String actual = output.toString();
        String expected = "-100 1.00\r\n-99 1.00\r\n-98 1.00\r\n-97 1.00\r\n-96 1.00\r\n-95 1.00\r\n-94 1.00\r\n-93 1.00\r\n-92 1.00\r\n-91 1.00\r\n" +
                "-90 1.00\r\n-89 1.00\r\n-88 1.00\r\n-87 1.00\r\n-86 1.00\r\n-85 1.00\r\n-84 1.00\r\n-83 1.00\r\n-82 1.00\r\n-81 1.00\r\n" +
                "-80 1.00\r\n-79 1.00\r\n-78 1.00\r\n-77 1.00\r\n-76 1.00\r\n-75 1.00\r\n-74 1.00\r\n-73 1.00\r\n-72 1.00\r\n-71 1.00\r\n" +
                "-70 1.00\r\n-69 1.00\r\n-68 1.00\r\n-67 1.00\r\n-66 1.00\r\n-65 1.00\r\n-64 1.00\r\n-63 1.00\r\n-62 1.00\r\n-61 1.00\r\n" +
                "-60 1.00\r\n-59 1.00\r\n-58 1.00\r\n-57 1.00\r\n-56 1.00\r\n-55 1.00\r\n-54 1.00\r\n-53 1.00\r\n-52 1.00\r\n-51 1.00\r\n" +
                "-50 1.00\r\n-49 1.00\r\n-48 1.00\r\n-47 1.00\r\n-46 1.00\r\n-45 1.00\r\n-44 1.00\r\n-43 1.00\r\n-42 1.00\r\n-41 1.00\r\n" +
                "-40 1.00\r\n-39 1.00\r\n-38 1.00\r\n-37 1.00\r\n-36 1.00\r\n-35 1.00\r\n-34 1.00\r\n-33 1.00\r\n-32 1.00\r\n-31 1.00\r\n" +
                "-30 1.00\r\n-29 1.00\r\n-28 1.00\r\n-27 1.00\r\n-26 1.00\r\n-25 1.00\r\n-24 1.00\r\n-23 1.00\r\n-22 1.00\r\n-21 1.00\r\n" +
                "-20 1.00\r\n-19 1.00\r\n-18 1.00\r\n-17 1.00\r\n-16 1.00\r\n-15 1.00\r\n-14 1.00\r\n-13 1.00\r\n-12 1.00\r\n-11 1.00\r\n" +
                "-10 1.00\r\n-9 1.00\r\n-8 1.00\r\n-7 1.00\r\n-6 1.00\r\n-5 1.00\r\n-4 1.00\r\n-3 1.00\r\n-2 1.00\r\n-1 1.00\r\n";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void t5() {
        String input = "-(d100-2)";
        Main.parse(input);
        String actual = output.toString();
        String expected = "-98 1.00\r\n-97 1.00\r\n-96 1.00\r\n-95 1.00\r\n-94 1.00\r\n-93 1.00\r\n-92 1.00\r\n-91 1.00\r\n" +
                "-90 1.00\r\n-89 1.00\r\n-88 1.00\r\n-87 1.00\r\n-86 1.00\r\n" +
                "-85 1.00\r\n-84 1.00\r\n-83 1.00\r\n-82 1.00\r\n-81 1.00\r\n" +
                "-80 1.00\r\n-79 1.00\r\n-78 1.00\r\n-77 1.00\r\n-76 1.00\r\n" +
                "-75 1.00\r\n-74 1.00\r\n-73 1.00\r\n-72 1.00\r\n-71 1.00\r\n" +
                "-70 1.00\r\n-69 1.00\r\n-68 1.00\r\n-67 1.00\r\n-66 1.00\r\n" +
                "-65 1.00\r\n-64 1.00\r\n-63 1.00\r\n-62 1.00\r\n-61 1.00\r\n" +
                "-60 1.00\r\n-59 1.00\r\n-58 1.00\r\n-57 1.00\r\n-56 1.00\r\n" +
                "-55 1.00\r\n-54 1.00\r\n-53 1.00\r\n-52 1.00\r\n-51 1.00\r\n" +
                "-50 1.00\r\n-49 1.00\r\n-48 1.00\r\n-47 1.00\r\n-46 1.00\r\n" +
                "-45 1.00\r\n-44 1.00\r\n-43 1.00\r\n-42 1.00\r\n-41 1.00\r\n" +
                "-40 1.00\r\n-39 1.00\r\n-38 1.00\r\n-37 1.00\r\n-36 1.00\r\n" +
                "-35 1.00\r\n-34 1.00\r\n-33 1.00\r\n-32 1.00\r\n-31 1.00\r\n" +
                "-30 1.00\r\n-29 1.00\r\n-28 1.00\r\n-27 1.00\r\n-26 1.00\r\n" +
                "-25 1.00\r\n-24 1.00\r\n-23 1.00\r\n-22 1.00\r\n-21 1.00\r\n" +
                "-20 1.00\r\n-19 1.00\r\n-18 1.00\r\n-17 1.00\r\n-16 1.00\r\n" +
                "-15 1.00\r\n-14 1.00\r\n-13 1.00\r\n-12 1.00\r\n-11 1.00\r\n" +
                "-10 1.00\r\n-9 1.00\r\n-8 1.00\r\n-7 1.00\r\n-6 1.00\r\n" +
                "-5 1.00\r\n-4 1.00\r\n-3 1.00\r\n-2 1.00\r\n-1 1.00\r\n" +
                "0 1.00\r\n1 1.00\r\n";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void t6() {
        String input = "d4+2";
        Main.parse(input);
        String actual = output.toString();

        String expected = "3 25.00\r\n4 25.00\r\n5 25.00\r\n6 25.00\r\n";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("replacement. expect correct string")
    void t7() {
        String input = "d4*d4";
        Main.parse(input);
        String actual = output.toString();

        String expected = "1 6.25\r\n2 12.50\r\n3 12.50\r\n4 18.75\r\n" +
                "6 12.50\r\n8 12.50\r\n9 6.25\r\n12 12.50\r\n16 6.25\r\n";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("replacement. expect correct string")
    void t8() {
        String input = "d3+d4*d2";
        Main.parse(input);
        String actual = output.toString();

        String expected = "2 4.17\r\n3 12.50\r\n4 16.67\r\n5 20.83\r\n" +
                "6 12.50\r\n7 12.50\r\n8 4.17\r\n9 8.33\r\n10 4.17\r\n11 4.17\r\n";

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("replacement. expect correct string")
    void t9() {
        String input = "d3+d4*(d2+2)";
        Main.parse(input);
        String actual = output.toString();


        String expected = "4 4.17\r\n5 8.33\r\n6 8.33\r\n7 8.33\r\n8 4.17\r\n" +
                "9 8.33\r\n10 8.33\r\n11 8.33\r\n12 4.17\r\n13 8.33\r\n14 8.33\r\n" +
                "15 8.33\r\n17 4.17\r\n18 4.17\r\n19 4.17\r\n";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void t10() {
        String input = "-1>10";
        Main.parse(input);
        String actual = output.toString();


        String expected = "0 100.00\r\n";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void t11() {
        String input = "4>3>2";
        Main.parse(input);
        String actual = output.toString();


        String expected = "0 100.00\r\n";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void t12() {
        String input = "4>(3>2)";
        Main.parse(input);
        String actual = output.toString();


        String expected = "1 100.00\r\n";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void t13() {
        String input = "(4>3)>2";
        Main.parse(input);
        String actual = output.toString();


        String expected = "0 100.00\r\n";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void t14() {
        String input = "(4>3)>2";
        Main.parse(input);
        String actual = output.toString();


        String expected = "0 100.00\r\n";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void t15() {
        String input = "d50>4>3>2>1>0>1>2>3>4>d50";
        Main.parse(input);
        String actual = output.toString();


        String expected = "0 100.00\r\n";
        Assertions.assertEquals(expected, actual);
    }

//    @Test
//    @DisplayName("overflow")
//    void t16() {
//        String input = "d4+(d6>2)+d4+(d6>2)+d4+(d6>2)+d4+(d6>2)+d4+(d6>2)+d4+(d6>2)+d4+(d6>2)+d4+(d6>2)+d4+(d6>2)+d4+(d6>2)";
//        Main.parse(input);
//        String actual = output.toString();
//
//
//        String expected = "0 100.00\r\n";
//        Assertions.assertEquals(expected, actual);
//    }

    @Test
    void t17() {
        String input = "d4-d6+d6";
        Main.parse(input);
        String actual = output.toString();

        String expected = "-4 0.69\r\n-3 2.08\r\n-2 4.17\r\n-1 6.94\r\n0 9.72\r\n" +
                "1 12.50\r\n2 13.89\r\n3 13.89\r\n4 12.50\r\n5 9.72\r\n" +
                "6 6.94\r\n7 4.17\r\n8 2.08\r\n9 0.69\r\n";
        Assertions.assertEquals(expected, actual);
    }


}
