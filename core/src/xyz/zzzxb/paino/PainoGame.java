package xyz.zzzxb.paino;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import xyz.zzzxb.paino.asset.MusicDemo;
import xyz.zzzxb.paino.constant.Constant;
import xyz.zzzxb.paino.screen.GameScreen;

public class PainoGame extends Game {
    public static Viewport viewport ;
    public static Batch batch;

    @Override
    public void create() {
        viewport = new ExtendViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch = new SpriteBatch();
        resize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        MusicDemo music = new MusicDemo();
        music.loadMusic();
        Constant.alltime = music.getAllTime();
        setScreen(new GameScreen());
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.860f, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render();
    }

    @Override
    public void dispose() {

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewport.update(width,height);
        viewport.apply();
        Constant.width = viewport.getWorldWidth();
        Constant.hight = viewport.getScreenHeight();
    }
}
