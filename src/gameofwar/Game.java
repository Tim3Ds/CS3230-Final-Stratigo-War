/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameofwar;

import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author tim
 */
public interface Game {
    public void initialize();
    public void play();
    public void displayWinner() throws IOException;
}
