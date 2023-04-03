package item;

import javax.imageio.ImageIO;
import java.io.IOException;
import main.Screen;
/**
 * Health bar Item, load Health-bar images from resources folder
 */
public class Health extends Item {

    public Health(Screen gp) {
        name = "Health";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/player/healthIcon.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/player/healthIconEmpty.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/player/healthIconHalf.png"));


        } catch (IOException e) {
            System.out.println("problem loading key image");
            e.printStackTrace();
        }

    }

}
