package main;
import java.util.Random;
import item.Key;
import item.Barrier;
import item.BonusItem;
import item.Victory;
import item.Trap;
import enemy.Enemy_Spider;
/**
 * ItemSetter class, in charge of setting all items on the board.
 * All items have a set starting location, except for the bonus item.
 */
public class ItemSetter {

    Screen gp;

    /**
     * Setting up random values for bonus item
     */
    Random r = new Random();
    int int_random1 = r.nextInt(5)+10;
    int int_random2 = r.nextInt(5)+10;
    // ItemSetter constructor, which calls GamePanel
    public ItemSetter(Screen gp){
        this.gp = gp;
    }

    /**
     * initialize enemies and place them on the map
     */
    public void setEnemies(){
        gp.enemy[0] = new Enemy_Spider(gp);
        gp.enemy[0].x = 16 *gp.tileSize;
        gp.enemy[0].y = 4 *gp.tileSize;

        gp.enemy[1] = new Enemy_Spider(gp);
        gp.enemy[1].x = 16 *gp.tileSize;
        gp.enemy[1].y = 17 *gp.tileSize;
        
    }
    
    /**
     * initialize items and place them on the map 
     */
    public void setItems(){
        
        // create arrays for item coordinates
        int[] xCoords = new int[]{9, 16, 2, 18, 18, 11, 12, 10};
        int[] yCoords = new int[]{14, 9, 11, 17, 18, 4, 4, 17}; 

        // Keys - 3 Key tiles
        for(int i = 0; i < 3; i++) {
            gp.items[i] = new Key();
            gp.items[i].x = xCoords[i] * gp.tileSize;
            gp.items[i].y = yCoords[i] * gp.tileSize;
        }

        // Barrier - 1 barrier tile
        gp.items[3] = new Barrier();
        gp.items[3].x = 18 * gp.tileSize;
        gp.items[3].y = 17 * gp.tileSize;

        // Victory - 1 Victory tile
        gp.items[4] = new Victory();
        gp.items[4].x = 18 * gp.tileSize;
        gp.items[4].y = 18 * gp.tileSize;

        // Trap - 3 Trap tiles
        for(int i = 5; i < 8; i++) {
            gp.items[i] = new Trap();
            gp.items[i].x = xCoords[i] * gp.tileSize;
            gp.items[i].y = yCoords[i] * gp.tileSize;
        }

        // Bonus item - 1 Bonus item tile
        gp.items[8] = new BonusItem();
        gp.items[8].x = int_random1 * gp.tileSize;
        gp.items[8].y = int_random2 * gp.tileSize;
    }
}
