import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Player controlled character with walking and idle animations
 * Sprites from Penusbmic on itch.io
 * 
 * @author Alex V. 
 * @version May 30th, 2022
 */
public class PlayerChar extends Actor
{
    /**
     * Act - do whatever the playerChar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int speed = 1;
    int walkIndex = 0;
    int idleIndex = 0;
    GreenfootImage[] walkRight = new GreenfootImage[8];
    GreenfootImage[] walkLeft = new GreenfootImage[8];
    GreenfootImage[] idleRight = new GreenfootImage[5];
    GreenfootImage[] idleLeft = new GreenfootImage[5];
    private SimpleTimer walkTimer = new SimpleTimer();
    private SimpleTimer idleTimer = new SimpleTimer();
    private boolean facingRight = true;
    private boolean isIdle = true;
    
    
    public PlayerChar()
    {
        for (int i = 0; i < 8; i ++)
        {
            walkRight[i] = new GreenfootImage("images/Ball and Chain Bot/walk/walk" + i + ".png");
            walkLeft[i] = new GreenfootImage("images/Ball and Chain Bot/walk/walk" + i + ".png");
            walkLeft[i].mirrorHorizontally();
        }
        for (int i = 0; i < 5; i ++)
        {
            idleRight[i] = new GreenfootImage("images/Ball and Chain Bot/idle/idle" + i + ".png");
            idleLeft[i] = new GreenfootImage("images/Ball and Chain Bot/idle/idle" + i + ".png");
            idleLeft[i].mirrorHorizontally();
        }
        setImage(walkRight[0]);
        walkTimer.mark();
        idleTimer.mark();
    }
    
    public void act()
    {
        // Add your action code here.
        keyInputs();
    }
    
    public void keyInputs()
    {
        if (Greenfoot.isKeyDown("d"))
        {
            move(speed);
            facingRight = true;
            isIdle = false;
        }
        if (Greenfoot.isKeyDown("a"))
        {
            move(speed * -1);
            facingRight = false;
            isIdle = false;
        }
        if(Greenfoot.isKeyDown("w"))
        {
            setLocation(getX(), getY()-speed);
            isIdle = false;
        }
        if(Greenfoot.isKeyDown("s"))
        {
            setLocation(getX(), getY()+speed);
            isIdle = false;
        }
        if (!Greenfoot.isKeyDown("s") && !Greenfoot.isKeyDown("a") && !Greenfoot.isKeyDown("w") && !Greenfoot.isKeyDown("d"))
        {
            isIdle = true;
        }
        animate();
    }
    
    public void animate()
    {
        if(walkTimer.millisElapsed() > 80)
        {
            if (facingRight == true && isIdle == false)
            {
                setImage(walkRight[walkIndex]);
                walkIndex = (walkIndex + 1) % 8;
            }
            else if (facingRight == false && isIdle == false)
            {
                setImage(walkLeft[walkIndex]);
                walkIndex = (walkIndex + 1) % 8;
            }
            walkTimer.mark();
        }
        if (idleTimer.millisElapsed() > 100)
        {
            if (facingRight == true && isIdle == true)
            {
                setImage(idleRight[idleIndex]);
                idleIndex = (idleIndex + 1) % 5;
            }
            if (facingRight == false && isIdle == true)
            {
                setImage(idleLeft[idleIndex]);
                idleIndex = (idleIndex + 1) % 5;
            }
            idleTimer.mark();
        }
    }
}
