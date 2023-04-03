package item;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import item.Item;

/**
 * victory item, upon pickup, the vicotry screen is shown with time and score
 */
public class Victory  extends Item{
    
    public Victory(){
        name = "victory";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/victoryTile.png"));
            // image = ImageIO.read(new File("game/src/main/resources/objects/key.png"));
        } catch (IOException e){
            System.out.println("problem loading victory image");
            e.printStackTrace();
        }
        // collision = true; // NOTE THIS IS FOR TESTING PURPOSE;
    }
    
}
