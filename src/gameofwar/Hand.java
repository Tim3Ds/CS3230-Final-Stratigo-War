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
public class Hand extends GroupOfCards {
    // define ways a hand would be used that may not be included in group methods
    // or may be different form the way the group methods do it 
    private String OwnerName;
//    private Hand discard = new Hand();
    public Hand(){  
        OwnerName = "Joe";
    }
    public Hand(String whosHand){
        OwnerName = whosHand;
    }
    public String getOwner(){
        return OwnerName;
    }
    public void SetOwner(String NewOwner){
        OwnerName = NewOwner;
    }
    // place a card from hand and remove it 
    public Card playCard(){
        Card temp = super.getCardFirst();
        super.removeCard(temp);
        return temp;
    }
    
    // change the toString method for hand spasific usage 
    @Override
    public String toString(){
        return "\n" + OwnerName + " has " + super.getGroupSize() + " cards in Hand \n" + super.toString();
    }
    public void addDiscardPileToHand(Hand discard){
        this.moveGroupToThis(discard);
    }
    public void passToDiscardPile(Hand HandWon){
        this.moveGroupToThis(HandWon);
    }
}
