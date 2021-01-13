package xyz.zzzxb.paino.actor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import xyz.zzzxb.paino.audio.Paino;

public class PainoActor extends Group {
    private Image panio;

    public PainoActor(String imageName,String keyCode){
        setName(keyCode);
        panio = new Image(new Texture(imageName));
        addActor(panio);

        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                panio.setColor(Color.valueOf("#777777"));
                addAction(Actions.delay(0.2F,Actions.run(()->{
                    panio.setColor(Color.WHITE);
                })));
                Paino.playSound(keyCode);
            }
        });
        setSize(panio.getWidth(),panio.getHeight());
    }
}
