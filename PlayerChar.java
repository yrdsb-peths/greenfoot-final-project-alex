import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Player controlled character with walking, idle, and charging animations
 * 
 * @author Alex V. 
 * @version 6.7.2022
 */
public class PlayerChar extends Actor
{
    public static int speed = 2;
    
    //indexes for different animations
    int walkIndex = 0;
    int idleIndex = 0;
    int chargeIndex = 0;
    int deathIndex = 0;
    
    //Arrays for character animations
    GreenfootImage[] walkRight = new GreenfootImage[8];
    GreenfootImage[] walkLeft = new GreenfootImage[8];
    GreenfootImage[] idleRight = new GreenfootImage[5];
    GreenfootImage[] idleLeft = new GreenfootImage[5];
    GreenfootImage[] chargeLeft = new GreenfootImage[4];
    GreenfootImage[] chargeRight = new GreenfootImage[4];
        
    //Timers for animations
    private SimpleTimer walkTimer = new SimpleTimer();
    private SimpleTimer idleTimer = new SimpleTimer();
    private SimpleTimer chargeTimer = new SimpleTimer();
    
    //Booleans that determine which animations should be shown onscreen
    private boolean facingRight = true;
    private boolean isIdle = true;
    private boolean charging = true;
    
    //timer controlling enemy spawning
    int spawnTimer = 2000;
    private SimpleTimer difficultyTimer = new SimpleTimer();
    
    public PlayerChar()
    {
        for (int i = 0; i < 8; i ++)
        {
            walkRight[i] = new GreenfootImage("images/playerChar/walk/walk" + i + ".png");
            walkLeft[i] = new GreenfootImage("images/playerChar/walk/walk" + i + ".png");
            walkLeft[i].mirrorHorizontally();
        }
        for (int i = 0; i < 5; i ++)
        {
            idleRight[i] = new GreenfootImage("images/playerChar/idle/idle" + i + ".png");
            idleLeft[i] = new GreenfootImage("images/playerChar/idle/idle" + i + ".png");
            idleLeft[i].mirrorHorizontally();
        }
        for (int i = 0; i < 4; i ++)
        {
            chargeRight[i] = new GreenfootImage("images/playerChar/chargeUp/charge" + i + ".png");
            chargeLeft[i] = new GreenfootImage("images/playerChar/chargeUp/charge" + i + ".png");
            chargeLeft[i].mirrorHorizontally();
        }
        setImage(walkRight[0]);
        walkTimer.mark();
        idleTimer.mark();
        chargeTimer.mark();
        difficultyTimer.mark();
    }
    
    public void act()
    {
        keyInputs();
        animate();
        difficultyIncrease();
        
        //Adds powerups and enemies to world
        MyWorld world = (MyWorld) getWorld();
        world.spawnEnemies(spawnTimer);
        world.spawnPowerups();
        world.healthPoints.setLocation(getX(), getY() - 20);
        
        //hitbox and playerAttack move with the player and enemies, use player coordinates to follow and track where the player is
        Enemy1.x = getX();
        Enemy1.y = getY();
        Enemy2.x = getX();
        Enemy2.y = getY();
        Hitbox.x = getX();
        Hitbox.y = getY();
        PlayerAttack.x = getX();
        PlayerAttack.y = getY();
    }
    
    /**
     * Controls different keys user can press to control character
     */
    private void keyInputs()
    {
        if (Greenfoot.isKeyDown("space") && !Greenfoot.isKeyDown("s") && !Greenfoot.isKeyDown("a") && !Greenfoot.isKeyDown("w") && !Greenfoot.isKeyDown("d"))
        {
            charging = true;
        }
        if (!Greenfoot.isKeyDown("space"))
        {
            charging = false;
        }
        //checks if character is idle
        if (!Greenfoot.isKeyDown("s") && !Greenfoot.isKeyDown("a") && !Greenfoot.isKeyDown("w") && !Greenfoot.isKeyDown("d"))
        {
            isIdle = true;
        }
        
        if (charging == false)
        {
            if (Greenfoot.isKeyDown("d"))
            {
                move(speed);
                facingRight = true;
                isIdle = false;
            }
            if (Greenfoot.isKeyDown("a"))
            {
                move(speed * -1);
                facingRight = false;
                isIdle = false;
            }
            if(Greenfoot.isKeyDown("w"))
            {
                setLocation(getX(), getY()-speed);
                isIdle = false;
            }
            if(Greenfoot.isKeyDown("s"))
            {
                setLocation(getX(), getY()+speed);
                isIdle = false;
            }
        }
    }
    
    /**
     * Animates character with different sets of animations
     */
    private void animate()
    {
        if(walkTimer.millisElapsed() > 80)
        {
            if (facingRight == true && isIdle == false)
            {
                setImage(walkRight[walkIndex]);
                walkIndex = (walkIndex + 1) % 8;
            }
            else if (facingRight == false && isIdle == false)
            {
                setImage(walkLeft[walkIndex]);
                walkIndex = (walkIndex + 1) % 8;
            }
            walkTimer.mark();
        }
        if (idleTimer.millisElapsed() > 100)
        {
            if (facingRight == true && isIdle == true)
            {
                setImage(idleRight[idleIndex]);
                idleIndex = (idleIndex + 1) % 5;
            }
            if (facingRight == false && isIdle == true)
            {
                setImage(idleLeft[idleIndex]);
                idleIndex = (idleIndex + 1) % 5;
            }
            idleTimer.mark();
        }
        if (chargeTimer.millisElapsed() > 100)
        {
            if (facingRight == true && charging == true)
            {
                setImage(chargeRight[chargeIndex]);
                chargeIndex = (chargeIndex + 1) % 4;
            }
            if (facingRight == false && charging == true)
            {
                setImage(chargeLeft[chargeIndex]);
                chargeIndex = (chargeIndex + 1) % 4;
            }
            chargeTimer.mark();
        }
    }
    
    /**
     * Increases enemy spawnrate every 15 seconds by 200 milliseconds
     */
    private void difficultyIncrease()
    {
        if (difficultyTimer.millisElapsed() > 15000 && spawnTimer >= 800)
        {
            spawnTimer -= 200;
            difficultyTimer.mark();
        }
    }
}
