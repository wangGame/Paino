package xyz.zzzxb.paino.musicutils;

import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;

import java.io.File;

public class MusicUtils {
    public static int getDuration(File file) {
        int length = 0;
        try {
            MP3File mp3File = (MP3File) AudioFileIO.read(file);
            MP3AudioHeader audioHeader = (MP3AudioHeader) mp3File.getAudioHeader();
            length = audioHeader.getTrackLength();
            return length;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return length;
    }
}