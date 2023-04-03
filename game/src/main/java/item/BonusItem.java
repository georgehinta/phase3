package item;

import javax.imageio.ImageIO;
import java.io.IOException;
/**
 * Bonus Item, load Bonus item image from resources folder
 */
public class BonusItem extends Item {

    public BonusItem() {
        name = "BonusItem";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/bonusItem.png"));
        } catch (IOException e) {
            System.out.println("problem loading key image");
            e.printStackTrace();
        }
        // collision = true; // NOTE THIS IS FOR TESTING PURPOSE;
    }

}
