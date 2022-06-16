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
    boolean chargedUp = false;

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
        
        //destroys enemies touching it when the attack has been released
        if(released)
        {
            destroy();
        }
        
        //blocks bullets while in charging state
        if (isTouching(Bullet.class) && chargedUp == true && released == false)
        {
            removeTouching(Bullet.class);
        }
    }

    /**
     * Animates the attack charging up and releasing
     */
    private void animate()
    {
        //while the spacebar is held and while the player is not moving, the attack charges up
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
            chargedUp = true;
            chargeUpTimer.mark();
        }
        //while the spacebar is not down, sets image to a blank image so it is invisible to players
        if (!Greenfoot.isKeyDown("space") && chargedUp == false)
        {
            setImage("images/blank.png");
            chargeIndex = 0;
        }
        
        //when the attack is fully charged and the spacebar released, it releases a wave of energy
        if (chargedUp == true && !Greenfoot.isKeyDown("space"))
        {
            if (releaseTimer.millisElapsed() > 40 && releaseIndex < 4)
            {
                if (releaseIndex == 0)
                {
                    GreenfootSound releasedSound = new GreenfootSound("sounds/attackReleased.wav");
                    releasedSound.setVolume(65);
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
            chargedUp = false;
            released = false;
            releaseIndex = 0;
            chargeIndex = 0;
        }
    }

    /**
     * Executes die function for either enemy if hit by the attack and increases score
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
