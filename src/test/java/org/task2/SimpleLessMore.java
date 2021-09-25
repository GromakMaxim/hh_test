package org.task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SimpleLessMore {
    @Test
    @DisplayName("expect")
    void t1() {
        Main.parse("5>1");
    }

}
