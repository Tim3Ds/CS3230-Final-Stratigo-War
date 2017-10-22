/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameofwar;

/**
 *
 * @author test
 */
public class DeckDriver {
    public static void main(String[] args) {
        // TODO code application logic here
        Deck deck = new Deck();
        System.out.printf(deck.toString());
        Hand hand = new Hand();
        for (int i = (deck.getGroupSize()/2);i>0;i--)
            hand.addCard(deck.dealCard());
        System.out.println(hand.toString());
        hand.orderCards();
        System.out.println(hand.toString());
    }
}
