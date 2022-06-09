import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class Hitbox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hitbox extends Actor
{
    public static int x;
    public static int y;
    
    /**
     * Act - do whatever the Hitbox wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        setLocation(x,y);
        checkHealth();
    }
    
    public void checkHealth()
    {
        if (isTouching(Enemy1.class))
        {
            List<Enemy1> enemies =  getIntersectingObjects(Enemy1.class);
            for(int i = 0; i < enemies.size(); i++){
                enemies.get(i).die();
                MyWorld world = (MyWorld) getWorld();
                world.healthDown(1);
            }
        } 
        if (isTouching(Enemy2.class))
        {
            List<Enemy2> enemies =  getIntersectingObjects(Enemy2.class);
            for(int i = 0; i < enemies.size(); i++){
                enemies.get(i).die();
                MyWorld world = (MyWorld) getWorld();
                world.healthDown(2);
            }
        }
    }
}
