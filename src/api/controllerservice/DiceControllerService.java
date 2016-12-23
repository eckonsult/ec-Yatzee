package api.controllerservice;

import api.model.Dice;
import api.model.DiceSet;

public class DiceControllerService {

    public static Dice GetDice() {
        Dice dice = new Dice();
        return dice;
    }

    public static DiceSet GetDiceSet(int f) {
        return new DiceSet(f);
    }
}
