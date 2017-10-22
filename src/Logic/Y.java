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
public class Y extends GroupPieces{
    Y(int size){
        for(int i = size;i>=0;i--)
            super.addPeace(13, 0);
    }
    public void remove(int y, Piece empty){
        //super.getPiece(y).setOffBoard();
        super.Group.set(y, empty);
    }
}
