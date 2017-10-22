/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author tim
 */
public abstract class GroupPieces{
    final ArrayList<Piece> Group;//[x][y]
    private int size;
    int GroupID = 0;
    public GroupPieces(){
        this.Group = new ArrayList();
        this.size = Group.size();
        GroupID++;
    }
    public void set(int y, Piece a){
        Group.set(y, a);
    }
    public int addPeace(int PieceID, int playerID){
        size++;
        Piece p = new Piece(PieceID, playerID);
        Group.add(p);
        if(Group.size() == size)
            return 0;
        else
            return 1;
    }
    
    public Piece getPiece(int index){
        return Group.get(index);
    }
    public int getSize(){
        return this.Group.size();
    }
    public void Shuffle(){
        Collections.shuffle(Group);
    }
    
    @Override
    public String toString(){
        String  printGroup = "";
        for(int i = this.size-1;i >= 0; i--){
            printGroup = printGroup + Group.get(i).toString() + "\n";
        }
        return printGroup;
    }
    
    
    
}
