/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import Logic.Piece;
import Logic.Player;


/**
 *
 * @author tim
 */
public class JUnitTestPlayer {
    private Player p1;
    private Piece p;
    public JUnitTestPlayer() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        p1 = new Player("test",1);
        p = new Piece(2,0);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void newPiece(){
        assertEquals(p.toString(), "Spy");
    }
    @Test 
    public void newPlayer(){
        assertEquals(p1.getSize(), 40);
    }
    @Test
    public void checkOnBoard(){
        p1.getPiece(25).setOnBoard();
        assertEquals(p1.getPiece(25).onBoard(), true);
    }
    @Test
    public void checkOffBoard(){
        p1.getPiece(25).setOffBoard();
        assertEquals(p1.getPiece(25).onBoard(), false);
    }
}
