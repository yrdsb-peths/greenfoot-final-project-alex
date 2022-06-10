import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Basic enemy, which tracks the player's position and follows, player takes 1 damage when touching this enemy
 * which is destroyed upon touching the player
 * 
 * @author Alex V.
 * @version (a version number or a date)
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

    public void checkRotation()
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

    public void animate()
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

    //creates a new enemy1death object at its location while removing itself from the screen
    public void die()
    {
        getWorld().addObject(new Enemy1Death(), this.getX(), this.getY());
        getWorld().removeObject(this);
    }
}
