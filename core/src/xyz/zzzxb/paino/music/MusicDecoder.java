package xyz.zzzxb.paino.music;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.SharedLibraryLoader;

public class MusicDecoder extends Decoder {
    public final long handle;

    public MusicDecoder(FileHandle file) {
        if (file.type() != Files.FileType.External && file.type() != Files.FileType.Absolute) {
            throw new IllegalArgumentException("File must be absolute or external!");
        } else {
            this.handle = this.openFile(file.file().getAbsolutePath());
        }
    }

    public int readSamples(short[] samples, int offset, int numSamples) {
        int read = this.readSamples(this.handle, samples, offset, numSamples);
        return read;
    }

    public int skipSamples(int numSamples) {
        return this.skipSamples(this.handle, numSamples);
    }

    public int getChannels() {
        return this.getNumChannels(this.handle);
    }

    public int getRate() {
        return this.getRate(this.handle);
    }

    public float getLength() {
        return this.getLength(this.handle);
    }

    public void dispose() {
        this.closeFile(this.handle);
    }

    private native long openFile(String var1);

    private native int readSamples(long var1, short[] var3, int var4, int var5);

    private native int skipSamples(long var1, int var3);

    private native int getNumChannels(long var1);

    private native int getRate(long var1);

    private native float getLength(long var1);

    private native void closeFile(long var1);

    static {
        (new SharedLibraryLoader()).load("gdx-audio");
    }
}