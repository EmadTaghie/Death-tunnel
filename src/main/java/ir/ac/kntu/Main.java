package ir.ac.kntu;

import ir.ac.kntu.gamelogic.Tunnel;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter lobby size");
        Tunnel tunnel = new Tunnel(input.nextInt());
        tunnel.start();
    }
}
