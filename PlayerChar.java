import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class playerChar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerChar extends Actor
{
    /**
     * Act - do whatever the playerChar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int speed = 1;
    int imageIndex = 0;
    GreenfootImage[] walkRight = new GreenfootImage[8];
    GreenfootImage[] walkLeft = new GreenfootImage[8];
    private SimpleTimer walkTimer = new SimpleTimer();
    private boolean facingRight = true;
    
    
    public PlayerChar()
    {
        for (int i = 0; i < 8; i ++)
        {
            walkRight[i] = new GreenfootImage("images/Ball and Chain Bot/walk/walk" + i + ".png");
            walkLeft[i] = new GreenfootImage("images/Ball and Chain Bot/walk/walk" + i + ".png");
            walkLeft[i].mirrorHorizontally();
        }
        setImage(walkRight[0]);
        walkTimer.mark();
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
        }
        if (Greenfoot.isKeyDown("a"))
        {
            move(speed * -1);
            facingRight = false;
        }
        if(Greenfoot.isKeyDown("w"))
        {
            setLocation(getX(), getY()-speed);
        }
        if(Greenfoot.isKeyDown("s"))
        {
            setLocation(getX(), getY()+speed);
        }
        animate();
    }
    
    public void animate()
    {
        if(walkTimer.millisElapsed() > 80)
        {
            if (facingRight == true)
            {
                setImage(walkRight[imageIndex]);
                imageIndex = (imageIndex + 1) % 8;
            }
            else 
            {
                setImage(walkLeft[imageIndex]);
                imageIndex = (imageIndex + 1) % 8;
            }
            walkTimer.mark();
        }
    }
}
