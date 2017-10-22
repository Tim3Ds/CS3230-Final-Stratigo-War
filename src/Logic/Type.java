/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

/**
 *
 * @author tim
 */
public enum Type {
    // declare all my piece types
    Flag(0, 1, 'F', 0), Bomb(1, 6, 'B', 0), Spy(2, 1, 'S'), Scout(3, 8, '1', 10), 
    Miner(4, 5, '2'), Sergeant(5, 4, '3'), Lieutenant(6, 4, '4'), Captain(7, 4, '5', 2), 
    Major(8, 3, '6'), Colonel(9, 2, '7'), General(10, 1, '8'), Marshall(11, 1, '9'), 
    // identifyer pieces with count 0;
    NoMansLand(12, 0, 'N', 0), empty(13, 0, 'E', 0), Winner(14,0,'W',0);
    final int id, count, Movement;
    char Rank;
    final boolean moveAttack;
    private Type(int id, int num, char rank){
        this.id = id;
        this.count = num;
        this.Rank = rank;
        this.Movement = 1;
        this.moveAttack = false;
    }
    private Type(int id, int num, char rank, int move){
        this.id = id;
        this.count = num;
        this.Rank = rank;
        this.Movement = move;
        this.moveAttack = move > 1;
        
    }
    
}
