/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.util.Scanner;


/**
 *
 * @author tim
 */
public class Main implements Game{
    private int x,y,dis,Winner = 0;
    private String direction;
    private boolean run;
    private Scanner scan;
    private Player p1, p2;
    private Board b;

    @Override
    public void initialize() {
        scan = new Scanner(System.in);
        p1 = new Player("Player 1",1);
        p2 = new Player("Player 2",2);
        b = new Board();        
        
        
        
    }

    @Override
    public void play(){
        System.out.print(b.toString());
        System.out.println(p1.showTheDead());
        System.out.println(p2.showTheDead());
        System.out.println("Add Player 1 flag to 0,0");
        b.addPiece(p1.getPiece(0), 0, 0);
        System.out.print(b.toString());
        System.out.println("Add Player 2 flag to 0,9");
        b.addPiece(p2.getPiece(0), 0, 9);
        System.out.print(b.toString());
        System.out.println("Add 6 more pieces for each player");
        p1.randomBoard(b);
        p2.randomBoard(b);
//        b.addPiece(p1.getPiece(1), 0, 1);
//        b.addPiece(p1.getPiece(18), 0, 6);
//        b.addPiece(p1.getPiece(7), 1, 2);
//        b.addPiece(p1.getPiece(16), 3, 3);
//        b.addPiece(p1.getPiece(24), 2, 3);
//        b.addPiece(p1.getPiece(38), 4, 3);
//        
//        b.addPiece(p2.getPiece(1), 0, 8);
//        b.addPiece(p2.getPiece(18), 0, 3);
//        b.addPiece(p2.getPiece(8), 1, 7);
//        b.addPiece(p2.getPiece(16), 2, 6);
//        b.addPiece(p2.getPiece(24), 3, 6);
//        b.addPiece(p2.getPiece(38), 4, 6);
        
        
        System.out.print(b.toString());
        System.out.println("Players are Player 1 Blue Player 2 red\n"
                + "when you see the prompt \n\"Player " + b.turnCheck() + " select a peace \""
                + "\n\" x:\" Input the x value for the piece you want to move"
                + "\n\" y:\" Input the y value for the piece you want to move"
                + "\n you will then be told where that piece can move and prompted "
                + "\n for the direction you want to move the piece"
                + "\n\" up, down, left, right:\" \"up\" for example "
                + "\n the next promt will ask how far you want to move the piece you can "
                + "\n only move it as far as the moveCheck has listed "
                + "\n\" distance:\" \"1\" for example"
                + "\n have fun! The goal is to capture the flag.");
        run = true;
//        System.out.println(p1.showTheDead());
//        System.out.println(p2.showTheDead());
        while(run){
            System.out.println(p1.showTheDead());
            System.out.println(p2.showTheDead());
            System.out.print("Player " + b.turnCheck() + " select a peace \n x:");
            x = scan.nextInt();
            System.out.print(" y:");
            y = scan.nextInt();
            b.choosePiece(x, y);
            System.out.print(" up, down, left, right:");
            scan.nextLine();
            direction = scan.nextLine();
            System.out.print(" Distance:");
            dis = scan.nextInt();
            b.move(b.choosePiece(x, y), direction, dis);
            System.out.println(b.toString());
            if(p2.checkWin()==1)
                Winner = 2;
            if(p1.checkWin()==1)
                Winner = 1;
            
        }
    }

    @Override
    public void displayWinner(){
        System.out.println(p1.showTheDead());
        System.out.println(p2.showTheDead());
        System.out.println("Winner: Player " + Winner);
    }
    public static void main(String[] args) {
        // TODO code application logic here
        Main m = new Main();
        m.initialize();
        m.play();
        m.displayWinner();
    }
    
}
