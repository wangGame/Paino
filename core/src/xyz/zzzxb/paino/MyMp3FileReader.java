package xyz.zzzxb.paino;

import com.badlogic.gdx.files.FileHandle;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyMp3FileReader {
    ArrayList<String> labels=new ArrayList<>();
    private FileInputStream is=null;
    private DataInfo dataInfo;
    private byte[] data;
    public MyMp3FileReader(FileHandle _filename){
        String[] mlabels={"AENC", "APIC", "COMM", "COMR", "ENCR", "EQUA", "ETCO", "GEOB", "GRID", "IPLS", "LINK", "MCDI", "MLLT", "OWNE", "PRIV", "PCNT", "POPM", "POSS", "RBUF", "RVAD", "RVRB", "SYLT", "SYTC", "TALB", "TBPM", "TCOM", "TCON", "TCOP", "TDAT", "TDLY", "TENC", "TEXT", "TFLT", "TIME", "TIT1", "TIT2", "TIT3", "TKEY", "TLAN", "TLEN", "TMED", "TOAL", "TOFN", "TOLY", "TOPE", "TORY", "TOWN", "TPE1", "TPE2", "TPE3", "TPE4", "TPOS", "TPUB", "TRCK", "TRDA", "TRSN", "TRSO", "TSIZ", "TSRC", "TSSE", "TYER", "TXXX", "UFID", "USER", "USLT", "WCOM", "WCOP", "WOAF", "WOAR", "WOAS", "WORS", "WPAY", "WPUB", "WXXX"};
        for(String s:mlabels){
            labels.add(s);
        }
        File file = _filename.file();
        try{
            is=new FileInputStream(file);
            byte[] bufFront=new byte[10];
            is.read(bufFront, 0, 10);
            this.dataInfo = new DataInfo(bufFront);//标签头 版本号 标签大小之类的
            byte[] headLabel = new byte[this.dataInfo.getSize()];
            is.read(headLabel, 0, headLabel.length);//往下读标签长度的字节，得到标签内容，赋值给headLabel
            int headLabelLength=this.ID3ByHeadList(headLabel);//得到标签信息（标题 作者等）return Map(name,data)
            data = new byte[(int) (file.length() - 128 - 10 - headLabelLength)];//总长度-结尾128-标签头10-标签,,数据不太符合
            System.arraycopy(headLabel, headLabelLength, data, 0, headLabel.length-headLabelLength);
            is.read(data, headLabel.length-headLabelLength, data.length-(headLabel.length-headLabelLength));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private int ID3ByHeadList(byte[] buf) {
        Map<String, String> map = new HashMap<String, String>();
        int length=0;
        int pix = 0;
        byte[] head;// 4  标识帧，说明其内容 TIT2表示内容为这首歌的标题，TPE1作者 TALB专辑 TRCK因规格是 TYER年代
        byte[] size;// 4 帧内容大小（只包含内容的大小）
        byte[] flag;// 2 存放标志 （c只读 i压缩 j加密等）
        int dataLeng = 0;
        byte[] dataBuf;// n
        for (; pix < buf.length; ) {//循环几组 标题组 作者组等
            head = new byte[4];
            size = new byte[4];
            flag = new byte[2];
            System.arraycopy(buf, pix, head, 0, 4);
            pix = pix + 4;
            System.arraycopy(buf, pix, size, 0, 4);
            pix = pix + 4;
            System.arraycopy(buf, pix, flag, 0, 2);
            pix = pix + 2;
            String headString=new String(head);
            if(!labels.contains(headString)){
                return length;
            }
            dataLeng = getSizeByByte(size);
            if (dataLeng <= 0)
                return length;
            dataBuf = new byte[dataLeng];
            dataLeng = dataLeng < buf.length - pix ? dataLeng : buf.length - pix;
            System.arraycopy(buf, pix, dataBuf, 0, dataLeng);
            pix = pix + dataLeng;
            map.put(new String(head), new String(dataBuf));
            length=pix;
        }
        return length;
    }

    class DataInfo  {
        private int size;
        public DataInfo(byte[] data) throws Exception {
            byte[] temp = null;
            if (data.length != 10) {
                System.out.println("数据不足10字节或者大于10字节！");
                throw new Exception();
            }
            int pos = 0;
            temp = new byte[3];//header 必须为ID3，否则认为标签不存在
            System.arraycopy(data, pos, temp, 0, 3);//得到data[pos,pos+3] (Object src,  int  srcPos,Object dest, int destPos,int length);
            pos = pos + temp.length;//pos=3
            temp = new byte[1];//版本号ID3V2.3 就记录3
            System.arraycopy(data, pos, temp, 0, 1);//得到data[pos,pos+1],即data[2,3]
            pos = pos + temp.length;
            temp = new byte[1];//副版本号 0
            System.arraycopy(data, pos, temp, 0, 1);
            pos = pos + temp.length;
            temp = new byte[1];//存放标志的字节
            System.arraycopy(data, pos, temp, 0, 1);
            pos = pos + temp.length;//pos=6
            temp = new byte[4];//标签大小，包括标签头的10 个字节和所有的标签帧的大小
            System.arraycopy(data, pos, temp, 0, 4);//data[5,9]
            this.size = MyMp3FileReader.getSizeByByte(temp) - 10;//去掉标签头的长度为所有标签帧的长度
        }
        public int getSize() {
            return this.size;
        }
    }

    private static int getSizeByByte(byte[] temp) {
        int r = temp[3] & 0xFF | ((temp[2] << 8) & 0xFF00) | ((temp[1] << 16) & 0xFF0000) | ((temp[0] << 24) & 0xFF0000);
        return r;
    }

    public int[] getData(){
        int[] mdata=new int[data.length];
        for(int i=0;i<data.length;i++)
            mdata[i]=(int)data[i];
        return mdata;
    }
}
