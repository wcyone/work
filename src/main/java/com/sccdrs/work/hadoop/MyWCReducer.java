package com.sccdrs.work.hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author wcy
 * @date 2019/8/13 9:54
 * @Description: shuffle:源码解释ruducer拉去mapper排序好的数据的动作
 * 1、reducer的泛型 输入key-value是mapper的输出的key-value, 输出的key-value 根据实际情况来设定
 */
public class MyWCReducer extends Reducer<Text, IntWritable,Text,IntWritable> {

    //2、重写reduce
    //相同的key为一组... 调用一次reduce方法，在方法内迭代这一组数据，运行计算
    private IntWritable result = new IntWritable();
    public void reduce(Text key,Iterable<IntWritable> values,Context context) throws IOException, InterruptedException {
        int sum = 0;
        for (IntWritable v : values){
            sum += v.get();
        }
        result.set(sum);
        context.write(key,result);
    }

}
