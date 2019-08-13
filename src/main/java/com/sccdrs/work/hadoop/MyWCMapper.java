package com.sccdrs.work.hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * @author wcy
 * @date 2019/8/13 9:54
 * @Description: Mapper的泛型不能使用java的基本类型
 * 1、根据业务需求设置Mapper的输入key-value 输出的key-value类型
 */
public class MyWCMapper extends Mapper<Object, Text, Text, IntWritable> {

    //2、重写map方法
    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();
    public void map(Object key,Text value,Context context) throws IOException ,InterruptedException{
        //一条记录 hello world 12
        StringTokenizer itr = new StringTokenizer(value.toString());
        while(itr.hasMoreElements()){
            word.set(itr.nextToken());
            context.write(word,one); //引用传递 然后组成个buffer数组
        }
    }
}
