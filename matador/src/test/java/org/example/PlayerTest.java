package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    @DisplayName("that setting the position is gettable")
    void getSetPosition() {
        Player player = new Player(0, "");
        player.setPosition(20);
        assertEquals(20, player.getPosition());
    }


}
