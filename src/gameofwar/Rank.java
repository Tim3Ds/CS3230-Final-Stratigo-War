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
public enum Rank{
    two(2), three(3), four(4), five(5), six(6), seven(7), eight(8), nine(9), ten(10), jack(11), queen(12), king(13), ace(14);
    final int rankOf;
    private Rank(int num){
        this.rankOf = num;
    }
}
