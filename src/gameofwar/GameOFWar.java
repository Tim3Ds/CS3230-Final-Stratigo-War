/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameofwar;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author test
 */
public class GameOFWar implements Game{
    final Scanner scan = new Scanner(System.in);
    private String player1, player2;
    private Deck deck;
    private Hand Winner, looser, hand1, hand2, discard1, discard2;
    private int rounds, count = 1;
    boolean run = true;
    static PrintWriter out = null;
    /**
     * @param args the command line arguments
     */
    
    /**
     * @param haDIS
     * @param hbDIS
     * @param ha
     * @param hb
     * @param out
     * @throws java.io.IOException
     */
    public static void war(Hand ha, Hand hb, Hand haDIS, Hand hbDIS){
//        Hand Winner = new Hand("WAR");
//        war(ha,hb, haDIS, hbDIS, Winner, out);
    }
    private static void war(Hand ha, Hand hb, Hand haDIS, Hand hbDIS, Hand Winner){
//        Card a,b;
////      both players play cards and compare them
//        a = ha.playCard();
//        b = hb.playCard();
//        out.println(ha.getOwner() + " Plays " + a.toString());
//        out.println(hb.getOwner() + " Plays " + b.toString());
//        Winner.addCard(a);
//        Winner.addCard(b);
////      if war
//        if (a.rank.rankOf == b.rank.rankOf){
//            out.println(a.toString() +"="+b.toString());
////          make sure player has cards for war
//            if (ha.getGroupSize() <= 3){
//                ha.addDiscardPileToHand(haDIS);
//                if (ha.getGroupSize() <= 3){
//                    hbDIS.passToDiscardPile(Winner);
//                    hbDIS.passToDiscardPile(ha);
//                    out.println(ha.getOwner() + " is out of cards");
//                    return;
//                }
//                    
//            }
//            if (hb.getGroupSize() <= 3){
//                hb.addDiscardPileToHand(hbDIS);
//                if (hb.getGroupSize() <= 3){
//                    haDIS.passToDiscardPile(Winner);
//                    haDIS.passToDiscardPile(hb);
//                    out.println(hb.getOwner() + " is out of cards");
//                    return;
//                }
//                    
//            }
////          each player plays three cards
//            Winner.addCard(ha.playCard());
//            Winner.addCard(ha.playCard());
//            Winner.addCard(ha.playCard());
//            Winner.addCard(hb.playCard());
//            Winner.addCard(hb.playCard());
//            Winner.addCard(hb.playCard());
////          recurse into function
//            out.println("WAR" + Winner.toString());
//            war(ha,hb,haDIS,hbDIS,Winner, out);
//        }else if(a.rank.rankOf > b.rank.rankOf){
//            //Winner.SetOwner(ha.getOwner());
//            out.println(a.toString() +">"+ b.toString());
//            out.println(ha.getOwner()+" Wins Round");
//            haDIS.passToDiscardPile(Winner);
//            //System.out.println(ha.toString() + haDIS.toString());
//            return;
//        }
//        //Winner.SetOwner(hb.getOwner());
//        out.println(a.toString() +"<"+ b.toString());
//        out.println(hb.getOwner()+" Wins Round");
//        hbDIS.passToDiscardPile(Winner);
//        //System.out.println(hb.toString() + hbDIS.toString());
    }

    @Override
    public void initialize() {
        System.out.println("Who is playing");
        System.out.print("Player 1: ");
        player1 = scan.nextLine();
        System.out.print("Player 2: ");
        player2 = scan.nextLine();
        System.out.print("How many rounds 0 runs till someone wins: ");
        rounds = scan.nextInt();
//      Declare deck, hands, discards
        
        deck = new Deck();
        hand1 = new Hand(player1);
        hand2 = new Hand(player2);
        discard1 = new Hand("discard1");
        discard2 = new Hand("discard2");
        
//      deal cards to both hands
        deck.dealWar(hand1, hand2);
//        Card card1 = new Card(Rank.King,Suit.Clubs);
//        Card card2 = new Card(Rank.Queen,Suit.Clubs);
//        Card card3 = new Card(Rank.seven,Suit.Clubs);
//        Card card4 = new Card(Rank.five,Suit.Clubs);
//        Card card5 = new Card(Rank.six,Suit.Clubs);
//        Card card6 = new Card(Rank.Jack,Suit.Clubs);
//        hand1.addCard(card1);
//        hand2.addCard(card2);
//        hand1.addCard(card3);
//        hand2.addCard(card4);
//        hand1.addCard(card5);
//        hand2.addCard(card6);
    }

    @Override
    public void play(){
               
        while(run){
            out.println("Round: " + count);
            
            //war(hand1, hand2, discard1, discard2, out);
            
            if(hand1.getGroupSize() < 3 || hand2.getGroupSize() < 3){
                hand1.addDiscardPileToHand(discard1);
                hand2.addDiscardPileToHand(discard2);
            }
            if(hand1.getGroupSize()==0 || hand2.getGroupSize()==0 || count == rounds){
                run = false;
            }
            count++;

        }
        
        // get winner
        hand1.addDiscardPileToHand(discard1);
        hand2.addDiscardPileToHand(discard2);
        if(hand1.getGroupSize() == hand2.getGroupSize()){
            Winner = hand1;
            looser = hand2;
            Winner.SetOwner("Draw");
            looser.SetOwner("Draw");
        }else if(hand1.getGroupSize() > hand2.getGroupSize()){
            Winner = hand1;
            looser = hand2;
        }else{
            Winner = hand2;
            looser = hand1;
        }
        
    }

    @Override
    public void displayWinner() throws IOException {
        //	end game print number of cards each player has 
//        out.println("Winner Name: " + Winner.getOwner() + " Cards: " + Winner.getGroupSize()+Winner.toString());
//        out.println("Looser Name: " + looser.getOwner() + " Cards: " + looser.getGroupSize());
    }

//    public static void main(String[] args) throws IOException {
//        GameOFWar gw = new GameOFWar();
//        out = new PrintWriter(new FileWriter("GameOfWarOutput.txt"));
//        gw.initialize();
////      start game
//        gw.play();
////      loop until winner or number of rounds is met
//        gw.displayWinner();
//        out.close();
//        
//    }

    
}
