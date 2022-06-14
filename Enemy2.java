import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Advanced enemy which moves around randomly, stopping occasionally to fire a bullet that  deals 1 damage to player
 * 
 * @author Alex v.
 * @version 6.10.2022
 */
public class Enemy2 extends Actor
{
    private int walkIndex = 0;
    private int attackIndex = 0;
    
    GreenfootImage[] walkRight = new GreenfootImage[6];
    GreenfootImage[] walkLeft = new GreenfootImage[6];
    private SimpleTimer walkTimer = new SimpleTimer();
    GreenfootImage[] attackRight = new GreenfootImage[7];
    GreenfootImage[] attackLeft = new GreenfootImage[7];
    private SimpleTimer attackTimer = new SimpleTimer();
    
    public static int x;
    public static int y;
    boolean facingRight = true; 
    boolean isAttacking = false;
    
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
            walkRight[i].scale(40,45);
            walkLeft[i] = new GreenfootImage("images/mudBot/walk/walk" + i + ".png");
            walkLeft[i].mirrorHorizontally();
            walkLeft[i].scale(40,45);
        }
        for (int i = 0; i < 7; i ++)
        {
            attackRight[i] = new GreenfootImage("images/mudBot/attack/attack" + i + ".png");
            attackRight[i].scale(70,45);
            attackLeft[i] = new GreenfootImage("images/mudBot/attack/attack" + i + ".png");
            attackLeft[i].mirrorHorizontally();
            attackLeft[i].scale(70,45);
        }
        setImage(walkRight[0]);
        walkTimer.mark();
        attackTimer.mark();
        directionSwitch.mark();
    }

    public void act()
    {
        animate();
        //turns towards a random position and moves towards it
        turnTowards(randomX, randomY);
        move(1);
        
        //every 3 seconds, the random position changes
        if (directionSwitch.millisElapsed() > 3000)
        {
            randomX = Greenfoot.getRandomNumber(800);
            randomY = Greenfoot.getRandomNumber(500);
            directionSwitch.mark();
        }        
        setRotation(0);
        checkRotation();
        
        //attacks if the randomly generated number equals 1
        int num = Greenfoot.getRandomNumber(200);
        if (num == 1)
        {
            isAttacking = true;
        }
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
        if(walkTimer.millisElapsed() > 60 && facingRight == true && isAttacking == false)
        {
            setImage(walkRight[walkIndex]);
            walkIndex = (walkIndex + 1) % 6;
            walkTimer.mark();
        }
        if (walkTimer.millisElapsed() > 60 && facingRight == false && isAttacking == false)
        {
            setImage(walkLeft[walkIndex]);
            walkIndex = (walkIndex + 1) % 6;
            walkTimer.mark();
        }
        
        //attack animation 
        if(attackTimer.millisElapsed() > 60 && facingRight == true && isAttacking && attackIndex < 7)
        {
            setImage(attackRight[attackIndex]);
            attackIndex = attackIndex + 1;
            attackTimer.mark();
        }
        if (attackTimer.millisElapsed() > 60 && facingRight == false && isAttacking && attackIndex < 7)
        {
            setImage(attackLeft[attackIndex]);
            attackIndex = attackIndex + 1;
            attackTimer.mark();
        }
        
        //once attack animation is complete, spawns a bullet object and points it towards the player
        if (attackIndex == 6)
        {
            Bullet b = new Bullet();
            getWorld().addObject(b, getX(), getY());
            b.turnTowards(x,y);
            attackIndex = 0;
            isAttacking = false;
        }
    
    }

    public void die()
    {
        getWorld().addObject(new Enemy2Death(), this.getX(), this.getY());
        getWorld().removeObject(this);
    }
}
