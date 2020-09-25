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




    /**
     *  配置druid的监控
     *  1.配置管理后台的servlet
     *  访问网址: http://127.0.0.1:8080/druid
     * @return
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");////指定拦截器只拦截druid管理页面的请求
        Map<String,String> mpa = new HashMap<>();
        mpa.put("loginUsername","admin");
        mpa.put("loginPassword","123456");
        mpa.put("allow", "");         //ip白名单，如果没有设置或为空，则表示允许所有访问
//        mpa.put("deny","");//
        mpa.put("resetEnable", "true");       //是否允许重置druid的统计信息
        bean.setInitParameters(mpa);
        return bean;
    }

    //2.配置一个监控的filter
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean bean =new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        Map<String ,String> map = new HashMap<>();
        map.put("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        //bean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        bean.setInitParameters(map);
        bean.setUrlPatterns(Arrays.asList("/*"));

        logger.debug("测试启动");

        return bean;

    }
}
