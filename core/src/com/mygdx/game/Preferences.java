package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Preferences implements Screen {
    private Manager parent;
    private Stage stage;
    // our new fields
    private Label titleLabel;
    private Label volumeMusicLabel;
    private Label volumeSoundLabel;
    private Label musicOnOffLabel;
    private Label soundOnOffLabel;
    public Preferences(Manager manager) {
        stage = new Stage(new ScreenViewport());
        parent = manager;     // setting the argument to our field.
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);

        stage.addActor(table);
        //XG: creates a skin using the imported assets.
        Skin skin = new Skin(Gdx.files.internal("pixthuluSkin/pixthulhu-ui.json"));

//volume
        final Slider volumeMusicSlider = new Slider( 0f, 1f, 0.1f,false, skin );
        volumeMusicSlider.setValue( parent.getPreferences().getMusicVolume() );
        volumeMusicSlider.addListener( new EventListener() {
            @Override
            public boolean handle(Event event) {
                parent.getPreferences().setMusicVolume( volumeMusicSlider.getValue() );
                return false;
            }
        });
        final Slider volumeSoundSlider = new Slider( 0f, 1f, 0.1f,false, skin );
        volumeSoundSlider.setValue( parent.getPreferences().getSoundVolume() );
        volumeSoundSlider.addListener( new EventListener() {
            @Override
            public boolean handle(Event event) {
                parent.getPreferences().setSoundVolume( volumeSoundSlider.getValue() );
                return false;
            }
        });
        //music
        final CheckBox musicCheckbox = new CheckBox(null, skin);
        musicCheckbox.setChecked( parent.getPreferences().isMusicEnabled() );
        musicCheckbox.addListener( new EventListener() {
            @Override
            public boolean handle(Event event) {
                boolean enabled = musicCheckbox.isChecked();
                parent.getPreferences().setMusicEnabled( enabled );
                return false;
            }
        });
        //sound
        final CheckBox soundCheckbox = new CheckBox(null, skin);
        soundCheckbox.setChecked( parent.getPreferences().isSoundEffectsEnabled() );
        soundCheckbox.addListener( new EventListener() {
            @Override
            public boolean handle(Event event) {
                boolean enabled = soundCheckbox.isChecked();
                parent.getPreferences().setSoundEffectsEnabled( enabled );
                return false;
            }
        });

// return to main screen button
        final TextButton backButton = new TextButton("Back", skin); // the extra argument here "small" is used to set the button to the smaller version instead of the big default version
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                stage.clear();
                parent.changeScreen(Manager.MENU);
            }
        });

        titleLabel = new Label( "Preferences", skin );
        volumeMusicLabel = new Label("Music Volume", skin );
        volumeSoundLabel = new Label( "Sound Volume", skin );
        musicOnOffLabel = new Label("Music Toggle", skin );
        soundOnOffLabel = new Label("Sound Toggle", skin );

        table.add(titleLabel).colspan(2);
        table.row().pad(10,0,10,0);
        table.add(volumeMusicLabel).left();
        table.add(volumeMusicSlider).size(300f,30f);
        table.row();
        table.add(musicOnOffLabel).left();
        table.add(musicCheckbox);
        table.row();
        table.add(volumeSoundLabel).left();
        table.add(volumeSoundSlider).size(300f,30f);
        table.row();
        table.add(soundOnOffLabel).left();
        table.add(soundCheckbox);
        table.row();
        table.add(backButton).colspan(2);

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int i, int i1) {
        stage.getViewport().update(i, i1, true);
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
