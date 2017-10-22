
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import Logic.Board;
import Logic.Player;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tim
 */
public class JUnitTestBoardSetUp {
    Board b;
    Player p1, p2;
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        p1 = new Player("test",1);
        p2 = new Player("test2", 2);
        b = new Board();
        b.addPiece(p1.getPiece(0), 0, 0);
        b.addPiece(p1.getPiece(5), 3, 1);
        b.addPiece(p1.getPiece(30), 6, 2);
        
        b.addPiece(p2.getPiece(15), 0, 1);
        b.addPiece(p2.getPiece(25), 4, 1);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // test placing pieces
    @Test
    public void TestBoardSetUpFlag(){
        assertEquals(b.choosePiece(0, 0).toString(), "Flag");
    }
    @Test
    public void TestBoardSetUpBomb(){
        assertEquals(b.choosePiece(3, 1).toString(), "Bomb");
    }
    @Test
    public void TestBoardSetUpCaptain(){
        assertEquals(b.choosePiece(6, 2).toString(), "Captain");
    }
    
}