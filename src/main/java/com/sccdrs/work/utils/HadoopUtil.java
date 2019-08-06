package com.sccdrs.work.utils;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wcy
 * @date 2019/8/6 16:51
 * @Description: hadoop 连接 基本操作   单节点连接
 */
public class HadoopUtil {
    private Logger logger = LoggerFactory.getLogger(HadoopUtil.class);

    /**
     * 连接Hadoop
     * nameNodeUrl
     * nameNodeName 对应的是core-site.xml的配置内容
     */
    public FileSystem connectHadoop() {
        String nameNodeUrl = "hdfs://192.168.76.129:9000";
        String nameNodeName = "fs.defaultFS";
        FileSystem fs = null;
        Configuration configuration = new Configuration();
        try {
            configuration.set(nameNodeName, nameNodeUrl);
            fs = FileSystem.get(configuration);
            logger.info("连接成功：Path={}", fs.getFileStatus(new Path("/")));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return fs;
    }

    /**
     * 创建目录
     * @throws Exception
     */
    public void mkdirFolder(String name) throws Exception {
        FileSystem fs = connectHadoop();
        String folderName = "/"+name;
        fs.mkdirs(new Path(folderName));
        logger.info("创建目录：folderName={}", folderName);
    }
}
