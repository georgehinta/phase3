package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
/**
 * Entity Class, manages player attributes such as position, speed, direction,
 * collision, and health.
 * Also loads each sprite individually from Player.java and displays it on screen
 */
public class Entity {
    // TODO: 2023-04-01 Variables x,y, and speed should all be private. Direction (line 24) should also be private.

    public int x,y;
    public int speed;

    public String name;


    protected BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    protected int spriteCounter = 0;
    protected int spriteNum = 1;

    public Rectangle collisionBox;
    public boolean collisionOn = false;

    // Character Status
    // TODO: 2023-04-01 Life/Maxlife should both be private
    public int maxLife;
    public int life;

    public int getmaxLife() {
        return this.maxLife;
    }
    public int getLife() {
        return this.life;
    }
    /**
     * finds image corresponding to requested image name and returns the image as bufferedImage
     * @param imageName is name of seeked image
     * @returns bufferedImage
     */
    public BufferedImage setImage(String imageName) {
        int worked = 1;
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imageName + ".png"));
        } catch (IOException exc) {
            System.out.println("Error opening player file: " + exc.getMessage());
            worked = 0;
        } if (worked == 0){
            try {
                /*
                 left2 = ImageIO.read(new File("game/src/main/resources/player/playerLeft2.png"));
                 */
                image = ImageIO.read(new File("game/src/main/resources/" + imageName + ".png"));
    
            } catch (IOException exc) {
                System.out.println("backup plan failed, David's problem: " + exc.getMessage());
            } 
        }

        return image;
    }

    public int getCollisionX(){
        return collisionBox.x;
    }

    public int getCollisionY(){
        return collisionBox.y;
    }

    public int getCollisionWidth(){
        return collisionBox.width;
    }

    public int getCollisionHeight(){
        return collisionBox.height;
    }
}
