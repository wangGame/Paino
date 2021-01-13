package xyz.zzzxb.paino.audio;

import com.badlogic.gdx.audio.Sound;
import xyz.zzzxb.paino.constant.Constant;

public class Paino {
    public static void playSound(String key){
        if (Constant.asset.musicHashMap.containsKey(key)) {
            Sound sound = Constant.asset.musicHashMap.get(key);
            sound.play();
        }
    }
}
