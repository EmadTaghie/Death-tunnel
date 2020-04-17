package ir.ac.kntu.gamelogic;

public class AssaultRifle extends Gun {
    public AssaultRifle(Caliber caliber){
        setDamage(10);
        setAccuracy(0.5);
        setCaliber(caliber);
    }
}
