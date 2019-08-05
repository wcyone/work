package com.sccdrs.work.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author wcy
 * @date 2019/7/30 17:30
 * @Description:
 */
@Component
public class EsConfig {
    @Bean
    public TransportClient client() throws UnknownHostException{
       InetSocketTransportAddress node = new InetSocketTransportAddress(
                InetAddress.getByName("129.28.66.150"),9300
        );
        Settings settings = Settings.builder()
                .put("cluster.name","my_app").build();
        TransportClient client = new PreBuiltTransportClient(settings);
        client.addTransportAddress(node);
        return client;
    }

}
