package xyz.zzzxb.paino.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;

import xyz.zzzxb.paino.audio.Paino;
import xyz.zzzxb.paino.screen.view.GameView;
import xyz.zzzxb.paino.PainoGame;

public class GameScreen implements Screen {
    private Stage stage;
    private InputMultiplexer inputMultiplexer ;

    private InputAdapter keyAdapter = new InputAdapter() {
        @Override
        public boolean keyUp(int keycode) {
            return super.keyDown(keycode);
        }

        @Override
        public boolean keyTyped(char character) {
            int key  = character;
            if (character == Character.toUpperCase(character)){
                Paino.playSound("a"+key+".mp3");
            }else {
                key -= 65;
                Paino.playSound("b"+key+".mp3");
            }
            return super.keyTyped(character);
        }
    };

    public GameScreen(){
        inputMultiplexer = new InputMultiplexer();
    }

    @Override
    public void show() {
        stage = new Stage(PainoGame.viewport, PainoGame.batch);
        GameView view = new GameView();
        stage.addActor(view);
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(keyAdapter);
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
