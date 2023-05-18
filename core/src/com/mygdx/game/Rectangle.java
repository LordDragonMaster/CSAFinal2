package com.mygdx.game;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;


public class Rectangle extends ApplicationAdapter{
public Rectangle wall(int x,int y, int z, int w){
    return new Rectangle(x,y,z,w);
}
public Rectangle wall(int x,int y){
    return new Rectangle(x,y,100,100);
}



}
