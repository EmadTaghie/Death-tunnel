package ir.ac.kntu.gamelogic;

import java.util.Random;

public class SniperRifle extends Gun {
    private boolean scope;

    public SniperRifle(Caliber caliber){
        setDamage(20);
        setAccuracy(0.6);
        setCaliber(caliber);
        setScope(false);
    }

    private void setScope(boolean scope){
        this.scope = scope;
    }

    private boolean getScope(){
        return this.scope;
    }

    @Override
    double makeAccuracy() {
        Random random = new Random();
        double accuracy =  super.makeAccuracy();
        if(getScope()){
            accuracy *= (1 + random.nextDouble() * 0.1 + 0.05);
        }
        return accuracy > 1 ? 1 : accuracy;
    }

    public int getDamage(boolean scope) {
        setScope(scope);
        return getDamage();
    }
}
