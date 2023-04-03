package item;

import java.awt.image.BufferedImage;
import java.awt.*;
import main.Screen;
/**
 * Item Superclass, all other items are extended from this class
 * Provides 3 BufferedImage's for other items to use
 * Provides x,y coordinates
 * Sets collision to false by default, can be changed for
 * items which require collision.
 */
public class Item{

    public BufferedImage image;
    public BufferedImage image2;
    public BufferedImage image3;

    public String name;
    public boolean collision = false;
    public int x, y; 


    public void draw(Graphics2D g2, Screen gp){
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}