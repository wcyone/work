package com.sccdrs.work.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.time.temporal.TemporalQueries;

/**
 * @author wcy
 * @date 2019/8/14 10:32
 * @Description: q求每个月温度最高的2天   如 2018-10-12 12:35:51 30c
 */
public class MyTQ {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration(true);
        Job job = Job.getInstance(conf);
        job.setJarByClass(MyTQ.class);

        //配置 map
        //job.setInputFormatClass();
        job.setMapperClass(TMapper.class);
        job.setMapOutputKeyClass(TQ.class); //map计算的输出的key
        job.setMapOutputValueClass(IntWritable.class);//map计算输出的value
        //分区
        job.setPartitionerClass(TPartitioner.class);
        //比较器
        job.setSortComparatorClass(TSortComparator.class);

        //job.setCombinerClass();

        //配置 reduce
        //分组比较器
        job.setGroupingComparatorClass(TGroupingComparator.class);
        job.setReducerClass(TReducer.class);


        //输入路径
        Path input = new Path("/data/input");
        FileInputFormat.addInputPath(job,input);

        //输出路径
        Path output = new Path("/data/output");
        if(output.getFileSystem(conf).exists(output)){
            output.getFileSystem(conf).delete(output,true);
        }
        FileOutputFormat.setOutputPath(job,output);
        //设置reduce数量
        job.setNumReduceTasks(2);

        job.waitForCompletion(true);
    }
}
