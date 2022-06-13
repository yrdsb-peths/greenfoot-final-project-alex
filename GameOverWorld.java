import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameOverWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOverWorld extends World
{

    /**
     * Constructor for objects of class GameOverWorld.
     * 
     */
    public GameOverWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 500, 1); 
        Label died = new Label("YOU DIED", 70);
        addObject(died, 400,250);
        Label startOver = new Label("Press Enter to start again", 40);
        addObject(startOver, 400,320);
        PlayerCharDeath p = new PlayerCharDeath();
        addObject(p, MyWorld.x, MyWorld.y);
    }
    
    public void act()
    {
        if (Greenfoot.isKeyDown("enter"))
        {
            Greenfoot.setWorld(new TitleScreen());
        }
    }
}
