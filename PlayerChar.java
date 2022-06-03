import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Player controlled character with walking and idle animations
 * Sprites from Penusbmic on itch.io
 * 
 * @author Alex V. 
 * @version May 30th, 2022
 */
public class PlayerChar extends Actor
{
    /**
     * Act - do whatever the playerChar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int speed = 2;
    int walkIndex = 0;
    int idleIndex = 0;
    int chargeIndex = 0;
    
    //Arrays for character animations
    GreenfootImage[] walkRight = new GreenfootImage[8];
    GreenfootImage[] walkLeft = new GreenfootImage[8];
    GreenfootImage[] idleRight = new GreenfootImage[5];
    GreenfootImage[] idleLeft = new GreenfootImage[5];
    GreenfootImage[] chargeLeft = new GreenfootImage[4];
    GreenfootImage[] chargeRight = new GreenfootImage[4];
    
    private SimpleTimer walkTimer = new SimpleTimer();
    private SimpleTimer idleTimer = new SimpleTimer();
    private SimpleTimer chargeTimer = new SimpleTimer();
    private boolean facingRight = true;
    private boolean isIdle = true;
    private boolean charging = true;
    
    
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
    }
    
    public void act()
    {
        // Add your action code here.
        keyInputs();
        animate();
        createEnemies();
        
        Enemy1.x = getX();
        Enemy1.y = getY();
        
        Hitbox.x = getX();
        Hitbox.y = getY();
        
        PlayerAttack.x = getX();
        PlayerAttack.y = getY();
    }
    
    //Controls different keys user can press to control character
    public void keyInputs()
    {
        if (Greenfoot.isKeyDown("d") && charging == false)
        {
            move(speed);
            facingRight = true;
            isIdle = false;
        }
        if (Greenfoot.isKeyDown("a") && charging == false)
        {
            move(speed * -1);
            facingRight = false;
            isIdle = false;
        }
        if(Greenfoot.isKeyDown("w") && charging == false)
        {
            setLocation(getX(), getY()-speed);
            isIdle = false;
        }
        if(Greenfoot.isKeyDown("s") && charging == false)
        {
            setLocation(getX(), getY()+speed);
            isIdle = false;
        }
        if (Greenfoot.isKeyDown("space"))
        {
            charging = true;
        }
        if (!Greenfoot.isKeyDown("space"))
        {
            charging = false;
        }
        if (!Greenfoot.isKeyDown("s") && !Greenfoot.isKeyDown("a") && !Greenfoot.isKeyDown("w") && !Greenfoot.isKeyDown("d"))
        {
            isIdle = true;
        }
    }
    
    //Animates character with different sets of animations
    public void animate()
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
    
    public void createEnemies()
    {
        MyWorld world = (MyWorld) getWorld();
        world.spawnEnemies();
    }
}
