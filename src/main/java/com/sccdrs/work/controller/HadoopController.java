package com.sccdrs.work.controller;

import com.sccdrs.work.aop.Operation;
import com.sccdrs.work.utils.HadoopUtil;
import org.apache.hadoop.fs.FileSystem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wcy
 * @date 2019/8/6 16:59
 * @Description:
 */
@RestController
public class HadoopController {

    @Operation(value = "hadoop客户端连接")
    @PostMapping("hadoop/client")
    public void client(String name) throws Exception{
        HadoopUtil hadoopUtil = new HadoopUtil();
        hadoopUtil.mkdirFolder(name);
    }
}
