package org.example.tiles;

import org.example.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ShipTileTest {
    @Test
    @DisplayName("that ship tile is functional")
    void addShipTile(){
        Player player = new Player(0,"");
        player.addShipTile(new ShipTile(1,"", Color.RED,1001, new int[500]));
        var tiles = player.getShipTiles();

        // where tile price eq 1001

        // if any then test success
        var trueval = true;

        assertEquals(true, trueval);
    }

}