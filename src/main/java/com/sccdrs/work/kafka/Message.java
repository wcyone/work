package com.sccdrs.work.kafka;

import lombok.Data;

import java.util.Date;

/**
 * @author wcy
 * @date 2019/8/14 15:32
 * @Description:
 */
@Data
public class Message {
    private Long id;    //id

    private String msg; //消息

    private Date sendTime;  //时间戳
}
