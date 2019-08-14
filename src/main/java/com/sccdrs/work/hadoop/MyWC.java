package com.sccdrs.work.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


/**
 * @author wcy
 * @date 2019/8/13 9:42
 * @Description: hadoop 计算字数出现的次数
 * 打成jar 放到namedome 上 使用 hadoop jar wc.jar com.sccdrs.work.hadoop.MyWC 执行
 */
public class MyWC {

    public static void main(String[] args) throws Exception{
        //1、hdfs参数 core-site.xml等
        Configuration configuration = new Configuration(true);
        //2、Job
        Job job = Job.getInstance(configuration);
        //设置运行的类名 和job执行的名字
        job.setJarByClass(MyWC.class);
        job.setJobName("MyWC");
        //输入文件的hdfs地址，输入地址可以是多个
        Path input = new Path("/input/test.txt");
        FileInputFormat.addInputPath(job,input);
        //输出文件的地址，输出地址只能为一个
        Path output = new Path("/output");
        //实际业务中或判断是否存在改路径  或者在文件夹后面加入时间区分
        FileOutputFormat.setOutputPath(job,output);
        //加入map处理类,map计算的输出类型
        job.setMapperClass(MyWCMapper.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        //加入reduce处理类
        //job.setNumReduceTasks(99); //设置Reduce数量 这个根据实际业务分组得到的  不能随便写
        job.setReducerClass(MyWCReducer.class);

        job.waitForCompletion(true);
    }
}
