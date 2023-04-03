package main;
import org.junit.Test;
import tile.TileManager;

import static org.junit.Assert.assertEquals;

public class pathTest {

    public TileManager tileM = new TileManager(new Screen());
    
    @Test
    public void subTest(){

        int[] from = new int[] {100,20};
        int[] to = new int[] {300,20};

        String path = tileM.pathfind(from, to);
        String moves[] = path.split("\\s+");
        //System.out.println(res);
        assertEquals("right", moves[1]);

    }
}
