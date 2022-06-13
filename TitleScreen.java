import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TitleScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleScreen extends World
{
    /**
     * Constructor for objects of class TitleScreen.
     * 
     */

    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 500, 1); 

        Label titleLabel = new Label("ROBOT RUSH", 120);
        addObject(titleLabel, 400, 150);
        Label instructions = new Label ("Press space to start!", 40);
        addObject(instructions, 400, 320);
        Label highScore = new Label ("HIGH SCORE: " + MyWorld.highScore, 80);
        addObject(highScore, 400, 250);
    }
    public void act()
    {
        if (Greenfoot.isKeyDown("space"))
        {
            MyWorld world = new MyWorld();
            Greenfoot.setWorld(world);
        }
    }
}
