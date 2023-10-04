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

public class ShopScreen implements Screen {
    private Manager parent; // a field to store our orchestrator

    Stage stage = new Stage(new ScreenViewport());


    public ShopScreen(Manager manager){
        parent = manager;

        Gdx.input.setInputProcessor(stage);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

    }
    BitmapFont textFont = new BitmapFont(Gdx.files.internal("pixthuluSkin/font-export.fnt"));
    Color titleColor=new Color(0,0,100,1);
    Label.LabelStyle textStyle = new Label.LabelStyle(textFont, titleColor);
    Label textPoints = new Label("Points: ", textStyle);
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

        TextButton startGame = new TextButton("Back to Game", skin);
        TextButton exit = new TextButton("Exit Game", skin);
        Label.LabelStyle titleStyle = new Label.LabelStyle(titleFont, titleColor);

        Label menuName = new Label("Shop",  titleStyle);


        TextButton buyHealth= new TextButton("Refill Health (100)",skin);
        TextButton buyAmmo= new TextButton("Out Of Order",skin);
        TextButton upgradeMaxHealth= new TextButton("Increase Max Health(1000)",skin);
        TextButton upgradeDamage= new TextButton("Increase Damage(700)",skin);
        TextButton upgradeSpeed= new TextButton("Increase Speed(200)",skin);
        TextButton upgradeDash= new TextButton("Upgrade Dash(500)",skin);
//XG: adds a new button, called 'newGame', to the table.
        table.add(menuName).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.add(textPoints).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.add(startGame).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.row();
        table.add(buyHealth).fillX().uniformX();
        table.add(buyAmmo).fillX().uniformX();
        table.row();
        table.add(upgradeDash).fillX().uniformX();
        table.add(upgradeSpeed).fillX().uniformX();
        table.row();
        table.add(upgradeDamage).fillX().uniformX();
        table.add(upgradeMaxHealth).fillX().uniformX();
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
                stage.clear();


            }
        });
        buyHealth.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.setRefillHealth();
            }
        });
        upgradeDamage.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.upgradeDamage();
            }
        });
        upgradeMaxHealth.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.upgradeHealthMax();
            }
        });
        upgradeSpeed.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.upgradeMoveSpeed();
            }
        });
        upgradeDash.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.upgradeDash();
            }
        });
    }





    @Override
    public void render(float delta) {
        textPoints.setText("Points: "+parent.getPoints());
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
