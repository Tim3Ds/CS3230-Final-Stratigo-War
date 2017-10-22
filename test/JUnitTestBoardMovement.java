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
import Logic.Board;
import Logic.Player;

/**
 *
 * @author tim
 */
public class JUnitTestBoardMovement {
    Board b;
    Player p1, p2;
    public JUnitTestBoardMovement() {
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
        p2 = new Player("test2",2);
        b = new Board();
        b.addPiece(p1.getPiece(0), 0, 0);
        b.addPiece(p1.getPiece(5), 3, 1);
        b.addPiece(p1.getPiece(30), 6, 2);
        
        b.addPiece(p2.getPiece(15), 0, 1);
        b.addPiece(p2.getPiece(23), 6, 3);
        b.addPiece(p2.getPiece(25), 4, 1);
        b.addPiece(p2.getPiece(39), 8, 2);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test// test moving pieces
    public void TestBoardMove0p1(){
        this.setUp();
        System.out.println(b.toString());
        
        assertEquals("Captain", b.choosePiece(6, 2).toString());
        b.move(b.choosePiece(6, 2), "up", 1);
        
        assertEquals("Captain", b.choosePiece(6, 3).toString());
        System.out.println(b.toString());
        System.out.println(b.turnCheck());
    }
    @Test// test attacking
    public void TestBoardMove1p2Attack1(){
        this.setUp();
        System.out.println(b.toString());
        b.move(b.choosePiece(6, 2), "down", 2);
        System.out.println(b.turnCheck());
        b.move(b.choosePiece(4, 1), "left", 1);
        System.out.println(b.toString());
        System.out.println(b.turnCheck());
        
        assertEquals("empty", b.choosePiece(4, 1).toString());
        assertEquals("Bomb", b.choosePiece(3, 1).toString());
        
        
    }
    
    @Test// test attacking
    public void TestBoardMove2p1Move2(){
        this.setUp();
        System.out.println(b.toString());
        b.move(b.choosePiece(6, 2), "right", 2);
        System.out.println(b.turnCheck());
        b.move(b.choosePiece(4, 1), "left", 1);
        System.out.println(b.toString());
        System.out.println(b.turnCheck());
        
        b.move(b.choosePiece(4, 2), "left", 2);
        System.out.println(b.toString());
        System.out.println(b.turnCheck());
        assertEquals("Marshall", b.choosePiece(8, 2).toString());
    }
    @Test// test Capture Flage
    public void TestBoardMove3p2CaptureFlage(){
        this.setUp();
        b.move(b.choosePiece(6, 2), "left", 2);
        b.move(b.choosePiece(4, 1), "left", 1);
        b.move(b.choosePiece(4, 2), "left", 2);
        
        b.move(b.choosePiece(0, 1), "down", 1);
        assertEquals("Winner", b.choosePiece(0, 0).toString());
    }
}
