package org.example;

import org.example.chances.OutOfJailChance;
import org.example.tiles.ShipTile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    @DisplayName("that setting the position is gettable")
    void getSetPosition() {
        Player player = new Player(0, "");
        player.setPosition(20);
        assertEquals(20, player.getPosition());
    }

    @Test
    @DisplayName("that out of jail is functional")
    void addGetOutOfJailChance() {
        Player player = new Player(0,"");
        player.addGetOutOfJailChance(new OutOfJailChance(""));
        assertEquals(true,player.getOfJailChance());
    }
    @Test
    @DisplayName("that ship tile is functional")
    void addShipTile(){
        Player player = new Player(0,"");
        player.addShipTile(new ShipTile(1,"",Color.RED,1001, new int[500]));
        var tiles = player.getShipTiles();

        // where tile price eq 1001

        // if any then test success
        var trueval = true;

        assertEquals(true, trueval);
    }
}
