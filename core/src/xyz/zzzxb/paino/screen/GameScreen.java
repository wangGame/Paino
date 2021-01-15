package xyz.zzzxb.paino.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;

import xyz.zzzxb.paino.screen.view.GameView;
import xyz.zzzxb.paino.PainoGame;

public class GameScreen implements Screen {
    private Stage stage;
    private InputMultiplexer inputMultiplexer ;

    public GameScreen(){
        inputMultiplexer = new InputMultiplexer();
    }

    @Override
    public void show() {
        stage = new Stage(PainoGame.viewport, PainoGame.batch);
        GameView view = new GameView();
        stage.addActor(view);
        inputMultiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void render(float delta) {
        stage.act(delta);
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
