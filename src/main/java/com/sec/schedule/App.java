package com.sec.schedule;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.sec.schedule.dao.TaskConfigDao;
import com.sec.schedule.entity.TaskConfig;


/**
 * Hello world!
 *
 */
@SpringBootApplication
@ImportResource(locations = { "classpath:applicationcontext-task.xml" })
// @EnableScheduling
public class App {
    public static ConfigurableApplicationContext ctx;

    public static void main(String[] args) {
        ctx = SpringApplication.run(App.class, args);
    }





    // //-- 缓存的配置信息 --//
    // public static Map<String,String> dict ;
    // @Autowired
    // private TaskConfigDao taskConfigDao;
    // /**
    //  * 初始化  缓存一些 配置信息
    //  */
	// @Override
	// public void run(String... args) throws Exception {
    //     dict = taskConfigDao.findAll()
    //                .stream()
    //                .collect(Collectors.toMap(TaskConfig::getDictKey, TaskConfig::getDictValue));
	// }
}
