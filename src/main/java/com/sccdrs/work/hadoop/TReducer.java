package com.sccdrs.work.hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author wcy
 * @date 2019/8/14 14:21
 * @Description:
 */
public class TReducer extends Reducer<TQ, IntWritable, Text,IntWritable> {

    Text rkey = new Text();
    IntWritable rvalue = new IntWritable();
    @Override
    protected void reduce(TQ key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int flg =0;
        int day =0;
        for(IntWritable v : values){
            if(flg==0){
                rkey.set(key.getYear()+"-"+key.getMonth()+"-"+key.getDay()+":"+key.getWd());
                rvalue.set(key.getWd());
                flg++;
                day=key.getDay();
                context.write(rkey,rvalue);
            }
            if(flg!=0 && day!=key.getDay()){
                rkey.set(key.getYear()+"-"+key.getMonth()+"-"+key.getDay()+":"+key.getWd());
                rvalue.set(key.getWd());
                context.write(rkey,rvalue);
                break;
            }

         }
    }

}
