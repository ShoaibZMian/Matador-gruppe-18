package org.example.models;

public class LanguageModel {
    public Game game;
    public Chance[] chance;
    public Tile tile;

    public class Game {
        public SortPlayers sortPlayers;
        public CantAfford cantAfford;
        public GameLoop gameLoop;
        public CheckWin checkWin;
        public ConfigGetPlayers configGetPlayers;

        public class CheckWin {
            public String balance;
            public String property;
            public String tie;
        }

        public class SortPlayers {
            public String message;
            public String button;
        }

        public class CantAfford {
            public String message;
            public String button;
        }

        public class GameLoop {
            public String playerTurn;
            public RollDie rollDie;

            public class RollDie {
                public String message;
                public String button;
            }
        }

        public class ConfigGetPlayers {
            public String numberOfPlayers;
            public String initial;
            public String age;
            public String name;
            public String car;
            public String invalid;
            public String validPlayer;
        }

    }

    public class Chance {
        public String description;
    }

    public class Tile {
        public Property property;
        public String freeParking;
        public String start;
        public TileList[] tileList;

        public class Property {
            public Buy buy;
            public Rent rent;
            public OwnTile ownTile;
            public CantAfford cantAfford;

            public class Buy {
                public String message;
                public String button;
            }

            public class Rent {
                public String message;
                public String button;
            }

            public class OwnTile {
                public String message;
                public String button;
            }

            public class CantAfford {
                public String message;
                public String button;
            }
        }

        public class TileList {
            public String title;
            public String subtext;
        }
    }

}
