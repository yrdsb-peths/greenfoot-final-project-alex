import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Screen that pops up when the player has died. Displays an option to play again, and player score.
 * 
 * @author Alex V.
 * @version 6.14.2022
 */
public class GameOverWorld extends World
{

    /**
     * Constructor for objects of class GameOverWorld.
     * 
     */
    Label startOver = new Label("PLAY AGAIN", 50);
    Button startOverB = new Button(60, 300);
    
    public GameOverWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 500, 1); 
        Label died = new Label("YOU DIED", 70);
        addObject(died, 400,230);
        Label score = new Label("SCORE: " + MyWorld.score, 50);
        addObject(score, 400, 290);
        addObject(startOverB, 400, 360);
        addObject(startOver, 400,360);
        
        PlayerCharDeath p = new PlayerCharDeath();
        addObject(p, MyWorld.x, MyWorld.y);
    }
    
    public void act()
    {
        if (Greenfoot.isKeyDown("enter"))
        {
            Greenfoot.setWorld(new TitleScreen());
        }
        if (Greenfoot.mousePressed(startOverB) || Greenfoot.mousePressed(startOver))
        {
            TitleScreen world = new TitleScreen();
            Greenfoot.setWorld(world);
        }
    }
}
