package com.sccdrs.work.utils;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

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
        String nameNodeUrl = "hdfs://139.155.112.146:9000";
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
    public void mkdirFolder(String folder) throws Exception {
        FileSystem fs = connectHadoop();
        String folderName = "/"+folder;
        fs.mkdirs(new Path(folderName));
        logger.info("创建目录：folderName={}", folderName);
    }

    /**
     * 上传文件到hadoop指定的目录
     * file:本地文件名称路径
     * name：hadoop文件路径名字
     */
    public void uploadFile(String loaclFilePath,String folder) throws Exception{
        FileSystem fs = connectHadoop();
        String fileName = loaclFilePath.substring(loaclFilePath.lastIndexOf("\\")+1);
        String uploadFolder = "/"+folder+"/";
        InputStream in =null;
        try {
            in = new FileInputStream(loaclFilePath);
            OutputStream out = fs.create(new Path(uploadFolder + fileName));
            IOUtils.copyBytes(in, out, 4096, true);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            in.close();
        }

    }

    /**
     * 下载文件
     * floder hadoop的存放文件名
     * fileName 具体文件名称
     */
    public void downFile(String floder,String fileName) throws Exception {
        FileSystem fs = connectHadoop();
        String downFolder = "/"+floder+"/";
        //定义要保存的路径
        String savePath = "D://www.mhylpt.com Hadoop//download//" + fileName;

        InputStream in = fs.open(new Path(downFolder + fileName));
        OutputStream out = new FileOutputStream(savePath);
        IOUtils.copyBytes(in, out, 4096, true);
    }
}
