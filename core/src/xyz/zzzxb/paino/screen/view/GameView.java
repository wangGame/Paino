package xyz.zzzxb.paino.screen.view;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Align;

import xyz.zzzxb.paino.audio.Paino;
import xyz.zzzxb.paino.constant.Constant;

public class GameView extends Group {

    String [] arr  = {
//           "d2","c2","b1","c2","b1","c2","b1","c2","b1","c2","a2","g2","#f2","g2","#f2"
//            "a1","V","a1","e2","V","e2","#f2","V","#f12","V","f12","e2","V"
            "a1","V","a1","e2","V","e2","#f2","V","#f2","e2","V","T","d2","V","d2","#c2","V","#c2","b1"
    };

    public GameView(){
        setSize(Constant.width, Constant.hight);
        PainoGroup group = new PainoGroup();
        group.setPosition(Constant.width/2,Constant.hight/2, Align.center);
        addActor(group);
    }

    float time  = 0;
    int index;
    @Override
    public void act(float delta) {
        super.act(delta);
        time += delta;
        if (time >0.4F){
            time = 0;
            if (index >= arr.length){
                index = 0;
            }
            String s = arr[index++];
            Paino.playSound(s+".mp3");
        }
    }
}
