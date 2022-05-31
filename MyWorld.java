import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    public static int health = 5;
    Label healthPoints = new Label("HP: " + health, 20);
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 500, 1); 
        health = 5;
        PlayerChar test = new PlayerChar();
        addObject(test, 400, 250);
        addObject(healthPoints, 25,15);
        
        Shadow s = new Shadow(1);
        addObject(s, 399, 263);
    }
    
    public void healthDown(int amount)
    {
        health -= amount;
        healthPoints.setValue("HP: " + health);
    }
}
