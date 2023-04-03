package item;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import item.Item;
/**
 * Trap Item, load Trap image from resources folder
 */

public class Trap  extends Item{
    
    public Trap(){
        name = "trap";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/trap.png"));
            // image = ImageIO.read(new File("game/src/main/resources/objects/key.png"));
        } catch (IOException e){
            System.out.println("problem loading key image");
            e.printStackTrace();
        }
        // collision = true; // NOTE THIS IS FOR TESTING PURPOSE;
    }
    
}
