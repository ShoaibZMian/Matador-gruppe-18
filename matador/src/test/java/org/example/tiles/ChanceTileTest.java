// package org.example.tiles;

// import gui_fields.GUI_Chance;
// import gui_main.GUI;
// import junit.framework.Assert;
// import org.example.Player;
// import org.example.chances.Chance;
// import org.example.chances.PaymentChance;
// import org.junit.jupiter.api.Test;

// import java.awt.*;
// import java.util.ArrayList;

// import static org.junit.jupiter.api.Assertions.*;

// class ChanceTileTest {

// @Test
// void tileAction() {
// boolean success = false;

// try{
// Player player = new Player(0,"");
// Chance chance = new PaymentChance(-1000,"De har kørt frem for “fuldt stop”,
// Betal 1000 kroner i bøde");
// ArrayList<Chance> chanceArrayList = new ArrayList<Chance>();
// chanceArrayList.add(chance);

// ChanceTile chanceTile = new ChanceTile(1);
// chanceTile.tileAction(player,new Player[]{player}, chanceArrayList, new
// GUI());

// success = true;
// }
// catch (Exception e){
// success = false;
// }
// assertEquals(true, success);
// }
// }