package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class dumbenmey {
private int where1; // this is  the baryer like when it hits this it start to the other dirction
        private int where2;
        private boolean uporright;//true if it is going right and left false for up and down
   Texture img;
    private Rectangle emey = new Rectangle();
    private int emenymovement;
public dumbenmey(int x, int y, int l, int w, int f,Texture look,boolean ur) {
    emey.x = x;
    emey.y = y;
    emey.height = l;
    emey.width = w;
    emenymovement = f;
    img = look;
uporright = ur;

}
public dumbenmey (int x, int y, int l, int w ) {
    emey.x = x;
    emey.y = y;
    emey.height = l;
    emey.width = w;
    img = new Texture("badlogic.jpg");


}
public Texture pic(){

    return img;
}

public void move() {

            if (emey.x>300) {
                emey.x -= emenymovement * Gdx.graphics.getDeltaTime();
                Gdx.app.log("MyTag", "ENMEY IS MOVEING left");
            } else if (emey.x>200) {

                    emey.x += emenymovement * Gdx.graphics.getDeltaTime();
                    Gdx.app.log("MyTag", "ENMEY IS MOVEING right");
                    //camera.position.x = player.x;
                }

            }
public float posx(){
    return emey.x;

}
    public float posy(){
        return emey.y;

    }


}