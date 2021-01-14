package xyz.zzzxb.paino.screen.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Align;

import java.util.ArrayList;
import java.util.Random;

import xyz.zzzxb.paino.ImageButton;
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
//        PainoGroup group = new PainoGroup();
//        group.setPosition(Constant.width/2,Constant.hight/2, Align.center);
//        addActor(group);


        long totalData = Constant.tatalData;
        ArrayList<Integer> alltime = Constant.alltime;
        int index = 0;
        int y = 0;
        Random random = new Random();
        int arr[] = Constant.arr;
        for (int i = 0; i < alltime.size()-1; i++) {
            //长度
            int i2 = random.nextInt(2);
            Integer integer = alltime.get(i + 1);
            int i1 = integer - alltime.get(i);


            int count = 0;
            short arrTemp[] = new short[i1];
            for (int i3 = alltime.get(i); i3 < integer-1; i3++) {
                if (i3>=arr.length - 10)break;
                short aa = (short)arr[i3];
                arrTemp[count++] = aa;
            }

            ImageButton button = new ImageButton(new Texture("image/withe_paino.png"),arrTemp);
            if (i2 == 0){
                button.setPosition(0,  y);
            }else {
                button.setPosition(40,  y);
            }
            button.setColor(Color.BLUE);
            System.out.println(index * 200);
            button.setHeight(i1*0.001F);
            index++;
            y += i1*0.001F;
            addActor(button);
            System.out.println("===>>>" + index * 200);
        }
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
