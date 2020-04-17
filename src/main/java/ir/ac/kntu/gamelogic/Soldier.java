package ir.ac.kntu.gamelogic;

import java.util.Random;

public class Soldier implements Cloneable{
    double health;
    Gun gun;

    public Soldier(){
        Random random = new Random();
        Caliber caliber =  random.nextDouble() > 0.5 ? Caliber.FiveMM : Caliber.SevenMM;
        setHealth(100);
        if(random.nextDouble() > 0.5){
            setGun(new AssaultRifle(caliber));
            return;
        }
        setGun(new SniperRifle(caliber));
    }

    private void setHealth(double health) {
        if(health > 100){
            this.health = 100;
            return;
        }

        if(health < 0){
            this.health = 0;
            return;
        }

        this.health = health;
    }

    private void setGun(Gun gun){
        try {
            this.gun = gun.clone();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public double getHealth() {
        return health;
    }

    public void decreaseHealth(double damage){
        if(damage < 0){
            damage = 0;
        }
        setHealth(getHealth() - damage);
    }

    public boolean isDead(){
        return getHealth() == 0 ? true : false;
    }

    @Override
    public String toString() {
        String buffer = "";
        buffer += health;
        if(gun instanceof SniperRifle) {
            buffer += "@SniperRifle@";
        }
        else{
            buffer += "@AssaultRifle@";
        }
        buffer += gun.caliber;
        return buffer;
    }

    @Override
    protected Soldier clone() throws CloneNotSupportedException {
        Soldier soldier = (Soldier)super.clone();
        soldier.gun = this.gun.clone();
        return soldier;
    }
}
