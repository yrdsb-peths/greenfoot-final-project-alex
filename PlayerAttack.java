import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * The player can hold the spacebar to charge up an energy attack and release the key to unleash energy that destroys enemies
 * 
 * @author Alex V.
 * @version 6.06.2022
 */
public class PlayerAttack extends Actor
{
    /**
     * Act - do whatever the PlayerAttack wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
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
        if(released){
            destroy();
        }
    }

    public void animate()
    {
        //while the spacebar is held the attack charges up
        if (chargeUpTimer.millisElapsed() > 50 && Greenfoot.isKeyDown("space") && chargeIndex < 6)
        {
            setImage(charge[chargeIndex]);
            chargeIndex = chargeIndex + 1;
            chargeUpTimer.mark();
        }

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

        if (!Greenfoot.isKeyDown("space") && isCharging == false)
        {
            setImage("images/attack0.png");
            chargeIndex = 0;
        }
    }

    //After holding the spacebar to charge up the attack, players can release the button to unleash a wave of energy which damages enemies
    public void release()
    {
        if (isCharging == true && !Greenfoot.isKeyDown("space"))
        {
            if (releaseTimer.millisElapsed() > 40 && releaseIndex < 4 && chargeIndex == 6 || chargeIndex == 7)
            {
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

    public void destroy()
    {
        if (isTouching(Enemy1.class))
        {
            List<Enemy1> enemies =  getObjectsInRange(100, Enemy1.class);
            for(int i = 0; i < enemies.size(); i++){
                enemies.get(i).die();
            }
            
            //removeTouching(Enemy1.class);
            MyWorld world = (MyWorld) getWorld();
            
            world.scoreUp(1);
        }       
    }
}
