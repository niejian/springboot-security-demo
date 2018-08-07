package cn.com.demo.controller;/**
 * Created by niejian on 2018/7/25.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author niejian
 * @date 2018/7/25
 */

@RestController

public class IndexController {
    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @GetMapping("/index")
    @PreAuthorize("hasRole('admin')")
    public Map<String, Object> index() {
        Map<String, Object> map = new HashMap<>(2);

        map.put("success", true);
        map.put("time", System.currentTimeMillis());
        return map;
    }
}
