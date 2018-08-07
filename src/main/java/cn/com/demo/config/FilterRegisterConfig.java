package cn.com.demo.config;/**
 * Created by niejian on 2018/7/26.
 */

import cn.com.demo.filter.MyUsernamePasswordAuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author niejian
 * @date 2018/7/26
 */

@Configuration
public class FilterRegisterConfig {
//    @Bean
//    public FilterRegistrationBean filterRegistrationBean() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        filterRegistrationBean.setFilter(new MyUsernamePasswordAuthenticationFilter());
//        filterRegistrationBean.setOrder(1);
//        filterRegistrationBean.addUrlPatterns("/login");
//        filterRegistrationBean.setName("spring-security拦截login过滤器");
//        return filterRegistrationBean;
//    }
}
