package ir.ac.kntu.gamelogic;

import java.util.Random;

public class Gun implements Cloneable{
    int damage;
    double accuracy;
    Caliber caliber;

    public Gun(){
        setDamage(10);
        setAccuracy(0.5);
        setCaliber(Caliber.FiveMM);
    }

    public void setDamage(int damage) {
        if(damage < 1){
            this.damage = 1;
            return;
        }
        this.damage = damage;
    }

    public void setAccuracy(double accuracy) {
        if(accuracy < 0.01){
            this.accuracy = 0.01;
            return;
        }
        this.accuracy = accuracy;
    }

    public void setCaliber(Caliber caliber) {
        if(caliber == null){
            this.caliber = Caliber.FiveMM;
            return;
        }
        this.caliber = caliber;
    }

    public double getAccuracy(){
        return this.accuracy;
    }

    public int getGunDamage(){
        return this.damage;
    }

    int makeDamage(){
        int damage = getGunDamage();
        if(caliber.equals(Caliber.SevenMM)){
            damage += 10;
        }
        return damage;
    }

    double makeAccuracy(){
        double accuracy = getAccuracy();
        if(caliber.equals(Caliber.FiveMM)){
            accuracy *= 1.15;
        }

        if(caliber.equals(Caliber.SevenMM)){
            accuracy *= 0.9;
        }

        return accuracy > 1 ? 1 : accuracy;
    }

    public int getDamage() {
        Random random = new Random();
        return random.nextDouble() > makeAccuracy() ? 0 : makeDamage();
    }

    @Override
    protected Gun clone() throws CloneNotSupportedException {
        return (Gun)super.clone();
    }
}
