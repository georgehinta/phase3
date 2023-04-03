package tile;

// REMOVE UNNECESSARY IMPORTS HERE

import main.Screen;

import javax.imageio.ImageIO;
import java.awt.*;
//import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class TileManager {

    Screen gp;
    public Tile[] tile;
    public int mapTileNum[][];

    /**
     * tile manager loads map from resources map, TODO: add more maps
     * 
     * @param gp is highest instance of game, holds all 
     */
    public TileManager(Screen gp) {

        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];

        getTileImage();
        loadMap();

    }

    /**
     * loads bufferedimages of Tile into Tile[] tile
     */
    public void getTileImage() {

        try{

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
            tile[2].collision = true;
            
        }catch(IOException exc) {
            System.out.println("error opening tile file: " + exc.getMessage());
        }
    }
    /**
     * loads map data from resoucres/map to mapTileNum[][]
     */
    public void loadMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/maps/map1.txt");

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxScreenCol && row < gp.maxScreenRow) {

                String line = br.readLine();

                while (col < gp.maxScreenCol) {

                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;

                }
                if (col == gp.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        }catch(Exception e) {

        }
    }
    /**
     * draws tiles using graphics2d package
     * @param g2
     */
    public void draw(Graphics2D g2) {

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gp.maxScreenCol && row < gp.maxScreenRow) {

            int tileNum = mapTileNum[col][row];

            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;

            if(col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;

            }
        }
    }

    /**
     * finds direction from "from" to "to" using breadth first search
     * @param from, int array x,y of source
     * @param to, inta array x,y of target
     * @return one of "up", "down", "left", "right"
     */
    public String pathfind(int[] from, int[] to){

        // Remove necessary print statements

        //System.out.println("from is " + from[0] + from[1]);
        //System.out.println("to is" + to[0] + to[1]);
        //if (true) return "error";
        int boxSize = gp.tileSize;
        int fromXCol = (from[0]+boxSize/2)/gp.tileSize;
        int toXCol = (to[0]+boxSize/2)/gp.tileSize;
        int fromYCol = (from[1]+boxSize*2/3)/gp.tileSize;
        int toYCol = (to[1]+boxSize*2/3)/gp.tileSize;

        int rows = mapTileNum.length;
        int cols = mapTileNum[0].length;
        String[][] path = new String[rows][cols];

       
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j ++){
                path[i][j] = "";
            }
        }

       Queue<Integer[]> q= new LinkedList<>();
       Integer arr[]= {fromXCol, fromYCol};
       q.add(arr);
       //int count = 0;

       while (!q.isEmpty()){
        //System.out.println("queue at" + q.peek()[0] + q.peek()[1]);
        //if (count == 10000){
          //  return "failure";
        //}
        Integer [] cur = q.remove();
        int cur_x = cur[0];
        int cur_y = cur[1];
        if (cur[0] == toXCol && cur[1] == toYCol){
            return path[cur[0]][cur[1]];
        }
        // up
        if (mapTileNum[cur_x][cur_y-1] == 0 && path[cur_x][cur_y-1].length()==0){
            q.add(new Integer[] {cur_x, cur_y-1});
            path[cur_x][cur_y-1] = path[cur_x][cur_y];//.substring(1);
            path[cur_x][cur_y-1] += " up";
        }
        //down
        if (mapTileNum[cur_x][cur_y+1] == 0 && path[cur_x][cur_y+1].length()==0){
            q.add(new Integer[] {cur_x, cur_y+1});
            path[cur_x][cur_y+1] = path[cur_x][cur_y];//.substring(1);
            path[cur_x][cur_y+1] += " down";
        }
        //left
        if (mapTileNum[cur_x-1][cur_y] == 0 && path[cur_x-1][cur_y].length()==0){
            q.add(new Integer[] {cur_x-1, cur_y});
            path[cur_x-1][cur_y] = path[cur_x][cur_y];//.substring(1);
            path[cur_x-1][cur_y] += " left";
        }
        //right
        if (mapTileNum[cur_x+1][cur_y] == 0 && path[cur_x+1][cur_y].length()==0){
            q.add(new Integer[] {cur_x+1, cur_y});
            path[cur_x+1][cur_y] = path[cur_x][cur_y];//.substring(1);
            path[cur_x+1][cur_y] += " right";
        }

        //count++;
       }

        return "error";

    }

    public int getTileType(int col, int row){

        return mapTileNum[col][row];
    }

    public Boolean tileCollidable (int a){
        return tile[a].collision;
    }
}
