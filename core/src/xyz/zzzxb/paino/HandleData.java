package xyz.zzzxb.paino;

import java.util.ArrayList;
import java.util.List;

public class HandleData {

    public static final int WINDOW_256=256;
    public static final int WINDOW_1024=1024;
    public static final int WINDOW_2048=2048;
    public static final int WINDOW_SIZE_10=10;
    public static final int WINDOW_SIZE_20=20;//
    public static final int WINDOW_SIZE_30=30;
    public static final double MULTIPLIER_1=1f;
    public static final double MULTIPLIER_1_5=1.5f;
    public static final double MULTIPLIER_2=2f;
    public static final double MULTIPLIER_2_5=2.5f;
    public static final double MULTIPLIER_3=3f;//
    public static final double MULTIPLIER_3_5=3.5f;
    public static final double MULTIPLIER_4=4f;

    /**
     * @param data  歌曲数据
     * @param allTime list引用，来得到数据的
     * @param musicTime  歌曲时间
     * @param _window  每次处理数据的大小
     * @param window_size  一种保护，担心数据发生越界的一种保护措施
     */
    public static void handleData(int[] data,ArrayList<Integer> allTime,long musicTime,int _window,int window_size){
        final int window=_window;//样本窗口越大，，可能超过阈值的越多
        final int THRESHOLD_WINDOW_SIZE=window_size;//阈值左右各取多少个样本窗口的光谱通量！！！！越慢的歌要的越少，size越大，块数越多
//        final double MULTIPLIER=multiplier;//阈值的加权值！！！！越慢的歌要的越高（快的歌太高，短时间变得快，块数越少）
        double[] dataFFT;//频谱
        List<Double> spectralFlux = new ArrayList<>();//每个样本窗口的光谱通量

        int len = data.length;
        int m=len/window;//样本窗口数  //近似。。待定加一        计算可以处理多少组

        System.out.println("处理的数据组数  len ---> "+m);

        dataFFT=new double[len];
        double[] dataFFTVariation=new double[len-m];
        FFT fft=new FFT(window);//本地类
        double[] re=new double[window];//实部
        double[] im=new double[window];//虚部，为0

        double max=re[0];
        double min=re[0];

        /**
         * 得到每一个数据  进行fft处理得到实部和虚部
         */
        for(int k=0;k<m;k++){
            //处理每个样本窗口
            for(int i=0;i<window;i++){
                re[i]=data[k*window+i];
                im[i]=0;
            }
            fft.fft(re, im);//每个样本窗口离散傅里叶变换后的fft,赋值到了re实部和im虚部里面

            //是不上一个数据和当前数据的插值
            double flux=0;//flux是样本窗口里本次采样频谱与上个频谱之差，所有频谱的差的正值和为该样本窗口的光谱通量
            for(int i=0;i<window;i++){
                dataFFT[k*window+i]=re[i];//把实部值添加到频谱数组里面

                if(i>0){
                    dataFFTVariation[k*window+i-1-k]=dataFFT[k*window+i]-dataFFT[k*window+i-1];
                    double value=dataFFTVariation[k*window+i-1-k];
                    flux+=value<0?0:value;
                }
            }
            spectralFlux.add( flux );
        }

        //阈值     差值减去最后的几个值
        int delete=10;
        for(int i=0;i<spectralFlux.size()-delete;i++){//去除最后结尾异变的几个值
            int start = Math.max( 0, i - THRESHOLD_WINDOW_SIZE );//防止开头、结尾溢出
            int end = Math.min( spectralFlux.size()-delete - 1, i + THRESHOLD_WINDOW_SIZE );

            //将所有的差值都加起来
            double mean = 0;
//            for( int j = start; j <= end; j++ ) {
//                mean += spectralFlux.get(j);
//            }
//            求平均
//            mean /= (end - start);
//            大于平均值
            if(mean<spectralFlux.get(i)){
                //时长    处理的第几个窗口值
                int time=(int)(i*window/(len*1.0)*musicTime);
                //间距   当前的值和上一个值的间距大于100的时候才进行处理
                if((allTime.size()>0&&(time-allTime.get(allTime.size()-1))>10000)||allTime.size()<1)
                    allTime.add(time);//不知道为什么只有16的时候效果比较好..平均采样率16000
            }
        }
    }
}
