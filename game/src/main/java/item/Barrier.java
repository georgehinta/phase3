package item;
import java.io.IOException;

import javax.imageio.ImageIO;

import item.Item;

/**
 * Barrier Item, load Barrier image from resources folder
 */
public class Barrier extends Item{
    
    public Barrier(){
        name = "barrier";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/barrier.png"));
        } catch (IOException e){
            System.out.println("problem loading key image");
            e.printStackTrace();
        }
        collision = true;
    }
    
}
