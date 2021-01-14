package xyz.zzzxb.paino.music;

import com.badlogic.gdx.utils.Disposable;

public abstract class Decoder implements Disposable {
    public Decoder() {
    }

    public abstract int readSamples(short[] var1, int var2, int var3);

    public short[] readAllSamples() {
        short[] out = new short[(int)Math.ceil((double)(this.getLength() * (float)this.getRate() * (float)this.getChannels()))];
        short[] buffer = new short[5120];
        int totalSamples;
        short[] tmp;
        int readSamples;
        for(totalSamples = 0; (readSamples = this.readSamples(buffer, 0, buffer.length)) > 0; totalSamples += readSamples) {
            if (readSamples + totalSamples >= out.length) {
                tmp = new short[readSamples + totalSamples];
                System.arraycopy(out, 0, tmp, 0, totalSamples);
                out = tmp;
            }

            System.arraycopy(buffer, 0, out, totalSamples, readSamples);
        }

        if (out.length != totalSamples) {
            tmp = new short[totalSamples];
            System.arraycopy(out, 0, tmp, 0, totalSamples);
            out = tmp;
        }

        return out;
    }

    public abstract int skipSamples(int var1);

    public abstract int getChannels();

    public abstract int getRate();

    public abstract float getLength();

    public abstract void dispose();
}
