package org.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AlternativeTest {


    @Test
    void t1() {
        String input = "2+2";
        long actual = Main.process(input);
        long expected = 4;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void t2() {
        String input = "2-2";
        long actual = Main.process(input);
        long expected = 0;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void t3() {
        String input = "2+2-3+1";
        long actual = Main.process(input);
        long expected = 2;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void t4() {
        String input = "2+(2-3)+1";
        long actual = Main.process(input);
        long expected = 2;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void t5() {
        String input = "2*2";
        long actual = Main.process(input);
        long expected = 4;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void t6() {
        String input = "4/2";
        long actual = Main.process(input);
        long expected = 2;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void t7() {
        String input = "2+2*2";
        long actual = Main.process(input);
        long expected = 6;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void t8() {
        String input = "(2+2)*2";
        long actual = Main.process(input);
        long expected = 8;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void t9() {
        String input = "2*(3-1)+1+(2-2)*1";
        long actual = Main.process(input);
        long expected = 5;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void t10() {
        String input = "-(2+2)";
        long actual = Main.process(input);
        long expected = -4;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void t11() {
        String input = "-(2-2)";
        long actual = Main.process(input);
        long expected = 0;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void t12() {
        String input = "-2";
        long actual = Main.process(input);
        long expected = -2;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void t13() {
        String input = "-0";
        long actual = Main.process(input);
        long expected = 0;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void t14() {
        String input = "-(3+2+6*2-100)+(1+1*2)*(-1)*10";
        long actual = Main.process(input);
        long expected = 53;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void t15() {
        String input = "-(10+9+3*(2-3+5+8-(250+50*10)*2*(10-6)))";
        long actual = Main.process(input);
        long expected = 17945;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void t16() {
        String input = "5>2";
        long actual = Main.process(input);
        long expected = 1;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void t17() {
        String input = "5<2";
        long actual = Main.process(input);
        long expected = 0;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void t18() {
        String input = "2<2";
        long actual = Main.process(input);
        long expected = 0;
        Assertions.assertEquals(expected, actual);
    }


    @Test
    @DisplayName("expect 0. testcase: 1>1")
    void t19() {
        long expected = 0;
        long actual = Main.process("1>1");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 0. testcase: 1+1 < 1+1")
    void t20() {
        long expected = 0;
        long actual = Main.process("1+1 < 1+1");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 0. testcase: 1+1 > 1+1")
    void t21() {
        long expected = 0;
        long actual = Main.process("1+1 > 1+1");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 0. testcase: 2*5+5 > 5*3")
    void t22() {
        long expected = 0;
        long actual = Main.process("2*5+5 > 5*3");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 0. testcase: 2*5+5 < 5*3")
    void t23() {
        long expected = 0;
        long actual = Main.process("2*5+5 < 5*3");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 0. testcase: 1_000_000 < 1_000_000")
    void t24() {
        long expected = 0;
        long actual = Main.process("1000000 < 1000000");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 1. testcase: 4*2 < 4*3 < 4*5")
    void t25() {
        long expected = 1;
        long actual = Main.process("4*2 < 4*3 < 4*5");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 0. testcase: 5 < 1")
    void t26() {
        long expected = 0;
        long actual = Main.process("5<1");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 0. testcase: 5+3 < 1")
    void t27() {
        long expected = 0;
        long actual = Main.process("5+3<1");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 0. testcase 5+5 < 1+1")
    void t28() {
        long expected = 0;
        long actual = Main.process("5+5<1+1");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 1. testcase: 5+5 < 10+10")
    void t29() {
        long expected = 1;
        long actual = Main.process("5+5<10+10");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 0. testcase: 5+3*2 < 1")
    void t30() {
        long expected = 0;
        long actual = Main.process("5+3*2<1");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 0. testcase: 1 < 1-1")
    void t31() {
        long expected = 0;
        long actual = Main.process("1 < 1-1");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 1. testcase: 1 < 1*1+2")
    void t32() {
        long expected = 1;
        long actual = Main.process("1 < 1*1+2");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 0. testcase: 1*2+(3-(1*2)) < 1")
    void t33() {
        long expected = 0;
        long actual = Main.process("1*2+(3-(1*2)) < 1");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 0. testcase: 5<2<0")
    void t34() {
        long expected = 0;
        long actual = Main.process("5<2<0");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 0. testcase: 5+5 < 2*2 < 0-5")
    void t35() {
        long expected = 0;
        long actual = Main.process("5+5 < 2*2 < 0-5");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 1. testcase: 1*(2+2*(1*10-5*2)) < 3")
    void t36() {
        long expected = 1;
        long actual = Main.process("1*(2+2*(1*10-5*2)) < 3");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 1. testcase: 5 > 1")
    void t37() {
        long expected = 1;
        long actual = Main.process("5>1");
        Assertions.assertEquals(expected, actual);
    }


    @Test
    @DisplayName("expect 1. testcase 5+3 > 1")
    void t38() {
        long expected = 1;
        long actual = Main.process("5+3>1");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 1. testcase 5+5 > 1+1")
    void t39() {
        long expected = 1;
        long actual = Main.process("5+5>1+1");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 0. testcase: 5+5 > 10+10")
    void t40() {
        long expected = 0;
        long actual = Main.process("5+5>10+10");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 1. testcase: 5+3*2 > 1")
    void t41() {
        long expected = 1;
        long actual = Main.process("5+3*2>1");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 1. testcase: 1 > 1-1")
    void t42() {
        long expected = 1;
        long actual = Main.process("1 > 1-1");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 0. testcase: 1 > 1*1+2")
    void t43() {
        long expected = 0;
        long actual = Main.process("1 > 1*1+2");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 1. testcase: 1*2+(3-(1*2)) > 1")
    void t44() {
        long expected = 1;
        long actual = Main.process("1*2+(3-(1*2)) > 1");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 1. testcase: 1*2+(3-(1*2)) > 1")
    void t45() {
        long expected = 1;
        long actual = Main.process("1*2+(3-(1*2)) > 1");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 1. testcase: 5>2>0")
    void t46() {
        long expected = 1;
        long actual = Main.process("5>2>0");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 1. testcase: 5+5>2*2>0-5")
    void t47() {
        long expected = 1;
        long actual = Main.process("5+5>2*2>0-5");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 0. testcase: 1*(2+2*(1*10-5*2)) > 3")
    void t48() {
        long expected = 0;
        long actual = Main.process("1*(2+2*(1*10-5*2)) > 3");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 1. testcase: 1+(1>2)")
    void t49() {
        long expected = 1;
        long actual = Main.process("1+(1>2)");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 1. testcase: 1>(1>2)")
    void t50() {
        long expected = 1;
        long actual = Main.process("1>(1>2)");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 1. testcase: 1>(-(1>2))")
    void t51() {
        long expected = 1;
        long actual = Main.process("1>(-(1>2))");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 1. testcase: 1>-(3>2)")
    void t52() {
        long expected = 1;
        long actual = Main.process("1>-(3>2)");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 16. testcase: 8/2*(2+2)")
    void t53() {
        long expected = 16;
        long actual = Main.process("8/2*(2+2)");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 0. testcase: 0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0")
    void t54() {
        long expected = 0;
        long actual = Main.process("0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("expect 0. testcase: d100*d100*d100")
    void t55() {
        long expected = 0;
        Main.parse("d100*d100*d100");
    }
}