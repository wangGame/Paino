package xyz.zzzxb.paino.asset;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.AudioDevice;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.io.Mpg123Decoder;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.compression.rangecoder.Decoder;


import java.io.File;
import java.util.ArrayList;

import xyz.zzzxb.paino.HandleData;
import xyz.zzzxb.paino.MusicUtils;
import xyz.zzzxb.paino.MyMp3FileReader;
import xyz.zzzxb.paino.constant.Constant;
import xyz.zzzxb.paino.music.MusicDecoder;

public class MusicDemo {
    ArrayList<Integer> allTime = new ArrayList<>();
    FileHandle internal;
    public void loadMusic(){
        internal = Gdx.files.external("1.mp3");
        Music music = Gdx.audio.newMusic(internal);
        MyMp3FileReader reader = new MyMp3FileReader(internal);
        int[] data = reader.getData();
//        int duration = MusicUtils.getDuration(internal.file());
        Constant.tatalData = data.length;
        Constant.arr = data;
        HandleData.handleData(data,allTime,10000000,1024,100);
        System.out.println("====>>>>>>>>>>>>>>>>>>");
        ddd();
    }
    short[] samples = new short[1024];
    AudioDevice device;
    public void ddd(){
//        decoder = new Mpg123Decoder(externalFile);
        Mpg123Decoder decoder = new Mpg123Decoder(internal);
        Constant.decode = decoder;
        Constant.devide = device = Gdx.audio.newAudioDevice(decoder.getRate(), decoder.getChannels() == 1 ? true : false);
    }

    public ArrayList<Integer> getAllTime() {
        return allTime;
    }
}
