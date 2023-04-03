package entity;

import main.Screen;
import main.KeyHandler;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Player Class, manage all values/attributes the player may contain such
 * as health, number of keys, number of bonus items
 * Also manages collision (with barriers and enemies) as well as collection
 * of items.
 */

public class Player extends Entity {

    Screen gp;
    KeyHandler keyH;
    // initialize counts of items/score
    private int keyCount = 0;
    private int Score = 0;

    /**
     * Getter for player score
     */

    public int getScore() {
        return this.Score;

    }
    public int getKeyCount() {
        return this.keyCount;
    }
    /**
     * constructor, given acess to game screen and key handler to read wasd and escape for player inputs
     * @param gp
     * @param keyH
     */
    public Player(Screen gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        name = "player";

        collisionBox = new Rectangle(gp.tileSize/6,gp.tileSize/3, gp.tileSize*2/3, gp.tileSize*2/3); // adjust as we go
        //////////////////
        //////////////////
        ///------------///
        ///------------///
        ///------------///
        ///------------///


        setDefaultValues();
        getPlayerImage();

    }
    /**
     * sets up player attributes, x,y,speed and startind direction and starting life
     */
    public void setDefaultValues() {

        x = 100;
        y = 100;
        speed = 4;
        direction = "down";

        // Player Status
        maxLife = 6;
        life = maxLife;
    }

    /**
     * loads player iamge, note this function came before entity.setimage
     */
    public void getPlayerImage() {

        int worked = 1;
        // load player images, set to different walking directions/animations
        try {
             up1 = ImageIO.read(new File(getClass().getClassLoader().getResource("player/playerUp1.png").getFile()));
             up2 = ImageIO.read(new File(getClass().getClassLoader().getResource("player/playerUp2.png").getFile()));
             down1 = ImageIO.read(new File(getClass().getClassLoader().getResource("player/playerDown1.png").getFile()));
             down2 = ImageIO.read(new File(getClass().getClassLoader().getResource("player/playerDown2.png").getFile()));
             right1 = ImageIO.read(new File(getClass().getClassLoader().getResource("player/playerRight1.png").getFile()));
             right2 = ImageIO.read(new File(getClass().getClassLoader().getResource("player/playerRight2.png").getFile()));
             left1 = ImageIO.read(new File(getClass().getClassLoader().getResource("player/playerLeft1.png").getFile()));
             left2 = ImageIO.read(new File(getClass().getClassLoader().getResource("player/playerLeft2.png").getFile()));
        
        } catch (IOException exc) {
            System.out.println("Error opening player file: " + exc.getMessage());
            worked = 0;
            // Fix so this works on David's computer
        } if (worked == 0){
            try {
                 up1 = ImageIO.read(new File("game/src/main/resources/player/playerUp1.png"));
                 up2 =ImageIO.read(new File("game/src/main/resources/player/playerUp2.png"));
                 down1 = ImageIO.read(new File("game/src/main/resources/player/playerDown1.png"));
                 down2 = ImageIO.read(new File("game/src/main/resources/player/playerDown2.png"));
                 right1 = ImageIO.read(new File("game/src/main/resources/player/playerRight1.png"));
                 right2 = ImageIO.read(new File("game/src/main/resources/player/playerRight2.png"));
                 left1 =ImageIO.read(new File("game/src/main/resources/player/playerLeft1.png"));
                 left2 = ImageIO.read(new File("game/src/main/resources/player/playerLeft2.png"));
    
    
    
    
            } catch (IOException exc) {
                System.out.println("backup plan failed, David's problem: " + exc.getMessage());
            } 
        }

    }


    /**
     * player update function, updates player location if external keypress is read, 
     * player moves if no collision is detected
     */
    public void update() {
        if(keyH.upPressed == true || keyH.downPressed == true ||
                keyH.leftPressed == true || keyH.rightPressed == true) {

            if(keyH.upPressed) {
                direction = "up";
            }
            else if(keyH.downPressed) {
                direction = "down";
                
            }
            else if(keyH.leftPressed) {
                direction = "left";
                
            }
            else if(keyH.rightPressed) {
                direction = "right";
                
            }
            
            // checking for collidable boxes in direction of travel
            collisionOn = false;
            gp.hChecker.CheckTile(this);
            int itemIndex = gp.hChecker.checkItem(this,true);
            pickUpItem(itemIndex);

            for (int i = 0; i < gp.enemy.length; i ++){
                gp.hChecker.checkEnt(this, gp.enemy[i]);
            }
            
            // if no collision, move player depending on direction
            if (!collisionOn){
                switch(direction){
                    case "up": y -= speed; break;
                    case "down": y += speed; break;
                    case "left": x -= speed; break;
                    case "right": x += speed;break;
                }
            }

            spriteCounter++;
            if(spriteCounter > 12) {
                // alternate between walking sprites
                if(spriteNum == 1) {
                    spriteNum = 2;
                }
                else if(spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    /**
     * if player collides with item, this function is called and decides the result of the pickup action
     * @param i is the index of the item picked up
     */
    public void pickUpItem(int i){
        if (i != 999){
            String itemName = gp.items[i].name;

            switch(itemName) {
            case "key":
                keyCount++;
                // increase score by 50
                Score += 50;
                // remove key from map
                gp.items[i] = null;
                break;

            case "barrier":
                if(keyCount >= 3) {
                    // remove barrier from map only if keys >= 3
                    gp.items[i] = null;
                    // remove used keys
                    keyCount = keyCount-3;
                }
                break;

            case "victory":
                // trigger victory screen
                gp.gameState = gp.victoryState;
                break;

            case "trap":
                // decrease health
                this.life -=  2;
                // remove trap from map
                gp.items[i] = null;
                break;

            case "BonusItem":
                // bonus item counts as 3 keys and adds 100 points to score
                keyCount += 3;
                Score += 100;
                // remove bonus item from map
                gp.items[i] = null;
                break;

            }
        }
    }




    /**
     * player render function
     * @param g2 is external render package
     */
    public void draw(Graphics2D g2) {

        // initialize player sprite
        BufferedImage image = null;

        switch(direction) {
            // change sprites depending on walk cycle and direction
            case "up":
                if(spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1) {
                    image = down1;
                }
                if(spriteNum == 2) {
                image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1) {
                    image = left1;
                }
                if(spriteNum == 2) {
                image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1) {
                    image = right1;
                }
                if(spriteNum == 2) {
                image = right2;
                }
                break;
        }
        // draw player at correct coordinates
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);





    }
}
