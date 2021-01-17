package xyz.zzzxb.paino.asset;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.AudioDevice;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.io.Mpg123Decoder;
import com.badlogic.gdx.files.FileHandle;


import java.util.ArrayList;

import xyz.zzzxb.paino.musicutils.HandleData;
import xyz.zzzxb.paino.musicutils.MyMp3FileReader;
import xyz.zzzxb.paino.constant.Constant;

public class MusicDemo {
    private ArrayList<Integer> allTime = new ArrayList<>();
    private FileHandle internal;

    public void loadMusic(){
        internal = Gdx.files.external("therefori_4437881R3w.mp3");
        MyMp3FileReader reader = new MyMp3FileReader(internal.file());
        int[] data = reader.getData();
        Constant.tatalData = data.length;
        Constant.arr = data;
        HandleData.handleData(data,allTime,10000000,1024,100);
        ddd();
    }

    public void ddd(){
        Mpg123Decoder decoder = new Mpg123Decoder(internal);
        Constant.decode = decoder;
        Constant.devide = Gdx.audio.newAudioDevice(decoder.getRate(), decoder.getChannels() == 1 ? true : false);
    }

    public ArrayList<Integer> getAllTime() {
        return allTime;
    }
}
