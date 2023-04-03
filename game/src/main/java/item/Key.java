package item;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import item.Item;

/**
 * Key Item, load Key image from resources folder
 */
public class Key  extends Item{
    
    public Key(){
        name = "key";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
            // image = ImageIO.read(new File("game/src/main/resources/objects/key.png"));
        } catch (IOException e){
            System.out.println("problem loading key image");
            e.printStackTrace();
        }
        // collision = true; // NOTE THIS IS FOR TESTING PURPOSE;
    }
    
}
