package xyz.zzzxb.paino.button;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import xyz.zzzxb.paino.constant.Constant;

public class ImageButton extends Image {
    private int arr[];
    public ImageButton(Texture texture, short[] arrTemp) {
        super(texture);

        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                int  readSamples = Constant.decode.readSamples(arrTemp, 0, arrTemp.length);
                Constant.devide.writeSamples(arrTemp, 0, readSamples);
            }
        });
    }
}
