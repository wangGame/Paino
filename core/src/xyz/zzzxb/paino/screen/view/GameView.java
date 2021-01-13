package xyz.zzzxb.paino.screen.view;

import com.badlogic.gdx.scenes.scene2d.Group;

import xyz.zzzxb.paino.constant.Constant;

public class GameView extends Group {

    public GameView(){
        setSize(Constant.width, Constant.hight);
        PainoGroup group = new PainoGroup();
        addActor(group);
    }
}
