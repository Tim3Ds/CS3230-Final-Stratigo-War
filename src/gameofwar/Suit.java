/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameofwar;

/**
 *
 * @author tim
 */
public enum Suit{
    diamonds(1), hearts(2), spades(3), clubs(4);
    final int siutID;
    private Suit(int num){
        this.siutID = num;
    }
}
