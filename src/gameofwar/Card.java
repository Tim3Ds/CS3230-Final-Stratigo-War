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
public class Card implements Comparable<Card>{
    // hold rank and suit
    final Rank rank;
    final Suit suit;
    // default constructor 
    public Card(){
        // get a random number for rank and suit
        Random rand = new Random();
        int a = rand.nextInt(3);
        int b = rand.nextInt(12);
        
        // set a random card 
        this.suit = Suit.values()[a];
        this.rank = Rank.values()[b];     
    }
    // PRIMARY constructor
    public Card(Rank a, Suit b){
        // take a rank and suit build set a card
        this.rank = a;
        this.suit = b; 
    }
    
    @Override
    public String toString(){
        // convert rank and suit to a string describing the card
        return String.format("%s_of_%s", rank, suit);
    }
    
    @Override
    public int compareTo(Card a) {
        // make a comparision on two cards
        // first test the suit and return 1 if first is card is grater 
        // then the second and -1 for the revers
        // if they are the same suit check the rank
        if (this.suit.siutID > a.suit.siutID){
            return 1;
        }else if (this.suit.siutID < a.suit.siutID){
            return -1;
        }else{
            // if the rank is first is grater then the second same as suit
            // if equal return 0 
            if (this.rank.rankOf > a.rank.rankOf){
                return 1;
            }else if (this.rank.rankOf < a.rank.rankOf){
                return -1;
            }else
                return 0;//end rank
        }//end suit
        
    }// end compareTo
    
    public int compWAR(Card a){
        if (this.rank.rankOf > a.rank.rankOf){
            return 1;
        }else if (this.rank.rankOf < a.rank.rankOf){
            return -1;
        }else
            return 0;//end rank
    }
      
}// end main
