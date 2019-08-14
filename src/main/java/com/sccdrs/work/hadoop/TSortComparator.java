package com.sccdrs.work.hadoop;

import org.apache.hadoop.io.RawComparator;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * @author wcy
 * @date 2019/8/14 14:07 排序
 * @Description:
 */
public class TSortComparator extends WritableComparator {

    public TSortComparator() {
        //调用父类的构造器
        super(TQ.class,true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        TQ t1 = (TQ)a;
        TQ t2 = (TQ)b;
        int  c1 = Integer.compare(t1.getYear(),t2.getYear());
        if(c1 == 0){
            int c2 = Integer.compare(t1.getMonth(),t2.getMonth());
            if(c2 == 0){
                //降序
                return -Integer.compare(t1.getWd(),t1.getWd());
            }
            return c2;
        }
        return c1;

    }
}
