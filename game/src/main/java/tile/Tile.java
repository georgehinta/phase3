package tile;


import java.awt.image.BufferedImage;

/**
 * tile class holds image of tile of drawing
 * as well as collision boolean for checking if entities around the map will collide into it
 */
public class Tile {

    public BufferedImage image;
    public boolean collision = false;
}
