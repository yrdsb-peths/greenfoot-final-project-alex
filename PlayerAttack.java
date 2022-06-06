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
    int releaseIndex = 0;
    GreenfootImage[] charge = new GreenfootImage[8];
    GreenfootImage[] release = new GreenfootImage[4];
    private SimpleTimer chargeUpTimer = new SimpleTimer();
    private SimpleTimer releaseTimer = new SimpleTimer();
    public static int x;
    public static int y;
    boolean isCharging = false;
    
    public static boolean released = false;    
    
    public PlayerAttack()
    {
        for (int i = 0; i < 8; i ++)
        {
            charge[i] = new GreenfootImage("images/attack/attack" + i + ".png");
        }
        for (int i = 0; i < 4; i ++)
        {
            release[i] = new GreenfootImage("images/attack/attackrelease" + i + ".png");
        }
        chargeUpTimer.mark();
        releaseTimer.mark();
    }
    
    public void act()
    {
        // Add your action code here.
        animate();
        setLocation(x,y);
        destroy();
        release();
    }
    
    public void animate()
    {
        if (chargeUpTimer.millisElapsed() < 100 && Greenfoot.isKeyDown("space"))
        {
            setImage(charge[chargeIndex]);
            chargeIndex = (chargeIndex + 1) % 8;
            isCharging = true;
        }
        chargeUpTimer.mark();
        if (!Greenfoot.isKeyDown("space") && isCharging == false)
        {
            setImage("images/attack0.png");
        }
    }
    
    public void release()
    {
        if (isCharging == true && !Greenfoot.isKeyDown("space"))
        {
            if (releaseTimer.millisElapsed() > 20 && releaseIndex < 4)
            {
                setImage(release[releaseIndex]);
                releaseIndex += 1;
                released = true;
                releaseTimer.mark();
            }
        }
        if (releaseIndex == 4)
        {
            isCharging = false;
            released = false;
            releaseIndex = 0;
        }
    }
    
    public void destroy()
    {
        if (released)
        {
            if (isTouching(Enemy1.class))
            {
                removeTouching(Enemy1.class);
            }
        }
    }
}
