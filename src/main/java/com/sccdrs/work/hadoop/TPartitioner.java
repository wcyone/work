package com.sccdrs.work.hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @author wcy
 * @date 2019/8/14 13:58 分区
 * @Description: map的输出key-value  是Partitioner 的输入key-value
 * Partition是对key进行分组 每个map都会访问一次
 * 涉及 数据倾斜的问题   如何解决？
 *
 */
public class TPartitioner extends Partitioner<TQ, IntWritable> {
    @Override
    public int getPartition(TQ key, IntWritable value, int i) {

        return key.hashCode() % i;
    }
}
