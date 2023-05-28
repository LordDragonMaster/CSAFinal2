package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class dumbEnemy {
private int where1; // this is  the baryer like when it hits this it start to the other dirction
        private int where2;
        private boolean uporright;//true if it is going right and left false for up and down
   Texture img;
   int start ;
   int end ;
    private Rectangle enemy = new Rectangle();
    private int enemyMovement;
    int yes;
public dumbEnemy(int x, int y, int l, int w, int f, Texture look, boolean ur) {
    enemy.x = x;
    enemy.y = y;
    start = l;
    end= w;
    enemyMovement = f;
    img = look;
uporright = ur;
yes =0;
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

public void move() {
    if (uporright == true) {
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
if (uporright == false){
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
/*    public  void restart(){

if (uporright==false){
    enemy.y=

}

    }
    */
public float posx(){
    return enemy.x;

}
    public float posy(){
        return enemy.y;

    }


}