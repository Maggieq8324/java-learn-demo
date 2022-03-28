package com.coisini.dynamic.config;

/**
 * @Description 动态邮箱单例
 * @author coisini
 * @date Mar 24, 2022
 * @version 1.0
 */
public class DynamicMailConfig {

    private static String mail;

    private final static DynamicMailConfig dynamic;

    static {
        dynamic = new DynamicMailConfig();
    }

    private DynamicMailConfig() {
        mail = "";
    }

    public static DynamicMailConfig getInstance() {
        return dynamic;
    }

    public String getDynamicMail() {
        return mail;
    }

    public void setDynamicMail(String mail) {
        this.mail = mail;
    }

}
