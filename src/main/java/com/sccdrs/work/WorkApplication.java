package com.sccdrs.work;

import com.sccdrs.work.kafka.KafkaSender;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@MapperScan("com.sccdrs.work.mapper")
@SpringBootApplication
public class WorkApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(WorkApplication.class, args);
        KafkaSender sender = context.getBean(KafkaSender.class);

        for (int i = 0; i < 300; i++) {
            //调用消息发送类中的消息发送方法
            sender.send();

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
