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
    Button startB;

    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 500, 1); 

        Label titleLabel = new Label("ROBOT RUSH", 120);
        addObject(titleLabel, 400, 100);
        Label highScore = new Label ("HIGH SCORE: " + MyWorld.highScore, 80);
        addObject(highScore, 400, 200);
        
        Button startB = new Button(60,360);
        addObject(startB, 400, 290);
        Label start = new Label("INSTRUCTIONS", 50);
        addObject(start, 400, 290);
        
        Button tutorialB = new Button(60,220);
        addObject(tutorialB, 400, 370);
        Label instructions = new Label("START", 60);
        addObject(instructions, 400, 370);
    }
    public void act()
    {
        if (Greenfoot.mouseClicked(startB))
        {
            MyWorld world = new MyWorld();
            Greenfoot.setWorld(world);
        }
    }
}
