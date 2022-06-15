import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Main world where the game is played
 * 
 * @author Alex V.
 * @version 6.15.2022
 */
public class MyWorld extends World
{
    public static int health = 5;
    public static int score = 0;
    public static int highScore;
    Label healthPoints = new Label("HP: " + health, 20);
    Label scoreLabel = new Label("BOTS DEMOLISHED: " + score, 80);
    
    //timers for spawning enemies and powerups
    SimpleTimer enemySpawn = new SimpleTimer();
    SimpleTimer powerupSpawn = new SimpleTimer();
    
    PlayerChar test = new PlayerChar();
    
    public static int x;
    public static int y;
    
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
        scoreLabel.setFillColor(Color.GRAY);
        
        enemySpawn.mark();
        Hitbox playerHitbox = new Hitbox();
        PlayerAttack attack = new PlayerAttack();
        
        addObject(attack, 400, 150);
        addObject(test, 400, 150);
        addObject(playerHitbox, 400, 150);
        
        addObject(healthPoints, 25,15);
    }
    
    /**
     * Changes player health by a certain number
     */
    public void healthDown(int amount)
    {
        health -= amount;
        healthPoints.setValue("HP: " + health);
    }
    
    /**
     * Increases score
     */
    public void scoreUp(int amount)
    {
        score += amount;
        scoreLabel.setValue("BOTS DEMOLISHED: " + score);
    }
    
    /**
     * Spawns enemies with a 1 in 6 chance of spawning an Enemy2 object
     */
    public void spawnEnemies(int timer)
    {
        if (enemySpawn.millisElapsed() > timer)
        {
            int rand = Greenfoot.getRandomNumber(6);
            if (rand != 1)
            {
                int x = Greenfoot.getRandomNumber(800);
                int y = Greenfoot.getRandomNumber(500);
                Enemy1 e = new Enemy1();
                addObject(e, x, y);
                enemySpawn.mark();
            } else if (rand == 1)
            {
                int x = Greenfoot.getRandomNumber(800);
                int y = Greenfoot.getRandomNumber(500);
                Enemy2 e = new Enemy2();
                addObject(e, x, y);
                enemySpawn.mark();
            }
        }
    }
    
    /**
     * Spawns powerups
     */
    public void spawnPowerups()
    {
        if (powerupSpawn.millisElapsed() > 10000)
        {
            int x = Greenfoot.getRandomNumber(800);
            int y = Greenfoot.getRandomNumber(500);
            Potion p = new Potion();
            addObject(p, x, y);
            powerupSpawn.mark();
        }
    }
    
    /**
     * When the game ends, collects playerChar's coordinates, sets highscore for title screen, switches world to GameOverWorld
     */
    public void gameOver()
    {
        x = test.getX();
        y = test.getY();
        if (score >= highScore)
        {
            highScore = score;
        }
        GameOverWorld world = new GameOverWorld();
        Greenfoot.setWorld(world);
    }
}
