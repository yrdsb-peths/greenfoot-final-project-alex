import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Sprite spawned in upon any Enemy1 death, acting as a death animation and corpse of enemy1. Removed after a few seconds of being onscreen
 * 
 * @author Alex V. 
 * @version 6.9.2022
 */
public class Enemy1Death extends Actor
{
    GreenfootImage[] death = new GreenfootImage[5];
    int deathIndex = 0;
    SimpleTimer deathTimer = new SimpleTimer();
    SimpleTimer disappearTimer = new SimpleTimer();
    
    /**
     * Act - do whatever the Enemy1Death wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        animateDeath();
        //After this object has been onscreen for 5 seconds, it despawns
        if (disappearTimer.millisElapsed() > 5000)
        {
            getWorld().removeObject(this);
        }
    }
    
    public Enemy1Death()
    {
        for (int i = 0; i < 5; i ++)
        {
            death[i] = new GreenfootImage("images/toasterBot/death/death" + i + ".png");
        }
        deathTimer.mark();
        disappearTimer.mark();
    }
    
    //Plays its animation only once, stopping at the last frame
    public void animateDeath()
    {
        if (deathTimer.millisElapsed() > 150 && deathIndex < 5)
        {
            setImage(death[deathIndex]);
            deathIndex = deathIndex + 1;
            deathTimer.mark();
        }
    }

      
}
