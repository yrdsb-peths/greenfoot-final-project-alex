import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Sprite spawned in upon Player death, acting as a death animation.
 * 
 * @author Alex V.
 * @version 6.13.2022
 */
public class PlayerCharDeath extends Actor
{
    GreenfootImage[] death = new GreenfootImage[5];
    int deathIndex = 0;
    SimpleTimer deathTimer = new SimpleTimer();
    SimpleTimer disappearTimer = new SimpleTimer();
    
    /**
     * Act - do whatever the PlayerCharDeath wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        animateDeath();
    }
    
    public PlayerCharDeath()
    {
        for (int i = 0; i < 5; i ++)
        {
            death[i] = new GreenfootImage("images/playerChar/death/death" + i + ".png");
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
