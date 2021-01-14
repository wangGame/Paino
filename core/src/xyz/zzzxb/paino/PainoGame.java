package xyz.zzzxb.paino;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.CpuSpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import xyz.zzzxb.paino.asset.Asset;
import xyz.zzzxb.paino.asset.MusicDemo;
import xyz.zzzxb.paino.constant.Constant;
import xyz.zzzxb.paino.screen.GameScreen;

public class PainoGame extends Game {
    private Texture texture;
    private Sprite sprite;
    public static SpriteBatch batch;
    public static boolean runGame;
    public static Viewport viewport ;

    @Override
    public void create() {
        viewport = new ExtendViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        resize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        texture = new Texture(Gdx.files.internal("title.png"));
        sprite = new Sprite(texture);
        batch = new CpuSpriteBatch();
        runGame = true;
        MusicDemo demo = new MusicDemo();
        demo.loadMusic();
        Constant.alltime = demo.getAllTime();
        Asset asset = new Asset();
        asset.loadSound();
        asset.loadFile();
        Constant.asset = asset;
        setScreen(new GameScreen());
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.860f, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render();
    }

    public void pause () {
        runGame = false;
    }

    @Override
    public void resume () {
        runGame = true;
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
