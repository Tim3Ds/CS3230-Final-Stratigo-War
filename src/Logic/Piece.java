/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;


/**
 *
 * @author tim
 */
public class Piece implements Comparable<Piece>{
    // devlare peace vareiable
    private final Type type;
    private boolean ONboard;
    

    //movement flags set by check movment funciton
    private final int pID;
    private int travleUp, travleDown, travleLeft, travleRight, x, y;
    public Piece(int id,int playerID) {
        this.type = Type.values()[id];
        this.ONboard = false;
        this.pID = playerID;
        //System.out.println(this.toString());
    }
    public boolean onBoard(){
        return ONboard;
    }
    public void setOnBoard() {
        ONboard = true;
    }
    public void setOffBoard() {
        ONboard = false;
    }
    public int getCount(){
        return this.type.count;
    }
    public char getRank(){
        return this.type.Rank;
    }
    public int maxMoves(){
        return this.type.Movement;
    }
    public void setTravleUP(int t){
        this.travleUp = t;
    }
    public void setTravleDOWN(int t){
        this.travleDown = t;
    }
    public void setTravleLEFT(int t){
        this.travleLeft = t;
    }
    public void setTravleRIGHT(int t){
        this.travleRight = t;
    }
    public int getTravleUP(){
        return this.travleUp;
    }
    public int getTravleDOWN(){
        return this.travleDown;
    }
    public int getTravleLEFT(){
        return this.travleLeft;
    }
    public int getTravleRIGHT(){
        return this.travleRight;
    }
    public boolean canAttack(){
        return this.type.moveAttack;
    }
    public void setXY(int X, int Y){
        this.x = X; this.y = Y;
    }
    public int X(){
        return this.x;
    }
    public int Y(){
        return this.y;
    }
    public int getPlayerID(){
        return pID;
    }
    
    
    public Piece attack(Piece o){
        int temp = this.compareTo(o);
        // this is always the attacker
        // first test the special cases
        // o NoMansLand -3
        // o empty -2
        // this wins 1
        // both loose 0
        // o wins -1
        // o flag capture 2
        switch (temp) {
            case -2:
                System.out.println(this.toString()+ " attacks " + o.toString() + " " + o.toString() + " Is Filled");
                o.ONboard = true;
                this.ONboard = true;
                return this;
            case -1:
                System.out.println(this.toString()+ " attacks " + o.toString() + " " + o.toString() + " Wins");
                this.ONboard = false;
                o.ONboard = true;
                return o;
            case 1:
                System.out.println(this.toString()+ " attacks " + o.toString() + " " + this.toString() + " Wins");
                o.ONboard = false;
                this.ONboard = true;
                return this;
            case 2:
                System.out.println(this.toString()+ " attacks " + o.toString() + " Game Over");
                o.ONboard = false;
                this.ONboard = false;
                return new Piece(14, this.pID);
            default:
                System.out.println(this.toString()+ " attacks " + o.toString() + " Both Die");
                o.ONboard = false;
                this.ONboard = false;
                return new Piece(13, 0);
        }
    }
    @Override
    public String toString(){
        return this.type.name();
    }

    @Override
    public int compareTo(Piece o) {
        // this is always the attacker
        // first test the special cases
        // o NoMansLand -3
        // o empty -2
        // this wins 1
        // both loose 0
        // o wins -1
        // o flag capture 2
        if(o.type.equals(Type.NoMansLand))
            return -3;
        if(o.type.equals(Type.empty))
            return -2;
         
        // this Miner vs Bomb Miner wins 1 else bomb wins -1
        if(this.type.equals(Type.Miner) && o.type.equals(Type.Bomb)){
            return 1;
        }
        if(o.type.equals(Type.Bomb))
            return -1;
        // this spy vs Marshall or this spy vs spy this wins
        if(this.type.equals(Type.Spy)){
            switch (o.type) {
                case Marshall:
                    return 1;
                case Spy:
                    return 0;
                default:
                    return -1;
            }
        }
        if(o.type.equals(Type.Spy))
            return 1;
        // check flag for winner
        if(o.type.equals(Type.Flag))
            return 2;
        if(this.type.Rank > o.type.Rank)
            return 1;
        else if (this.type.Rank == o.type.Rank)
            return 0;
        else 
            return -1;
        
        
    }
    
}
