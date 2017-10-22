/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import Logic.Board;
import Logic.Game;
import Logic.Piece;
import Logic.Player;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.collections.ObservableList;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author tim
 */
public class Controller implements Initializable, Game{
    Parent root;
    private Scene scene;
    Stage stage;
    private Board b;
    private Player p1, p2;
    private Image REDback, BLUEback, empty, ImPiece, ImTemp, Green, imUp,imDown,imLeft,imRight;
    private String passthrough;
    boolean autoSetUP = false;
    private Piece temp;
    double progress = 0.0;
    int T;
    
    @Override
    public void initialize() {
//        boardGrid.setCursor(Cursor.WAIT);
//        P1Grid.setCursor(Cursor.WAIT);
//        P2Grid.setCursor(Cursor.WAIT);
//        set.setCursor(Cursor.WAIT);
        
        b = new Board();
        p1 = new Player("Player 1", 1);
        p2 = new Player("Player 2", 2);
        
        String filePath = "/images/RED/Back.png";
        REDback = new Image(filePath);
        filePath = "/images/BLUE/Back.png";
        BLUEback = new Image(filePath);
        filePath = "/images/empty.png";
        empty = new Image(filePath);
        filePath = "/images/green.png";
        Green = new Image(filePath);
        filePath = "/images/up.png";
        imUp = new Image(filePath);
        filePath = "/images/down.png";
        imDown = new Image(filePath);
        filePath = "/images/left.png";
        imLeft = new Image(filePath);
        filePath = "/images/right.png";
        imRight = new Image(filePath);
        ClickStart.setVisible(false);
//        boardGrid.setCursor(Cursor.DEFAULT);
//        P1Grid.setCursor(Cursor.DEFAULT);
//        P2Grid.setCursor(Cursor.DEFAULT);
//        set.setCursor(Cursor.DEFAULT);
        
        
    }
    
    @Override
    public void play() {
        
    }
    private void SetTurn(){
        String PlayerTurn;
        if(b.turnCheck()==1)
            PlayerTurn = p1.getName();
        else
            PlayerTurn = p2.getName();
        Turn.setText(PlayerTurn);
    }

