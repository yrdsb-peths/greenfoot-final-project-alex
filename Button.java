import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Button extends Actor
{
    /**
     * Act - do whatever the Button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int length;
    private int width;
    
    public Button(int length, int width)
    {
        this.length = length;
        this.width = width;
        GreenfootImage b = new GreenfootImage("images/button.png");
        b.scale(width, length);
        setImage(b);
    }
    
    public void act()
    {
        if (Greenfoot.mouseDragged(this))
        {
            GreenfootImage b = new GreenfootImage("images/mouseoverbutton.png");
            b.scale(this.width, this.length);
            setImage(b);
        } else {
            GreenfootImage b = new GreenfootImage("images/button.png");
            b.scale(width, length);
            setImage(b);
        }
    }
}
