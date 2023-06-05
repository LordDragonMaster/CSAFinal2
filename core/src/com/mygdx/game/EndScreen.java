package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class EndScreen implements Screen{
   int points;
    private Manager parent; // a field to store our orchestrator
    Stage stage = new Stage(new ScreenViewport());
    // our constructor
    public EndScreen(Manager manager){
        parent = manager;     // setting the argument to our field.



            Gdx.input.setInputProcessor(stage);

            stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
            stage.draw();
    }

    @Override
    public void show() {
        points = parent.getPoints();
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);


        stage.addActor(table);
        //XG: creates a skin using the imported assets.
        Skin skin = new Skin(Gdx.files.internal("pixthuluSkin/pixthulhu-ui.json"));

        BitmapFont titleFont = new BitmapFont(Gdx.files.internal("pixthuluSkin/font-title-export.fnt"));
        Color titleColor=new Color(100,0,0,1);
        BitmapFont textFont = new BitmapFont(Gdx.files.internal("pixthuluSkin/font-export.fnt"));
        Label.LabelStyle textStyle = new Label.LabelStyle(textFont, titleColor);
        TextButton exit = new TextButton("Exit", skin);
        Label.LabelStyle titleStyle = new Label.LabelStyle(titleFont, titleColor);
        Label gameName = new Label("You Lost!",  titleStyle);
        Label exitWords = new Label("Exit the game and restart to play again.",  textStyle);
        Label pointsNumber = new Label("Final Score: " +points,  titleStyle);

//XG: adds a new button, called 'newGame', to the table.
        table.add(gameName).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.add(exitWords).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.row();
        table.add(pointsNumber).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.row();
        table.add(exit).fillX().uniformX();
        exit.addListener(new ChangeListener() {
            //XG: Closes the game
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
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
