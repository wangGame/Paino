# Paino

使用键盘或者按键按下，来完成代码演凑效果。

## 自动播放


## 分支 弹钢琴实现方式二

使用切割流的方式，每一个块代表这一个块的数据，可以通过不断的给设置流数据来实现音乐播放

缺点：


## 基本处理过程

MainActivity  开始

块处理，块一次创建所有，然后给它们设置动画延迟，等到时间了在出现和运动

块中数据

文件地址
文件大小
时长
window
windowsize : size越大块就越多
mutiplier : 阈值，值越大，块越少
duration ：滑块时间



----------------------------------

## 解析 mp3文件

前10

	//开头，音频信息，版本号 标签大小等
	/*
	 * char Header[3]; 字符串 "ID3"
	 *
	 * char Ver; 版本号ID3V2.3 就记录3
	 *
	 * char Revision; 副版本号此版本记录为0
	 *
	 * char Flag; 存放标志的字节，这个版本只定义了三位，很少用到，可以忽略
	 *
	 * char Size[4]; 标签大小，除了标签头的10 个字节的标签帧的大小
	 *
	 *
	 */

头

	/*TSSE软硬件编码格式
	 *APIC图片
	 *COMM注解评论 comments
	 *TALB专辑
	 *TIT2歌名 内容描述
	 *TPE1作者
	 *TPE2乐队成员
	 *0000*/

数据

	数据的长度：
	file.length-128-10-headlabel
	文件总长 - 尾部128 - 开始10 - 歌曲信息

128尾部

	char Header[3]; /*标签头必须是"TAG"否则认为没有标签*/
	char Title[30]; /*标题*/
	char Artist[30]; /*作者*/
	char Album[30]; /*专集*/
	char Year[4]; /*出品年代*/
	char Comment[28]; /*备注*/
	char reserve; /*保留*/
	char track;; /*音轨*/
	char Genre; /*类型*/


## 处理的基本思路


先获取到数据部分，然后1000个字符是一个处理周期，对1000个进行数据处理

```java
data.length = var length;
1000 = var window；
//可以处理的数据组数
length / window = int num;

num . for i
	window .for j
		data [1000 * i + j] = var currentData;

//对数据进行fft 处理
im[];
re[];
fft(re,im);

计算当前值和前面一个值的差值   存起来
计算插值的平均值


插值大于平均值的拉出去  进行展示  但是为了拥堵的情况，上一个和下一个的时间差需要大于100ms

```

得到之后所有需要进行处理的数据。



### 解析代码

## 解析mp3文件

读取文件流

### 步骤

- 读取文件流

```java
FileInputStream is=new FileInputStream(file);

```

- 前10

```java
byte[] bufFront=new byte[10];
is.read(bufFront, 0, 10);
```

- //标签头（3） 版本号（1） 副版本（1） 标志字节（1） 标签大小（4）

```java
byte[] temp = null;
if (data.length != 10) {
	System.out.println("数据不足10字节或者大于10字节！");
	throw new Exception();
}
int pos = 0;
temp = new byte[3];//header 必须为ID3，否则认为标签不存在

---------->
byte [] bytes = {73,68,51};
System.out.println(new String(bytes));

ID3
---------->result

System.arraycopy(data, pos, temp, 0, 3);//得到data[pos,pos+3] (Object src,  int  srcPos,Object dest, int destPos,int length);
pos = pos + temp.length;//pos=3
temp = new byte[1];//版本号ID3V2.3 就记录3
System.arraycopy(data, pos, temp, 0, 1);//得到data[pos,pos+1],即data[2,3]

---------->
byte [] bytes = {3};
版本是数字
3
---------->result

pos = pos + temp.length;
temp = new byte[1];//副版本号 0
System.arraycopy(data, pos, temp, 0, 1);

---------->
byte [] bytes = {0};
副版本是数字
0
---------->result

pos = pos + temp.length;
temp = new byte[1];//存放标志的字节
System.arraycopy(data, pos, temp, 0, 1);

---------->
byte [] bytes = {0};

0
---------->result


pos = pos + temp.length;//pos=6
temp = new byte[4];//标签大小，包括标签头的10 个字节和所有的标签帧的大小
System.arraycopy(data, pos, temp, 0, 4);//data[5,9]

---------->
byte [] bytes = {0,2,107,65};
大小转换为十进制
int r = temp[3] & 0xFF | ((temp[2] << 8) & 0xFF00) | ((temp[1] << 16) & 0xFF0000) | ((temp[0] << 24) & 0xFF0000);

158519
---------->result
this.size = MyMp3FileReader.getSizeByByte(temp) - 10;//去掉标签头的长度为所有标签帧的长度
```

### size

为除了头之外的所有数据，下面是TIT2表示内容为这首歌的标题，TPE1作者 TALB专辑 TRCK因规格是 TYER年代等数据
所有使用循环进行，如果不包含头了，也就结束了。

- 标识帧

byte [] bytes = {84,73,84,50};
System.out.println(new String(bytes));

---->>>>>
TIT2歌名

- 大小

byte [] bytes = {0,0,0,41};
int sizeByByte = getSizeByByte(bytes);
System.out.println(sizeByByte);

----->>>>
41

- 标志

0

- 读取数据  刚才大小是41，那么需要读取41字节

```java
public static void main(String[] args) {
	byte [] bytes = {1,-1,-2,103,114,108,-102,-50,87
		,2,94,32,0,40,0,50,0,48,0,49,0,56,0,1,-128,55,
		117,105,91,-47,83,3,94,26,79,-80,115,58,87,41,0};
System.out.println(new String(bytes, Charset.forName("GB18030")));
```

基本就是这个过程，一直向下读取数据

直到不包含头信息结束。

最后末尾128字节。

### 音乐数据部分

file.length() - 128 - 10 - headLabelLength)