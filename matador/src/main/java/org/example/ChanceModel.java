package org.example;

public class ChanceModel {
    public final String message;
    public final int newPosition;
    public final int amount;

    public ChanceModel(String message, int newPosition, int amount) {
        this.newPosition = newPosition;
        this.amount = amount;
        this.message = "";
    }
}
