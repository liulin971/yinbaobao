package com.liulin.soft.yinbaobao.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * 自定义系统检查
 */
@Component
public class MyAppHealthlndicator implements HealthIndicator {
    @Override
    public Health getHealth(boolean includeDetails) {
        return null;
    }

    @Override
    public Health health() {
        //自定义检查方法
        //Health.up().build() 代表健康
        return Health.down().withDetail("msg","服务异常信息").build();
    }
}
