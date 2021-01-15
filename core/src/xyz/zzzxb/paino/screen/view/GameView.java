package xyz.zzzxb.paino.screen.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;

import java.util.ArrayList;
import java.util.Random;

import xyz.zzzxb.paino.button.ImageButton;
import xyz.zzzxb.paino.constant.Constant;

public class GameView extends Group {
    public GameView(){
        setSize(Constant.width, Constant.hight);
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
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
