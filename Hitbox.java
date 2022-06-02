import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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
        if (this.isTouching(Enemy1.class))
        {
            removeTouching(Enemy1.class);
            MyWorld world = (MyWorld) getWorld();
            world.healthDown(1);
        }
    }
}
