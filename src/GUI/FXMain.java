/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author tim
 */
public class FXMain extends Application {
    Parent root;
    Scene scene;
    private Image IflagB;
    private Image IflagR;
    private Image IcardB;
    private Image IcardF;
    private ProgressIndicator pi;
    private ImageView card;
    private ImageView flag;
    PauseTransition delay;
    PauseTransition imChange;
    private double progress = 0.0;
        
    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        String path = "/images/BLUE/WinnerB.png";
        IflagB = new Image(path);
        path = "/images/RED/WinnerR.png";
        IflagR = new Image(path);
        
        path = "/images/ace_of_clubs.png";
        IcardB = new Image(path);
        path = "/images/back.png";
        IcardF = new Image(path);
        
        try {
            root = FXMLLoader.load(getClass().getResource("splash.fxml"));
        } catch (IOException e){
            return;
        }
        
        scene = new Scene(root);
        primaryStage.setTitle("Stratigo By: Timothy Kelly");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
        pi = (ProgressIndicator)root.lookup("#Counter");
        card = (ImageView)root.lookup("#SPcard");
        flag = (ImageView)root.lookup("#SPflag");
        
        PauseTransition rt = new PauseTransition(javafx.util.Duration.seconds(11));
        rt.setOnFinished(event -> startScreen(primaryStage));
        
        new FXMain.WaitThread().start();
        
        rt.play();
    }
    private void startScreen(Stage primaryStage){
        
        try {
            root = FXMLLoader.load(getClass().getResource("start.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    private class WaitThread extends Thread { 
 
        @Override 
        public void run() { 
            int count = 0, cb = 0;
            while (true) { 
                if (progress <= 1) { 
                    progress += 0.001; 
                } else {
                    
                    break;
                } 
                count++;
                pi.setProgress(progress);
                //System.out.println(progress + " " + count);
                if(count%100 == 0){
                    cb++;
                }
                if (cb%2 == 0){
                    card.setImage(IcardF);
                    flag.setImage(IflagB);
                }    
                else{
                    card.setImage(IcardB);
                    flag.setImage(IflagR);
                }
                try { 
                    Thread.sleep(10); 
                } catch (InterruptedException ex) { 
                    Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex); 
                } 
                catch (Exception ex) 
                { 
                    Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex); 
                } 
            } 
        } 
    } 
}
