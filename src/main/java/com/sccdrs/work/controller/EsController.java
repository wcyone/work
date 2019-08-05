package com.sccdrs.work.controller;

import com.sccdrs.work.config.EsConfig;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author wcy
 * @date 2019/7/31 15:15
 * @Description: elasticsearch
 */
@RestController
@RequestMapping("/es")
public class EsController {

    @Autowired
    TransportClient client;

    /**
     * 根据id查询具体信息
     * @param id
     * @return
     */
    @RequestMapping("/get/book/novel/{id}")
    public ResponseEntity get(@PathVariable String id){
        if(id.isEmpty()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        GetResponse result = this.client.prepareGet("book","novel",id).get();
        if(!result.isExists()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(result.getSource(),HttpStatus.OK);
    }


}
