package com.mygdx.game;
import com.badlogic.gdx.Game;


//XG: this is the class we will be using to manage
// XG: different screens, game overs, and other stuff like that.
public class Manager extends Game{
    private LoadingScreen loadingScreen;
    private MenuScreen menuScreen;
    private Tutorial mainScreen;
    private EndScreen endScreen;
    private CSAGame csaScreen;
    private ShopScreen shopScreen;
    private AppPreferences preferences;
    private Preferences preferencesScreen;
    private int points= 0;
    private int healthMax= 5;
    private boolean refillHealth;
    private int ammoBought;
    private int moveSpeed = 100;
    private int damage = 5;
    private int bulletSpeed = 400;

    private int dashSpeed = 250;

    private int dashCooldown = 1200;


    public final static int MENU = 0;
    public final static int TUTORIAL = 1;
    public final static int CSAGAME = 2;
    public final static int ENDGAME = 3;
    public final static int SHOP = 4;
    public final static int PREFERENCES = 5;

    @Override
    public void create() {
        preferences = new AppPreferences();

            loadingScreen = new LoadingScreen(this);
            setScreen(loadingScreen);

    }
    public void setPoints(int p){
        points=p;
    }
    public int getPoints(){
        return points;
    }
    public void upgradeHealthMax(){
        if(points>=1000) {
            healthMax += 1;
            points-=1000;
        }
    }
    public int getHealthMax(){
        return healthMax;
    }
    public void upgradeMoveSpeed(){
        if(points>=200) {
        moveSpeed+=15;
        points-=200;
        }
    }
    public int getMoveSpeed(){
        return moveSpeed;
    }

    public void upgradeDash(){
        if(points>=500) {
            dashSpeed += 50;
            if (dashCooldown > 900) {
                dashCooldown -= 50;
            }
            points-=500;
        }
    }
    public int getDashSpeed(){
        return dashSpeed;
    }
    public int getDashCooldown(){
        return dashCooldown;
    }
    public void setRefillHealth(){
        if(points>=100) {

            refillHealth = true;
            points-=100;
        }
    }
    public boolean getRefillHealth(){
        if(refillHealth){
            refillHealth=false;
            return true;
        }
        else return false;
    }
    public void upgradeDamage(){
        if(points>=700) {
            damage += 1;
            points-=700;
        }
    }
    public int getDamage(){
        return damage;
    }
    //XG: need a 'clear' method
    public void changeScreen(int screen){
        switch(screen){
            case MENU:
                if(menuScreen == null) menuScreen = new MenuScreen(this); // added (this)
                this.setScreen(menuScreen);
                break;
            case TUTORIAL:
                if(mainScreen == null) mainScreen = new Tutorial(this); //added (this)
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
                if(shopScreen == null) shopScreen = new ShopScreen(this);  // added (this)
                this.setScreen(shopScreen);
                break;
            case PREFERENCES:
                if(preferencesScreen == null)preferencesScreen = new Preferences(this);  // added (this)
                this.setScreen(preferencesScreen);
                break;

        }
    }

    public AppPreferences getPreferences() {
        return this.preferences;
    }
    }


