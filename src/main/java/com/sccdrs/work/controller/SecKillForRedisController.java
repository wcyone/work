package com.sccdrs.work.controller;

import com.alibaba.fastjson.JSON;
import com.sccdrs.work.aop.Operation;
import com.sccdrs.work.service.SecKillForRedisService;
import com.sccdrs.work.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wcy
 * @date 2019/8/6 14:20
 * @Description: 基于redis分布式锁的 秒杀活动业务
 * 获取redis缓存数据  讲成功的数据 存到mq  然后在冲mq获取数据操作数据库数据
 */
@RestController
public class SecKillForRedisController {

    @Autowired
    private SecKillForRedisService secKill;

    @Operation(value = "秒杀活动用户状态记录")
    @PostMapping("seckill")
    public JSON secKill(int id){
        int num = secKill.secKill(id);
        if(num !=0){
            return ResponseUtil.success(id+"秒杀成功");
        }else{
            return ResponseUtil.fail(id+"秒杀失败");
        }
    }
}
