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
public class Player extends GroupPieces{
    private String name;
    private final int pID;
    private int countOffboard = 0;
    private final Piece flag;
    public Player(String Name, int id1or2){
        name = Name;
        pID = id1or2;
        for(int i = 0;i<12; i++){
            int num = Type.values()[i].count;
            for(int count = 0;count<num; count++){
                super.addPeace(i, this.pID);
            }
        }
        flag = super.getPiece(0);
    }
    public String getName(){
        return name;
    }
    public void setName(String InputName){
        name = InputName;
    }
    public void randomBoard(Board b){
        super.Shuffle();
        if(this.pID == 0){
            System.out.println("missing player id should be 1 or 2");
            return;
        }
        int i = super.getSize()-1;
        Piece temp = super.getPiece(i);
        
        if(this.pID == 1){
            for(int x = 0;10>x;x++){
                for(int y = 9;y>5;y--){
                    b.addPiece(temp, x, y);
                    i--;
                    if(i<0)
                        return;
                    temp = super.getPiece(i);
                }// for y
            }//for x
        }// if id 1
        if(this.pID == 2){
            for(int x = 0;10>x;x++){
                for(int y = 0;4>y;y++){
                    b.addPiece(temp, x, y);
                    i--;
                    if(i<0)
                        return;
                    temp = super.getPiece(i);
                }// for y
            }//for x
        }// if id 2
        
                
    }// end randomBoard
    public int checkWin(){
        if(!flag.onBoard()){
            return this.pID;
        }
        else return 0;
    }
    public int getCountOffBoard(){
        return countOffboard;
    }
    public String showTheDead(){
        countOffboard = 0;
        String temp = this.name + "'s Pieces\n";
        for (int i = 0; i< this.getSize();i++){
            if(!this.Group.get(i).onBoard()){
                temp += this.Group.get(i).toString() + " " + this.Group.get(i).getRank() + " ";
                countOffboard++;
            }
            if((i+1)%4 == 0){
                temp += "\n";
            }
        }
        return temp + "\n";
    }
    @Override
    public String toString(){
        return name + "\n" + super.toString();
    }
}
