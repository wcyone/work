package com.sccdrs.work.hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.StringUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author wcy
 * @date 2019/8/14 11:26
 * @Description: 默认输入类型是textInputFormat 返回类型是longWritable
 */

public class TMapper extends Mapper<LongWritable, Text, TQ, IntWritable> {
    TQ mkey = new TQ();
    IntWritable mvlaue = new IntWritable();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //2018-10-12 12:35:51   30c

        try {
            String[] strs = StringUtils.split(value.toString(),'\t');
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = simpleDateFormat.parse(strs[0]);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            mkey.setYear(calendar.get(Calendar.YEAR));
            mkey.setMonth(calendar.get(Calendar.MONTH)+1);
            mkey.setDay(calendar.get(Calendar.DAY_OF_MONTH));

            int wd = Integer.parseInt(strs[1].substring(0,strs[1].length()-1));
            mkey.setWd(wd);

            mvlaue.set(wd);

            //map输出
            context.write(mkey,mvlaue);

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
