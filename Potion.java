import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Potion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
    
    public Potion()
    {
        for (int i = 0; i < 12; i ++)
        {
            swirl[i] = new GreenfootImage("images/healthPot/potion" + i + ".png");
            swirl[i].scale(15,30);
        }
        animateTimer.mark();
    }
    
    public void act()
    {
        // Add your action code here.
        animate();
    }
    
    public void animate()
    {
        if (animateTimer.millisElapsed() > 100)
        {
            setImage(swirl[animateIndex]);
            animateIndex = (animateIndex + 1) % 12;
            animateTimer.mark();
        }
    }
}
