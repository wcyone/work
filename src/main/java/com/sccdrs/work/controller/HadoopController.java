package com.sccdrs.work.controller;

import com.sccdrs.work.aop.Operation;
import com.sccdrs.work.utils.HadoopUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wcy
 * @date 2019/8/6 16:59
 * @Description:
 * 错误1：java.io.IOException: Could not locate executable null\bin\winutils.exe in the Hadoop binaries.
 */
@RestController
public class HadoopController {

    @Operation(value = "hadoop创建文件夹")
    @PostMapping("hadoop/client")
    public void client(String folder) throws Exception{
        HadoopUtil hadoopUtil = new HadoopUtil();
        hadoopUtil.mkdirFolder(folder);
    }

    @Operation(value = "hadoop上传文件")
    @PostMapping("hadoop/upload")
    public void uploadFile(String loaclFile,String folder) throws Exception{
        HadoopUtil hadoopUtil = new HadoopUtil();
        hadoopUtil.uploadFile(loaclFile,folder);
    }
}
