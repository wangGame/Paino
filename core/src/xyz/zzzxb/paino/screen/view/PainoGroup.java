package xyz.zzzxb.paino.screen.view;

import com.badlogic.gdx.scenes.scene2d.Group;

import xyz.zzzxb.paino.actor.PainoActor;

public class PainoGroup extends Group {
    private int [] whiteKeyCode = {
            49,50,51,52,53,54,55,56,57,48,81,87,69,82,84,89,85,73,79,80,65,83,68,70,71,72,74,75,76,90,88,67,86,66,78,77
    };
    private int [] blockKeyCode = {
            49,50,51,52,53,54,55,56,57,48,81,87,69,82,84,89,85,73,79,80,65,83,68,70,71,72,74,75,76,90,88,67,86,66,78
    };

    public PainoGroup() {
        setSize(25*36,100);
        initGroup();
    }

    private void initGroup() {
        setDebug(true);
        int index = 0;
        //白
        for (int i : whiteKeyCode) {
            PainoActor actor = new PainoActor("image/withe_paino.png","a"+i+".mp3");
            actor.setPosition(index * 26,0);
            index ++ ;
            addActor(actor);
        }

        index = 0;
        int kong = 0;
        int last = 3;
        for (int i : blockKeyCode) {
            kong ++;
            if (last == 3){
                if (kong == 3){
                    kong = 0;
                    last = 2;
                    index ++;
                    continue;
                }
            }else {
                if (kong == 4){
                    kong = 0;
                    last = 3;
                    index++;
                    continue;
                }
            }
            PainoActor actor = new PainoActor("image/black_panio.png","a"+i+".mp3");
            actor.setPosition(index * 26+19,24);
            index ++ ;
            addActor(actor);
        }
    }
}
