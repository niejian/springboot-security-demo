package cn.com.demo;/**
 * Created by niejian on 2018/7/25.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * @author niejian
 * @date 2018/7/25
 */
@SpringBootApplication
@PropertySource({
        "classpath:/jwt.properties"
})
public class ApplicationBootstrap {
    public static void main(String[] args) {
        try {
            SpringApplication.run(ApplicationBootstrap.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
