import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Displays controls and mechanics for player to see
 * 
 * @author Alex V.
 * @version 6.14.2022
 */
public class InstructionsWorld extends World
{

    /**
     * Constructor for objects of class InstructionsWorld.
     * 
     */
    Button confirmB = new Button(60, 220);
    Label confirm = new Label("CONFIRM", 55);
    
    public InstructionsWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 500, 1); 
        addObject(confirmB, 400, 450);
        addObject(confirm, 400, 450);
    }
    
    public void act()
    {
        if (Greenfoot.mousePressed(confirmB) || Greenfoot.mousePressed(confirm))
        {
            TitleScreen world = new TitleScreen();
            Greenfoot.setWorld(world);
        }
    }
}
