import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Potion object which heals player for 2 health points
 * 
 * @author Alex V.
 * @version 6.8.2022
 */
public class Potion extends Actor
{
    /**
     * Act - do whatever the Potion wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int animateIndex = 0;
    GreenfootImage[] swirl = new GreenfootImage[12];
    SimpleTimer animateTimer = new SimpleTimer();
    SimpleTimer disappearTimer = new SimpleTimer();
    
    public Potion()
    {
        for (int i = 0; i < 12; i ++)
        {
            swirl[i] = new GreenfootImage("images/healthPot/potion" + i + ".png");
            swirl[i].scale(15,30);
        }
        animateTimer.mark();
        disappearTimer.mark();
    }
    
    public void act()
    {
        // Add your action code here.
        animate();
        if (disappearTimer.millisElapsed() > 25000)
        {
            getWorld().removeObject(this);
        }
    }
    
    /**
     * Animates the potion
     */
    private void animate()
    {
        if (animateTimer.millisElapsed() > 100)
        {
            setImage(swirl[animateIndex]);
            animateIndex = (animateIndex + 1) % 12;
            animateTimer.mark();
        }
    }
}
