import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Sprite spawned in upon any Enemy2 death, acting as a death animation and corpse of enemy2. Removed after a few seconds of being onscreen
 * 
 * @author Alex V. 
 * @version 6.10.2022
 */
public class Enemy2Death extends Actor
{
    GreenfootImage[] death = new GreenfootImage[8];
    int deathIndex = 0;
    SimpleTimer deathTimer = new SimpleTimer();
    SimpleTimer disappearTimer = new SimpleTimer();
    
    /**
     * Act - do whatever the Enemy2Death wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        animateDeath();
        disappear();
    }
    
    public Enemy2Death()
    {
        for (int i = 0; i < 8; i ++)
        {
            death[i] = new GreenfootImage("images/mudBot/death/death" + i + ".png");
            death[i].scale(70,45);
        }
        deathTimer.mark();
        disappearTimer.mark();
    }
    
    public void animateDeath()
    {
        if (deathTimer.millisElapsed() > 130 && deathIndex < 8)
        {
            setImage(death[deathIndex]);
            deathIndex = deathIndex + 1;
            deathTimer.mark();
        }
    }
    
    public void disappear()
    {
        if (disappearTimer.millisElapsed() > 5000)
        {
            getWorld().removeObject(this);
        }
    }
}
