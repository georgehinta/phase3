package main;
//import main;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import enemy.Enemy_Spider;
import entity.Entity;




public class spiderTest {

    @Test
    public void subTest(){

        Enemy_Spider a = new Enemy_Spider(new Screen());
        a.x = 100;
        a.y = 100;

        a.update();
        a.update();
        System.out.println(a.y);
    }
    
}
