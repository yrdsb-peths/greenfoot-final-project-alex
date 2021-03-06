import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Hitbox for the player object, set at the player object's position
 * 
 * @author Alex V. 
 * @version 6.10.2022
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
        //sets location to player's location
        setLocation(x,y);
        checkHealth();
        
        //ends game when health equals 0
        if (MyWorld.health <= 0)
        {
            MyWorld world = (MyWorld) getWorld();
            world.gameOver();
            
        }
    }
    
    /**
     * Changes health variable from MyWorld based on which object the hitbox is in contact with
     */
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
        if (isTouching(Bullet.class))
        {
            List<Bullet> bullets =  getIntersectingObjects(Bullet.class);
            for(int i = 0; i < bullets.size(); i++){
                bullets.get(i).die();
                MyWorld world = (MyWorld) getWorld();
                world.healthDown(1);
            }
        }
        //increase health when touching potion, but cannot go past the max health of 5
        if (isTouching(Potion.class))
        {
            removeTouching(Potion.class);
            GreenfootSound consumed = new GreenfootSound("sounds/potionCollected.mp3");
            consumed.play();
            MyWorld world = (MyWorld) getWorld();
            if (world.health + 2 > 5)
            {
                world.healthDown(-(5-world.health));
            }else{
                world.healthDown(-2);
            }
        }
    }
}
