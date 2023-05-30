package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import com.badlogic.gdx.math.Rectangle;


public class bullet extends Rectangle {



    private double velX;
    private double velY;

    public bullet(double xVel, double yVel){

velX = xVel;
velY= yVel;
    }
    void setVelX(double xVel) {
       velX= xVel ;
    }
    void setVelY(double yVel) {
     velY = yVel;
    }
    public  double getVelX() {
        return velX;
    }
    public double getVelY() {
        return velY;
    }

}