    @Override
    public void displayWinner() {
        stage = (Stage) credits.getScene().getWindow();
        try {
            root = FXMLLoader.load(getClass().getResource("Winner.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    @FXML
    private TextField Player1;
    @FXML
    private TextField Player2;
    
    
    @FXML
    private RadioButton P1Auto;
    @FXML
    private RadioButton P2Auto;
    @FXML
    private RadioButton ShowBLUE;
    @FXML
    private RadioButton ShowRed;
    @FXML
    private Label Up;
    @FXML
    private Label Down;
    @FXML
    private Label Left;
    @FXML
    private Label Right;
    @FXML
    private Label Turn;
    @FXML
    private Label Message;
    @FXML
    private Label ClickStart;
    @FXML
    private Button credits;
    @FXML
    private Button set;
    @FXML
    private Button start;
    @FXML
    private Button goToGame;
    @FXML
    private Button War;
    @FXML
    private AnchorPane ScreenAnckor;
    @FXML
    private ProgressIndicator pb;
    @FXML
    private ImageView SPcard;
    @FXML
    private ImageView SPflag;
    
    @FXML
    private void credits() throws IOException{
        stage = (Stage) ScreenAnckor.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("start.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void starCountertScreen() throws IOException{
        stage = (Stage) ScreenAnckor.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("start.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void goToGame(){
        
        getStratigo();
    }
    private void getStratigo(){
        goToGame.toBack();
        stage = (Stage) ScreenAnckor.getScene().getWindow();
        try {
            root = FXMLLoader.load(getClass().getResource("board.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void goToWar() throws IOException{
        stage = (Stage) ScreenAnckor.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("FXMLa.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    private void Winner(String Wname) throws IOException{
        stage = (Stage) set.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("Winner.fxml"));
        
        Label Winner = (Label) root.lookup("#Winner");
        Winner.setText(Wname);
        
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
    
    @FXML
    public void GetSettings(){
        ClickStart.setVisible(false);
        
        
        initialize();
        
        
        
        p1.setName(Player1.getText());
        p2.setName(Player2.getText());
        
        autoSetUP = P2Auto.isSelected();
        if(autoSetUP){
            p2.randomBoard(b);
        }else
            SetP2DnD();
        
        autoSetUP = P1Auto.isSelected();
        if(autoSetUP){
            p1.randomBoard(b);
        }else
            SetP1DnD();
        
        
        
        Up.setText(Integer.toString(0));
        Down.setText(Integer.toString(0));
        Left.setText(Integer.toString(0));
        Right.setText(Integer.toString(0));
        
        
        
        RefreshBoard();
        if(p1.getCountOffBoard() == 0 && p2.getCountOffBoard() == 0){
            start.setDisable(false);
            ClickStart.setVisible(true);
        }
            
    }
    @FXML
    private void StartIT(){
        ClickStart.setVisible(false);
        KillDnD();
        RefreshBoard();
        SetEnteredEvent();
        set.setDisable(true);
        
    }
    
    
    public void SetP1DnD(){
//        ScreenAnckor.setCursor(Cursor.WAIT);
        int dex = 0;
        for(int y=0;y<10;y++)
            for(int x=0;x<4;x++){
                ImageView source  = getNodeByRowColumnIndex(x, y, P1Grid);
                source.setOnDragDetected((MouseEvent event) -> {
                    System.out.println("Drag Detected");
                    Dragboard db = source.startDragAndDrop(TransferMode.COPY);
                    temp = (Piece)source.getUserData();
                    System.out.println("Piece " + temp.toString());
                    ClipboardContent content = new ClipboardContent();
                    ImPiece = getPieceImage(temp);
                    content.putImage(ImPiece);
                    db.setContent(content);
                    db.getDragView();
                    
                    event.consume();
                });
                dex++;
                source.setOnDragDone((DragEvent event) -> {
                    System.out.println("onDragDone");
                    //source.setImage(empty);
                    RefreshBoard();
                    if(p1.getCountOffBoard() == 0 && p2.getCountOffBoard() == 0)
                        start.setDisable(false);
                });
            }
        for(int y = 6;y<10;y++){
            for(int x=0;x<10;x++){
                ImageView target  = getNodeByRowColumnIndex(x, y, boardGrid);
                final int j = x, k = y;
                target.setOnDragOver((DragEvent event) -> {
                    System.out.println("onDrageOver");
                    if (event.getGestureSource() != target && event.getDragboard().hasImage()){
                        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                    }
                    event.consume();
                });
                target.setOnDragEntered((DragEvent event) -> {
                    ImTemp = target.getImage();
                    target.setImage(Green);
                });
                target.setOnDragExited((DragEvent event) -> {
                    target.setImage(ImTemp);
                });
                target.setOnDragDropped((DragEvent event) -> {
                    System.out.println("OnDragDropped");
                    Dragboard db = event.getDragboard();
                    boolean success = false;
                    if(db.hasImage()){
                        target.setImage(db.getImage());
                        temp.setXY(j, k);
                        b.addPiece(temp, temp.X(), temp.Y());
                        success = true;
                    }
                    
                    event.setDropCompleted(success);
                    event.consume();
                });
            }
        }
        ScreenAnckor.setCursor(Cursor.DEFAULT);
        
    }
    public void SetP2DnD(){
//        ScreenAnckor.setCursor(Cursor.WAIT);
        int dex = 0;
        for(int y=0;y<10;y++)
            for(int x=0;x<4;x++){
                ImageView source  = getNodeByRowColumnIndex(x, y, P2Grid);
                source.setOnDragDetected((MouseEvent event) -> {
                    System.out.println("Drag Detected");
                    Dragboard db = source.startDragAndDrop(TransferMode.COPY);
                    temp = (Piece)source.getUserData();
                    System.out.println("Piece " + temp.toString());
                    ClipboardContent content = new ClipboardContent();
                    ImPiece = getPieceImage(temp);
                    content.putImage(ImPiece);
                    db.setContent(content);
                    db.getDragView();
                    event.consume();
                });
                dex++;
                source.setOnDragDone((DragEvent event) -> {
                    System.out.println("onDragDone");
                    //source.setImage(empty);
                    RefreshBoard();
                    if(p1.getCountOffBoard() == 0 && p2.getCountOffBoard() == 0)
                        start.setDisable(false);
                });
            }
        for(int y = 0;y<4;y++){
            for(int x=0;x<10;x++){
                ImageView target  = getNodeByRowColumnIndex(x, y, boardGrid);
                final int j = x, k = y;
                target.setOnDragOver((DragEvent event) -> {
                    System.out.println("onDrageOver");
                    if (event.getGestureSource() != target && event.getDragboard().hasImage()){
                        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                    }
                    event.consume();
                });
                target.setOnDragEntered((DragEvent event) -> {
                    ImTemp = target.getImage();
                    target.setImage(Green);
                });
                target.setOnDragExited((DragEvent event) -> {
                    target.setImage(ImTemp);
                });
                target.setOnDragDropped((DragEvent event) -> {
                    System.out.println("OnDragDropped");
                    Dragboard db = event.getDragboard();
                    boolean success = false;
                    if(db.hasImage()){
                        target.setImage(db.getImage());
                        temp.setXY(j, k);
                        b.addPiece(temp, temp.X(), temp.Y());
                        success = true;
                    }
                    event.setDropCompleted(success);
                    event.consume();
                });
            }
        }
        
    }
    public void KillDnD(){
        for(int y=0;y<10;y++)
            for(int x=0;x<4;x++){
                ImageView source  = getNodeByRowColumnIndex(x, y, P1Grid);
                source.setOnDragDetected((MouseEvent event) -> {
                });
                source.setOnDragDone((DragEvent event) -> {
                });
            }
        for(int y=0;y<10;y++)
            for(int x=0;x<4;x++){
                ImageView source  = getNodeByRowColumnIndex(x, y, P2Grid);
                source.setOnDragDetected((MouseEvent event) -> {
                });
                source.setOnDragDone((DragEvent event) -> {
                });
            }
        for(int y = 0;y<10;y++){
           
            for(int x=0;x<10;x++){
                ImageView target  = getNodeByRowColumnIndex(x, y, boardGrid);
                target.setOnDragOver((DragEvent event) -> {
                });
                target.setOnDragEntered((DragEvent event) -> {
                });
                target.setOnDragExited((DragEvent event) -> {
                });
                target.setOnDragDropped((DragEvent event) -> {
                });
                
            }
        }
    }
    
    @FXML
    private GridPane boardGrid;
    @FXML
    private GridPane P1Grid;
    @FXML
    private GridPane P2Grid;
    
    @FXML
    private void SetEnteredEvent(){
//        boardGrid.setCursor(Cursor.WAIT);
//        P1Grid.setCursor(Cursor.WAIT);
//        P2Grid.setCursor(Cursor.WAIT);
//        ScreenAnckor.setCursor(Cursor.WAIT);
        int dex = 0;
        for(int y=0;y<10;y++)
            for(int x=0;x<10;x++){
                ImageView ENTERIT  = getNodeByRowColumnIndex(x, y, boardGrid);
                ENTERIT.setOnMouseClicked((MouseEvent event) -> {
                    ImageView iv;
                    ImTemp = ENTERIT.getImage();
                    Integer colIndex = GridPane.getColumnIndex(ENTERIT);
                    Integer rowIndex = GridPane.getRowIndex(ENTERIT);
                    boolean canMove = false;
                    if(colIndex > 9 || rowIndex > 9){
                        Message.setText("Selection Failure x:" + Integer.toString(colIndex) + " y:" + Integer.toString(rowIndex));
                    }else{
                        passthrough = "Selection x:" + Integer.toString(colIndex) + " y:" + Integer.toString(rowIndex);
                        Message.setText(passthrough);
                        temp = b.choosePiece(colIndex, rowIndex);
                        passthrough += " Piece: " + temp.toString();
                        Message.setText(passthrough);
                        Up.setText(Integer.toString(temp.getTravleDOWN()));
                        Down.setText(Integer.toString(temp.getTravleUP()));
                        Left.setText(Integer.toString(temp.getTravleLEFT()));
                        Right.setText(Integer.toString(temp.getTravleRIGHT()));
                        // get nodes and set approprate image
                        for(int U = 0;U<temp.getTravleUP();){
                            U++;
                            System.out.println("Node Down: "+ U);
                            iv = getNodeByRowColumnIndex(temp.X(), temp.Y()+U, boardGrid);
                            iv.setImage(imDown);
                            iv.setId("up");
                            iv.setUserData(U);
                            canMove = true;
                        }
                        for(int D = 0;D<temp.getTravleDOWN();){
                            D++;
                            System.out.println("Node Up: " + D);
                            iv = getNodeByRowColumnIndex(temp.X(), temp.Y()-D, boardGrid);
                            iv.setImage(imUp);
                            iv.setId("down");
                            iv.setUserData(D);
                            canMove = true;
                        }
                        for(int L = 0;L<temp.getTravleLEFT();){
                            L++;
                            System.out.println("Node Left: " + L);
                            iv = getNodeByRowColumnIndex(temp.X()-L, temp.Y(), boardGrid);
                            iv.setImage(imLeft);
                            iv.setId("left");
                            iv.setUserData(L);
                            canMove = true;
                        }
                        for(int R = 0;R<temp.getTravleRIGHT();){
                            R++;
                            System.out.println("Node Right: " + R);
                            iv = getNodeByRowColumnIndex(temp.X()+R, temp.Y(), boardGrid);
                            iv.setImage(imRight);
                            iv.setId("right");
                            iv.setUserData(R);
                            canMove = true;
                        }
                        if(canMove)
                            SetMoveEvent(); 
                            
                        
                        
                    }
                          
                    
                });
            }
        
        
    }
    @FXML
    private void SetMoveEvent(){
//        boardGrid.setCursor(Cursor.WAIT);
//        P1Grid.setCursor(Cursor.WAIT);
//        P2Grid.setCursor(Cursor.WAIT);
//        ScreenAnckor.setCursor(Cursor.WAIT);
        int dex = 0;
        for(int y=0;y<10;y++)
            for(int x=0;x<10;x++){
                ImageView MOVEIT  = getNodeByRowColumnIndex(x, y, boardGrid);
                MOVEIT.setOnMouseClicked((MouseEvent event) -> {
                    if(MOVEIT.getUserData() == null){
                        SetEnteredEvent();
                        RefreshBoard();
                        Up.setText(Integer.toString(0));
                        Down.setText(Integer.toString(0));
                        Left.setText(Integer.toString(0));
                        Right.setText(Integer.toString(0));
                        event.consume();
                    }else{
                        b.move(temp, MOVEIT.getId(), (int)MOVEIT.getUserData());
                        Message.setText(b.getMessage());
                        MOVEIT.setImage(ImTemp);
                        SetEnteredEvent();
                        GetWinner();
                        RefreshBoard();
                        event.consume();
                    }
                });
            }
        boardGrid.setCursor(Cursor.DEFAULT);
        P1Grid.setCursor(Cursor.DEFAULT);
        P2Grid.setCursor(Cursor.DEFAULT);
        ScreenAnckor.setCursor(Cursor.DEFAULT);
        
    }
    public void RefreshBoard() {
        
        //System.out.println(b.toString());
        for(int j=0;j<10;j++)
            for(int k=0;k<10;k++){
                ImageView node = getNodeByRowColumnIndex(j, k, boardGrid);
                node.setImage(getPieceImage(b.getPiece(j, k)));
                node.setX(j);
                node.setY(k);
            }
             
        
        setOffBoard();
        
        SetTurn();
        
        setOffBoard();
        
    }
   
    private void setOffBoard(){
        
//        P1Grid.setCursor(Cursor.WAIT);
//        P2Grid.setCursor(Cursor.WAIT);
        int dex = 0;
        for(int y=0;y<10;y++)
            for(int x=0;x<4;x++){
                getNodeByRowColumnIndex(x, y, P1Grid).setImage(getOffBoardImage(p1.getPiece(dex)));
                getNodeByRowColumnIndex(x, y, P1Grid).setUserData(p1.getPiece(dex));
                getNodeByRowColumnIndex(x, y, P2Grid).setImage(getOffBoardImage(p2.getPiece(dex)));
                getNodeByRowColumnIndex(x, y, P2Grid).setUserData(p2.getPiece(dex));
                dex++;
            }
        
        P1Grid.setCursor(Cursor.DEFAULT);
        P2Grid.setCursor(Cursor.DEFAULT);
    }
    private void GetWinner(){
        if(p2.checkWin()==2)
            try {
                Winner(p1.getName());
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(p1.checkWin()==1)
            try {
                Winner(p2.getName());
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private Image getPieceImage(Piece a){
        // problem with file name path working on it
        String filePath;
        switch (a.getPlayerID()) {
            case 1:
                if(ShowBLUE.isSelected())
                    filePath = "/images/BLUE/" + a.toString() + ".png";
                else
                    filePath = "/images/BLUE/Back.png";
                break;
            case 2:
                if(ShowRed.isSelected())
                    filePath = "/images/RED/" + a.toString() + ".png";
                else
                    filePath = "/images/RED/Back.png";
                break;
            default:
                return empty;
        }
        ImPiece = new Image(filePath);      
        return ImPiece;
    }
    private Image getOffBoardImage(Piece a){
        String filePath = null;
        if(!a.onBoard()){
            switch (a.getPlayerID()) {
                case 1:
                    filePath = "/images/BLUE/"+ a.toString() +".png";
                    break;
                case 2:
                    filePath = "/images/RED/"+ a.toString() +".png";
                    break;
                default:
                    return empty;
            }
            return new Image(filePath);
        }
        
        return empty;
    }
    public ImageView getNodeByRowColumnIndex (final int column, final int row, GridPane gridPane) {
        Node result = null;
        //System.out.println(gridPane.getColumnIndex();
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            if(GridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }

        return ( ImageView ) result;
    }
    private class ProgresBarRunner extends Thread { 
        
        @Override 
        public void run() { 
            pb.setVisible(true);
            int count = 0;
            while (true) { 
                if (progress <= 1) { 
                    progress += 0.001; 
                } else {
                    progress = 0.0;
                    break;
                } 
                count++;
                pb.setProgress(progress);
                //System.out.println(progress + " " + count);
                
                try { 
                    Thread.sleep(T-1); 
                } catch (InterruptedException ex) { 
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex); 
                } 
                catch (Exception ex) 
                { 
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex); 
                } 
            } 
        } 
    } 
}