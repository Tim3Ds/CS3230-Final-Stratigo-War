/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author tim
 */
public class Board{
    private final List<Y> position;//[x][y] position;
    private final Piece NoMansLand = new Piece(12, 0);
    private final Piece empty = new Piece(13, 0);
    private int turn;
    private String Message = "No New Messages";
    public Board(){
        this.position = new ArrayList<>(9);
        for(int i = 0; i <= 9; i++) {
            this.position.add(new Y(9));
        }
        turn = 1;
        this.position.get(2).set(4,NoMansLand);
        this.position.get(3).set(4,NoMansLand);
        this.position.get(2).set(5,NoMansLand);
        this.position.get(3).set(5,NoMansLand);
        this.position.get(6).set(4,NoMansLand);
        this.position.get(7).set(4,NoMansLand);
        this.position.get(6).set(5,NoMansLand);
        this.position.get(7).set(5,NoMansLand);
    }
    public int addPiece(Piece a,int x,int y){
        Piece temp = this.position.get(x).getPiece(y);
        int comp = temp.compareTo(empty);
        if(comp == -2){
            a.setXY(x, y);
            this.position.get(x).set(y,a);
            a.setOnBoard();
            return 1;
        }else
            return 0;
        
    }
    private int checkAttack(Piece a, int moves) {
        if (a.canAttack() || moves == 1)
            return 1;
        else
            return 0;
    }
    public Piece getPiece(int x, int y){
        return this.position.get(x).getPiece(y);
    }
    public String getMessage(){
        return Message;
    }
    public Piece choosePiece(int x, int y){
        Piece temp = this.position.get(x).getPiece(y);
        if (turnCheck() != temp.getPlayerID()){
            Message = "Cant Move other Players Pieces";
            System.out.println(Message);
            temp.setTravleUP(0);
            temp.setTravleDOWN(0);
            temp.setTravleLEFT(0);
            temp.setTravleRIGHT(0);
            return temp;
        }
        temp.setTravleUP(this.upCheck(temp, temp.maxMoves(), y));
        System.out.println("travle: " +  temp.getTravleUP());
        temp.setTravleDOWN(this.downCheck(temp, temp.maxMoves(), y));
        System.out.println("travle: " + temp.getTravleDOWN());
        temp.setTravleLEFT(this.leftCheck(temp, temp.maxMoves(), x));
        System.out.println("travle: " + temp.getTravleLEFT());
        temp.setTravleRIGHT(this.rightCheck(temp, temp.maxMoves(), x));
        System.out.println("travle: " + temp.getTravleRIGHT());
        return temp;
    }
    private int upCheck(Piece a, int moves, int y){
        //check out of bounds or out of range recursive call 
        if(moves == 0 || y+1 < 0 || y+1 > 9){
            System.out.print("\n upCheck Out of range ");
            Message = "Movment Check range";
            return 0;
        }
        System.out.print("\nupCheck ");
        // get next piece
        Piece temp = this.position.get(a.X()).getPiece(y+1);
        // make sure its not your piece
        if (a.getPlayerID() != temp.getPlayerID())
            switch (a.compareTo(temp)) {
                case -2:
                    System.out.print("empty ");
                    return 1+upCheck(a, --moves, ++y);
                case -3:
                    System.out.print("NoMansLand ");
                    return 0;
                default:
                    return checkAttack(a, moves);
        }else{
            System.out.print("Your Piece ");
            return 0;
        }
            
    }
    private int downCheck(Piece a, int moves, int y){
        if(moves == 0 || y-1 < 0 || y+1 > 9){
            System.out.print("\n downCheck Out of range ");
            Message = "Movment Check range";
            
            return 0;
        }
        System.out.print("\ndownCheck ");
        Piece temp = this.position.get(a.X()).getPiece(y-1);
        if (a.getPlayerID() != temp.getPlayerID())
            switch (a.compareTo(temp)) {
                case -2:
                    System.out.print("empty ");
                    return 1+downCheck(a, --moves, --y);
                case -3:
                    System.out.print("NoMansLand ");
                    return 0;
                default:
                    System.out.print(temp.toString() + " ");
                    return checkAttack(a, moves);
            }
        else{
            System.out.print("Your Piece ");
            return 0;
        }
                
    }
    private int leftCheck(Piece a, int moves, int x){
        if(moves == 0 ||  x-1 < 0 || x-1 > 9){
            System.out.print("\n leftCheck Out of range ");
            Message = "Movment Check range";
            return 0;
        }
        System.out.print("\nleftCheck ");
        Piece temp = this.position.get(x-1).getPiece(a.Y());
        if (a.getPlayerID() != temp.getPlayerID())
            switch (a.compareTo(temp)) {
                case -2:
                    System.out.print("empty ");
                    return 1+leftCheck(a, --moves, --x);
                case -3:
                    System.out.print("NoMansLand ");
                    return 0;
                default:
                    System.out.print(temp.toString() + " ");
                    return checkAttack(a, moves);
            }
        else{
            System.out.print("Your Piece ");
            return 0;
        }
    }
    private int rightCheck(Piece a, int moves, int x){
        if(moves == 0 || x+1 < 0 || x+1 > 9){
            System.out.print("\n rightCheck Out of range ");
            Message = "Movment Check range";
            return 0;
        }
        System.out.print("\nrightCheck ");
        Piece temp = this.position.get(x+1).getPiece(a.Y());
        if (a.getPlayerID() != temp.getPlayerID())
            switch (a.compareTo(temp)) {
                case -2:
                    System.out.print("empty ");
                    return 1+rightCheck(a, --moves, ++x);
                case -3:
                    System.out.print("NoMansLand ");
                    return 0;
                default:
                    System.out.print(temp.toString() + " ");
                    return checkAttack(a, moves);
            }
        else{
            System.out.print("Your Piece ");
            return 0;
        }
    }
    
