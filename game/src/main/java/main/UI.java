package main;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.lang.Boolean;
import item.Health;
import item.*;

/**
 * UI Class, handles all on screen images such as score (keys) and health.
 * Also handles victory screen and game over screen.
 * */
public class UI {
    Screen gp;
    Graphics2D g2;
    /**
     * Setting up font
     * */
    Font papyrus_40;
    /**
     * Using BufferedImage for keys, health-bar
     * */
    BufferedImage keyImage;
    BufferedImage health_full, health_empty, health_half;
    /**
     * Measure playtime for on-screen timer
     * */
    double playTime;

    DecimalFormat dFormat = new DecimalFormat("#0.00");
    public UI(Screen gp) {
        this.gp = gp;
        papyrus_40 = new Font("Papyrus", Font.PLAIN, 40);
        /**
         * Set up keyImage by loading in .png from resources folder
         * Provides a backup method for loading (David's computer can't use the first method...)
         * */
        Boolean david_import_failed = false;
         
        try {
            keyImage = ImageIO.read(new File("game/src/main/resources/objects/key.png"));
        }catch(IOException e) {
            //e.printStackTrace();
            System.out.println("david's implementation failed, going over to og implementation");
            david_import_failed = true;
        }
        if (david_import_failed){
            
            try{
                keyImage = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
            } catch (IOException e){
                System.out.println("problem loading key image, switching to david's implementation");
                e.printStackTrace();
            }
        }

        /**
         * Set-up health-bar UI images
         * */
        Item health = new Health(gp);
        health_full = health.image;
        health_empty = health.image2;
        health_half = health.image3;

    }
    /**
     * Draw function for UI
     * */
    public void draw(Graphics2D g2) {

        this.g2=g2;
        g2.setFont(papyrus_40);
        g2.setColor(Color.white);
        g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 5, gp.tileSize, gp.tileSize, null);
        g2.drawString("x " + gp.getPlayerKeyCount(), gp.tileSize*3/2, gp.tileSize);//70,46
        drawPlayerLife();
        /**
         * Conditional statements for in-game, pause screen, victory screen, and game-over screen
         * */
        if (gp.gameState == gp.playState){

            g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 5, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.getPlayerKeyCount(), gp.tileSize*3/2, gp.tileSize);
            drawPlayerLife();
            // Time
            playTime += (double) 1 / 60;
            g2.drawString("Time:" + dFormat.format(playTime), gp.tileSize * 15, gp.tileSize);
        } 
        if (gp.gameState == gp.pauseState) {
            if(g2.getColor() == Color.white) {
                g2.setColor(Color.black);
            }
            else {
                g2.setColor(Color.white);
            }
            pauseScreen();
        }
        if (gp.gameState == gp.victoryState) {
            victoryScreen();
        }
        if (gp.gameState == gp.gameOverState) {
            gameOverScreen();
        }
    }
    /**
     * Pause screen, victory screen, game-over screen methods
     * */
    public void pauseScreen(){
        String text = "PAUSED";
        int x = alignTextCenter(text);
        int y = gp.screenHeight/2;
        g2.drawString(text,x,y);
    }
    
    public void victoryScreen() {
        g2.setColor(Color.black);
        String text = "VICTORY!";
        int x = alignTextCenter(text);
        int y = gp.screenHeight/2;
        g2.drawString(text,x,y);
        g2.drawString("Time:" + dFormat.format(playTime), alignTextCenter("Time:" + dFormat.format(playTime)), 11*gp.tileSize);
        g2.drawString("Score: " + gp.getPlayerScore(), alignTextCenter("Score: " + gp.getPlayerScore()),12*gp.tileSize);


    }

    public void gameOverScreen() {
        g2.setColor(Color.red);
        String text = "GAME OVER";
        int x = alignTextCenter(text);
        int y = gp.screenHeight/2;
        g2.drawString(text,x,y);
    }

    /**
     * Align text for pause/victory/game-over screens
     */
    public int alignTextCenter(String text){
        
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth/2 - length/2;
    }

    /**
     * Drawing health-bar method
     * Starts by drawing the empty health bar, and then draws over
     * it with a filled health bar.
     * This depends on the value of i, which is always less than
     * the overall player life. E.g, if i < 3, at most 2 of the health-bar
     * can be filled.
     * */
     public void drawPlayerLife() {

            int x = gp.tileSize*3;
            int y = gp.tileSize/4;
            int i = 0;
            // Set up empty life icons
            while( i < gp.getPlayerMaxLife()/2) {

                g2.drawImage(health_empty, x, y, gp.tileSize, gp.tileSize, null);
                i++;
                x += gp.tileSize;
            }
            // Resetting x,y,i
             x = gp.tileSize*3;
             y = gp.tileSize/4;
             i = 0;
            // Display current Life - draw over empty life icons
            while (i < gp.getPlayerLife()) {
                g2.drawImage(health_half, x,y, gp.tileSize, gp.tileSize,null);
                i++;
                if (i < gp.getPlayerLife()) {
                    g2.drawImage(health_full, x,y, gp.tileSize, gp.tileSize, null);
                }
                i++;
                x += gp.tileSize;
            }


        }

    }


