package GUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import gameofwar.Card;
import gameofwar.Deck;
import gameofwar.Game;
import gameofwar.Hand;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author tim
 */
public class WARcontroller implements Initializable, Game {

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initialize();
        String cardImage = "/images/NoCard.png";
        NOcard = new Image(cardImage);
        cardImage = "/images/back.png";
        imBack = new Image(cardImage);
        
    }
    
    private Deck deck;
    private Hand Winner, looser, hand1, hand2, discard1, discard2;
    private int rounds = 0;
    private boolean CardsInPlay;
    private Card H1Played;
    private Card H2Played;
    private Image NOcard;
    private Image imBack;
    String passthrough;
    @Override
    public void initialize() {
        deck = new Deck();
        hand1 = new Hand("player1");
        hand2 = new Hand("player2");
        discard1 = new Hand("discard1");
        discard2 = new Hand("discard2");
        Winner = new Hand("Winner");
        looser = new Hand("Looser");
        deck.Shuffle();
        deck.dealWar(hand1, hand2);
    }
    
    @Override
    public void play(){
        if(!CardsInPlay)
            return;
        Card H1WAR1;
        Card H1WAR2;
        Card H1WAR3;
        Card H2WAR1;
        Card H2WAR2;
        Card H2WAR3;
        rounds++;
        if (H1Played.compWAR(H2Played) > 0){// hand 1 wins
            Winner.addCard(H1Played);
            Winner.addCard(H2Played);
            discard1.passToDiscardPile(Winner);
            discard1Count.setText(String.valueOf(discard1.getGroupSize()));
            hand1WARiv.setImage(NOcard);
            hand1WAR1iv.setImage(NOcard);
            hand1WAR2iv.setImage(NOcard);
            hand1WAR3iv.setImage(NOcard);
            hand2WARiv.setImage(NOcard);
            hand2WAR1iv.setImage(NOcard);
            hand2WAR2iv.setImage(NOcard);
            hand2WAR3iv.setImage(NOcard);
        }else if (H1Played.compWAR(H2Played) < 0){// hand 2 wins
            Winner.addCard(H2Played);
            Winner.addCard(H1Played);
            discard2.passToDiscardPile(Winner);
            discard2Count.setText(String.valueOf(discard2.getGroupSize()));
            hand1WARiv.setImage(NOcard);
            hand1WAR1iv.setImage(NOcard);
            hand1WAR2iv.setImage(NOcard);
            hand1WAR3iv.setImage(NOcard);
            hand2WARiv.setImage(NOcard);
            hand2WAR1iv.setImage(NOcard);
            hand2WAR2iv.setImage(NOcard);
            hand2WAR3iv.setImage(NOcard);
        }else{//war
            if(hand1.getGroupSize() < 3 && discard1.getGroupSize() == 0){
                try {
                    displayWinner();
                } catch (IOException ex) {
                    Logger.getLogger(WARcontroller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            // set war image views
            hand1WARiv.setImage(getCARDimage(H1Played));
            hand2WARiv.setImage(getCARDimage(H2Played));
            H1WAR1 = hand1.playCard();
            hand1WAR1iv.setImage(imBack);
            H1WAR2 = hand1.playCard();
            hand1WAR2iv.setImage(imBack);
            H1WAR3 = hand1.playCard();
            hand1WAR3iv.setImage(imBack);
            H2WAR1 = hand2.playCard();
            hand2WAR1iv.setImage(imBack);
            H2WAR2 = hand2.playCard();
            hand2WAR2iv.setImage(imBack);
            H2WAR3 = hand2.playCard();
            hand2WAR3iv.setImage(imBack);
            // move war into winner hand
            Winner.addCard(H1WAR1);
            Winner.addCard(H1WAR2);
            Winner.addCard(H1WAR3);
            Winner.addCard(H2WAR1);
            Winner.addCard(H2WAR2);
            Winner.addCard(H2WAR3);
        }
        
        CardsInPlay = false;
    }

    @Override
    public void displayWinner() throws IOException{
        // check to see who wins
        hand1.SetOwner(hand1NameLB.getText());
        hand2.SetOwner(hand2NameLB.getText());
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
        
        Stage stage;
        Parent root;
        stage = (Stage) goToGame.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("WarWinner.fxml"));
        // set winner name to 
        passthrough = Winner.getOwner();
        Label label = (Label) root.lookup("#hand1NameLB");
        label.setText(passthrough);
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    
    
    @FXML
    private Button goToGame;
    @FXML
    private void goToGame(ActionEvent e) throws IOException{
        Stage stage;
        Parent root;
        stage = (Stage) goToGame.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("FXMLg.fxml"));
        
        // store text field
        passthrough = hand1NameTF.getText();
        Label label = (Label) root.lookup("#hand1NameLB");
        label.setText(passthrough);
        
        passthrough = hand2NameTF.getText();
        label = (Label) root.lookup("#hand2NameLB");
        label.setText(passthrough);
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
        
        
    }
    @FXML
    private void goToStart(ActionEvent e) throws IOException{
        Stage stage;
        Parent root;
        stage = (Stage) goToGame.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("start.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
        
        
    }
    

    @FXML
    private ImageView hand1iv;
    @FXML
    private Label hand1Count;
    @FXML
    private Label discard1Count;
    @FXML
    private Label hand1NameLB;
    @FXML
    private TextField hand1NameTF;
    @FXML
    private ImageView hand1PLAYEDiv;
    @FXML
    private ImageView hand1WARiv;
    @FXML
    private ImageView hand1WAR1iv;
    @FXML
    private ImageView hand1WAR2iv;
    @FXML
    private ImageView hand1WAR3iv;
    @FXML
    private Button hand1bu;
    @FXML
    private ImageView hand1DISiv;
    @FXML
    private Button hand1DISbu;
    
    @FXML
    private ImageView hand2iv;
    @FXML
    private Label hand2Count;
    @FXML
    private Label discard2Count;
    @FXML
    private Label hand2NameLB;
    @FXML
    private Label Message;
    @FXML
    private TextField hand2NameTF;
    @FXML
    private ImageView hand2PLAYEDiv;
    @FXML
    private ImageView hand2WARiv;
    @FXML
    private ImageView hand2WAR1iv;
    @FXML
    private ImageView hand2WAR2iv;
    @FXML
    private ImageView hand2WAR3iv;
    @FXML
    private Button hand2bu;
    @FXML
    private ImageView hand2DISiv;
    @FXML
    private Button hand2DISbu;
    
    @FXML
    private Button colectCards;
    
    @FXML
    private Image getCARDimage(Card a){
        String filePath = "/images/";
        String Card = filePath + a.toString() + ".png";
        System.out.println(Card);
        Image CardI = new Image(Card);
        return CardI;
    }
    
    @FXML
    private void playCards(){
        //Play cards and display number left in deck
        if(CardsInPlay){
            Message.setText("Collect cards First!");
            return;
        }
        if(hand1.getGroupSize() > 0){
            H1Played = hand1.playCard();
            hand1Count.setText(String.valueOf(hand1.getGroupSize()));
            hand1PLAYEDiv.setImage(getCARDimage(H1Played));
        }else{
            hand1PLAYEDiv.setImage(NOcard);
            hand1Count.setText(String.valueOf(hand1.getGroupSize()));
        }
        
        if(hand1.getGroupSize() > 0){
            H2Played = hand2.playCard();
            hand2Count.setText(String.valueOf(hand2.getGroupSize()));
            hand2PLAYEDiv.setImage(getCARDimage(H2Played));
        }else{
            hand2PLAYEDiv.setImage(NOcard);
            hand2Count.setText(String.valueOf(hand2.getGroupSize()));
        }
        CardsInPlay = true;
    }
    @FXML
    private void CollectCards(){
        hand1PLAYEDiv.setImage(NOcard);
        hand2PLAYEDiv.setImage(NOcard);
        play();
        showLastDiscard();
    }
    @FXML
    private void showLastDiscard(){
        String filePath = "/images/";
        String disFile;
        if(discard1.getGroupSize() > 0){
            disFile = filePath + discard1.getCardLast().toString() + ".png";
            hand1DISiv.setImage(new Image(disFile));
        }else{
            hand1DISiv.setImage(NOcard);
        }
        if(discard2.getGroupSize() > 0){
            disFile = filePath + discard2.getCardLast().toString() + ".png";
            hand2DISiv.setImage(new Image(disFile));
        }else{
            hand2DISiv.setImage(NOcard);
        }
        
    }
    @FXML
    private void H1ColectDiscard(){
        hand1.addDiscardPileToHand(discard1);
        hand1Count.setText(String.valueOf(hand1.getGroupSize()));
        discard1Count.setText(String.valueOf(discard1.getGroupSize()));
        hand1DISiv.setImage(NOcard);
    }
    @FXML
    private void H2ColectDiscard(){
        hand2.addDiscardPileToHand(discard2);
        hand2Count.setText(String.valueOf(hand2.getGroupSize()));
        discard2Count.setText(String.valueOf(discard2.getGroupSize()));
        hand2DISiv.setImage(NOcard);
    }
    
}
