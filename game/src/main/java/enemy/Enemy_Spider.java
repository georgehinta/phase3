package enemy;
// TO-done-DO: 2023-04-01  Remove unused imports
import entity.Entity;
import main.Screen;
import java.awt.*;
import java.awt.image.BufferedImage;
//import javax.imageio.ImageIO;
//import java.util.random.*;
//import java.util.Random;

/**
 * main enemy type in game
 * moves and chase players;
 */
public class Enemy_Spider extends Entity{

    Screen gp;
    private int frameCounter;
    

    /**
     * constructor, is fed access to main game screen
     * @param gp
     */
    public Enemy_Spider(Screen gp){
        this.gp = gp;

        name = "spider";
        speed = 1;
        maxLife = 4;
        life = maxLife;

        direction = "up";

        //collisionBox = new Rectangle(gp.tileSize/6,gp.tileSize/3, gp.tileSize*2/3, gp.tileSize*2/3);// modified height and width to 1/3
        collisionBox = new Rectangle(gp.tileSize/3,gp.tileSize/3, gp.tileSize/3, gp.tileSize/3);

        getImage();
    }

    /**
     * loads images which is later drawn
     */
    public void getImage(){
        up1 = setImage("/enemy/enemyUp1");
        up2 = setImage("/enemy/enemyUp2");
        left1 = setImage("/enemy/enemyLeft1");
        left2 = setImage("/enemy/enemyLeft2");
        right1 = setImage("/enemy/enemyRight1");
        right2 = setImage("/enemy/enemyRight2");
        down1 = setImage("/enemy/enemyDown1");
        down2 = setImage("/enemy/enemyDown2");
    }

    /**
     * draws and swaps sprites to appear moving
     * @param g2
     */
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        switch(direction) {
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
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }

    /**
     * decides the spider's next move
     */
    public void nextMove(){

        
         frameCounter ++;
         int arr[] = new int[2]; // can i [x,y]?
         arr[0] = x;
         arr[1] = y;
         //int arr2[] = new int[] {3*48, 6*48};
         
         //System.out.println("strat says" + gp.pathfind(arr, gp.getPlayerCoord()));
         //System.out.println((10+x)/48 + " " + (20+y)/48);
        if (frameCounter == 10){
            

        

        //String dir = gp.pathfind(arr, gp.getPlayerCoord());
            //System.out.println("strat says" + path);
            //System.out.println("moves[0] is " + moves[1]);

            
        
            int d = gp.tileSize;
            if ((x % d < 10 || x % d > 38 ) && (y%d<10 || y%d >38)){
                String path =  gp.pathfind(arr, gp.getPlayerCoord());
                String moves[] = path.split("\\s+");
                direction = moves[1];

            }
            frameCounter = 0;
        }
    }

    /**
     * update spider location, calls pathfinder to decide next move, is able to collide with map entities
     */
    public void update(){
        try{nextMove();}catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        collisionOn = false;
        gp.hChecker.CheckTile(this);
        gp.hChecker.checkEnt(this, gp.player);
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