    public void move(Piece a,String direction,int distance){
        Piece temp;
        int x,y;
        x=a.X();
        y=a.Y();
        if(a.maxMoves()>=distance){
            switch(direction.toLowerCase()){
                case "up":
                    temp = this.position.get(x).getPiece(y+distance);
                    if(a.getTravleUP() < distance || distance == 0){
                        System.out.println(" Cant move " + temp.toString() + " Up travle: " + temp.getTravleUP());
                        Message = " Cant move " + temp.toString() + " there.";
                        break;
                    }
                    this.position.get(x).set(y+distance, a.attack(temp));
                    this.position.get(x).remove(y,empty);
                    a.setXY(x, y+distance);
                    turnChange();
                    break;
                case "down":
                    temp = this.position.get(x).getPiece(y-distance);
                    if(a.getTravleDOWN()< distance || distance == 0){
                        System.out.println(" Cant move " + temp.toString() + " Down travle: " + temp.getTravleDOWN());
                        Message = " Cant move " + temp.toString() + " there.";
                        break;
                    }
                    this.position.get(x).set(y-distance, a.attack(temp));
                    this.position.get(x).remove(y,empty);
                    a.setXY(x, y-distance);
                    turnChange();
                    break;
                case "left":
                    temp = this.position.get(x-distance).getPiece(y);
                    if(a.getTravleLEFT()< distance || distance == 0){
                        System.out.println(" Cant move " + temp.toString() + " Left travle: " + temp.getTravleLEFT());
                        Message = " Cant move " + temp.toString() + " there.";
                        break;
                    }
                    this.position.get(x-distance).set(y, a.attack(temp));
                    this.position.get(x).remove(y,empty);
                    a.setXY(x-distance, y);
                    turnChange();
                    break;
                case "right":
                    temp = this.position.get(x+distance).getPiece(y);
                    if(a.getTravleRIGHT()< distance || distance == 0){
                        System.out.println(" Cant move " + temp.toString() + " Right travle: " + temp.getTravleRIGHT());
                        Message = " Cant move " + temp.toString() + " there.";
                        break;
                    }
                    this.position.get(x+distance).set(y, a.attack(temp));
                    this.position.get(x).remove(y,empty);
                    a.setXY(x+distance, y);
                    turnChange();
                    break;
                default:
                    System.out.println(" Piece Cant move that way");
                    Message = " Piece Cant move that way";
                    break;
            }// end switch           
        }else{
            System.out.println(" Piece Cant move that far");
            Message = "Piece Cant move that far";
        }
            
    }
    
    private void turnChange(){
        if(turn == 1)
            turn = 2;
        else
            turn = 1;
    }
    public int turnCheck(){
        return turn;
    }
    @Override
    public String toString(){
        String temp = "  ";
        for(int y = 0;y<=9;y++){
            for(int dex = 0;dex<62;dex++)
                    temp += "-";
            temp += "\n" + y;
            for(int x = 0; x< this.position.size();x++){
                temp += "  |  ";
                if(this.position.get(x).getPiece(y).getPlayerID() == 2)
                    temp += "\033[31m";// red
                else if(this.position.get(x).getPiece(y).getPlayerID() == 1)
                    temp += "\033[34m";// blue
                else 
                    temp += "\033[0m";// black
                temp += this.position.get(x).getPiece(y).getRank() + "\033[0m";

            }
            temp += "  |\n  ";
            
        }
        for(int dex = 0;dex<62;dex++)
            temp += "-";
        temp += ("\n      0     1     2     3     4     5     6     7     8     9 \n");
        return temp + "\n";
    }
}