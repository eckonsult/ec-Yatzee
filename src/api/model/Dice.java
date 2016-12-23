package api.model;
public class Dice {

    private int value;

    public Dice() {
        rollDice();
    }

    public int getValue() {
        return value;
    }

    public int ReRoll() {
        rollDice();
        return value;
    }

    private void rollDice() {
        value = (int) Math.floor(Math.random() * 5) + 1;
    }
}


