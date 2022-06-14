import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Button object for the different screens in game
 * 
 * @author Alex V.
 * @version 6.14.2022
 */
public class Button extends Actor
{
    /**
     * Act - do whatever the Button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */    
    //Allows for creation of buttons of different sizes using inputted values
    public Button(int length, int width)
    {
        GreenfootImage b = new GreenfootImage("images/button.png");
        b.scale(width, length);
        setImage(b);  
    }
}
