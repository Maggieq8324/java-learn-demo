package com.coisini.dynamic.config;

import com.coisini.dynamic.util.YmlUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Description spring容器初始化完成后进行一些其他初始化操作
 * @date Mar 24, 2022
 * @version 1.0
 */
@Slf4j
@Component
public class InitializationConfig implements ApplicationRunner {

    private static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
    private static String profile;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        scheduleUpdateConf();
    }

    private void scheduleUpdateConf() {
        try {
            Map lhm = YmlUtil.loadYaml("application.yml");
            profile = (String) YmlUtil.getValByKey(lhm, "spring.profiles.active");
        } catch (Exception e) {
            log.error("加载配置文件application.yml异常");
        }

        // TODO 开启定时刷新内存中配置文件内容
        log.info("refresh config file start");
        executorService.scheduleAtFixedRate(InitializationConfig::updateConfVal, 0, 10, TimeUnit.SECONDS);
        log.info("refresh config file end");
    }

    /**
     * 更新配置文件值
     */
    private static void updateConfVal(){
        try{
            Map lhm = YmlUtil.loadYaml("application-" + profile + ".yml");

            String mail = YmlUtil.getValByKey(lhm,"coisini.mail").toString();
            DynamicMailConfig instance = DynamicMailConfig.getInstance();
            if (!instance.getDynamicMail().equals(mail)) {
                instance.setDynamicMail(mail);
                log.info("实时配置mail更新：" + instance.getDynamicMail());
            }
        } catch (Exception e){
            log.error("更新配置文件值异常: ", e);
        }
    }

}
