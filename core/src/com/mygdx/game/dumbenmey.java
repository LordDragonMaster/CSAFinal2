package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class dumbenmey {
private int where1; // this is  the baryer like when it hits this it start to the other dirction
        private int where2;
        private boolean uporright;//true if it is going right and left false for up and down
   Texture img;
   int start ;
   int end ;
    private Rectangle emey = new Rectangle();
    private int emenymovement;
    int yes;
public dumbenmey(int x, int y, int l, int w, int f,Texture look,boolean ur) {
    emey.x = x;
    emey.y = y;
    start = l;
    end= w;
    emenymovement = f;
    img = look;
uporright = ur;
yes =0;
}
public dumbenmey (int x, int y, int l, int w ) {
    emey.x = x;
    emey.y = y;
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
        if (emey.x <= end && yes == 0) {
            emey.x += emenymovement * Gdx.graphics.getDeltaTime();
            // Gdx.app.log("MyTag", "ENMEY IS MOVEING right");


        }
        if (emey.x >= start && yes == 1) {
            emey.x -= emenymovement * Gdx.graphics.getDeltaTime();

            //   Gdx.app.log("MyTag", "ENMEY IS MOVEING left");

            //camera.position.x = player.x;

            if (emey.x > end)
                yes = 1;
            if (emey.x < start)
                yes = 0;
        }
    }
if (uporright == false){
    if (emey.y<=end && yes == 0) {
        emey.y += emenymovement * Gdx.graphics.getDeltaTime();
        //  Gdx.app.log("MyTag", "ENMEY IS MOVEING up");


    }  if (emey.y>=start&& yes==1 ) {
        emey.y -= emenymovement * Gdx.graphics.getDeltaTime();

        //  Gdx.app.log("MyTag", "ENMEY IS MOVEING down");

        //camera.position.x = player.x;
    }
    if (emey.y>end)
        yes =1;
    if (emey.y<start)
        yes=0;}
}
/*    public  void restart(){

if (uporright==false){
    emey.y=

}

    }
    */
public float posx(){
    return emey.x;

}
    public float posy(){
        return emey.y;

    }


}