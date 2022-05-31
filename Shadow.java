import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class shadow here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shadow extends Actor
{
    /**
     * Act - do whatever the shadow wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int speed;
    
    public Shadow(int speed)
    {
        this.speed = speed;
    }
    
    public void act()
    {
        // Add your action code here.
        if (Greenfoot.isKeyDown("d") && !Greenfoot.isKeyDown("space"))
        {
            move(speed);
        }
        if (Greenfoot.isKeyDown("a") && !Greenfoot.isKeyDown("space"))
        {
            move(speed * -1);        }
        if(Greenfoot.isKeyDown("w") && !Greenfoot.isKeyDown("space"))
        {
            setLocation(getX(), getY()-speed);
        }
        if(Greenfoot.isKeyDown("s") && !Greenfoot.isKeyDown("space"))
        {
            setLocation(getX(), getY()+speed);
        }
    }
}
