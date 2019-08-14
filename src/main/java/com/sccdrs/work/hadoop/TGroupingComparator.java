package com.sccdrs.work.hadoop;

import org.apache.hadoop.io.RawComparator;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * @author wcy
 * @date 2019/8/14 14:18
 * @Description:  分组
 */
public class TGroupingComparator extends WritableComparator {
    public TGroupingComparator() {
        super(TQ.class,true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        TQ t1 = (TQ)a;
        TQ t2 = (TQ)b;
        int  c1 = Integer.compare(t1.getYear(),t2.getYear());
        if(c1 == 0){
            return Integer.compare(t1.getMonth(),t2.getMonth());
        }
        return c1;
    }
}
