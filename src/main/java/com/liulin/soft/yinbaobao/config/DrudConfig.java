package com.liulin.soft.yinbaobao.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DrudConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource(){
        return new DruidDataSource();
    }

    Logger logger = LoggerFactory.getLogger(getClass());


    //配置druid的监控
    //1.配置管理后台的servlet
    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        Map<String,String> mpa = new HashMap<>();
        mpa.put("loginUsername","admin");
        mpa.put("loginPassword","123456");
        mpa.put("allow","");//默认允许所有
//        mpa.put("deny","");//
        bean.setInitParameters(mpa);
        return bean;
    }

    //2.配置一个监控的filter
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean bean =new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        Map<String ,String> map = new HashMap<>();
        map.put("exclusions","*.js,*.css,/druid/*");
        bean.setInitParameters(map);
        bean.setUrlPatterns(Arrays.asList("/*"));

        logger.debug("测试启动");

        return bean;

    }
}
