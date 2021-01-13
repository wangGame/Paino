package xyz.zzzxb.paino.asset;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

import java.util.HashMap;

/**
 * loading
 */
public class Asset {
    public HashMap<String, Sound> musicHashMap = new HashMap<>();
    public void loading(){
        FileHandle internal = Gdx.files.internal("music/");
        for (FileHandle fileHandle : internal.list()) {
            if (fileHandle.isDirectory()){
                eachDir(fileHandle);
            }else {
                musicHashMap.put(fileHandle.name(),new SoundAsset(fileHandle.path()));
            }
        }
    }

    public void eachDir(FileHandle handle){
        FileHandle[] list = handle.list();
        for (FileHandle fileHandle : list) {
            if (fileHandle.isDirectory()) {
                eachDir(fileHandle);
            }
            else {
                musicHashMap.put(fileHandle.name(),new SoundAsset(fileHandle.path()));
            }
        }
    }

    public HashMap<String, Sound> getMusicHashMap() {
        return musicHashMap;
    }
}
