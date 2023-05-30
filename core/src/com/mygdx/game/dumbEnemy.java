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
private int where1; // this is  the baryer like when it hits this it start to the other dirction
        private int where2;
        private boolean uporright;//true if it is going right and left false for up and down
   Texture img;
   int start ;
   int end ;
    private Rectangle enemy = new Rectangle();
    private int enemyMovement;
     int health;
    int yes;
    float xOrigin;
    float yOrigin;
    String type = "walk";
    //bullet bullet = (com.mygdx.game.bullet) iter.next();
public dumbEnemy(int x, int y, int l, int w, int f, Texture look, boolean ur) {
    enemy.x = x;
    enemy.y = y;
    start = l;
    end= w;
    enemyMovement = f;
    img = look;
uporright = ur;
yes =0;
// replace the following with "enemy width/2" and "enemy height/2" respectively.
     xOrigin = 16;
    yOrigin = 16;
    //replace the following with an input from the constructor
    health = 5;
}
public dumbEnemy(int x, int y, int l, int w ) {
    enemy.x = x;
    enemy.y = y;
    start = l;
    end= w;
    img = new Texture("badlogic.jpg");
    yes =0;

}
public Texture pic(){

    return img;
}
public void attack(float x,float y){
    Vector3 playerPos = new Vector3();
    playerPos.set(x, y, 0);

    double angle = Math.atan2(y - enemy.y -yOrigin,x - enemy.x- xOrigin);
    double enemyVelX= enemyMovement * cos(angle);
    double enemyVelY = enemyMovement * sin(angle);
    //fire(enemyVelX, enemyVelY);dddd
    enemy.y += enemyVelY* Gdx.graphics.getDeltaTime();
    enemy.x += enemyVelX* Gdx.graphics.getDeltaTime();
    /*if(Objects.equals(type, "Shoot")){

    }*/
}
public void damage(int damage){
    health =-damage;
}
public void move() {
    if (uporright) {
        if (enemy.x <= end && yes == 0) {
            enemy.x += enemyMovement * Gdx.graphics.getDeltaTime();
            // Gdx.app.log("MyTag", "ENMEY IS MOVEING right");


        }
        if (enemy.x >= start && yes == 1) {
            enemy.x -= enemyMovement * Gdx.graphics.getDeltaTime();

            //   Gdx.app.log("MyTag", "ENMEY IS MOVEING left");

            //camera.position.x = player.x;

            if (enemy.x > end)
                yes = 1;
            if (enemy.x < start)
                yes = 0;
        }
    }
if (!uporright) {
    if (enemy.y<=end && yes == 0) {
        enemy.y += enemyMovement * Gdx.graphics.getDeltaTime();
        //  Gdx.app.log("MyTag", "ENMEY IS MOVEING up");


    }  if (enemy.y>=start&& yes==1 ) {
        enemy.y -= enemyMovement * Gdx.graphics.getDeltaTime();

        //  Gdx.app.log("MyTag", "ENMEY IS MOVEING down");

        //camera.position.x = player.x;
    }
    if (enemy.y>end)
        yes =1;
    if (enemy.y<start)
        yes=0;}
}


public float posx(){
    return enemy.x;

}
    public float posy(){
        return enemy.y;

    }


}