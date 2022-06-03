import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PlayerAttack here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerAttack extends Actor
{
    /**
     * Act - do whatever the PlayerAttack wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int chargeIndex = 0;
    GreenfootImage[] charge = new GreenfootImage[8];
    private SimpleTimer chargeUpTimer = new SimpleTimer();
    public static int x;
    public static int y;
    
    
    public PlayerAttack()
    {
        for (int i = 0; i < 8; i ++)
        {
            charge[i] = new GreenfootImage("images/attack/attack" + i + ".png");
        }
        chargeUpTimer.mark();
    }
    
    public void act()
    {
        // Add your action code here.
        animate();
        setLocation(x,y);
        if (!Greenfoot.isKeyDown("space"))
        {
            setImage("images/attack0.png");
        }
    }
    
    public void animate()
    {
        if (chargeUpTimer.millisElapsed() < 100 && Greenfoot.isKeyDown("space"))
        {
            setImage(charge[chargeIndex]);
            chargeIndex = (chargeIndex + 1) % 8;
        }
        chargeUpTimer.mark();
    }
}
