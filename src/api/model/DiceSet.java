package api.model;
public class DiceSet {

    Dice[] dices;

    public DiceSet() {
        this(5);
    }

    public DiceSet(int f) {
        dices = new Dice[f];
        for (int i = 0; i < f; i++) {
            dices[i] = new Dice();
        }
       
    }
}
