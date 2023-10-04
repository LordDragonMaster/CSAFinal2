package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.util.Objects;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class dumbEnemy {
   int start ;
   int end ;
   private Rectangle enemy = new Rectangle();
    private int enemyMovement;
     int health;
    int yes;
    float xOrigin;
    float yOrigin;
    boolean alive;
    String type = "walk";
public dumbEnemy(float x, float y, int f, int h, int w, int l, String t) {
    enemy.x = x;
    enemy.y = y;
    enemy.height = l;
 enemy.width = w;
    enemyMovement = f;

yes =0;
// replace the following with "enemy width/2" and "enemy height/2" respectively.
     xOrigin = enemy.width/2;
    yOrigin = enemy.height/2;
    //replace the following with an input from the constructor
    alive = true;
    health = h;
    type=t;
}



public void attack(float x,float y){
    if (alive) {
        Vector3 playerPos = new Vector3();
        playerPos.set(x, y, 0);

        double angle = Math.atan2(y - enemy.y - yOrigin, x - enemy.x - xOrigin);
        double enemyVelX = enemyMovement * cos(angle);
        double enemyVelY = enemyMovement * sin(angle);
        //fire(enemyVelX, enemyVelY);dddd
        enemy.y += enemyVelY * Gdx.graphics.getDeltaTime();
        enemy.x += enemyVelX * Gdx.graphics.getDeltaTime();
    /*if(Objects.equals(type, "Shoot")){

    }*/
    }
}
    public Rectangle getRectangle(int damage) {
    return enemy;
    }

public void damage(int damage){
    health -=damage;
    if (health <= 0){
        alive = false;
    }
}
public void die(){

}

public float posx(){
    return enemy.x;

}
    public float posy(){
        return enemy.y;

    }
    public Rectangle getEnemy(){
        return enemy;

    }



    public void dispose() {


    }
}