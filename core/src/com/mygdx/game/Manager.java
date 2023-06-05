package com.mygdx.game;
import com.badlogic.gdx.Game;


//XG: this is the class we will be using to manage
// XG: different screens, game overs, and other stuff like that.
public class Manager extends Game{
    private LoadingScreen loadingScreen;
    private MenuScreen menuScreen;
    private MainScreen mainScreen;
    private EndScreen endScreen;
    private CSAGame csaScreen;
    private ShopScreen shopScreen;
    private int points;
    private int damageInc;
    private int maxHealthInc;
    private boolean refillHealth;
    private int speedInc;
    private int dashInc;
    private int ammoBought;


    public final static int MENU = 0;
    public final static int APPLICATION = 1;
    public final static int CSAGAME = 2;
    public final static int ENDGAME = 3;
    public final static int SHOP = 4;
    @Override
    public void create() {

            loadingScreen = new LoadingScreen(this);
            setScreen(loadingScreen);

    }
    public void setPoints(int p){
        points=p;
    }
    public int getPoints(){
        return points;
    }
    public void setDamageIncupgradeDamage(int d){
        damageInc+=d;
    }
    public void setMaxHealthInc(int d){
        maxHealthInc+=d;
    }
    public void setRefillHealth(boolean t){
       refillHealth=t;
    }
    public void upgradeDamage(int d){
        damageInc+=d;
    }
    //XG: need a 'clear' method
    public void changeScreen(int screen){
        switch(screen){
            case MENU:
                if(menuScreen == null) menuScreen = new MenuScreen(this); // added (this)
                this.setScreen(menuScreen);
                break;
            case APPLICATION:
                if(mainScreen == null) mainScreen = new MainScreen(this); //added (this)
                this.menuScreen.pause();
                this.setScreen(mainScreen);
                break;
            case ENDGAME:
                if(endScreen == null) endScreen = new EndScreen(this);  // added (this)
                this.setScreen(endScreen);
                break;
           case CSAGAME:
                if(csaScreen == null) csaScreen = new CSAGame(this);  // added (this)
                this.setScreen(csaScreen);
                break;
            case SHOP:
                shopScreen = new ShopScreen(this);  // added (this)
                this.setScreen(shopScreen);
                break;

        }
    }
}
