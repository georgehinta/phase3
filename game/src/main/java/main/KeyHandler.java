package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * KeyHandler class - in charge of accepting all user inputs and passing to GamePanel
 */
public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean escPressed, enterPressed;
    
    private Screen gp;

    /**
     * keyhandler is provided access to game screen which holds all other game related objects
     * @param gp
     */
    public KeyHandler(Screen gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent input){
        // PETER: idk why i had to add this it wouldn't compile otherwise, apr 1, 2023
    }

    /**
     * detects which key is pressed
     */
    @Override
    public void keyPressed(KeyEvent input) {

        int code = input.getKeyCode();
        // record pressed key (W, S, A, D, ENTER, or ESCAPE are valid)
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            escPressed = true;
            if (gp.gameState == gp.playState){
                gp.gameState = gp.pauseState;
            } else if (gp.gameState == gp.pauseState){
                gp.gameState = gp.playState;
            }
        }
    }

    /**
     * detects which key is related
     */
    @Override
    public void keyReleased(KeyEvent input) {

        int code = input.getKeyCode();
        // record released key (W, S, A, D, ENTER, or ESCAPE are valid)
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = false;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            escPressed = false;
        }

    }
}
