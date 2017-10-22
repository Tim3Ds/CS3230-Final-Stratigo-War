/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameofwar;

import java.util.Random;

/**
 *
 * @author test
 */
public class Deck extends GroupOfCards{
    // build a deck of 52 unique card
    public Deck(){
        for(int a = 0; a <= 3; a++){
            for(int b = 0; b <= 12; b++)
            {
                super.addCard(new Card(Rank.values()[b],Suit.values()[a]));
            }
        }
        // randomize order of cards
        super.Shuffle();
    }
    
    public Card RandomCard(){
        // get random card from deck
        Random rand = new Random();
        return super.getCard(rand.nextInt(super.getGroupSize()));
    }
    
    public Card dealCard(){
        // get top card and remove it form deck
        temp = super.getCardFirst();
        super.removeCard(temp);
        return temp;
    }
    public void dealWar(Hand a, Hand b){
        // get top card and remove it form deck
        while(super.getGroupSize()>0){
            a.addCard(this.dealCard());
            b.addCard(this.dealCard());
        }
        
    }
    @Override
    public String toString(){
        return "\nCard Count: " + super.getGroupSize() + " cards in Deck\n" + super.toString();
    }
    
}
