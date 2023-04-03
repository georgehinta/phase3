package main;
//import main;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
//import java.beans.Transient;

//import main;
import javax.swing.JFrame;

public class gameTest {

    /**
         * Peter was here
     */

    @Test
    public void subTest(){

        
        Screen screen = new Screen();
        screen.setUpGame();
        screen.startGameThread();

        int z[] = screen.getPlayerCoord();
        assertEquals(100,z[0]); // player x == 100
        assertEquals(10, screen.getItemLength());
        assertEquals(9*screen.tileSize,screen.getItemX(0));

    }

    
}
