package com.sccdrs.work.utils;

import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FileStatus;
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
 *
 * MR
 *  M：由数据分片确定map的数据默认是1个block是1个map
 *      一行记录调一次map，计算结果为k,v ，由切片来确定记录
 *      sort:M计算的结果进行排序
 *  R：由业务决定，可以自己根据实际业务来确定reduce的数量，参考map计算出来的数据类型
 *
 *
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
        Path path = new Path(folderName);
        //业务逻辑：判断是否hdfs已存在  若存在则提示目录已存在  若不存在则创建
        //此处逻辑为 若存在则 递归删除文件夹在创建文件
        if(fs.exists(path)){
            fs.delete(path,true);
            logger.info("创建目录失败：folderName={},已存在", folderName);
        }else{
            fs.mkdirs(path);
            logger.info("创建目录成功：folderName={}", folderName);
        }


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

    /**
     * 获取hdfs上指定文件的block块的信息
     * Path path = new Path("/input/abc.txt");
     * @param path
     * @throws Exception
     * getFileBlockLocations  第二个参数输入文件的开始  第三个是截至  可以根据需求自己输入
     * 此处是查询该文件所有的位置
     */
    public void getBolck(Path path) throws  Exception{
        FileSystem fs = connectHadoop();
        FileStatus ifile = fs.getFileStatus(path);
        BlockLocation[] bls = fs.getFileBlockLocations(ifile,0,ifile.getLen());
        // 文件存储的block块  存在不同的node上面
    }
}
