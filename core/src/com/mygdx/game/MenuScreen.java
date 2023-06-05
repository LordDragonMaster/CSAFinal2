package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import com.badlogic.gdx.graphics.*;

public class MenuScreen implements Screen {
    private Manager parent; // a field to store our orchestrator

    Stage stage = new Stage(new ScreenViewport());

    // our constructor with a Box2DTutorial argument
    public MenuScreen(Manager manager){
        parent = manager;     // setting the argument to our field.

        Gdx.input.setInputProcessor(stage);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();






/*In the code below we use “table.add(newGame).fillX().uniform();” This adds our first
button to the table and sets it to fill out on the x axis (horizontally) and make it
uniform with other cells in this column (currently none). So now we have a table with
a single cell containing a button and nothing else. Next we add “table.row().pad(10,0,10,0);”
 This mean the next table.add() will add their items to the next row instead of suffixing them
  to the current row. We also set the padding of the new row to 10 pixels top, 0 left, 10 bottom and 0  right.
  Next, we add our second button similar to the first button and again add a new row, this time with no
   padding as our previous row is already padding the bottom 10 pixels and finally, we add our last button.
 */


    }
    @Override
    public void show() {


//XG: Creates a table covering the screen. We will put all of our buttons in this table.
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);

        stage.addActor(table);
        //XG: creates a skin using the imported assets.
        Skin skin = new Skin(Gdx.files.internal("pixthuluSkin/pixthulhu-ui.json"));

        BitmapFont titleFont = new BitmapFont(Gdx.files.internal("pixthuluSkin/font-title-export.fnt"));
       Color titleColor=new Color(100,0,0,1);
        TextButton startGame = new TextButton("Start Game", skin);
        TextButton exit = new TextButton("Exit", skin);
        Label.LabelStyle titleStyle = new Label.LabelStyle(titleFont, titleColor);
        Label gameName = new Label("Bug Destroyer",  titleStyle);
//XG: adds a new button, called 'newGame', to the table.
        table.add(gameName).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.add(startGame).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.row();
        table.add(exit).fillX().uniformX();

        //XG: Causes the buttons to have an effect when pressed.
        exit.addListener(new ChangeListener() {
            //XG: Closes the game
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        startGame.addListener(new ChangeListener() {
            //XG: Sets the screen to the application.
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                parent.changeScreen(Manager.CSAGAME);
                dispose();


            }
        });


    }





    @Override
    public void render(float delta) {

            Gdx.gl.glClearColor(0f, 0f, 0f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
            stage.draw();

    }

    @Override
    public void resize(int width, int height) {

        stage.getViewport().update(width, height, true);
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
        stage.dispose();
    }
}
