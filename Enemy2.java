import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class enemy1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy2 extends Actor
{
    int walkIndex = 0;
    GreenfootImage[] walkRight = new GreenfootImage[6];
    GreenfootImage[] walkLeft = new GreenfootImage[6];
    private SimpleTimer walkTimer = new SimpleTimer();
    public static int x;
    public static int y;
    boolean facingRight = true; 
    
    int randomX = Greenfoot.getRandomNumber(800);
    int randomY = Greenfoot.getRandomNumber(500);
    private SimpleTimer directionSwitch = new SimpleTimer();
    
    /**
     * Act - do whatever the enemy1 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Enemy2()
    {
        for (int i = 0; i < 6; i ++)
        {
            walkRight[i] = new GreenfootImage("images/mudBot/walk/walk" + i + ".png");
            walkRight[i].scale(50,45);
            walkLeft[i] = new GreenfootImage("images/mudBot/walk/walk" + i + ".png");
            walkLeft[i].mirrorHorizontally();
            walkLeft[i].scale(50,45);
        }
        
        setImage(walkRight[0]);
        walkTimer.mark();
        directionSwitch.mark();
    }

    public void act()
    {
        // Add your action code here.
        animate();
        turnTowards(randomX, randomY);
        move(1);
        if (directionSwitch.millisElapsed() > 3000)
        {
            randomX = Greenfoot.getRandomNumber(800);
            randomY = Greenfoot.getRandomNumber(500);
            
            directionSwitch.mark();
        }
        
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
        if(walkTimer.millisElapsed() > 60 && facingRight == true)
        {
            setImage(walkRight[walkIndex]);
            walkIndex = (walkIndex + 1) % 6;
            walkTimer.mark();
        }
        if (walkTimer.millisElapsed() > 60 && facingRight == false)
        {
            setImage(walkLeft[walkIndex]);
            walkIndex = (walkIndex + 1) % 6;
            walkTimer.mark();
        }
    }

    public void die()
    {
        getWorld().addObject(new Enemy2Death(), this.getX(), this.getY());
        getWorld().removeObject(this);
    }
    
    public void fireBullet()
    {
        
    }
}
