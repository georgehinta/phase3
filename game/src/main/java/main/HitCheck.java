package main;

// TO- done- DO: Remove unnecessary imports

//import javax.xml.stream.events.EntityDeclaration;
import java.awt.*; 

import entity.Entity;
//import main.Screen;

/**
 * equivalent to video 6 topic
 * due to not using player traking camera and world coordinate, tile counting methods are different
 */
public class HitCheck {
    Screen gp;
    /**
     * constructor 
     * hitchecker class 
     * @param gp is gamepanel 
     */
    public HitCheck(Screen gp){
        this.gp = gp;
    }
    /**
     * checks if entity is about to move into tile with collision, if so, movement is inhibited
     * @param ent
     */
    public void CheckTile(Entity ent){
        // TO -done- DO: 2023-04-01  // Single dot rule broken

        int entLeftX = ent.x + ent.getCollisionX();
        int entRightX = ent.x + ent.getCollisionX() + ent.getCollisionWidth();
        int entTopY = ent.y + ent.getCollisionY();
        int entBottomY = ent.y + ent.getCollisionY() + ent.getCollisionHeight();

        int entLeftCol = entLeftX/gp.tileSize;
        int entRightCol = entRightX/gp.tileSize;
        int entTopRow = entTopY/gp.tileSize;
        int entBotRow = entBottomY/gp.tileSize;
        // TO -done- DO: 2023-04-01  // Unnecessary print statement 

        int tile1 = 0, tile2 = 0;
        // TO -done- DO: 2023-04-01  // Single dot rule broken again lines 47-49
        switch(ent.direction){
            case "up":
                entTopRow = (entTopY - ent.speed)/gp.tileSize;
                //tile1 = gp.tileM.mapTileNum[entLeftCol][entTopRow];
                //tile2 = gp.tileM.mapTileNum[entRightCol][entTopRow];
                tile1 = gp.getTileType(entLeftCol, entTopRow);
                tile2 = gp.getTileType(entRightCol, entTopRow);
                // TO -done- DO: 2023-04-01 Duplicate code, poor switch statement organization

                
                break;
            case "down":
                entBotRow = (entBottomY + ent.speed)/gp.tileSize;
                //tile1 = gp.tileM.mapTileNum[entLeftCol][entBotRow];
                //tile2 = gp.tileM.mapTileNum[entRightCol][entBotRow];
                tile1 = gp.getTileType(entLeftCol, entBotRow);
                tile2 = gp.getTileType(entRightCol, entBotRow);
                
                break;
            case "left":
                entLeftCol = (entLeftX - ent.speed)/gp.tileSize;
                //tile1 = gp.tileM.mapTileNum[entLeftCol][entTopRow];
                //tile2 = gp.tileM.mapTileNum[entLeftCol][entBotRow];
                tile1 = gp.getTileType(entLeftCol, entTopRow);
                tile2 = gp.getTileType(entLeftCol, entBotRow);
                
                break;
            case "right":
                entRightCol = (entRightX + ent.speed)/gp.tileSize;
                //tile1 = gp.tileM.mapTileNum[entRightCol][entTopRow];
                //tile2 = gp.tileM.mapTileNum[entRightCol][entBotRow];
                tile1 = gp.getTileType(entRightCol, entTopRow);
                tile2 = gp.getTileType(entRightCol, entBotRow);
                
                break;
        }

        /*
         * 
         if (gp.tileM.tile[tile1].collision == true || gp.tileM.tile[tile2].collision == true){
             ent.collisionOn = true;
            }
            */

        if (gp.tileCollidable(tile1) || gp.tileCollidable(tile2)){
            ent.collisionOn = true;
        }
        
    }

    /**
     * checks if player collision box is interesecting any of the item collision boxes, if so, item is picked up
     * @param ent is the entity, just player atm
     * @param player is to check entity is player
     * @returns item index to player,
     */
    public int checkItem(Entity ent, boolean player){
        int index = 999;
        for (int i = 0; i <gp.getItemLength(); i++){
            if (gp.items[i] != null){
                // get entity's collision box
                // TO -done- DO: 2023-04-01  Provide getter method for hitbox, single dot rule
                Rectangle ent_collision = new Rectangle(ent.getCollisionX() + ent.x,
                                                        ent.getCollisionY() + ent.y,
                                                        ent.getCollisionWidth(),
                                                        ent.getCollisionHeight());
                // get item's collision box
                Rectangle item_collision = new Rectangle(gp.getItemX(i) + gp.tileSize/4, gp.getItemY(i) + gp.tileSize/4, 
                                                        gp.tileSize/2, gp.tileSize/2);
                
                switch(ent.direction){
                    case "up":
                        ent_collision.y -= ent.speed;
                        
                        break;
                    case "down":
                        ent_collision.y += ent.speed;
                        
                        break;
                    case "left":
                        ent_collision.x -= ent.speed;
                        
                        break;
                    case "right":
                        ent_collision.x += ent.speed;
                        
                        break;

                }
                if (ent_collision.intersects(item_collision)){
                    //System.out.println("up collision!");
                    if (gp.itemCollidable(i)){
                        ent.collisionOn = true;
                    }
                    if (player == true){
                        index = i;
                    }
                }

            }
        }

        return index;
    }
    /**
     * checks if two entity's collision boxes are interescting, 
     * if they are colliding, check if one is player, if one is player, player dies
     * @param ent1 is entity 1
     * @param ent2 is entity 2
     */
    // TO-done-DO: 2023-04-01 Apply single dot rule, lines 172 - 180

    public void checkEnt(Entity ent1, Entity ent2){
        if (ent2 != null){
            // get entity's collision box
            Rectangle ent_collision = new Rectangle(ent1.getCollisionX() + ent1.x,
                                                    ent1.getCollisionY() + ent1.y,
                                                    ent1.getCollisionWidth(),
                                                    ent1.getCollisionHeight());
             // get item's collision box
             Rectangle ent2_collision = new Rectangle(ent2.getCollisionX() + ent2.x,
                                                      ent2.getCollisionY() + ent2.y,
                                                      ent2.getCollisionWidth(),
                                                      ent2.getCollisionX());

            //System.out.println(ent1.name + ent2.name);

            Boolean entCollision = false;
            
            switch(ent1.direction){
                case "up":
                    ent_collision.y -= ent1.speed;
                    
                        break;
                    case "down":
                        ent_collision.y += ent1.speed;
                        
                        break;
                    case "left":
                        ent_collision.x -= ent1.speed;
                        
                        break;
                    case "right":
                        ent_collision.x += ent1.speed;
                        
                        break;

                }

                if (ent_collision.intersects(ent2_collision)){
                    //System.out.println("up collision!");
                        entCollision = true;
                    }

                if (entCollision){

                    ent1.collisionOn = true;
                    
                    if (ent1.collisionOn){
                        if (ent1.name == "player"){
                            //System.out.println("problem here");
                            ent1.life = 0;
                        } 
                        if (ent2.name == "player"){
                            //System.out.println("problem here");
                            ent2.life = 0;
                        }
                    } 
                }
            }
    }
}
