import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Sprite spawned in upon any Enemy1 death, acting as a death animation. Removed after a few seconds of being onscreen
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
        disappear();
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

    //After this object has been onscreen for 5 seconds, it despawns
    public void disappear()
    {
        if (disappearTimer.millisElapsed() > 5000)
        {
            getWorld().removeObject(this);
        }
    }
}
