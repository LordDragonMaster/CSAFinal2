package com.mygdx.game;
import com.badlogic.gdx.Screen;

public class LoadingScreen implements Screen{

    private Manager parent; // a field to store our orchestrator

    // our constructor with a Box2DTutorial argument
    public LoadingScreen(Manager manager){
        parent = manager;     // setting the argument to our field.
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        parent.changeScreen(Manager.MENU);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
