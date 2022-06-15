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
    Button tutorialB = new Button(60,360);
    Button startB = new Button(60,220);
    Label instructions = new Label("INSTRUCTIONS", 50);
    Label start = new Label("START", 60);
    
    public static GreenfootSound backgroundMusic = new GreenfootSound("sounds/Soundtrack.mp3");
    public static boolean playing = false;
    
    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 500, 1); 

        Label titleLabel = new Label("ROBOT RUSH", 120);
        addObject(titleLabel, 400, 100);
        Label highScore = new Label ("HIGH SCORE: " + MyWorld.highScore, 80);
        addObject(highScore, 400, 200);

        addObject(startB, 400, 370);
        addObject(tutorialB, 400, 290);
        addObject(instructions, 400, 290);
        addObject(start, 400, 370);
        backgroundMusic.setVolume(45);
        playing = false;
    }
    public void act()
    {
        //Changes worlds depending on which button the player presses
        if (Greenfoot.mousePressed(startB) || Greenfoot.mousePressed(start))
        {
            MyWorld world = new MyWorld();
            Greenfoot.setWorld(world);
        }
        if (Greenfoot.mousePressed(tutorialB) || Greenfoot.mousePressed(instructions))
        {
            InstructionsWorld world = new InstructionsWorld();
            Greenfoot.setWorld(world);
        }
        if (playing == false)
        {
            backgroundMusic.playLoop();
            playing = true;
        }
    }
}
