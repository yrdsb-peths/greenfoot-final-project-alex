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
    public static int score = 0;
    Label healthPoints = new Label("HP: " + health, 20);
    Label scoreLabel = new Label("BOTS DEMOLISHED: " + score, 80);
    SimpleTimer enemySpawn = new SimpleTimer();
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 500, 1); 
        
        //labels for health and score
        addObject(scoreLabel, 400, 250);
        health = 5;
        score = 0;
        
        healthPoints.setValue("HP: " + health);
        scoreLabel.setValue("BOTS DEMOLISHED: " + score);
        
        enemySpawn.mark();
        PlayerChar test = new PlayerChar();
        Hitbox playerHitbox = new Hitbox();
        PlayerAttack a = new PlayerAttack();
        
        addObject(a, 400, 150);
        addObject(test, 400, 150);
        addObject(playerHitbox, 400, 150);
        
        addObject(healthPoints, 25,15);
        
    }
    
    public void healthDown(int amount)
    {
        health -= amount;
        healthPoints.setValue("HP: " + health);
    }
    public void scoreUp(int amount)
    {
        score += amount;
        scoreLabel.setValue("BOTS DEMOLISHED: " + score);
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
