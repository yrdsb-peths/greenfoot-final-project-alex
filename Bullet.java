import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Bullet fired by the Enemy2 class
 * 
 * @author Alex V.
 * @version 6.10.2022
 */
public class Bullet extends Actor
{    
    /**
     * Act - do whatever the Bullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        move(4);
        //removes bullet if it is at edge
        if (isAtEdge())
        {
            die();
        }
    }
    
    /**
     * deletes bullet object
     */
    public void die()
    {
        getWorld().removeObject(this);
    }
}
