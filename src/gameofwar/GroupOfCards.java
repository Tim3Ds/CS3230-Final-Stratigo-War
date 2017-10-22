/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameofwar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/**
 *
 * @author test
 */
public abstract class GroupOfCards {
    private final ArrayList<Card> groupOfCards;
    Card temp;

    public GroupOfCards() {
        this.groupOfCards = new ArrayList();
    }
    
    // way to add a card to the group
    public void addCard(Card a){
        groupOfCards.add(a);
    }
    // way to remove a card from a group
    public void removeCard(Card a){
        groupOfCards.remove(a);
    }
    public void moveCard(Card a){
        
    }
    public void moveGroupToThis(Hand discard){
        while(discard.getGroupSize()> 0){
            temp = discard.getCardLast();
            discard.removeCard(temp);
            groupOfCards.add(temp);
            
        }
    }
    // way to get the a card at a location
    public Card getCard(int i){
        return groupOfCards.get(i);
    }
    public Card getCardLast(){
        return groupOfCards.get(this.getGroupSize()-1);
    }
    public Card getCardFirst(){
        return groupOfCards.get(0);
    }
    // way to get the size of the group
    public int getGroupSize(){
        
        return groupOfCards.size();
    }
    // way to shuffle the group
    public void Shuffle(){
        Collections.shuffle(groupOfCards);
    }
    // way to organize the group
    public void orderCards(){
        Comparator<Card> orderHItoLo = (o1, o2)-> o1.compareTo(o2);//lamda expression new in java 8
        groupOfCards.sort(orderHItoLo);
        //Collections.sort(groupOfCards);// old with implimantations only works one way
    }
    // way to print out cards in a group
    @Override
    public String toString(){
        String  deckPrint = "";
        for(int i = this.getGroupSize()-1;i >= 0; i--){
            deckPrint = deckPrint + groupOfCards.get(i).toString() + "\n";
        }
        return deckPrint;
    }
}
