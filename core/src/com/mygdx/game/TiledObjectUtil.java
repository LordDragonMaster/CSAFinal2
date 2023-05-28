package com.mygdx.game;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class TiledObjectUtil {
    public static void parseTiledObjectLayer(MapObjects objects) {
        for (MapObject object : objects) {
            if (object instanceof RectangleMapObject) {

            }
        }
    }


    public boolean collisionCheck(float x, float y, Rectangle checker, MapObjects walls) {
        checker.y = y;
        checker.x = x;

        for (RectangleMapObject rectangleObject : walls.getByType(RectangleMapObject.class)) {

            Rectangle wall = rectangleObject.getRectangle();
            if (Intersector.overlaps(wall, checker)) {
                return true;
                //insert collision here
            }
        }
        return false;
    }
}