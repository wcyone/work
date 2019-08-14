package com.sccdrs.work.hadoop;

import lombok.Data;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author wcy
 * @date 2019/8/14 10:52
 * @Description:
 */
@Data
public class TQ implements WritableComparable<TQ> {

    private int year;
    private int month;
    private int day;
    private int wd;

    @Override
    public int compareTo(TQ o) {
        //日期正序
        int c1 = Integer.compare(this.year,o.getYear());
        if(c1 == 0){
            int c2 =Integer.compare(this.month,o.getMonth());
            if(c2 == 0){
                return Integer.compare(this.day,o.getDay());
            }
            return c2;
        }
        return c1;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(year);
        dataOutput.writeInt(month);
        dataOutput.writeInt(day);
        dataOutput.writeInt(wd);

    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.year=dataInput.readInt();
        this.month=dataInput.readInt();
        this.day=dataInput.readInt();
        this.wd=dataInput.readInt();
    }
}
