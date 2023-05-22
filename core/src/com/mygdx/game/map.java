package com.mygdx.game;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class map extends ApplicationAdapter{
    private int wherx;
    private int whery;
    private int sizex;
    private int sizey;

    public  map(int x,int y, int z, int w){
         wherx =z;
         whery =w;
         sizex=x;
        sizey=y;
}
public  map(int x,int y){
    wherx =100;
    whery =100;
    sizex=x;
    sizey=y;
        // return new Rectangle(x,y,100,100);
}
public Rectangle create2(){
   return new Rectangle(sizex,sizey,wherx,whery);

}



}
