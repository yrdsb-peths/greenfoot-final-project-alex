import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Basic enemy, which tracks the player's position and follows player
 * Destroyed upon touching player which causes the player to take 1 damage
 * 
 * @author Alex V.
 * @version 6.8.2022
 */
public class Enemy1 extends Actor
{
    int walkIndex = 0;
    GreenfootImage[] walkRight = new GreenfootImage[8];
    GreenfootImage[] walkLeft = new GreenfootImage[8];
    private SimpleTimer walkTimer = new SimpleTimer();
    public static int x;
    public static int y;
    boolean facingRight = true;   
    GreenfootSound death = new GreenfootSound("sounds/robotDeath.wav");
    
    /**
     * Act - do whatever the enemy1 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Enemy1()
    {
        for (int i = 0; i < 8; i ++)
        {
            walkRight[i] = new GreenfootImage("images/toasterBot/walk/walk" + i + ".png");
            walkLeft[i] = new GreenfootImage("images/toasterBot/walk/walk" + i + ".png");
            walkLeft[i].mirrorHorizontally();
        }
        setImage(walkRight[0]);
        walkTimer.mark();
        death.setVolume(90);
    }

    public void act()
    {
        // Add your action code here.
        animate();
        turnTowards(x, y);
        move(1);
        setRotation(0);

        checkRotation();
    }

    /**
     * Turns Enemy1 relative to the player's location, making sure it is always facing the player
     */
    private void checkRotation()
    {
        if (x < getX())
        {
            facingRight = false;
        }
        if (x > getX())
        {
            facingRight = true;
        }
    }

    /**
     * Animates Enemy1
     */
    private void animate()
    {
        if(walkTimer.millisElapsed() > 80 && facingRight == true)
        {
            setImage(walkRight[walkIndex]);
            walkIndex = (walkIndex + 1) % 8;
            walkTimer.mark();
        }
        if (walkTimer.millisElapsed() > 80 && facingRight == false)
        {
            setImage(walkLeft[walkIndex]);
            walkIndex = (walkIndex + 1) % 8;
            walkTimer.mark();
        }
    }

    /**
     * Creates a new enemy1death object at its location while removing itself from the screen
     */    
    public void die()
    {
        getWorld().addObject(new Enemy1Death(), this.getX(), this.getY());
        getWorld().removeObject(this);
        death.play();
    }
}
