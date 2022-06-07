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
    SimpleTimer enemySpawn = new SimpleTimer();
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 500, 1); 
        health = 5;
        enemySpawn.mark();
        PlayerChar test = new PlayerChar();
        Hitbox playerHitbox = new Hitbox();
        PlayerAttack a = new PlayerAttack();
        
        addObject(a, 400, 250);
        addObject(test, 400, 250);
        addObject(playerHitbox, 400, 250);
        
        addObject(healthPoints, 25,15);
    }
    
    public void healthDown(int amount)
    {
        health -= amount;
        healthPoints.setValue("HP: " + health);
    }
    
    public void spawnEnemies()
    {
        if (enemySpawn.millisElapsed() > 800)
        {
            int x = Greenfoot.getRandomNumber(800);
            int y = Greenfoot.getRandomNumber(500);
            Enemy1 e = new Enemy1();
            addObject(e, x, y);
            enemySpawn.mark();
        }
    }
}
