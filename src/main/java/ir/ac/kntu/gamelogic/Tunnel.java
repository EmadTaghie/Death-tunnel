package ir.ac.kntu.gamelogic;

import net.sf.saxon.expr.InstanceOfExpression;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tunnel {
    private List<Soldier>[] soldiers;
    private List<Soldier>[] aliveSoldiers;
    private int round;
    private Random random = new Random();

    public Tunnel(int length){
        this.soldiers = new List[2];
        this.soldiers[0] = new ArrayList<>();
        this.soldiers[1] = new ArrayList<>();
        this.aliveSoldiers = new List[2];
        for (int i = 0; i < length; i++){
            this.soldiers[0].add(new Soldier());
            this.soldiers[1].add(new Soldier());
        }
        aliveSoldiers[0] = new ArrayList<>(soldiers[0]);
        aliveSoldiers[1] = new ArrayList<>(soldiers[1]);
        this.round = 0;
    }

    public void start(){
        while (aliveSoldiers[0].size() != 0 && aliveSoldiers[1].size() != 0){
            printStartRound();
            draw();
            printEndRound();
            this.round++;
        }

        if(aliveSoldiers[0].size() == 0){
            System.out.println("\n\t\t\tVICTORY FOR BLUE TEAM");
        }
        else {
            System.out.println("\n\t\t\tVICTORY FOR RED TEAM");
        }
    }

    private void draw(){
        int side = random.nextInt(2);
        int sideSoldierNum = random.nextInt(aliveSoldiers[side].size());
        int rivalSideSoldierNum = random.nextInt(aliveSoldiers[1 - side].size());
        sideSoldierNum = soldiers[side].indexOf(aliveSoldiers[side].get(sideSoldierNum));
        rivalSideSoldierNum = soldiers[1 - side].indexOf(aliveSoldiers[1 - side].get(rivalSideSoldierNum));
        shoot(side, sideSoldierNum, rivalSideSoldierNum);
        if(soldiers[side].get(sideSoldierNum).isDead()){
            aliveSoldiers[side].remove(soldiers[side].get(sideSoldierNum));
            System.out.println("S" + (sideSoldierNum + 1) + " has died");
            return;
        }
        shoot(1 - side, rivalSideSoldierNum, sideSoldierNum);
        if(soldiers[1 - side].get(rivalSideSoldierNum).isDead()){
            aliveSoldiers[1 - side].remove(soldiers[1 - side].get(rivalSideSoldierNum));
            System.out.println("S" + (rivalSideSoldierNum + 1) + " has died");
        }
    }

    private void shoot(int side, int sideNum, int rivalNum){
        int damage = 0;
        boolean scope = random.nextBoolean();
        if(soldiers[1 - side].get(rivalNum).gun instanceof SniperRifle) {
            damage = ((SniperRifle)soldiers[1 - side].get(rivalNum).gun).getDamage(scope);
            printShooting(side, sideNum, rivalNum, damage, scope);
        }
        else{
            damage = soldiers[1 - side].get(rivalNum).gun.getDamage();
            printShooting(side, sideNum, rivalNum, damage, false);
        }
        soldiers[side].get(sideNum).decreaseHealth(damage);
    }

    public List<Soldier>[] getSoldiers() {
        return this.soldiers.clone();
    }

    private void printStartRound() {
        System.out.println("###########ROUND " + round + "###########");
        System.out.print("Red Team: ");
        printSoldiers(0);
        System.out.println();
        System.out.print("Blue Team: ");
        printSoldiers(1);
    }

    private void printEndRound(){
        System.out.println("Red Team alive: " + aliveSoldiers[0].size());
        System.out.println("Blue Team alive : " + aliveSoldiers[1].size());
        System.out.println("###########END ROUND " + round + "###########");
    }

    private void printSoldiers(int side){
        for (int i = 0; i < soldiers[side].size(); i++){
            if(i % 3 == 0){
                System.out.print("\n\t");
            }
            System.out.print("[S" + (i + 1) + "$" +  soldiers[side].get(i) + "]");
        }
        System.out.println();
    }

    private void printShooting(int side, int sideNum, int rivalNum, int damage, boolean scope){
        String buffer = "";
        if(side == 0){
            buffer += "Red team S" + (sideNum + 1) + " get Shot from Blue Team S"
                    + (rivalNum + 1) + " with " + damage + " damage";
        }
        else{
            buffer += "Blue team S" + (sideNum + 1) + " get Shot from Red Team S"
                    + (rivalNum + 1) + " with " + damage + " damage";
        }

        if(scope){
            buffer += " in open scope";
        }
        System.out.println(buffer);
    }
}
