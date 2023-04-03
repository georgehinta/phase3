package main;

import item.*;

import entity.Player;
import tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;
import enemy.Enemy_Spider;

/**
 * Screen class - subclass of JPanel and works as the games 'screen'
 * Instantiates classes which include item setting, collision detection, UI,
 * and user input.
 */
public class Screen extends JPanel implements Runnable{

    /**
     * Set up screen, each tile is 16x16 (pixels), scaled to 48x48
     * Overall map is 20x20 (tiles)
     * Screen width and height is determined by Tile Size (16x16) multiplied
     * by the max length/width of the screen, which is 20x20.
     * Therefore overall max width/height is 960x960 pixels
     */
    final int originalTileSize = 16; //16x16 tile
    /**
     * 
█▀▀ █░█ ▄▀█ █▄░█ █▀▀ █▀▀   █▀ █▀▀ ▄▀█ █░░ █▀▀   ▀█▀ █▀█   ▀█   █ █▀▀   █▀▀ ▄▀█ █▀▄▀█ █▀▀   █▀▄ █▀█ █▀▀ █▀ █▄░█ ▀ ▀█▀
█▄▄ █▀█ █▀█ █░▀█ █▄█ ██▄   ▄█ █▄▄ █▀█ █▄▄ ██▄   ░█░ █▄█   █▄   █ █▀░   █▄█ █▀█ █░▀░█ ██▄   █▄▀ █▄█ ██▄ ▄█ █░▀█ ░ ░█░

█▀▀ █ ▀█▀   █▀ █▀▀ █▀█ █▀▀ █▀▀ █▄░█  
█▀░ █ ░█░   ▄█ █▄▄ █▀▄ ██▄ ██▄ █░▀█ 
     * 🄲🄷🄰🄽🄶🄴 🅂🄲🄰🄻🄴 🅃🄾 2 🄸🄵 🄶🄰🄼🄴 🄳🄾🄴🅂🄽❜🅃 🄵🄸🅃 🅂🄲🅁🄴🄴🄽
     * 
     * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓
     */
    final int scale = 3; // <-<-<-<-<- change this
    public final int tileSize = originalTileSize * scale; //48x48 tile
    public final int maxScreenCol = 20; // number of tiles on the screen
    public final int maxScreenRow = 20;
    public final int screenWidth = tileSize * maxScreenCol; //960 pixels
    public final int screenHeight = tileSize * maxScreenRow; //960 pixels

    int FPS = 60;
    /**
     * Instantiating all classes such as itemsetter, hitcheck (collision), user input, UI, and enemy.
     */
    public TileManager tileM = new TileManager(this);

    // User Input
    KeyHandler keyH = new KeyHandler(this);
    Thread gameThread;
    // Set items
    public ItemSetter iSetter = new ItemSetter(this);
    // Set collision
    public HitCheck hChecker = new HitCheck(this);
    
    public Player player = new Player(this,keyH);
    // Set player
    public Item items[] = new Item[10];
    // Set enemy
    public Enemy_Spider enemy[] = new Enemy_Spider[5];
    /**
     * Set up game states such as play, pause, victory, etc. for pause screens, game-over/victory screens.
     */

    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int victoryState = 3;
    public final int gameOverState = 4;

    // Set UI
    public UI ui = new UI(this);

    /**
     * Screen method
     */
    public Screen(){
        // set preferred screen size, background colour
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }
    /**
     * Methods for setting items/enemies
     */
    public void setUpGame(){

        iSetter.setItems();
        iSetter.setEnemies();
        gameState = playState;
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * 'Tick' Method for measuring in-game time
     *
     */
        public void run() {
            double drawInterval = 1000000000/FPS;
            double delta = 0;
            long lastTime = System.nanoTime();
            long currentTime;
            long timer = 0;
            int drawCount = 0;

            while (gameThread != null) {
                currentTime = System.nanoTime();
                delta += (currentTime - lastTime) / drawInterval;
                timer += (currentTime - lastTime);
                lastTime = currentTime;
                if(delta >= 1){
                    update();
                    repaint();
                    delta--;
                    drawCount++;
                }
                if(timer >= 1000000000) {
                    // System.out.println("FPS: " + drawCount);
                    drawCount = 0;
                    timer = 0;

                }
            }
        }
    /**
     * Change game state based on player life
     */
        public void update() {
            // game over if health == 0
            if (player.life == 0) {
                gameState = gameOverState;

                
            }
            if (gameState == playState) {
                player.update();
                for (int i = 0; i < enemy.length; i++){
                    if (enemy[i] != null){
                        enemy[i].update();
                    }
                }
            } 
            if (gameState == pauseState) {
                // do nothing for now - all in UI
            }
            if (gameState == victoryState) {
                // do nothing for now - all in UI
            }
            if (gameState == gameOverState) {
                // do nothing for now - all in UI
            }

        }
    /**
     * Draws out all tiles, items, UI and enemy
     */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D)g;
            // draw tiles
            tileM.draw(g2);
            // draw items
            for (int i = 0; i <items.length;i++){
                if (items[i] != null){
                    items[i].draw(g2, this);
                }
            }
            // draw enemy
            for (int i = 0; i < enemy.length; i++){
                if (enemy[i] != null){
                    enemy[i].draw(g2);
                }
            }
            // draw player
            player.draw(g2);
            // draw UI - hearts, keys, time, score
            ui.draw(g2);
            g2.dispose();


        }
    /**
     * Getter method for player coordinate
     * Passes x,y values to player
     */
        public int[] getPlayerCoord(){
            int arr[] = new int[2];
            arr[0] = player.x;
            arr[1] = player.y;
            return arr;
        }
    /**
     * Pathfinder getter method
     * Passes from,to values to tile
     */
        public String pathfind(int[] from, int[] to){

            return tileM.pathfind(from,to);

        }

    public int getTileType(int col, int row){

        return tileM.getTileType(col, row);
    }

    public Boolean tileCollidable (int tile){
        return tileM.tileCollidable(tile);
    }

    public int getItemLength(){
        return items.length;
    }

    public int getItemX(int i){
        return items[i].x;
    }

    public int getItemY(int i){
        return items[i].y;
    }

    public Boolean itemCollidable(int i){
        return items[i].collision;
    }

    // Getter for player score
    public int getGetScore() {
          return player.getScore();
    }
    // Getter for player keycount
    public int getGetKeyCount() {
            return player.getKeyCount();
    }

}



