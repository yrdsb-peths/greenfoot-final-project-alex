import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Players can hold the spacebar to charge up the attack
 * When the key is released the attack releases, destroying enemies in its radius
 * 
 * @author Alex V.
 * @version 6.6.2022
 */
public class PlayerAttack extends Actor
{
    int chargeIndex = 0;
    int releaseIndex = 0;

    GreenfootImage[] charge = new GreenfootImage[8];
    GreenfootImage[] release = new GreenfootImage[4];

    private SimpleTimer chargeUpTimer = new SimpleTimer();
    private SimpleTimer releaseTimer = new SimpleTimer();
    SimpleTimer deathDelay = new SimpleTimer();

    public static int x;
    public static int y;
    boolean isCharging = false;

    public static boolean released = false;    

    public PlayerAttack()
    {
        for (int i = 0; i < 8; i ++)
        {
            charge[i] = new GreenfootImage("images/attack/attack" + i + ".png");
        }
        for (int i = 0; i < 4; i ++)
        {
            release[i] = new GreenfootImage("images/attack/attackrelease" + i + ".png");
        }
        chargeUpTimer.mark();
        releaseTimer.mark();
        deathDelay.mark();
        
    }

    public void act()
    {
        // Add your action code here.
        animate();
        setLocation(x,y);
        release();
        
        //destroys enemies touching it when the attack has been released
        if(released){
            destroy();
        }
        
        //blocks bullets while in charging state
        if (isTouching(Bullet.class) && isCharging == true && released == false)
        {
            removeTouching(Bullet.class);
        }
    }

    /**
     * Animates the attack charging up
     */
    private void animate()
    {
        //while the spacebar is held the attack charges up
        if (chargeUpTimer.millisElapsed() > 65 && Greenfoot.isKeyDown("space") && chargeIndex < 6 && !Greenfoot.isKeyDown("s") 
        && !Greenfoot.isKeyDown("a") && !Greenfoot.isKeyDown("w") && !Greenfoot.isKeyDown("d"))
        {
            if (chargeIndex == 0)
            {
                GreenfootSound chargeSound = new GreenfootSound("sounds/chargeUp.wav");
                chargeSound.setVolume(80);
                chargeSound.play();
            }
            setImage(charge[chargeIndex]);
            chargeIndex = chargeIndex + 1;
            chargeUpTimer.mark();
        }
        //when fully charged the attack alternates between its last two frames
        if (chargeUpTimer.millisElapsed() > 130 && Greenfoot.isKeyDown("space") && chargeIndex == 6 || chargeIndex == 7)
        {
            setImage(charge[chargeIndex]);
            if (chargeIndex == 6)
            {
                chargeIndex = 7;
            }
            else 
            {
                chargeIndex = 6;
            }
            isCharging = true;
            chargeUpTimer.mark();
        }
        //otherwise, while the space key is not held, the image is set to a small circle, not visible to players
        if (!Greenfoot.isKeyDown("space") && isCharging == false)
        {
            setImage("images/blank.png");
            chargeIndex = 0;
        }
    }

    /**
     * After holding the spacebar to charge up the attack, players can release the button to unleash a wave of energy which damages enemies
     */
    private void release()
    {
        if (isCharging == true && !Greenfoot.isKeyDown("space"))
        {
            if (releaseTimer.millisElapsed() > 40 && releaseIndex < 4 && chargeIndex == 6 || chargeIndex == 7)
            {
                if (releaseIndex == 0)
                {
                    GreenfootSound releasedSound = new GreenfootSound("sounds/attackReleased.wav");
                    releasedSound.setVolume(70);
                    releasedSound.play();
                }
                setImage(release[releaseIndex]);
                releaseIndex += 1;
                released = true;
                releaseTimer.mark();
            }
        }
        if (releaseIndex == 4)
        {
            isCharging = false;
            released = false;
            releaseIndex = 0;
            chargeIndex = 0;
        }
    }

    /**
     * Executes die function for either enemy if hit by the attack, increasing score
     */
    private void destroy()
    {
        if (isTouching(Enemy1.class))
        {
            List<Enemy1> enemies =  getIntersectingObjects(Enemy1.class);
            for(int i = 0; i < enemies.size(); i++){
                enemies.get(i).die();
                MyWorld world = (MyWorld) getWorld();
                world.scoreUp(1);
            }
        } 
        if (isTouching(Enemy2.class))
        {
            List<Enemy2> enemies =  getIntersectingObjects(Enemy2.class);
            for(int i = 0; i < enemies.size(); i++){
                enemies.get(i).die();
                MyWorld world = (MyWorld) getWorld();
                world.scoreUp(1);
            }
        } 
    }
}
